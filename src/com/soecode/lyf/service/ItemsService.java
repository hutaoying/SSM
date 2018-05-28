package com.soecode.lyf.service;

import java.util.List;

import com.soecode.lyf.dto.ItemsCustomVo;
import com.soecode.lyf.entity.Items;
import com.soecode.lyf.entity.ItemsCustom;



public interface ItemsService {
	//商品查询列表
	public List<ItemsCustom> findItemsList(ItemsCustomVo itemsCustomVo);
//根据id查询商品
	public ItemsCustom findItemsById(Integer id)throws Exception;
	//更新商品信息
	public void updateItems(Integer id,ItemsCustom itemsCustom);
	
	
}
