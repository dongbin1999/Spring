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
import study.graduate.dto.comment.CommentUpdateRequestDTO;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long addComment(CommentAddRequestDTO commentAddRequestDTO){
        PostEntity postEntity = postRepository.findById(commentAddRequestDTO.getPostId()).orElseThrow();
        UserEntity userEntity = userRepository.findById(commentAddRequestDTO.getUserId()).orElseThrow();
        CommentEntity commentEntity = commentAddRequestDTO.toCommentEntity(userEntity,postEntity);
        commentRepository.save(commentEntity);
        return commentEntity.getCommentId();
    }

    @Transactional
    public void updateComment(CommentUpdateRequestDTO commentUpdateRequestDTO){
        CommentEntity commentEntity = commentRepository.findById(commentUpdateRequestDTO.getCommentId()).orElseThrow();
        commentEntity.updateComment(commentUpdateRequestDTO.getCommentContent());
        commentRepository.save(commentEntity);
    }

    @Transactional
    public void deleteComment(Long commentId){
        commentRepository.deleteById(commentId);
    }

    public List<CommentEntity> findComments(){
        return commentRepository.findAll();
    }
}