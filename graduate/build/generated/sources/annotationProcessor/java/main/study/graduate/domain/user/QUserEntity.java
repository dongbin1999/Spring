package study.graduate.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserEntity is a Querydsl query type for UserEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserEntity extends EntityPathBase<UserEntity> {

    private static final long serialVersionUID = 1087111269L;

    public static final QUserEntity userEntity = new QUserEntity("userEntity");

    public final StringPath loginId = createString("loginId");

    public final StringPath password = createString("password");

    public final ListPath<study.graduate.domain.post.PostEntity, study.graduate.domain.post.QPostEntity> posts = this.<study.graduate.domain.post.PostEntity, study.graduate.domain.post.QPostEntity>createList("posts", study.graduate.domain.post.PostEntity.class, study.graduate.domain.post.QPostEntity.class, PathInits.DIRECT2);

    public final StringPath userEmail = createString("userEmail");

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final StringPath userName = createString("userName");

    public QUserEntity(String variable) {
        super(UserEntity.class, forVariable(variable));
    }

    public QUserEntity(Path<? extends UserEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserEntity(PathMetadata metadata) {
        super(UserEntity.class, metadata);
    }

}

