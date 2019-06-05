package com.distance.service.core.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.distance.service.common.constants.Constant;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.Charset;

/**
 * 配置response json序列化转换器
 */
@Configuration
public class HttpConvertersConfig {
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        //1.需要定义一个Convert转换消息的对象
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();

        //2.添加fastjson的配置信息，比如是否要格式化返回的json数据
        FastJsonConfig config = new FastJsonConfig();
        //SerializerFeature.WriteMapNullValue序列化null的字段
        config.setSerializerFeatures(SerializerFeature.PrettyFormat);
        config.setCharset(Charset.forName(Constant.CHATSET));

        //3.在convert中添加配置信息
        converter.setFastJsonConfig(config);
        return new HttpMessageConverters(converter);
    }
}
