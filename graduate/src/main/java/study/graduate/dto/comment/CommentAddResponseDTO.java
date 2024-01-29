package study.graduate.dto.comment;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CommentAddResponseDTO {

    private Long userId;
    private Long postId;
    private Long commentId;
    private String commentContent;
}
