package study.graduate.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import study.graduate.domain.comment.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity,Long> {
}
