package com.spring.mediacompaign.controllers;

import com.spring.mediacompaign.config.WebConfig;
import com.spring.mediacompaign.dao.models.AdminModel;
import com.spring.mediacompaign.dao.models.SocialPlatformModel;
import com.spring.mediacompaign.services.api.AdminService;
import com.spring.mediacompaign.services.api.CampaignService;
import com.spring.mediacompaign.services.api.SocialPlatformService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PagesController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private SocialPlatformService socialPlatformService;

    @Autowired
    private CampaignService campaignService;

    @RequestMapping({"/", "/home"})
    public String dashboard(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> map = new HashMap();
        map.put("socialPlatforms", socialPlatformService.list());
        return returnPage("dashboard.ftl", map, request, response);
    }

    @RequestMapping("/campaign/create")
    public String createCampaign(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> map = new HashMap();
        return returnPage("create_campaign.ftl", map, request, response);
    }

    @RequestMapping("/platform/create")
    public String createSocialPlatform(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> map = new HashMap();
        return returnPage("create_social.ftl", map, request, response);
    }

    @RequestMapping("/platform/edit/{id}")
    public String editSocialPlatform(@PathVariable(name = "id") String id, HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> map = new HashMap();
        final SocialPlatformModel socialPlatform = socialPlatformService.getById(id);
        map.put("socialPlatform", socialPlatform);
        return returnPage("update_social.ftl", map, request, response);
    }

    @RequestMapping("/platform/delete/{id}")
    public void deleteSocialPlatform(@PathVariable(name = "id") String id, HttpServletRequest request, HttpServletResponse response) {
        socialPlatformService.delete(id);
        response.setHeader("Location", "/home");
        response.setStatus(302);
    }

    @RequestMapping("/platform/update")
    public void updateSocialPlatform(HttpServletRequest request, HttpServletResponse response) {
        final String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            SocialPlatformModel socialPlatformModel = saveDataToPlatform(socialPlatformService.getById(id), request);

            socialPlatformService.update(socialPlatformModel);
        }
        response.setHeader("Location", "/home");
        response.setStatus(302);
    }

    @RequestMapping("/platform/save")
    public void saveSocialPlatform(HttpServletRequest request, HttpServletResponse response) {
        SocialPlatformModel socialPlatformModel = saveDataToPlatform(new SocialPlatformModel(), request);

        socialPlatformService.save(socialPlatformModel);
        response.setHeader("Location", "/home");
        response.setStatus(302);
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, String error) {
        final HttpSession oldSession = request.getSession();
        String errorMessage = null;
        if (oldSession.getAttribute("errorMessage") != null) {
            errorMessage = (String) oldSession.getAttribute("errorMessage");
        }
        if (error != null) {
            errorMessage = error;
        }
        oldSession.invalidate();

        HttpSession newSession = request.getSession();
        if (errorMessage != null) {
            newSession.setAttribute("errorMessage", errorMessage);
        }
        return render(new HashMap(), "login.ftl");
    }

    @RequestMapping("/404")
    @ExceptionHandler(Throwable.class)
    public String blank(Map map) {
        return render(map, "blank.ftl");
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

    private String returnPage(String page, HashMap<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            AdminModel user = (AdminModel) session.getAttribute("user");
            user = adminService.getById(user.getId());
            map.put("user", user);
        } else {
            response.setStatus(301);
            return login(request, response, "No Session, Try logging in again");
        }
        response.setStatus(200);
        return render(map, page);
    }

    private SocialPlatformModel saveDataToPlatform(SocialPlatformModel socialPlatformModel, HttpServletRequest request) {
        final String name = request.getParameter("name");
        if (name != null && !name.isEmpty()) {
            socialPlatformModel.setName(name);
        }
        final String access_token = request.getParameter("access_token");
        if (access_token != null && !access_token.isEmpty()) {
            socialPlatformModel.setAccessToken(access_token);
        }
        final String app_id = request.getParameter("app_id");
        if (app_id != null && !app_id.isEmpty()) {
            socialPlatformModel.setAppId(app_id);
        }
        final String app_secret = request.getParameter("app_secret");
        if (app_secret != null && !app_secret.isEmpty()) {
            socialPlatformModel.setAppSecret(app_secret);
        }

        return socialPlatformModel;
    }
}
