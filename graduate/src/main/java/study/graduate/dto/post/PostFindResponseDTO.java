package study.graduate.dto.post;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import study.graduate.domain.post.PostEntity;

@Builder
@Getter
@Setter
public class PostFindResponseDTO {

    private Long postId;
    private String postTitle;
    private String postContent;

    public static PostFindResponseDTO topostFindResponseDTO(PostEntity postEntity){
        return PostFindResponseDTO.builder()
                .postId(postEntity.getPostId())
                .postTitle(postEntity.getPostTitle())
                .postContent(postEntity.getPostContent())
                .build();
    }
}
