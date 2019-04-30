package com.plumre.service;

/*
 * Created by renhongjiang on 2019/3/26.
 */

import com.plumre.common.BusinessException;
import com.plumre.service.model.OrderModel;

/**
 * TODO
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/3/26 16:58
 */
public interface OrderService {

    OrderModel createOrder(Integer userId, Integer itemId, Integer quantity, Integer promoId) throws BusinessException;

}