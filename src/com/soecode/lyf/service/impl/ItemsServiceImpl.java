package com.soecode.lyf.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.soecode.lyf.dao.ItemsDao;
import com.soecode.lyf.dao.ItemsDaoCustom;
import com.soecode.lyf.dto.ItemsCustomVo;
import com.soecode.lyf.entity.Items;
import com.soecode.lyf.entity.ItemsCustom;
import com.soecode.lyf.exception.CustomException;
import com.soecode.lyf.service.ItemsService;

public class ItemsServiceImpl implements ItemsService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	// 注入Service依赖
	@Autowired
	private ItemsDaoCustom itemsDaoCustom;

	@Autowired
	private ItemsDao itemsDao;

	@Override
	public List<ItemsCustom> findItemsList(ItemsCustomVo itemsCustomVo) {
		// TODO Auto-generated method stub
		return itemsDaoCustom.findItemsList(itemsCustomVo);

	}

	@Override
	public ItemsCustom findItemsById(Integer id) throws CustomException {
		Items items= itemsDao.selectByPrimaryKey(id);
		//中间对商品信息进行业务处理
		if(items==null) {
			throw new CustomException("修改的商品信息不存在");
		}
		
		//将items的属性值拷贝到itemsCustom
		ItemsCustom itemsCustom=null;
		if(items!=null) {
			itemsCustom=new ItemsCustom();
			BeanUtils.copyProperties(items, itemsCustom);
		}
		
		
		 return itemsCustom;
	}

	@Override
	public void updateItems(Integer id, ItemsCustom itemsCustom) {
		// 添加业务校验  通常在service接口对关键参数进行校验
		//校验id是否为空
		
		//更新商品信息   该方法可更新items表中的所有字段，包括文本
		itemsCustom.setId(id);
		itemsDao.updateByPrimaryKeyWithBLOBs(itemsCustom);

	}

}
