package com.soecode.lyf.dao;

import java.util.List;

import com.soecode.lyf.dto.ItemsCustomVo;
import com.soecode.lyf.entity.ItemsCustom;




public interface ItemsDaoCustom {
   
	public List<ItemsCustom> findItemsList(ItemsCustomVo itemsCustomVo);
}