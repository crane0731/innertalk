package talk.innertalk.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import talk.innertalk.domain.Member;
import talk.innertalk.service.BookMarkService;
import talk.innertalk.service.MemberService;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/innertalk")
public class BookMarkController {


    private final BookMarkService bookMarkService;

    /**
     * 북마크에 게시글을 추가
     */
    @PostMapping("/bookmark/{postId}")
    public String addBookMark(@PathVariable("postId") Long postId) {


        //북마크 생성 및 저장
        bookMarkService.saveBookMark(postId);


        return "redirect:/innertalk/posts/" + postId;
    }


}
