package com.example.demo.controller.contextreq;

import com.example.demo.controller.utils.DummyUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/xss/mvc/holderreq-cross")
    public ModelAndView unstandardRequestHandlerHolder2() {
        ModelAndView mav = new ModelAndView("welcome");
        if (request == null) {
            mav.addObject("name", "Cotton Eye Joe");
            return mav;
        }
        mav.addObject("name", DummyUtil.getParameter("name"));
        return mav;
    }

    @RequestMapping("/xss/mvc/holderreq-cross-2")
    public String unstandardRequestHandlerHolder3() {
        // this is a hard case, we need to check the request object
        // and the DummyUtil.getParameter() method
        HttpServletRequest request = DummyUtil.getRequest();
        if (request == null) {
            return "welcome";
        }
        request.setAttribute("name", request.getParameter("name"));
        return "welcome";
    }
}
