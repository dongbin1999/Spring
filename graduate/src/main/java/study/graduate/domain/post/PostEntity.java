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
    private Long postId;

    @Column(nullable = false)
    private String postTitle;

    @Column(nullable = false)
    private String postContent;

    //유저가 닉네임을 바꾸면 이상하니까, nickname 변수를 따로 만들면 안좋을거같아..

    @Builder
    public PostEntity(String postTitle, String postContent, UserEntity userEntity){
        this.postTitle=postTitle;
        this.postContent=postContent;
        this.userEntity=userEntity;
    }

    //정적 메서드. 작명에 신경쓰자.
    public static PostEntity toPostEntity(String postTitle, String postContent, UserEntity userEntity){
        return PostEntity.builder()
                .postTitle(postTitle)
                .postContent(postContent)
                .userEntity(userEntity).build();
    }

    //이게 setter야.. @Setter를 써도 되긴하는데, 의미파악을 위해서 직접 만들었다. 이것도 두개로 분리하는게 깔끔할지도?
    public void updatePost(String postTitle, String postContent){
        this.postTitle=postTitle;
        this.postContent=postContent;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private UserEntity userEntity;

    @OneToMany(mappedBy = "postEntity", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<CommentEntity> users;
}
