package study.graduate.domain.post;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import study.graduate.domain.BaseEntity;
import study.graduate.domain.user.UserEntity;
import study.graduate.domain.comment.CommentEntity;

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
    public PostEntity(String post_title, String post_content, UserEntity userEntity){
        this.post_title=post_title;
        this.post_content=post_content;
        this.userEntity=userEntity;
    }

    //정적 메서드. 작명에 신경쓰자.
    public static PostEntity toPostEntity(String post_title, String post_content, UserEntity userEntity){
        return PostEntity.builder()
                .post_title(post_title)
                .post_content(post_content)
                .userEntity(userEntity).build();
    }

    //이게 setter야.. @Setter를 써도 되긴하는데, 의미파악을 위해서 직접 만들었다. 이것도 두개로 분리하는게 깔끔할지도?
    public void updatePost(String post_title, String post_content){
        this.post_title=post_title;
        this.post_content=post_content;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @OneToMany(mappedBy = "PostEntity", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<CommentEntity> users;
}
