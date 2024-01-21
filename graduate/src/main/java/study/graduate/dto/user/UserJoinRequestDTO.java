package study.graduate.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import study.graduate.domain.user.UserEntity;

//기본 생성자.
@NoArgsConstructor
@Getter
public class UserJoinRequestDTO {

    private String userName;
    private String userEmail;
    private String loginId;
    private String password;

    public UserEntity toUserEntity(){
        return UserEntity.builder()
                .userName(userName)
                .userEmail(userEmail)
                .loginId(loginId)
                .password(password)
                .build();
    }
}