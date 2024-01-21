package study.graduate.appllication.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.graduate.domain.post.PostEntity;
import study.graduate.domain.post.PostRepository;
import study.graduate.domain.user.UserEntity;
import study.graduate.domain.user.UserRepository;
import study.graduate.dto.post.PostAddRequestDTO;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long addPost(PostAddRequestDTO postAddRequestDTO){
        UserEntity userEntity = postAddRequestDTO.getUserEntity();

        PostEntity postEntity = postAddRequestDTO.toPostEntity();
        postRepository.save(postEntity);
        return postEntity.getPostId();
    }

    @Transactional
    public void updatePostEntity(Long postId, String postTitle, String postContent){
        PostEntity postEntity = postRepository.findById(postId).orElseThrow();
        postEntity.updatePost(postTitle,postContent);
        postRepository.save(postEntity);
    }

    public PostEntity findById(Long postId){
        return postRepository.findById(postId).orElseThrow();
    }

    public void deletePost(Long postId){
        postRepository.deleteById(postId);
    }
}
