package user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

@Controller
//simple logging facade for java.
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
}
