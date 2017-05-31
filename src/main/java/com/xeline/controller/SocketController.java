package com.xeline.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.socket.TextMessage;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * @desp Socket控制器
 * @author xenron
 * @date 2016-5-6
 *
 */
@Controller
public class SocketController{
    
    private static final Logger logger = LoggerFactory.getLogger(SocketController.class);

    @RequestMapping(value="/login")
    public String login(HttpSession session){
        logger.info("create new connection by user");
        
        session.setAttribute("user", "xenron");
        
        return "home";
    }

    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public String sendMessage() throws UnirestException{
        
    	HttpResponse<String> httpResponse = Unirest.get("http://localhost:18080/mes-web-service/customers/").asString();
        String json = httpResponse.getBody();
        System.out.println(json);
        
        return "message";
    }

}
