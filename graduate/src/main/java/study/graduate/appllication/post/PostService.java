package study.graduate.appllication.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.graduate.domain.post.PostEntity;
import study.graduate.domain.post.PostRepository;
import study.graduate.domain.user.UserEntity;
import study.graduate.domain.user.UserRepository;

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
