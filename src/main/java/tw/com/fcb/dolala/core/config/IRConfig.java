package tw.com.fcb.dolala.core.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Copyright (C),2022,FirstBank
 * FileName: IRConfig
 * Author: Han-Ru
 * Date: 2022/4/7 下午 02:32
 * Description: IRConfig
 */
@Configuration
@Getter
@Setter
@PropertySource("classpath:application.properties")
public class IRConfig {


    @Value("${fcb.core.ir.env-type}")
    String envType;



}
