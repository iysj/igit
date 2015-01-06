package com.kingsj.admin.common;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;
import org.springframework.stereotype.Component;

/**
 * To solve the type of Date to json converter 
 * @author <a href="mailto:kingsj.yuan@foxmail">Yuan,sj</a>
 * @history
 * <TABLE id="HistoryTable" border="1">
 * 	<TR><TD>时间</TD><TD>描述</TD><TD>作者</TD></TR>
 *	<TR><TD>2015年1月2日</TD><TD>创建初始版本</TD><TD>Yuan,sj</TD></TR>
 * </TABLE>
 */
@Component("customObjectMapper")
public class CustomObjectMapper extends ObjectMapper{
	
	public CustomObjectMapper() {
		CustomSerializerFactory factory = new CustomSerializerFactory();
		factory.addGenericMapping(Date.class, new JsonSerializer<Date>() {

			@Override
			public void serialize(Date value, JsonGenerator jsonGenerator,
					SerializerProvider serializerProvider) throws IOException,
					JsonProcessingException {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				jsonGenerator.writeString(sdf.format(value));
			}
		});
		this.setSerializerFactory(factory);
	}

}

/**
 * Copyright (c) 2014, kingsj.yuan@foxmail.com All rights reserved.
 */