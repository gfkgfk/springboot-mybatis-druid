package com.test.springboot.controller.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {
    public static final Logger logger = LoggerFactory.getLogger(LogController.class);

    @RequestMapping(value = "/log", method = RequestMethod.POST)
    public int getLog() {
        logger.debug("log显示");
        return 1;
    }
}
