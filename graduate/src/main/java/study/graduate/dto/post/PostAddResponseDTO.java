package study.graduate.dto.post;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import study.graduate.domain.comment.CommentEntity;
import study.graduate.domain.user.UserEntity;

import java.util.List;

@Builder
@Getter
@Setter
@ToString
public class PostAddResponseDTO {

    private Long postId;
    private String postTitle;
    private String postContent;
    private UserEntity userEntity;
    private List<CommentEntity> users;
}
