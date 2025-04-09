package talk.innertalk.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import talk.innertalk.dto.JoinRequestDto;
import talk.innertalk.exception.CustomException;
import talk.innertalk.exception.ErrorMessages;
import talk.innertalk.repository.MemberRepository;
import talk.innertalk.service.MemberService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/innertalk")
public class LoginController {


    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @GetMapping("/login")
    public String loginPage(Model model) {
        return "login";
    }


    @GetMapping("/signup")
    public String signupPage(Model model) {
        JoinRequestDto joinRequestDto = new JoinRequestDto();
        model.addAttribute("joinRequestDto", joinRequestDto);
        return "signup";
    }


    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute("joinRequestDto") JoinRequestDto joinRequestDto , BindingResult bindingResult) {

        //중복 검사 로직
        duplicatedCheck(joinRequestDto.getEmail(), joinRequestDto.getNickname(), bindingResult);

        //패스워드일치 검사 로직
        passwordChecked(joinRequestDto.getPassword(), joinRequestDto.getPasswordCheck(), bindingResult);


        //만약 에러가 있으면
        if (bindingResult.hasErrors()) {
            return "signup";
        }

        memberService.join(joinRequestDto);


        return "redirect:/innertalk/login";
    }


    /**
     * 패스워드와 패스워드체크가 일치하는지 검사하는 로직
     * @param password
     * @param passwordCheck
     */
    private void passwordChecked(String password, String passwordCheck,BindingResult bindingResult) {
        if(!password.equals(passwordCheck)) {
            bindingResult.reject("passwordNotEqual",ErrorMessages.NOT_EQUAL_PASSWORD);
        }
    }

    /**
     * 중복 검사
     */
    private Boolean duplicatedCheck(String email, String nickname, BindingResult bindingResult) {
        if(memberRepository.findByEmail(email).isPresent()) {
            bindingResult.reject("emailDuplicated",ErrorMessages.DUPLICATED_EMAIL);
        }

        if (memberRepository.findByNickname(nickname).isPresent()) {
            bindingResult.reject("emailDuplicated",ErrorMessages.DUPLICATED_NICKNAME);
        }
        return true;
    }




}
