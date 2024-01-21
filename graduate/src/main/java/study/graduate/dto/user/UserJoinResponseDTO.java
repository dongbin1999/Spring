package study.graduate.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import study.graduate.domain.user.UserEntity;

@Builder
@Getter
@Setter
@ToString
public class UserJoinResponseDTO {

    private Long userId;
    private String userName;
    private String userEmail;
    private String loginId;
    private String password;

    public static UserJoinResponseDTO toUserResponseDTO(UserEntity userEntity) {
        return UserJoinResponseDTO.builder()
                .userId(userEntity.getUserId())
                .userName(userEntity.getUserName())
                .userEmail(userEntity.getUserEmail())
                .loginId(userEntity.getLoginId())
                .password(userEntity.getPassword())
                .build();
    }
}
