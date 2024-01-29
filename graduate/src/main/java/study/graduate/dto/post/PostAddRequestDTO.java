package study.graduate.dto.post;

import lombok.Getter;
import study.graduate.domain.post.PostEntity;
import study.graduate.domain.user.UserEntity;

@Getter
public class PostAddRequestDTO {

    //이걸 front에서 넘겨받으려면, JoinUser할때 userId반환을 해서 고유Id정보를 알려줘야겠지?
    private Long userId;
    private String postTitle;
    private String postContent;

    //Q.얘는 userId만 필요한데... Entity를 다 넘겨주면 DTO를 만드는 의미가 없는거아닌가?
    //->A. userEntity파라미터는, 위의 userId필드로 userService에서 찾아서 넘겨주는거임.
    public PostEntity toPostEntity(UserEntity userEntity){
        return PostEntity.builder()
                .postTitle(postTitle)
                .postContent(postContent)
                .userEntity(userEntity)
                .build();
    }
}