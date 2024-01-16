package user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    //그리고 orElseThrow 대신 ifPresent써도 되나?
    private void validateDuplicateLoginId(UserEntity userEntity){
        userRepository.findByLogin_id(userEntity.getLogin_id()).orElseThrow(() ->
                new IllegalStateException("이미 존재하는 아이디입니다."));
    }

    //얘는 Test에서 써야되니까 public인가?
    public List<UserEntity> findUsers(){
        return userRepository.findAll();
    }

    public Optional<UserEntity> findById(Long user_id){
        return userRepository.findById(user_id);
    }
}
