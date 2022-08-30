package com.test.springboot.controller.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebSocketController {
    @RequestMapping(value = "/client")
    public String client() {
        return "client";
    }
}
