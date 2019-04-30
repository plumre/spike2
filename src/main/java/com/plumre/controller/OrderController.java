package com.plumre.controller;

/*
 * Created by renhongjiang on 2019/3/26.
 */

import com.plumre.common.BaseController;
import com.plumre.common.BusinessErrorEnum;
import com.plumre.common.BusinessException;
import com.plumre.common.CommonReturnType;
import com.plumre.controller.viewobject.OrderVO;
import com.plumre.service.OrderService;
import com.plumre.service.model.OrderModel;
import com.plumre.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * TODO
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/3/26 18:10
 */
@Controller
@RequestMapping("/order")
@CrossOrigin(allowedHeaders = "{*}", allowCredentials = "true")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping(value = "/createOrder", method = RequestMethod.POST, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType createOrder(@RequestParam("itemId") Integer itemId,
                                        @RequestParam("quantity") Integer quantity,
                                        @RequestParam(value = "promoId", required = false) Integer promoId) throws BusinessException {
        Boolean isLogin = (Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");
        if (isLogin == null || !isLogin) {
            throw new BusinessException(BusinessErrorEnum.USER_NO_LOGIN);
        }
        UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute("LOGIN_USER");

        OrderModel orderModel = orderService.createOrder(userModel.getId(), itemId, quantity, promoId);
        OrderVO orderVO = convertFromModel(orderModel);
        return CommonReturnType.create(orderVO);
    }

    private OrderVO convertFromModel(OrderModel orderModel) {
        if (orderModel == null) {
            return null;
        }
        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(orderModel, orderVO);
        return orderVO;
    }


}