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
import study.graduate.dto.post.PostFindResponseDTO;
import study.graduate.dto.post.PostUpdateRequestDTO;

import java.util.ArrayList;
import java.util.List;

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


    //UserEntity를 참조하긴 하지만, 결과적으로 "post"를 읽는 것이니, PostService와 PostController에서 관리해주는게 맞다.
    public List<PostFindResponseDTO> readPosts(String loginId){
        UserEntity userEntity = userRepository.findByLoginId(loginId).orElseThrow();
        List<PostFindResponseDTO> posts=new ArrayList<>();

        for(PostEntity post:userEntity.getPosts()){
            posts.add(PostFindResponseDTO.topostFindResponseDTO(post));
        }

        //postId 내림차순으로 정렬.
        posts.sort((o1,o2) -> (int)(o2.getPostId()-o1.getPostId()));
        //포스트 제목 사전순으로 정렬.
        posts.sort((o1,o2)->o1.getPostTitle().compareToIgnoreCase(o2.getPostTitle()));
        return posts;
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
