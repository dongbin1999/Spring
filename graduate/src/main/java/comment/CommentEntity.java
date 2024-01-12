package comment;

import base.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import post.PostEntity;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class CommentEntity extends BaseEntity {

    @Id
    @GeneratedValue
    private Long comment_id;

    //이거 자료형이 UserEntity이어야하지 않나..?
    @Column(nullable = false)
    private String create_id;

    @Column(nullable = false)
    private String comment_content;

    @Builder
    public void Comment(Long comment_id, String create_id, String post_content){
        this.comment_id=comment_id;
        this.create_id=create_id;
        this.comment_content=post_content;
    }

    public void update(String comment_content){
        this.comment_content=comment_content;
    }

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity postEntity;
}
