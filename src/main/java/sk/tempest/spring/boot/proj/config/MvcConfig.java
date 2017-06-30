/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.tempest.spring.boot.proj.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/another").setViewName("another");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/hellouser").setViewName("hellouser");
        registry.addViewController("/contact").setViewName("contact");
        registry.addViewController("/books").setViewName("books");
        registry.addViewController("/error").setViewName("error");
    }

}
