package com.example.demo.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public abstract class GlobalErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, HttpServletResponse response, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            model.addAttribute("message", status.toString());
        }
        return "error";
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ModelAndView maxUploadSizeExceeded(MaxUploadSizeExceededException e) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.getModel().put("message", e.getMessage().split(";")[0]);
        log.error(e.getMessage());
        return modelAndView;
    }

}
