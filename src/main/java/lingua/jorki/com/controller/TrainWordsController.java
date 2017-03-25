package lingua.jorki.com.controller;

import lingua.jorki.com.model.User;
import lingua.jorki.com.modelJDBC.Word;
import lingua.jorki.com.service.TrainTranslationService;
import lingua.jorki.com.service.TrainWordsService;
import lingua.jorki.com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Саша on 25.03.2017.
 */
@RestController
@RequestMapping("/ru/train/words")
public class TrainWordsController {

    @Autowired
    UserService userService;

    @Autowired
    TrainWordsService trainWordsService ;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Word> wordTranslation(Model model) {

        UserDetails userDetails = (UserDetails) org.springframework.security.core.context.SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();

        User user = userService.findByUsername(userDetails.getUsername());

        List<Word> unexploredWords = trainWordsService.getUnexploredWords(user);

        return unexploredWords;
    }

    @RequestMapping(value = "/wrong", method = RequestMethod.GET)
    public List<Word> wrongWordTranslation(Model model) {

        List<Word> wrongWords = trainWordsService.getWrongWords();

        return wrongWords;
    }
    @RequestMapping(value = "/repeat/{id}", method = RequestMethod.GET)
    public Long wordRepeat(@PathVariable Long id, Model model) {

        UserDetails userDetails = (UserDetails) org.springframework.security.core.context.SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();

        User user = userService.findByUsername(userDetails.getUsername());

        return trainWordsService.getCountRepeat(user, id);
    }

    @RequestMapping(value = "/updateRepeat/{id}", method = RequestMethod.PUT)
    public Long updateQuestion(@PathVariable Long id, @RequestBody Long count, Model model) {
        UserDetails userDetails = (UserDetails) org.springframework.security.core.context.SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();

        User user = userService.findByUsername(userDetails.getUsername());

        trainWordsService.setRepeatDate(user, id, count);

        return null;

    }
}
