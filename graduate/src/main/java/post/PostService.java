package post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import user.UserEntity;
import user.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long addPost(String title, String content, Long user_id){
        UserEntity userEntity = userRepository.findById(user_id).orElseThrow();

        PostEntity postEntity = PostEntity.toPostEntity(title, content, userEntity);
        postRepository.save(postEntity);
        return postEntity.getPost_id();
    }

    @Transactional
    public void updatePostEntity(Long post_id, String post_title, String post_content){
        PostEntity postEntity = postRepository.findById(post_id).orElseThrow();
        postEntity.updatePost(post_title,post_content);
        postRepository.save(postEntity);
    }
}
