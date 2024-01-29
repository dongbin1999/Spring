package user;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.graduate.appllication.user.UserService;
import study.graduate.domain.user.UserEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class UserServiceTest {

//    UserService userService;
//
//    @Test
//    void 회원가입(){
//        //given
//        UserEntity userEntity = UserEntity.builder().login_id("userA").build();
//
//        //when
//        Long joinId = userService.join(userEntity);
//
//        //then
//        //assertThat(userEntity).isEqualTo(userService.findById(joinId).orElseThrow());
//    }
//
//    @Test
//    void 중복_회원_예외() throws Exception {
//        //given
//        UserEntity user1 = UserEntity.builder().login_id("userA").build();
//        UserEntity user2 = UserEntity.builder().login_id("userA").build();
//
//        //when
//        userService.join(user1);
//        IllegalStateException e = assertThrows(IllegalStateException.class, () -> userService.join(user2));
//
//        //then
//        assertThat(e.getMessage()).isEqualTo("이미 존재하는 아이디입니다.");
//    }
}
