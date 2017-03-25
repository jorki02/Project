package lingua.jorki.com.controller;

import lingua.jorki.com.model.User;
import lingua.jorki.com.modelJDBC.Word;
import lingua.jorki.com.service.TrainService;
import lingua.jorki.com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

/**
 * Created by Саша on 18.03.2017.
 */
@RestController
@RequestMapping("/ru/train")
public class TrainController {

    @Autowired
    UserService userService;

    @Autowired
    TrainService trainService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Word> wordTranslation(Model model) {

        UserDetails userDetails = (UserDetails) org.springframework.security.core.context.SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();

        User user = userService.findByUsername(userDetails.getUsername());

        List<Word> unexploredWords = trainService.getUnexploredWords(user);

        return unexploredWords;
    }

    @RequestMapping(value = "/repeat/{id}", method = RequestMethod.GET)
    public Long wordRepeat(@PathVariable Long id, Model model) {

        UserDetails userDetails = (UserDetails) org.springframework.security.core.context.SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();

        User user = userService.findByUsername(userDetails.getUsername());

        return trainService.getCountRepeat(user, id);
    }

    @RequestMapping(value = "/updateRepeat/{id}", method = RequestMethod.PUT)
    public Long updateQuestion(@PathVariable Long id, @RequestBody Long count, Model model) {
        UserDetails userDetails = (UserDetails) org.springframework.security.core.context.SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();

        User user = userService.findByUsername(userDetails.getUsername());

        trainService.setRepeatDate(user, id, count);

        return null;

    }

    @RequestMapping(value = "/remaining/{type}", method = RequestMethod.GET)
    public Long countRemainingWords(@PathVariable String type, Model model) {
        UserDetails userDetails = (UserDetails) org.springframework.security.core.context.SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();

        User user = userService.findByUsername(userDetails.getUsername());

        return trainService.countRemainingWords(type, user);

    }
}
