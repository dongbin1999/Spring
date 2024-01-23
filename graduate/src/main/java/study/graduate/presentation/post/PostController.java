package study.graduate.presentation.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.graduate.appllication.post.PostService;
import study.graduate.appllication.user.UserService;
import study.graduate.dto.post.PostAddRequestDTO;
import study.graduate.dto.post.PostUpdateRequestDTO;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    //이걸 써서, postService의 userRepository를 지워보자.
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
    public ResponseEntity<HttpStatus> addPost(@RequestBody PostAddRequestDTO postAddRequestDTO){
        postService.addPost(postAddRequestDTO);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/modify")
    public ResponseEntity<HttpStatus> updatePost(@RequestBody PostUpdateRequestDTO postUpdateRequestDTO){
        postService.updatePost(postUpdateRequestDTO);
        return ResponseEntity.ok().build();
    }
}