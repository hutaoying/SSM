package com.soecode.lyf.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.soecode.lyf.dto.ItemsCustomVo;
import com.soecode.lyf.entity.Book;
import com.soecode.lyf.entity.Items;
import com.soecode.lyf.entity.ItemsCustom;
import com.soecode.lyf.exception.CustomException;
import com.soecode.lyf.service.ItemsService;
import com.soecode.lyf.validation.ValidGroup1;


@Controller
@RequestMapping("/items")//窄化请求映射
public class ItemsController{
	
	
	@Autowired
	private ItemsService itemsService;
	@ModelAttribute("itemtypes")
	public Map<String,String> getItemTypes(){
		Map<String,String> itemTypes=new HashMap<String,String>();
		itemTypes.put("101", "数码");
		itemTypes.put("102", "母婴");
		
		return itemTypes;
	}
	@RequestMapping("/itemsView/{id}")
	public @ResponseBody ItemsCustom itemsView(@PathVariable("id") Integer id)throws Exception{
		ItemsCustom itemsCustom=itemsService.findItemsById(id);
		return itemsCustom;
		
	}
	@RequestMapping("/queryItems")
	public ModelAndView queryItems(HttpServletRequest request,ItemsCustomVo itemsCustomVo ) {
		
		// System.out.println(request.getParameter("id"));
		
		
		List<ItemsCustom> itemsList=itemsService.findItemsList(itemsCustomVo);
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("itemsList",itemsList);
		modelAndView.setViewName("itemsList");// WEB-INF/jsp/"itemsList".jsp
		
		return modelAndView;
	}
	//批量修改页面查询
	@RequestMapping("/editItemsQuery")
	public ModelAndView editItemsQuery(HttpServletRequest request,ItemsCustomVo itemsCustomVo ) {
		
		// System.out.println(request.getParameter("id"));
		
		
		List<ItemsCustom> itemsList=itemsService.findItemsList(itemsCustomVo);
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("itemsList",itemsList);
		modelAndView.setViewName("editItemsList");// WEB-INF/jsp/"itemsList".jsp
		
		return modelAndView;
	}
	//商品修改页面显示
	@RequestMapping(value="/editItems",method= {RequestMethod.POST,RequestMethod.GET})
	public String editItems(Model model,@RequestParam(value="id")Integer items_id
			
			) throws Exception {
		//调用service
		//业务无关  在controller中抛出异常
		
		ItemsCustom itemsCustom=itemsService.findItemsById(items_id);
		/*if(itemsCustom==null)
		{
			throw new CustomException("修改的商品信息不存在");
		}*/
		model.addAttribute("items", itemsCustom);
	//	ModelAndView modelAndView=new ModelAndView();
		//modelAndView.addObject("itemsCustom",itemsCustom);
	//	modelAndView.setViewName("editUI");// WEB-INF/jsp/"editUI".jsp
		
	//	return modelAndView;
		return "editUI";
	}
	
	//商品信息修改提交
	//@ModelAttribute("items")制定pojo回显到页面在request中的key
	@RequestMapping(value="/editSubmit",method= {RequestMethod.POST,RequestMethod.GET})
	public String updateItems(Model model,Integer id,
			@ModelAttribute("items")@Validated(value= {ValidGroup1.class}) ItemsCustom itemsCustom,BindingResult bindingResult,
			MultipartFile items_pic//接收商品图片
			) throws IOException {
		//获取错误信息
				if(bindingResult.hasErrors()) {
					List<ObjectError> allErrors=bindingResult.getAllErrors();
					for(ObjectError objectError:allErrors) {
						System.out.println(objectError.getDefaultMessage());
					}
					//出错跳转到商品修改页面
					model.addAttribute("allErrors", allErrors);
					//可以直接使用model将pojo回显到页面
					model.addAttribute("items", itemsCustom);
					return "editUI";
				}
				
		//上传图片
				//原始名称
				String originalFilename=items_pic.getOriginalFilename();
				if(items_pic!=null && originalFilename!=null && originalFilename.length()>0) {
					//物理路径
					String pic_path="D:\\kafaku\\pic\\";
					
					//新的图片名称
					String newfilename=UUID.randomUUID()+originalFilename.substring(originalFilename.lastIndexOf("."));
					//新图片
					File newFile=new File(pic_path+newfilename);
					//将内存中的数据写入磁盘
					items_pic.transferTo(newFile);
					itemsCustom.setPic(newfilename);
				}
		//	System.out.println(request.getParameter("name")+"------------");
		//调用service更新商品信息
				
		itemsService.updateItems(id, itemsCustom);
		//ModelAndView modelAndView=new ModelAndView();
		//modelAndView.addObject("itemsCustom",itemsCustom);
		//modelAndView.setViewName("list");// WEB-INF/jsp/"itemsList".jsp
		//return "list";
		//return "redirect:queryItems.action";
		//return "forward:queryItems.action";
		return "list";
	}
	//批量删除商品信息
	@RequestMapping("/deleteItems")
	public String deleteItems(Integer[] items_id) {
		
		
		return "list";
		
	}
	 //批量修改商品
	//在需要校验的pojo前边添加Validated，在需要校验的后面添加BindingResult bindingResult接收校验出错信息  配对出现
	//顺序固定
	@RequestMapping("/editItemsAllSubmit")
	public String editItemsAllSubmit(@Validated ItemsCustomVo itemsCustomVo,BindingResult bindingResult) {
		//获取错误信息
		if(bindingResult.hasErrors()) {
			List<ObjectError> allErrors=bindingResult.getAllErrors();
			for(ObjectError objectError:allErrors) {
				System.out.println(objectError.getDefaultMessage());
			}
		}
		
		//itemsCustomList
		
		
		return "list";
		
	}
	

	
}
