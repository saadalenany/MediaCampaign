package com.spring.mediacompaign.controllers;

import com.spring.mediacompaign.config.WebConfig;
import com.spring.mediacompaign.dao.models.*;
import com.spring.mediacompaign.services.api.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private SourcePageService sourcePageService;

    @Autowired
    private TargetPageService targetPageService;

    @RequestMapping({"/", "/home"})
    public String dashboard(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> map = new HashMap();
        map.put("socialPlatforms", socialPlatformService.list());
        return returnPage("dashboard.ftl", map, request, response);
    }

    @RequestMapping("/campaign/create")
    public String createCampaign(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> map = new HashMap();
        map.put("socialPlatforms", socialPlatformService.list());
        map.put("sourcePages", sourcePageService.list());
        map.put("targetPages", targetPageService.list());
        return returnPage("create_campaign.ftl", map, request, response);
    }

    @RequestMapping("/campaign/save")
    public void saveCampaign(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            AdminModel user = (AdminModel) session.getAttribute("user");
            CampaignModel campaignModel = saveDataToCampaign(new CampaignModel(), request, user.getId());
            campaignService.save(campaignModel);
            response.setHeader("Location", "/home");
            response.setStatus(302);
        } else {
            response.setStatus(301);
            response.setHeader("Location", "/login");
        }
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
        response.setHeader("Location", "/login");
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

    private CampaignModel saveDataToCampaign(CampaignModel campaignModel, HttpServletRequest request, String owner_id) {
        final String social_platform = request.getParameter("social_platform");
        if (social_platform != null && !social_platform.isEmpty()) {
            campaignModel.setSocialPlatformId(social_platform);
        }
        final String name = request.getParameter("name");
        if (name != null && !name.isEmpty()) {
            campaignModel.setName(name);
        }

        String target_id = findExistingTargetPageOrSaveNew(request, owner_id);
        if ( target_id != null) {
            campaignModel.setTargetPageId(target_id);
        }
        String source_id = findExistingSourcePageOrSaveNew(request, owner_id);
        if (source_id != null) {
            campaignModel.setSourcePageId(source_id);
        }

        final String scrap_limitation = request.getParameter("scrap_limitation");
        if (scrap_limitation != null && !scrap_limitation.isEmpty()) {
            campaignModel.setScrapLimitation(Integer.parseInt(scrap_limitation));
        }

        String post_thread = null;
        final String number_of_posts = request.getParameter("number_of_posts");
        if (number_of_posts != null && !number_of_posts.isEmpty()) {
            post_thread = number_of_posts + ",";
        }
        final String per = request.getParameter("per");
        if (per != null && !per.isEmpty()) {
            post_thread += per;
        }
        campaignModel.setPostThread(post_thread);

        final String[] source_with_ornots = request.getParameterValues("source_with_ornot");
        if (source_with_ornots != null && source_with_ornots.length > 0 && !source_with_ornots[0].isEmpty()) {
            campaignModel.setSourceWithOrnot(true);
        }

        final String[] post_types = request.getParameterValues("post_type");
        if (post_types != null && post_types.length > 0) {
            campaignModel.setPostType(String.join(",", post_types));
        }

        final String block_keywords = request.getParameter("block_keywords");
        if (block_keywords != null && !block_keywords.isEmpty()) {
            campaignModel.setBlockKeywords(block_keywords);
        }

        final String[] actives = request.getParameterValues("active");
        if (actives != null && actives.length > 0 && !actives[0].isEmpty()) {
            campaignModel.setActive(true);
        } else {
            campaignModel.setActive(false);
        }
        return campaignModel;
    }

    private String findExistingSourcePageOrSaveNew(HttpServletRequest request, String owner_id) {
        final String source_page_exists = request.getParameter("source_page_exists");
        if (source_page_exists != null && !source_page_exists.isEmpty()) {
            return source_page_exists;
        } else {
            final String source_page_new = request.getParameter("source_page_new");
            if (source_page_new != null && !source_page_new.isEmpty()) {
                SourcePageModel sourcePageModel = new SourcePageModel();
                sourcePageModel.setPageUrl(source_page_new);
                sourcePageModel.setOwnerId(owner_id);
                final String username = request.getParameter("username");
                if (username != null && !username.isEmpty()) {
                    sourcePageModel.setUsername(username);
                }
                final String password = request.getParameter("password");
                if (password != null && !password.isEmpty()) {
                    sourcePageModel.setPassword(password);
                }
                final VersionedModel savedPage = sourcePageService.save(sourcePageModel);
                return savedPage.getId();
            }
        }
        return null;
    }

    private String findExistingTargetPageOrSaveNew(HttpServletRequest request, String owner_id) {
        final String target_page_exists = request.getParameter("target_page_exists");
        if (target_page_exists != null && !target_page_exists.isEmpty()) {
            return target_page_exists;
        } else {
            final String target_page_new = request.getParameter("target_page_new");
            if (target_page_new != null && !target_page_new.isEmpty()) {
                TargetPageModel targetPageModel = new TargetPageModel();
                targetPageModel.setPageUrl(target_page_new);
                targetPageModel.setOwnerId(owner_id);
                final VersionedModel savedPage = targetPageService.save(targetPageModel);
                return savedPage.getId();
            }
        }
        return null;
    }

}
