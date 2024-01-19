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

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long addComment(String commentContent, Long userId, Long postId){
        PostEntity postEntity = postRepository.findById(postId).orElseThrow();
        UserEntity userEntity = userRepository.findById(userId).orElseThrow();

        CommentEntity commentEntity = CommentEntity.toCommentEntity(commentContent,postEntity,userEntity);
        commentRepository.save(commentEntity);
        return commentEntity.getCommentId();
    }

    @Transactional
    public void updateCommentEntity(Long commentId, String commentContent){
        CommentEntity commentEntity = commentRepository.findById(commentId).orElseThrow();
        commentEntity.updateComment(commentContent);
        commentRepository.save(commentEntity);
    }
}
