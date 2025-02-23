package caps.tf.controller;

import caps.tf.dto.auth.AuthSignUpDto;
import caps.tf.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(
            @RequestBody AuthSignUpDto authSignUpDto
    ) {
        authService.signUp(authSignUpDto);
        return ResponseEntity.ok().build();
    }
}
