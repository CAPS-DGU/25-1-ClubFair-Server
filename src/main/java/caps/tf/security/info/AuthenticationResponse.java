package caps.tf.security.info;

import caps.tf.constants.Constants;
import caps.tf.dto.jwt.response.JwtDto;
import caps.tf.exception.ErrorCode;
import caps.tf.exception.GlobalErrorCode;
import caps.tf.util.CookieUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONValue;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class AuthenticationResponse {
    public static void makeLoginSuccessResponse(
            HttpServletResponse response,
            String domain,
            JwtDto jwtDto,
            Integer refreshExpiration
    ) throws IOException {
        CookieUtil.addCookie(
                response,
                domain,
                Constants.ACCESS_COOKIE_NAME,
                jwtDto.accessToken()
        );
        CookieUtil.addSecureCookie(
                response,
                domain,
                Constants.REFRESH_COOKIE_NAME,
                jwtDto.refreshToken(),
                refreshExpiration
        );
        log.info("Access Token Cookie: {}", jwtDto.accessToken());
        log.info("Refresh Token Cookie: {}", jwtDto.refreshToken());
        makeSuccessResponse(response);
    }

    public static void makeSuccessResponse(HttpServletResponse response) throws IOException {

        ErrorCode successCode = GlobalErrorCode.SUCCESS;
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(successCode.getStatus().value());

        Map<String, Object> body = new HashMap<>();
        body.put("errorCode", successCode.getErrorCode());
        body.put("message", successCode.getMessage());
        body.put("result", null);

        response.getWriter().write(JSONValue.toJSONString(body));
    }

    public static void makeFailureResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(errorCode.getStatus().value());

        Map<String, Object> body= new HashMap<>();
        body.put("errorCode", errorCode.getErrorCode());
        body.put("message", errorCode.getMessage());
        body.put("result", null);

        response.getWriter().write(JSONValue.toJSONString(body));
    }
}
