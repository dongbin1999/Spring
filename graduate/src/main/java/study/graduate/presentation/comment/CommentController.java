package study.graduate.presentation.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.graduate.appllication.comment.CommentService;
import study.graduate.dto.comment.CommentAddRequestDTO;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("")
    public ResponseEntity<?> addComment(CommentAddRequestDTO commentAddRequestDTO){
        commentService.addComment(commentAddRequestDTO);
        return ResponseEntity.ok().build();
    }
}
