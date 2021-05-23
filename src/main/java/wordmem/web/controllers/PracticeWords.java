package wordmem.web.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import wordmem.web.User;
import wordmem.web.data.JdbcWordsRepository;

@Controller
@RequestMapping(path = "/practice")
@SessionAttributes("user")
public class PracticeWords {

    private final JdbcWordsRepository wordRepo;
    String userName = null;

    @ModelAttribute(name = "user")
    public User user() {
        return new User();
    }

    @Autowired
    public PracticeWords(JdbcWordsRepository wordRepo) {
        this.wordRepo = wordRepo;
    }

    @GetMapping
    public String displayWords(Model model, Principal principal) {
        userName = principal.getName();
        System.out.println("User in Practice is = " + userName);
        User user = new User();
        user.setUsername(userName);
        user = wordRepo.findAllWords(user);
        model.addAttribute("user", user);

        return "practice";
    }
}
