package study.graduate.domain.comment;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import study.graduate.domain.BaseEntity;
import study.graduate.domain.post.PostEntity;
import study.graduate.domain.user.UserEntity;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentEntity extends BaseEntity {

    @Id
    @GeneratedValue
    private Long commentId;

    @Column(nullable = false)
    private String commentContent;

    @Builder
    public CommentEntity(String commentContent, PostEntity postEntity, UserEntity userEntity){
        this.commentContent=commentContent;
        this.postEntity=postEntity;
        this.userEntity=userEntity;
    }

    public static CommentEntity toCommentEntity(String commentContent, PostEntity postEntity, UserEntity userEntity){
        return CommentEntity.builder()
                .commentContent(commentContent)
                .postEntity(postEntity)
                .userEntity(userEntity).build();
    }

    public void updateComment(String comment_content){
        this.commentContent=commentContent;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    private PostEntity postEntity;

    //지금이게 다대일 단방향 연결이 돼버렸다..
    //유저가 삭제->댓글도 삭제돼야하는데, 영속성 전이속성을 넣어야되는데, manytoone에 못넣어. 어떡하지?
    @ManyToOne(fetch = FetchType.LAZY)
    //이렇게하자!(영속성 전이)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "userId")
    private UserEntity userEntity;
}
