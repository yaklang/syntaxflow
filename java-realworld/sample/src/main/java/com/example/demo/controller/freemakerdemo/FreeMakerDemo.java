package com.example.demo.controller.freemakerdemo;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/freemarker")
public class FreeMakerDemo {
    @Autowired
    private Configuration freemarkerConfig;

    @GetMapping("/template")
    public void template(String name, Model model, HttpServletResponse response) throws Exception {
        Template tpl = freemarkerConfig.getTemplate("no-return-template.ftl");
        model.addAttribute("name", name);
        String templateText = FreeMarkerTemplateUtils.processTemplateIntoString(tpl, model);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(templateText);
        writer.flush();
        writer.close();
    }

    @GetMapping("/welcome")
    public String welcome(@RequestParam String name, Model model) {
        if (name == null || name.isEmpty()) {
            model.addAttribute("name", "Welcome to Safe FreeMarker Demo, try <code>/freemarker/safe/welcome?name=Hacker<>`");
        } else {
            model.addAttribute("name", name);
        }
        return "welcome";
    }

    @GetMapping("/welcome-safe")
    public String safeWelcome(@RequestParam String name, Model model) {
        if (name == null || name.isEmpty()) {
            model.addAttribute("name", "Welcome to Safe FreeMarker Demo, try <code>/freemarker/safe/welcome-safe?name=Hacker<>`");
            return "welcome";
        } else {
            model.addAttribute("name", name);
        }
        return "welcome-safe";
    }

    @GetMapping("/welcome-no-model")
    public String welcomeNoModel() {
        return "welcome";
    }

    @GetMapping("/welcome-no-ftl")
    public String welcomeNoFTL() {
        return "welcome-no-existed-totally";
    }

    @ModelAttribute("defaultName")
    public String getDefaultName() {
        return "default name for FreeMarker Demo";
    }

    @GetMapping("/welcome-default-model")
    public String welcomeDefaultModel() {
        return "welcome2";
    }


}
