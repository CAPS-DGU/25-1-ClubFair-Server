package caps.tf.security.handler.logout;

import caps.tf.exception.UserErrorCode;
import caps.tf.security.info.AuthenticationResponse;
import caps.tf.util.CookieUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Value("${server.cookie-domain}")
    private String cookieDomain;
  
    public void onLogoutSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {
        if (authentication == null) {
            log.info("인증 정보가 존재하지 않습니다. authentication is null.");
            AuthenticationResponse.makeFailureResponse(response, UserErrorCode.NOT_FOUND_USER);
        }
        CookieUtil.logoutCookie(request, response, cookieDomain);
        AuthenticationResponse.makeSuccessResponse(response);
    }
}
