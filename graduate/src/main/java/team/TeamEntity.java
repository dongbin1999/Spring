package team;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import user.UserEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class TeamEntity {

    @Id
    @GeneratedValue
    @Column(name = "team_id")
    private Long team_id;

    @Column(name = "team_name", nullable = false)
    private String teamName;

    @OneToMany(mappedBy = "TeamEntity")
    private List<UserEntity> users = new ArrayList<UserEntity>();
}
