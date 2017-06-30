/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.tempest.spring.boot.proj.controll;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SimpleController {
    
    @Value("${spring.application.name}")
    String appName;
    
    @RequestMapping("/")
    public String getDefaultHomePage(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }
 
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHomePage(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }
    
    /*
    @RequestMapping(value = "/another", method = RequestMethod.GET)
    public String getAnotherPage(Model model) {
        return "another";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage(Model model) {
        return "login";
    }
    */
    
}
