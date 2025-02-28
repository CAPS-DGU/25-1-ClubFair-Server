package caps.tf.security.handler.logout;

import caps.tf.constants.Constants;
import caps.tf.exception.CommonException;
import caps.tf.exception.GlobalErrorCode;
import caps.tf.service.jwt.JwtService;
import caps.tf.util.HeaderUtil;
import caps.tf.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomLogoutHandler implements LogoutHandler {
    private final JwtUtil jwtUtil;
    private final JwtService jwtService;
    @Override
    @Transactional
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {
        if (authentication == null) {
            log.error("Authentication object is null");
            throw CommonException.type(GlobalErrorCode.UNAUTHORIZED);
        }

        log.info("Performing logout for user: {}", authentication.getPrincipal());
        // 로그아웃 로직 수행
        String accessToken = HeaderUtil.refineHeader(request, Constants.PREFIX_AUTH, Constants.PREFIX_BEARER)
                .orElseThrow(() -> CommonException.type(GlobalErrorCode.INVALID_HEADER_VALUE));

        Claims claims = jwtUtil.validateToken(accessToken);
        jwtService.deleteRefreshToken(UUID.fromString(claims.get(Constants.CLAIM_USER_ID, String.class)));
    }
}
