package com.spring.mediacompaign.controllers;

import com.spring.mediacompaign.config.WebConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PagesController {

    @RequestMapping({"/home", "/"})
    public String dashboard(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> map = new HashMap();
        response.setStatus(200);
        return render(map, "dashboard.ftl");
    }

    @RequestMapping("/campaign/create")
    public String createCampaign(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> map = new HashMap();
        response.setStatus(200);
        return render(map, "create_campaign.ftl");
    }

    @RequestMapping("/social/create")
    public String createSocial(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> map = new HashMap();
        response.setStatus(200);
        return render(map, "create_social.ftl");
    }

    private String render(Map map, String filename) {
        // write the freemarker output to a StringWriter
        StringWriter stringWriter = new StringWriter();
        try {
            Configuration cfg = WebConfig.getConfiguration();
            //Create Data Model
            Template template = cfg.getTemplate(filename);
            template.process(map, stringWriter);
            map.clear();
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
        return stringWriter.toString();
    }
}
