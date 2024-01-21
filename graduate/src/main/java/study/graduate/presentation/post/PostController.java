package study.graduate.presentation.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.graduate.appllication.post.PostService;
import study.graduate.appllication.user.UserService;
import study.graduate.domain.post.PostEntity;
import study.graduate.dto.post.PostAddRequestDTO;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    //PostController가 userService를 알고있는건 단일성원칙을 크게 위반한건 아니다?
    private final UserService userService;

    @GetMapping("/{postId}")
    public ResponseEntity<?> findPost(@PathVariable Long postId){
        return ResponseEntity.ok().body(postService.findById(postId));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<HttpStatus> deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("")
    //나중에 userId를 front에서 받아오는 annotation으로 수정해야함.
    public ResponseEntity<HttpStatus> addPost(PostAddRequestDTO postAddRequestDTO, Long userId){
        postAddRequestDTO.setUserEntity(userService.findById(userId));
        postService.addPost(postAddRequestDTO);
        return ResponseEntity.ok().build();
    }

//    @PatchMapping("/postId")
//    public ResponseEntity<HttpStatus> updatePost(PostAddRequestDTO postAddRequestDTO){
//        postService.updatePostEntity();
//    }
}
