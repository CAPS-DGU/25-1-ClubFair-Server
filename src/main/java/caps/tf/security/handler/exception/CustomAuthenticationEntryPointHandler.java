package caps.tf.security.handler.exception;

import caps.tf.exception.ErrorCode;
import caps.tf.exception.GlobalErrorCode;
import caps.tf.security.info.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPointHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authenticationException) throws IOException {
        ErrorCode errorCode = (ErrorCode) request.getAttribute("exception");
        if (errorCode == null) {
            AuthenticationResponse.makeFailureResponse(response, GlobalErrorCode.VALIDATION_ERROR);
            return;
        }
        AuthenticationResponse.makeFailureResponse(response, errorCode);
    }
}

