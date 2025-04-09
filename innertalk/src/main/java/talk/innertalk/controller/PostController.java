package talk.innertalk.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import talk.innertalk.service.PostService;

@Controller
@Slf4j
@RequiredArgsConstructor
public class PostController {


    private final PostService postService;


}
