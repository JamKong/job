package cn.jamkong.controller;

import cc.innovator.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("")
public class MainController {

    @Value("${username}")
    private String username;

    @Value("${password}")
    private String password;

    @RequestMapping(value = "login")
    public String toLogin() {
        return "login";
    }

    @RequestMapping(value = "signIn")
    public String signIn(User user, HttpServletRequest request) {
        if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
            request.getSession().setAttribute("isAuth", true);
            return "redirect:/job/";
        }
        return "login";
    }

    @RequestMapping(value = "logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("isAuth");
        return "redirect:/login";
    }
}
