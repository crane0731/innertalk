package talk.innertalk.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/innertalk")
public class LoginController {


    @GetMapping("/login")
    public String loginPage(Model model) {
        return "login";
    }



}
