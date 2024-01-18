package study.graduate.appllication.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.graduate.domain.user.UserEntity;
import study.graduate.domain.user.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
//메소드 앞뒤에 db랑 커넥션/연결해제코드 추가해주는 annotation. AOP 기술.
@Transactional(readOnly = true)//readOnly하면 select밖에안해서 변경감지 안해도됨. 빨라짐.
//final 필드만 포함된 생성자 생성.(UserRepository가 final이라서..)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //여기다 또 달아주면 default인 false가 된다... join은 update해야하니까 false가 맞고.
    @Transactional
    public Long join(UserEntity userEntity){
        validateDuplicateLoginId(userEntity);
        userRepository.save(userEntity);
        return userEntity.getUser_id();
    }

    //그리고 orElseThrow 대신 ifPresent써도 되나? -> if를 써야 맞다.
    private void validateDuplicateLoginId(UserEntity userEntity){
        userRepository.findByLogin_id(userEntity.getLogin_id()).ifPresent(a
                -> {throw new IllegalStateException("이미 존재하는 아이디입니다.");
        });
    }

    /**
     * presentation layer와 domain layer 간의 의존성을 끊기 위해 필요하다.
     * 이게없으면 controller가 userRepository를 알아야되니까...
     */
    public List<UserEntity> findUsers(){
        return userRepository.findAll();
    }

    public Optional<UserEntity> findById(Long user_id){
        return userRepository.findById(user_id);
    }

    public void deleteUser(Long user_id){
        userRepository.deleteById(user_id);
    }

    public void updateUser(UserEntity userEntity){
        userRepository.
    }
}
