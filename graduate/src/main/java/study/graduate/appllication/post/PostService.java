package study.graduate.appllication.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.graduate.domain.post.PostEntity;
import study.graduate.domain.post.PostRepository;
import study.graduate.domain.user.UserEntity;
import study.graduate.domain.user.UserRepository;
import study.graduate.dto.post.PostAddRequestDTO;
import study.graduate.dto.post.PostAddResponseDTO;
import study.graduate.dto.post.PostUpdateRequestDTO;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long addPost(PostAddRequestDTO postAddRequestDTO){
        UserEntity userEntity = userRepository.findById(postAddRequestDTO.getUserId()).orElseThrow();
        PostEntity postEntity = postAddRequestDTO.toPostEntity(userEntity);
        postRepository.save(postEntity);
        return postEntity.getPostId();
    }

    @Transactional
    public void updatePost(PostUpdateRequestDTO postUpdateRequestDTO){
        PostEntity postEntity = postRepository.findById(postUpdateRequestDTO.getPostId()).orElseThrow();
        postEntity.updatePost(postUpdateRequestDTO.getPostTitle(), postUpdateRequestDTO.getPostContent());
    }

    public PostEntity findById(Long postId){
        return postRepository.findById(postId).orElseThrow();
    }

    public PostAddResponseDTO readPost(Long postId){
        PostEntity postEntity = postRepository.findById(postId).orElseThrow();
        return PostAddResponseDTO.toPostAddResponseDTO(postEntity);
    }

    @Transactional
    public void deletePost(Long postId){
        postRepository.deleteById(postId);
    }
}
