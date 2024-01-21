package study.graduate.dto.comment;

import lombok.Setter;
import study.graduate.domain.comment.CommentEntity;
import study.graduate.domain.post.PostEntity;
import study.graduate.domain.user.UserEntity;

public class CommentAddRequestDTO {

    private String commentContent;
    @Setter
    private PostEntity postEntity;
    @Setter
    private UserEntity userEntity;

    public CommentEntity toCommentEntity(){
        return CommentEntity.builder()
                .commentContent(commentContent)
                .postEntity(postEntity)
                .userEntity(userEntity)
                .build();
    }
}
