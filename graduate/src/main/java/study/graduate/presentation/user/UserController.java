package study.graduate.presentation.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.graduate.appllication.user.UserService;
import study.graduate.domain.user.UserEntity;

@RestController
//simple logging facade for java.
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> findUser(@PathVariable Long userId){
        //http body에다가 userService메소드의 반환값을 json형태로 넘겨준다.
        return ResponseEntity.ok().body(userService.findById(userId));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("")
    public ResponseEntity<?> joinUser(UserEntity userEntity){
        userService.join(userEntity);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<?> updateUser(UserEntity userEntity){
        userService.join(userEntity);
        return ResponseEntity.ok().build();
    }
}