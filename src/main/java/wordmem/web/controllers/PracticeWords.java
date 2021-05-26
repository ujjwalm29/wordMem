package wordmem.web.controllers;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import wordmem.web.UserWords;
import wordmem.web.data.JdbcWordsRepository;

@Controller
@RequestMapping(path = "/practice")
@SessionAttributes("user")
public class PracticeWords {

    private final JdbcWordsRepository wordRepo;
    String userName = null;

    @ModelAttribute(name = "user")
    public UserWords user() {
        return new UserWords();
    }

    @Autowired
    public PracticeWords(JdbcWordsRepository wordRepo) {
        this.wordRepo = wordRepo;
    }

    @GetMapping
    public String displayWords(Model model, Principal principal) {
        userName = principal.getName();
        System.out.println("User in Practice is = " + userName);
        UserWords user = new UserWords();
        user.setUsername(userName);
        user = wordRepo.findAllWords(user);
        List<String> temp = user.getWordList();
        Collections.shuffle(temp);
        user.setWordList(temp);
        model.addAttribute("user", user);

        return "practice";
    }
}
