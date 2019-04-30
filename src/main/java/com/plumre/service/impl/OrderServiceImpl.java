package com.plumre.service.impl;

/*
 * Created by renhongjiang on 2019/3/26.
 */

import com.plumre.common.BusinessErrorEnum;
import com.plumre.common.BusinessException;
import com.plumre.dao.ItemDOMapper;
import com.plumre.dao.OrderDOMapper;
import com.plumre.dao.SequenceDOMapper;
import com.plumre.dataobject.OrderDO;
import com.plumre.dataobject.SequenceDO;
import com.plumre.service.ItemService;
import com.plumre.service.OrderService;
import com.plumre.service.UserService;
import com.plumre.service.model.ItemModel;
import com.plumre.service.model.OrderModel;
import com.plumre.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * TODO
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/3/26 16:59
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDOMapper orderDOMapper;
    @Autowired
    private SequenceDOMapper sequenceDOMapper;
    @Autowired
    private ItemService itemService;
    @Autowired
    private UserService userService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderModel createOrder(Integer userId, Integer itemId, Integer quantity, Integer promoId) throws BusinessException {
        // validate  user? item? quantity?
        ItemModel itemModel = itemService.getItemById(itemId);
        if (itemModel == null) {
            throw new BusinessException(BusinessErrorEnum.PARAMETER_INVALIDATION, "商品信息不存在");
        }
        UserModel userModel = userService.getUserById(userId);
        if (userModel == null) {
            throw new BusinessException(BusinessErrorEnum.PARAMETER_INVALIDATION, "用户不存在");
        }
        if (quantity < 0 || quantity > 99) {
            throw new BusinessException(BusinessErrorEnum.PARAMETER_INVALIDATION, "不支持购买的数量");
        }

        // validate promotion
        if (promoId != null) {
            if (promoId.intValue() != itemModel.getPromoModel().getId()) {
                throw new BusinessException(BusinessErrorEnum.PARAMETER_INVALIDATION, "活动信息不存在");
            }
            if (itemModel.getPromoModel().getStatus() != 2) {
                throw new BusinessException(BusinessErrorEnum.PARAMETER_INVALIDATION, "活动信息未开始");
            }
        }

        // reduce stock after ordered 落单减库存
        boolean b = itemService.decreaseStock(itemId, quantity);
        if (!b) {
            throw new BusinessException(BusinessErrorEnum.STOCK_NOT_ENOUGH);
        }
        // durable order info to database
        OrderModel orderModel = new OrderModel();
        orderModel.setItemId(itemId);
        if (promoId != null) {
            orderModel.setItemPrice(itemModel.getPromoModel().getPromoItemPrice());
        } else {
            orderModel.setItemPrice(itemModel.getPrice());
        }
        orderModel.setQuantity(quantity);
        orderModel.setUserId(userId);
        orderModel.setOrderAmount(orderModel.getItemPrice().multiply(new BigDecimal(quantity)));
        orderModel.setPromoId(promoId);
        /**
         * generate order number
         */
        orderModel.setId(generateOrderNo(userId));
        OrderDO orderDO = convertFromModel(orderModel);
        orderDOMapper.insertSelective(orderDO);
        // update item sales
        itemService.increaseSales(itemId, quantity);
        return orderModel;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public String generateOrderNo(Integer userId) {
        // orderNo 16 digits
        StringBuilder builder = new StringBuilder();
        // the first 8 : time yyyyMMdd
        LocalDateTime now = LocalDateTime.now();
        String dateStr = now.format(DateTimeFormatter.ISO_DATE).replace("-", "");
        builder.append(dateStr);
        // the middle 6 : natural increment
        SequenceDO sequenceDO = sequenceDOMapper.getSequenceByName("order_info");
        int sequence = sequenceDO.getCurrentValue();
        updateSequence(sequenceDO);
        // make middle number to meet 6 digits, zerofill
        fillZero(builder, sequence, 6);
        builder.append(sequence);

        // the last 2 : for split database / split table
        // for example, 99 tables
        int lastTwo = userId % 100;
        fillZero(builder, lastTwo, 2);
        builder.append(lastTwo);
        return builder.toString();
    }

    private void fillZero(StringBuilder builder, int location, int digits) {
        for (int i = 0; i < digits - String.valueOf(location).length(); i++) {
            builder.append(0);
        }
    }

    private void updateSequence(SequenceDO sequenceDO) {
        sequenceDO.setCurrentValue(sequenceDO.getCurrentValue() + sequenceDO.getStep());
        sequenceDOMapper.updateByPrimaryKeySelective(sequenceDO);
    }

    private OrderDO convertFromModel(OrderModel orderModel) {
        if (orderModel == null) {
            return null;
        }
        OrderDO orderDO = new OrderDO();
        BeanUtils.copyProperties(orderModel, orderDO);
        return orderDO;
    }
}