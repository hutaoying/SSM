package com.soecode.lyf.web.conversion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

//日期转换
public class ConversionService implements Converter<String, Date>{

	@Override
	public Date convert(String dateStr) {
		
		if(dateStr!=null&& dateStr!="") {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
			try {
				return dateFormat.parse(dateStr);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}		
		return null;
		
	}

}
