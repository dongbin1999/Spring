package study.graduate.dto.post;

import lombok.Getter;

@Getter
public class PostUpdateRequestDTO {

    private Long postId;
    private String postTitle;
    private String postContent;
}
