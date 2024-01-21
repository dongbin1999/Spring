package study.graduate.dto.post;

import lombok.Getter;
import lombok.Setter;
import study.graduate.domain.post.PostEntity;
import study.graduate.domain.user.UserEntity;


@Getter
public class PostAddRequestDTO {

    private String postTitle;
    private String postContent;
    @Setter
    private UserEntity userEntity;

    //얘는 userId만 필요한데... Entity를 다 넘겨주면 DTO를 만드는 의미가 없는거아닌가?
    public PostEntity toPostEntity(){
        return PostEntity.builder()
                .postTitle(postTitle)
                .postContent(postContent)
                .userEntity(userEntity)
                .build();
    }
}