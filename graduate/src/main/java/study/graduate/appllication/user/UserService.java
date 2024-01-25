package study.graduate.appllication.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.graduate.domain.post.PostEntity;
import study.graduate.domain.user.UserEntity;
import study.graduate.domain.user.UserRepository;
import study.graduate.dto.user.UserFindPostResponseDTO;
import study.graduate.dto.user.UserJoinRequestDTO;
import study.graduate.dto.user.UserJoinResponseDTO;
import study.graduate.dto.user.UserUpdateRequestDTO;

import java.util.List;

@Service
//메소드 앞뒤에 db랑 커넥션/연결해제코드 추가해주는 annotation. AOP 기술.
@Transactional(readOnly = true)//readOnly하면 select밖에안해서 변경감지 안해도됨. 빨라짐.
//final 필드만 포함된 생성자 생성.(UserRepository가 final이라서..)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //여기다 또 달아주면 default인 false가 된다... join은 update해야하니까 false가 맞고.
    @Transactional
    public Long join(UserJoinRequestDTO userJoinRequestDTO){
        UserEntity userEntity = userJoinRequestDTO.toUserEntity();
        validateDuplicateLoginId(userEntity);
        userRepository.save(userEntity);
        return userEntity.getUserId();
    }

    //나중에 userName, userEmail 중복검사도 같이하자.
    private void validateDuplicateLoginId(UserEntity userEntity){
        userRepository.findByLoginId(userEntity.getLoginId()).ifPresent(a
                -> {throw new IllegalStateException("이미 존재하는 아이디입니다.");
        });
    }

    @Transactional
    //userUpdateRequestDTO도 update하는 필드마다 따로만드는게 나은가..? -> ㄴㄴ 아니다 한번에하자.
    //파라미터는 String UserName만 주면 안되겠지? userId, password등도 알아야하니까?
    public void updateUser(UserUpdateRequestDTO userUpdateRequestDTO){
        UserEntity userEntity = userRepository.findById(userUpdateRequestDTO.getUserId()).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 유저입니다."));
        //update할때는 DTO의 password랑 repository에서 찾은 entity의 password가 같은지 교차검증하는 코드도 필요할거같아...
        userEntity.updateUser(userUpdateRequestDTO);
    }

    /**
     * presentation layer와 domain layer 간의 의존성을 끊기 위해 필요하다.
     * 이게없으면 controller가 userRepository를 알아야되니까...
     */
    public List<UserEntity> findUsers(){
        return userRepository.findAll();
    }

    public UserEntity findById(Long userId){
        return userRepository.findById(userId).orElseThrow();
    }

    public UserJoinResponseDTO readUser(Long userId){
        UserEntity userEntity = findById(userId);
        return UserJoinResponseDTO.toUserResponseDTO(userEntity);
    }

    public UserFindPostResponseDTO readPosts(Long userId){
        UserEntity userEntity = findById(userId);
        List<PostEntity> posts = userEntity.getPosts();
        return UserFindPostResponseDTO.toUserFindPostResponseDTO(posts);
    }

    //정신차리자.. Transactional 안붙이고 뭐하냐...
    @Transactional
    public void deleteUser(Long userId){
        userRepository.deleteById(userId);
    }
}
