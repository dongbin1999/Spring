package comment;

import base.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import post.PostEntity;
import user.UserEntity;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentEntity extends BaseEntity {

    @Id
    @GeneratedValue
    private Long comment_id;

    @Column(nullable = false)
    private String comment_content;

    @Builder
    public CommentEntity(String comment_content, PostEntity postEntity, UserEntity userEntity){
        this.comment_content=comment_content;
        this.postEntity=postEntity;
        this.userEntity=userEntity;
    }

    public static CommentEntity comment(String comment_content, PostEntity postEntity, UserEntity userEntity){
        return CommentEntity.builder().comment_content(comment_content)
                .postEntity(postEntity).userEntity(userEntity).build();
    }

    public void update(String comment_content){
        this.comment_content=comment_content;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private PostEntity postEntity;

    //지금이게 다대일 단방향 연결이 돼버렸다..
    //유저가 삭제->댓글도 삭제돼야하는데, 영속성 전이속성을 넣어야되는데, manytoone에 못넣어. 어떡하지?
    @ManyToOne(fetch = FetchType.LAZY)
    //이렇게하자!(영속성 전이)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
}
