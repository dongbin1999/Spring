package user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.TeamEntity;

@Getter
@Entity
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "userName", nullable = false)
    private String userName;

    @Column(name = "user_email", nullable = false)
    private String user_email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private TeamEntity teamEntity;
}
