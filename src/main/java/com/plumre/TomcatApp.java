package com.plumre;

/*
 * Created by renhongjiang on 2019/4/1.
 */

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * TODO
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/4/1 11:09
 */
public class TomcatApp extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
       return builder.sources(App.class);
    }
}