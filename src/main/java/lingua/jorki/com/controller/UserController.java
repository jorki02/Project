package lingua.jorki.com.controller;

import lingua.jorki.com.model.User;
import lingua.jorki.com.modelJDBC.Word;
import lingua.jorki.com.service.SecurityService;
import lingua.jorki.com.service.TrainService;
import lingua.jorki.com.service.UserService;
import lingua.jorki.com.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Controller for {@link lingua.jorki.com.model.User}'s pages.
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    TrainService trainService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        try {
            securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/ru/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }

        return "login";
    }

    @RequestMapping(value = {"/", "/ru/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "ru/welcome";
    }

    @RequestMapping(value = {"/ru/dictionary"}, method = RequestMethod.GET)
    public String dictionary(Model model) {
        return "ru/dictionary";
    }

    @RequestMapping(value = {"/ru/train/cards"}, method = RequestMethod.GET)
    public String cards(Model model) {

        return "ru/train/cards";
    }

    @RequestMapping(value = {"/ru/progress"}, method = RequestMethod.GET)
    public String progress(Model model) {

        return "ru/progress";
    }

    @RequestMapping(value = {"/ru/train/translationWord"}, method = RequestMethod.GET)
    public String translationWord(Model model) {

        return "ru/train/translationWord";
    }

    @RequestMapping(value = {"/ru/train/wordTranslation"}, method = RequestMethod.GET)
    public String wordTranslation(Model model) {

        return "ru/train/wordTranslation";
    }

}
