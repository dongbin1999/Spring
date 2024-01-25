package study.graduate.presentation.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.graduate.appllication.user.UserService;
import study.graduate.dto.user.UserJoinRequestDTO;
import study.graduate.dto.user.UserUpdateRequestDTO;

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
        return ResponseEntity.ok().body(userService.readUser(userId));
    }

    @GetMapping("/{userId}/posts")
    public ResponseEntity<?> findPosts(@PathVariable Long userId){
        return ResponseEntity.ok().body(userService.readPosts(userId));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("")
    //@RequestBody를 붙여줘야 Json 데이터를 잘 넘겨받을수있다. 직렬화/역직렬화 공부하기.
    public ResponseEntity<?> joinUser(@RequestBody UserJoinRequestDTO userJoinRequestDTO){
        userService.join(userJoinRequestDTO);
        return ResponseEntity.ok().build();
    }

    //나중에 백준사이트처럼 만들어보자.
    @PatchMapping("/modify")
    public ResponseEntity<?> updateUser(@RequestBody UserUpdateRequestDTO userUpdateRequestDTO){
        userService.updateUser(userUpdateRequestDTO);
        return ResponseEntity.ok().build();
    }
}