package com.example.demo.controller.contextreq;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContextRequestController {
    @GetMapping("/xss/mvc/holderreq")
    public ModelAndView unstandardRequestHandler() {
        ModelAndView mav = new ModelAndView("welcome");
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (sra == null) {
            mav.addObject("name", "Cotton Eye Joe");
            return mav;
        }
        HttpServletRequest request = sra.getRequest();
        mav.addObject("name", request.getParameter("name"));
        return mav;
    }

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/xss/mvc/contextreq")
    public ModelAndView unstandardRequestHandler2() {
        ModelAndView mav = new ModelAndView("welcome");
        if (request == null) {
            mav.addObject("name", "Cotton Eye Joe");
            return mav;
        }
        mav.addObject("name", request.getParameter("name"));
        return mav;
    }
}
