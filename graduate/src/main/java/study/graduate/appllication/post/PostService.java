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
    public Long addPost(String title, String content, Long userId){
        UserEntity userEntity = userRepository.findById(userId).orElseThrow();

        PostEntity postEntity = PostEntity.toPostEntity(title, content, userEntity);
        postRepository.save(postEntity);
        return postEntity.getPostId();
    }

    @Transactional
    public void updatePostEntity(Long postId, String postTitle, String postContent){
        PostEntity postEntity = postRepository.findById(postId).orElseThrow();
        postEntity.updatePost(postTitle,postContent);
        postRepository.save(postEntity);
    }
}
