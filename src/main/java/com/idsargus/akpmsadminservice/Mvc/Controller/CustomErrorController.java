package com.idsargus.akpmsadminservice.Mvc.Controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        return "Url not matched or 404 not found or resource not found";
    }

    @Override
    public String getErrorPath() {
        return "";
    }
}
