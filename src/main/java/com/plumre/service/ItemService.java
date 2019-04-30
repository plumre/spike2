package com.plumre.service;

/*
 * Created by renhongjiang on 2019/3/26.
 */

import com.plumre.common.BusinessException;
import com.plumre.service.model.ItemModel;

import java.util.List;

/**
 * TODO
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/3/26 10:49
 */
public interface ItemService {

    ItemModel createItem(ItemModel itemModel) throws BusinessException;

    List<ItemModel> listItems();

    ItemModel getItemById(Integer id);

    boolean decreaseStock(Integer itemId, Integer quantity);

    void increaseSales(Integer itemId, Integer quantity);

}