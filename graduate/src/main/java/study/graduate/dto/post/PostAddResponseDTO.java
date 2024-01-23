package study.graduate.dto.post;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PostAddResponseDTO {

    private Long postId;
    private Long userId;
    private String postTitle;
    private String postContent;
}
