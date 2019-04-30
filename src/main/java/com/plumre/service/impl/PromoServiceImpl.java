package com.plumre.service.impl;

/*
 * Created by renhongjiang on 2019/3/27.
 */

import com.plumre.dao.PromoDOMapper;
import com.plumre.dataobject.PromoDO;
import com.plumre.service.PromoService;
import com.plumre.service.model.PromoModel;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/3/27 10:29
 */
@Service
public class PromoServiceImpl implements PromoService {

    @Autowired
    private PromoDOMapper promoDOMapper;

    @Override
    public PromoModel getPromoByItemId(Integer itemId) {
        PromoDO promoDO = promoDOMapper.selectByItemId(itemId);
        PromoModel model = convertFromDataObject(promoDO);
        if (model == null) {
            return null;
        }
        if (model.getStartTime().isAfterNow()) {
            model.setStatus(1);
        } else if (model.getEndTime().isBeforeNow()) {
            model.setStatus(3);
        } else {
            model.setStatus(2);
        }
        return model;

    }

    private PromoModel convertFromDataObject(PromoDO promoDO) {
        if (promoDO == null) {
            return null;
        }
        PromoModel model = new PromoModel();
        BeanUtils.copyProperties(promoDO, model);
        model.setStartTime(new DateTime(promoDO.getStartTime()));
        model.setEndTime(new DateTime(promoDO.getEndTime()));
        return model;
    }
}