package caps.tf.security.handler.login;

import caps.tf.domain.jwt.RefreshToken;
import caps.tf.dto.jwt.response.JwtDto;
import caps.tf.repository.RefreshTokenRepository;
import caps.tf.security.info.AuthenticationResponse;
import caps.tf.security.info.UserPrincipal;
import caps.tf.util.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class DefaultSuccessHandler implements AuthenticationSuccessHandler {
    @Value("${server.cookie-domain}")
    private String cookieDomain;

    private final JwtUtil jwtUtil;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    @Transactional
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        JwtDto jwtDto = jwtUtil.generateTokens(userPrincipal.getUserId(), userPrincipal.getRole());

        refreshTokenRepository.save(
                RefreshToken.issueRefreshToken(userPrincipal.getUserId(), jwtDto.refreshToken())
        );

        AuthenticationResponse.makeLoginSuccessResponse(response, cookieDomain, jwtDto, jwtUtil.getRefreshExpiration());
    }
}
