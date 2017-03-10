package lingua.jorki.com.controller;

import lingua.jorki.com.model.User;
import lingua.jorki.com.modelJDBC.Translation;
import lingua.jorki.com.modelJDBC.Word;
import lingua.jorki.com.modelJDBC.helper.WordTranslation;
import lingua.jorki.com.service.UserDetailsServiceImpl;
import lingua.jorki.com.service.UserService;
import lingua.jorki.com.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/ru/dictionary")
public class WordController {

    @Autowired
    WordService wordService;

    @Autowired
    UserService userService;

    @Autowired
    UserDetailsService userDetailsService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<WordTranslation> getAllWords() {
        UserDetails userDetails = (UserDetails) org.springframework.security.core.context.SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();

        User user = userService.findByUsername(userDetails.getUsername());

        return wordService.getWordListByUser(user);
    }

    @RequestMapping(value = "/{word}", method = RequestMethod.GET)
    public List<WordTranslation> getTranslations(@PathVariable(value = "word") String word) {
        return wordService.getTranslationsByWord(word);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public List<WordTranslation> addWord(@RequestBody WordTranslation wordTranslation) {

        UserDetails userDetails = (UserDetails) org.springframework.security.core.context.SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();

        User user = userService.findByUsername(userDetails.getUsername());
        wordService.addWordWithTranslationToUser(wordTranslation.getEnglish_id(), wordTranslation.getTranslation_id(), user);

        return wordService.getWordListByUser(user);
    }

    /*@RequestMapping(value = "/ru/dictionary/{word}", method = RequestMethod.GET)
    public String findAll(@PathVariable(value = "word") String inputWord){

        wordService.findByUser(user);

        model.addAttribute();

    }*/
}
