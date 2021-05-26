package wordmem.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import wordmem.web.data.ConfirmationTokenRepository;
import wordmem.web.data.JdbcRegisterUserRepository;
import wordmem.web.data.UserRepository;
import wordmem.web.services.EmailService;
import wordmem.web.ConfirmationToken;
import wordmem.web.Users;

@Controller
@RequestMapping(path = "/")
public class RegisterUserController {

    private final JdbcRegisterUserRepository wordRepo;
    private final PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private ConfirmationTokenRepository confirmationTokenRepository;
    private EmailService emailSenderService;
    private ConfirmationToken save;

    @Autowired
    public RegisterUserController(JdbcRegisterUserRepository wordRepo, PasswordEncoder passwordEncoder,
            UserRepository userRepository, ConfirmationTokenRepository confirmationTokenRepository,
            EmailService emailSenderService) {
        this.wordRepo = wordRepo;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.emailSenderService = emailSenderService;
    }

    @PostMapping(path = "/register")
    public ModelAndView processRegistration(ModelAndView modelAndView, @ModelAttribute Users user) {

        System.out.println("ada = " + user.getEmail());
        Users existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            modelAndView.addObject("message", "This email already exists!");
            modelAndView.setViewName("error");
        } else {
            wordRepo.saveUser(user.toUser(passwordEncoder));

            ConfirmationToken confirmationToken = new ConfirmationToken(user);

            System.out.println(confirmationToken);

            save = confirmationTokenRepository.save(confirmationToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("chand312902@gmail.com");
            mailMessage.setText("To confirm your account, please click here : " + "http://localhost:8080/confirm?token="
                    + confirmationToken.getConfirmationToken());

            emailSenderService.sendEmail(mailMessage);

            modelAndView.addObject("emailId", user.getEmail());

            modelAndView.setViewName("successfulRegisteration");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/confirm", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token") String confirmationToken) {
        System.out.println("asdnasd");
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        System.out.println("asdnasd");
        if (token != null) {
            Users user = userRepository.findByEmail(token.getUser().getEmail());
            user.setEnabled(true);
            userRepository.save(user);
            modelAndView.setViewName("accountVerified");
        } else {
            modelAndView.addObject("message", "The link is invalid or broken!");
            modelAndView.setViewName("error");
        }

        return modelAndView;
    }
}
