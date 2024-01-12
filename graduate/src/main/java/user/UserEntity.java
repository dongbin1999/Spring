package user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import post.PostEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "userName", nullable = false)
    private String userName;

    @Column(name = "user_email", nullable = false)
    private String user_email;

    @Builder
    public void User(Long user_id, String userName, String user_email){
        this.user_id=user_id;
        this.userName=userName;
        this.user_email=user_email;
    }

    @OneToMany(mappedBy = "UserEntity", cascade = CascadeType.PERSIST)
    private List<PostEntity> users = new ArrayList<>();
}
