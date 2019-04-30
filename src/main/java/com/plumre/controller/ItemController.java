package com.plumre.controller;

/*
 * Created by renhongjiang on 2019/3/26.
 */

import com.plumre.common.BaseController;
import com.plumre.common.BusinessException;
import com.plumre.common.CommonReturnType;
import com.plumre.controller.viewobject.ItemVO;
import com.plumre.service.ItemService;
import com.plumre.service.model.ItemModel;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/3/26 11:26
 */
@Controller
@RequestMapping("/item")
@CrossOrigin(allowedHeaders = "{*}", allowCredentials = "true")
public class ItemController extends BaseController {
    @Autowired
    private ItemService itemService;

    @ResponseBody
    @RequestMapping(value = "/createItem", method = RequestMethod.POST, consumes = {CONTENT_TYPE_FORMED})
    public CommonReturnType createItem(@RequestParam("title") String title,
                                       @RequestParam("price") BigDecimal price,
                                       @RequestParam("stock") Integer stock,
                                       @RequestParam("description") String description,
                                       @RequestParam("imgUrl") String imgUrl) throws BusinessException {

        ItemModel itemModel = new ItemModel();
        itemModel.setTitle(title);
        itemModel.setPrice(price);
        itemModel.setStock(stock);
        itemModel.setDescription(description);
        itemModel.setImgUrl(imgUrl);
        ItemModel item = itemService.createItem(itemModel);
        ItemVO itemVO = convertFromModel(item);
        return CommonReturnType.create(itemVO);
    }


    @ResponseBody
    @RequestMapping(value = "/getItem", method = RequestMethod.GET)
    public CommonReturnType getItem(@RequestParam("id") Integer id) {
        ItemModel itemModel = itemService.getItemById(id);
        ItemVO itemVO = convertFromModel(itemModel);
        return CommonReturnType.create(itemVO);
    }

    @ResponseBody
    @RequestMapping(value = "/listItems", method = RequestMethod.GET)
    public CommonReturnType listItems() {
        List<ItemModel> modelList = itemService.listItems();
        List<ItemVO> itemVOList = modelList.stream().map(model -> {
            return convertFromModel(model);
        }).collect(Collectors.toList());
        return CommonReturnType.create(itemVOList);
    }

    private ItemVO convertFromModel(ItemModel itemModel) {
        if (itemModel == null) {
            return null;
        }
        ItemVO itemVO = new ItemVO();
        BeanUtils.copyProperties(itemModel, itemVO);
        if (itemModel.getPromoModel() != null) {
            itemVO.setPromoId(itemModel.getPromoModel().getId());
            itemVO.setPromoPrice(itemModel.getPromoModel().getPromoItemPrice());
            itemVO.setPromoStatus(itemModel.getPromoModel().getStatus());
            itemVO.setStartTime(itemModel.getPromoModel().getStartTime().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
            itemVO.setEndTime(itemModel.getPromoModel().getEndTime().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
        } else {
            itemVO.setPromoStatus(0);
        }
        return itemVO;
    }

}