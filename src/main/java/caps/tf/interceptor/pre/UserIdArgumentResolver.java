package caps.tf.interceptor.pre;

import caps.tf.annotation.UserId;
import caps.tf.exception.CommonException;
import caps.tf.exception.GlobalErrorCode;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.UUID;

@Component
public class UserIdArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(UUID.class)
                && parameter.hasParameterAnnotation(UserId.class);
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) throws Exception {
        final Object userId = webRequest.getAttribute("USER_ID", WebRequest.SCOPE_REQUEST);
        if(userId == null)
            throw CommonException.type(GlobalErrorCode.INVALID_HEADER_VALUE);

        return UUID.fromString(userId.toString());
    }
}
