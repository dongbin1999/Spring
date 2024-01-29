package study.graduate.dto.comment;

import lombok.Getter;
import study.graduate.domain.comment.CommentEntity;
import study.graduate.domain.post.PostEntity;
import study.graduate.domain.user.UserEntity;

@Getter
public class CommentAddRequestDTO {

    private Long userId;
    private Long postId;
    private String commentContent;

    public CommentEntity toCommentEntity(UserEntity userEntity,PostEntity postEntity){
        return CommentEntity.builder()
                .commentContent(commentContent)
                .postEntity(postEntity)
                .userEntity(userEntity)
                .build();
    }
}
