package post;

import base.BaseEntity;
import comment.CommentEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import user.UserEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class PostEntity extends BaseEntity {

    @Id
    @GeneratedValue
    private Long post_id;

    @Column(nullable = false)
    private String post_title;

    //이거도 자료형이 PostEntity이어야하지않나?
    @Column(nullable = false)
    private String create_id;

    @Column(nullable = false)
    private String post_content;

    @Builder
    public void Post(Long post_id, String post_title, String create_id, String post_content){
        this.post_id=post_id;
        this.post_title=post_title;
        this.create_id=create_id;
        this.post_content=post_content;
    }

    public void update(String post_title, String post_content){
        this.post_title=post_title;
        this.post_content=post_content;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @OneToMany(mappedBy = "PostEntity", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<CommentEntity> users = new ArrayList<>();
}
