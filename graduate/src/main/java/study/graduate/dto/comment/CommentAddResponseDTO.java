package study.graduate.dto.comment;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class CommentAddResponseDTO {

    private Long userId;
    private Long postId;
    private Long commentId;
    private String commentContent;
}
