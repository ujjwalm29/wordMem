package wordmem.web.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import wordmem.web.UserWords;

@Controller
@RequestMapping(path = "/userhome")
public class UserHomePage {
    @GetMapping()
    public String userHome(Model model, Principal principal) {
        if (model.getAttribute("u") == null) {
            UserWords u = new UserWords();
            u.setUsername(principal.getName());
            model.addAttribute("user", u);
            System.out.println("The model before is : " + u);
            model.addAttribute("repeat", false);
            return "userHome";
        } else {
            System.out.println("Not configured");
        }
        return "userHome";
    }
}
