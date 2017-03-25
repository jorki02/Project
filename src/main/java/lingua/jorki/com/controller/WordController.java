package lingua.jorki.com.controller;

import lingua.jorki.com.model.User;
import lingua.jorki.com.modelJDBC.Word;
import lingua.jorki.com.modelJDBC.Word_Progress;
import lingua.jorki.com.service.UserService;
import lingua.jorki.com.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

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
    public List<Word> getAllWords() {
        UserDetails userDetails = (UserDetails) org.springframework.security.core.context.SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();

        User user = userService.findByUsername(userDetails.getUsername());

        return wordService.getWordListByUser(user);
    }

    @RequestMapping(value = "/{word}", method = RequestMethod.GET)
    public List<Word> getTranslations(@PathVariable(value = "word") String word) {
        return wordService.getWordsByEnglish(word);
    }

    @RequestMapping(value = "/add/{wordId}", method = RequestMethod.POST)
    public List<Word> addWord(@PathVariable(value = "wordId") Long wordId) {

        UserDetails userDetails = (UserDetails) org.springframework.security.core.context.SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();

        User user = userService.findByUsername(userDetails.getUsername());
        wordService.addWordToUser(wordId, user);

        return wordService.getWordListByUser(user);
    }

    @RequestMapping(value = "/addList", method = RequestMethod.POST)
    public List<Word> addWordList(@RequestBody Word word) {
        wordService.addWord(word);

        return wordService.getWordsByEnglish(word.getEnglish());
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public List<Word> deleteWord(@PathVariable(value = "id") Long id){
        UserDetails userDetails = (UserDetails) org.springframework.security.core.context.SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();

        User user = userService.findByUsername(userDetails.getUsername());

        return wordService.deleteWord(user, id);
    }

    @RequestMapping(value = "/find/{word}", method = RequestMethod.GET)
    public List<Word> findWords(@PathVariable(value = "word") String word) {
        UserDetails userDetails = (UserDetails) org.springframework.security.core.context.SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();

        User user = userService.findByUsername(userDetails.getUsername());

        return wordService.findWords(user, word);
    }

    @RequestMapping(value = "/progress", method = RequestMethod.GET)
    public List<Word_Progress> getProgress(){

        UserDetails userDetails = (UserDetails) org.springframework.security.core.context.SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();

        User user = userService.findByUsername(userDetails.getUsername());

        return wordService.getProgressByUser(user);


    }
}
