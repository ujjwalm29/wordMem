package wordmem.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import wordmem.web.UserWords;
import wordmem.web.data.JdbcWordsRepository;

@Controller
@RequestMapping(path = "/addword")
@SessionAttributes("user")
public class AddWord {

    private final JdbcWordsRepository wordRepo;

    @ModelAttribute(name = "user")
    public UserWords user() {
        return new UserWords();
    }

    @Autowired
    public AddWord(JdbcWordsRepository wordRepo) {
        this.wordRepo = wordRepo;
    }

    @PostMapping()
    public String addWordInUserList(Model model, @ModelAttribute UserWords user) {
        System.out.println("The model in AddWord is  : " + user);
        if (user.getNewWord() != null)
            wordRepo.save(user);
        model.addAttribute("repeat", true);
        return "userHome";
    }
}
