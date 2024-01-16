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
    public Long register(String title, String content, Long user_id){
        UserEntity userEntity = userRepository.findById(user_id).orElseThrow();

        PostEntity postEntity = PostEntity.posting(title, content, userEntity);
        postRepository.save(postEntity);
        return postEntity.getPost_id();
    }

    @Transactional
    public void updatePostEntity(Long post_id, String post_title, String post_content){
        PostEntity postEntity = postRepository.findById(post_id).orElseThrow();
        postEntity.update(post_title,post_content);
    }

    public List<PostEntity> findAll(){
        return postRepository.findAll();
    }

    //이 기능이 필요한가? 보통은 user_id로 검색하지 않을까?
    public Optional<PostEntity> findById(Long post_id) {
        return postRepository.findById(post_id);
    }
}
