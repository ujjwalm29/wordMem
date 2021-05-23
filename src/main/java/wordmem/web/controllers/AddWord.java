package wordmem.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import wordmem.web.User;
import wordmem.web.data.JdbcWordsRepository;

@Controller
@RequestMapping(path = "/addword")
@SessionAttributes("user")
public class AddWord {

    private final JdbcWordsRepository wordRepo;

    @ModelAttribute(name = "user")
    public User user() {
        return new User();
    }

    @Autowired
    public AddWord(JdbcWordsRepository wordRepo) {
        this.wordRepo = wordRepo;
    }

    @PostMapping()
    public String addWordInUserList(Model model, @ModelAttribute User user) {
        System.out.println("The model in AddWord is  : " + user);
        wordRepo.save(user);
        model.addAttribute("repeat", true);
        return "userHome";
    }
}
