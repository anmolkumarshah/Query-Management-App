package com.virtusa.querymanagement.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CustomErrorController implements ErrorController {


  @RequestMapping("/error")
  @ResponseBody
  String error() {
    return "<h1>Error occurred</h1>";
  }

}