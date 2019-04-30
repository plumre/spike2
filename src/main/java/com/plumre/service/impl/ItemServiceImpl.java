package com.plumre.service.impl;

/*
 * Created by renhongjiang on 2019/3/26.
 */

import com.plumre.common.BusinessErrorEnum;
import com.plumre.common.BusinessException;
import com.plumre.dao.ItemDOMapper;
import com.plumre.dao.ItemStockDOMapper;
import com.plumre.dataobject.ItemDO;
import com.plumre.dataobject.ItemStockDO;
import com.plumre.service.ItemService;
import com.plumre.service.PromoService;
import com.plumre.service.model.ItemModel;
import com.plumre.service.model.PromoModel;
import com.plumre.validator.ValidationResult;
import com.plumre.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/3/26 10:50
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ValidatorImpl validator;
    @Autowired
    private ItemDOMapper itemDOMapper;
    @Autowired
    private ItemStockDOMapper itemStockDOMapper;
    @Autowired
    private PromoService promoService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ItemModel createItem(ItemModel itemModel) throws BusinessException {
        // validate parameters
        ValidationResult result = validator.validate(itemModel);
        if (result.isHasErrors()) {
            throw new BusinessException(BusinessErrorEnum.PARAMETER_INVALIDATION, result.getErrorMessage());
        }


        // model --> data object
        ItemDO itemDO = convertFromModel(itemModel);
        ItemStockDO stockDO = convertStockFromModel(itemModel);

        // durable to database
        itemDOMapper.insertSelective(itemDO);

        stockDO.setItemId(itemDO.getId());
        itemStockDOMapper.insertSelective(stockDO);

        // return object finished

        return getItemById(itemDO.getId());
    }

    private ItemStockDO convertStockFromModel(ItemModel itemModel) {
        if (itemModel == null) {
            return null;
        }
        ItemStockDO stockDO = new ItemStockDO();
        stockDO.setStock(itemModel.getStock());
        return stockDO;
    }

    private ItemDO convertFromModel(ItemModel itemModel) {
        if (itemModel == null) {
            return null;
        }
        ItemDO itemDO = new ItemDO();
        BeanUtils.copyProperties(itemModel, itemDO);
        return itemDO;
    }

    @Override
    public List<ItemModel> listItems() {
        List<ItemDO> itemDOList = itemDOMapper.listItems();
        return itemDOList.stream().map(itemDO -> {
            ItemStockDO stockDO = itemStockDOMapper.selectByItemId(itemDO.getId());
            return convertFromDataObject(itemDO, stockDO);
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean decreaseStock(Integer itemId, Integer quantity) {
        int affectedRow = itemStockDOMapper.updateStockAfterOrdered(itemId, quantity);
        return affectedRow > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void increaseSales(Integer itemId, Integer quantity) {
        itemDOMapper.updateSales(itemId, quantity);
    }

    @Override
    public ItemModel getItemById(Integer id) {
        ItemDO itemDO = itemDOMapper.selectByPrimaryKey(id);
        if (itemDO == null) {
            return null;
        }
        ItemStockDO itemStockDO = itemStockDOMapper.selectByItemId(itemDO.getId());
        ItemModel itemModel = convertFromDataObject(itemDO, itemStockDO);
        /**
         * promotion info
         */
        PromoModel promoModel = promoService.getPromoByItemId(itemModel.getId());
        if (promoModel != null && promoModel.getStatus() != 3) {
            itemModel.setPromoModel(promoModel);
        }
        return itemModel;
    }

    private ItemModel convertFromDataObject(ItemDO itemDO, ItemStockDO itemStockDO) {
        ItemModel itemModel = new ItemModel();
        BeanUtils.copyProperties(itemDO, itemModel);
        itemModel.setStock(itemStockDO.getStock());
        return itemModel;
    }
}