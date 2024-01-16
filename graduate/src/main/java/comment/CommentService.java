package comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import post.PostEntity;
import post.PostRepository;
import user.UserEntity;
import user.UserRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Transactional
    //이 함수 이름.. PostService랑 같아도 되려나?
    public Long register(String comment_content, Long user_id, Long post_id){
        PostEntity postEntity = postRepository.findById(post_id).orElseThrow();
        UserEntity userEntity = userRepository.findById(user_id).orElseThrow();

        CommentEntity commentEntity = CommentEntity.comment(comment_content,postEntity,userEntity);
        commentRepository.save(commentEntity);
        return commentEntity.getComment_id();
    }

    @Transactional
    public void updateCommentEntity(Long comment_id, String comment_content){
        CommentEntity commentEntity = commentRepository.findById(comment_id).orElseThrow();
        commentEntity.update(comment_content);
    }

    public List<CommentEntity> findAll(){
        return commentRepository.findAll();
    }
}
