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
    public Long addComment(String comment_content, Long user_id, Long post_id){
        PostEntity postEntity = postRepository.findById(post_id).orElseThrow();
        UserEntity userEntity = userRepository.findById(user_id).orElseThrow();

        CommentEntity commentEntity = CommentEntity.toCommentEntity(comment_content,postEntity,userEntity);
        commentRepository.save(commentEntity);
        return commentEntity.getComment_id();
    }

    @Transactional
    public void updateCommentEntity(Long comment_id, String comment_content){
        CommentEntity commentEntity = commentRepository.findById(comment_id).orElseThrow();
        commentEntity.updateComment(comment_content);
        commentRepository.save(commentEntity);
    }
}
