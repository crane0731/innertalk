package talk.innertalk.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import talk.innertalk.service.LikeService;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/innertalk")
public class LikeController {

    private final LikeService likeService;

    /**
     * 게시글 공감
     */
    @PostMapping("/like/{postId}")
    public String likePost(@PathVariable("postId")Long postId) {

        likeService.addLike(postId);

        return "redirect:/innertalk/posts/" + postId;
    }


}
