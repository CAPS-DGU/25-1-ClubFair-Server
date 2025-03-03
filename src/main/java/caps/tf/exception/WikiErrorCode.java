package caps.tf.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum WikiErrorCode implements caps.tf.exception.ErrorCode {
    INVALID_COLLEGE_NAME(HttpStatus.BAD_REQUEST, "WIKI_001", "유효하지 않는 단과대명입니다."),
    INVALID_DEPARTMENT_NAME(HttpStatus.BAD_REQUEST, "WIKI_002", "유효하지 않는 학과명입니다."),
    NOT_FOUND_WIKI(HttpStatus.NOT_FOUND, "WIKI_003", "존재하지 않는 위키입니다."),
    INVALID_PAGE_WIKI(HttpStatus.BAD_REQUEST, "WIKI_004", "유효하지 않는 페이지 넘버입니다."), INVALID_QUERY_PARAMETER(HttpStatus.BAD_REQUEST, "WIKI_004", "유효하지 않는 쿼리 파라미터입니다.")
    ;

    private final HttpStatus status;
    private final String errorCode;
    private final String message;
}

