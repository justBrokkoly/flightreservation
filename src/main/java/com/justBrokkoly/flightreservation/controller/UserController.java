package com.justBrokkoly.flightreservation.controller;


import com.justBrokkoly.flightreservation.entity.User;
import com.justBrokkoly.flightreservation.repos.UserRepository;
import com.justBrokkoly.flightreservation.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private UserRepository userRepository;
    private BCryptPasswordEncoder encoder;
    private SecurityService securityService;

    public UserController(UserRepository userRepository, BCryptPasswordEncoder encoder, SecurityService securityService) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.securityService = securityService;
    }

    private static final  Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/regShow")
    public String showRegistrationPage(){
        LOGGER.info("inside  showRegistrationPage()");
        return "login/registerUser";
    }


    @PostMapping("/registerUser")
    public String register(@ModelAttribute("user") User user){
        LOGGER.info("inside  register()"+user);
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return "login/login";
    }

    @RequestMapping("/logShow")
    public String showLoginPage(){
        LOGGER.info("inside  showLoginPage()");
        return "login/login";
    }


    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password,Model model){
        LOGGER.info("inside  login() and the email is: "+ email);

       boolean loginResponse = securityService.login(email,password);
        if(loginResponse){
            return "findflights";
        }else{
            model.addAttribute("msg","Invalid user name or password. Pls try again.");
        }
        return "login/login";
    }


}
