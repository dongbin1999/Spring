package study.graduate.dto.comment;

import lombok.Getter;

@Getter
public class CommentUpdateRequestDTO {

    private Long commentId;
    private String commentContent;
}
