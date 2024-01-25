package study.graduate.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import study.graduate.domain.post.PostEntity;
import study.graduate.dto.post.PostFindResponseDTO;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
public class UserFindPostResponseDTO {

    private List<PostFindResponseDTO> posts;

//    public static PostFindResponseDTO postEntityToDTO(PostEntity postEntity){
//        return PostFindResponseDTO.topostFindResponseDTO(postEntity);
//    }

    public static UserFindPostResponseDTO toUserFindPostResponseDTO(List<PostEntity> postEntities){
        List<PostFindResponseDTO> postFindResponseDTOs = new ArrayList<>();

        for(PostEntity post:postEntities){
            postFindResponseDTOs.add(PostFindResponseDTO.topostFindResponseDTO(post));
        }

        return UserFindPostResponseDTO.builder()
                .posts(postFindResponseDTOs)
                .build();
    }
}
