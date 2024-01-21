package study.graduate.appllication.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.graduate.domain.comment.CommentEntity;
import study.graduate.domain.comment.CommentRepository;
import study.graduate.domain.post.PostEntity;
import study.graduate.domain.post.PostRepository;
import study.graduate.domain.user.UserEntity;
import study.graduate.domain.user.UserRepository;
import study.graduate.dto.comment.CommentAddRequestDTO;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public Long addComment(CommentAddRequestDTO commentAddRequestDTO){
        CommentEntity commentEntity = commentAddRequestDTO.toCommentEntity();
        commentRepository.save(commentEntity);
        return commentEntity.getCommentId();
    }

    @Transactional
    public void updateCommentEntity(Long commentId, String commentContent){
        CommentEntity commentEntity = commentRepository.findById(commentId).orElseThrow();
        commentEntity.updateComment(commentContent);
        commentRepository.save(commentEntity);
    }

    public void deleteComment(Long commentId){
        commentRepository.deleteById(commentId);
    }
}