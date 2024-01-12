package post;

import base.BaseEntity;
import comment.CommentEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import user.UserEntity;

import java.util.List;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostEntity extends BaseEntity {

    @Id
    @GeneratedValue
    private Long post_id;

    @Column(nullable = false)
    private String post_title;

    @Column(nullable = false)
    private String post_content;

    //유저가 닉네임을 바꾸면 이상하니까, nickname 변수를 따로 만들면 안좋을거같아..

    @Builder
    public PostEntity(String post_title, String post_content){
        this.post_title=post_title;
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
    private List<CommentEntity> users;
}
