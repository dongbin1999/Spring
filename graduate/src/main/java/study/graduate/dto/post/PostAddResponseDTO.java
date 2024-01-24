package study.graduate.dto.post;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import study.graduate.domain.post.PostEntity;

@Builder
@Getter
@Setter
public class PostAddResponseDTO {

    private Long postId;
    private Long userId;
    private String postTitle;
    private String postContent;

    public static PostAddResponseDTO toPostAddResponseDTO(PostEntity postEntity){
        return PostAddResponseDTO.builder()
                .postId(postEntity.getPostId())
                .userId(postEntity.getUserEntity().getUserId())
                .postTitle(postEntity.getPostTitle())
                .postContent(postEntity.getPostContent())
                .build();
    }
}
