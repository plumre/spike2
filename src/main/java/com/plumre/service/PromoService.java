package com.plumre.service;

/*
 * Created by renhongjiang on 2019/3/27.
 */

import com.plumre.service.model.PromoModel;

/**
 * TODO
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/3/27 10:28
 */
public interface PromoService {

    PromoModel getPromoByItemId(Integer itemId);

}