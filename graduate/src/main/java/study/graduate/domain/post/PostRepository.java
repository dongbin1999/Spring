package study.graduate.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;
import study.graduate.domain.post.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity,Long> {
}
