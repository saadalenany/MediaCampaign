package com.spring.mediacompaign.controllers;

import com.spring.mediacompaign.dao.models.AdminModel;
import com.spring.mediacompaign.services.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
    public void checkLogin(HttpServletRequest request, HttpServletResponse response) {

        String name = request.getParameter("name");
        String pass = request.getParameter("pass");

        AdminModel user = adminService.getByUsernameAndPassword(name, pass);

        HttpSession session = request.getSession();
        if (user == null) {
            session.setAttribute("errorMessage", String.format("No Admin found with name [%s] & password [%s]", name, pass));
            response.addHeader("location", "/login");
            response.setStatus(301);
        } else {
            session.setAttribute("user", user);

            response.addHeader("location", "/home");
            response.setStatus(301);
        }
    }
}
