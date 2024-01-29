package study.graduate.presentation.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.graduate.appllication.comment.CommentService;
import study.graduate.dto.comment.CommentAddRequestDTO;
import study.graduate.dto.comment.CommentUpdateRequestDTO;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("")
    public ResponseEntity<?> addComment(CommentAddRequestDTO commentAddRequestDTO){
        commentService.addComment(commentAddRequestDTO);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/modify")
    public ResponseEntity<?> updateComment(CommentUpdateRequestDTO commentUpdateRequestDTO){
        commentService.updateComment(commentUpdateRequestDTO);
        return ResponseEntity.ok().build();
    }
}
