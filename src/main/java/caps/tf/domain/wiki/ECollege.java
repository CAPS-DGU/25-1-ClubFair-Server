package caps.tf.domain.wiki;

import caps.tf.exception.CommonException;
import caps.tf.exception.WikiErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum ECollege {
    BUDDHIST_COLLEGE("불교대학"),
    LIBERAL_ARTS_COLLEGE("문과대학"),
    SCIENCE_COLLEGE("이과대학"),
    LAW_COLLEGE("법과대학"),
    SOCIAL_SCIENCE_COLLEGE("사회과학대학"),
    POLICE_JUDICIAL_COLLEGE("경찰사법대학"),
    BUSINESS_COLLEGE("경영대학"),
    ENGINEERING_COLLEGE("공과대학"),
    EDUCATION_COLLEGE("사범대학"),
    ARTS_COLLEGE("예술대학"),
    FUTURE_CONVERGENCE_COLLEGE("미래융합대학"),
    ADVANCED_CONVERGENCE_COLLEGE("첨단융합대학"),
    PHARMACY_COLLEGE("약학대학"),
    BIO_SYSTEMS_COLLEGE("바이오시스템대학"),
    OPEN_MAJOR_COLLEGE("열린전공학부");

    private final String name;

    public static ECollege fromCollege(String collegeName) {
        return Arrays.stream(ECollege.values())
                .filter(eCollege -> eCollege.getName().equals(collegeName))
                .findFirst()
                .orElseThrow(() -> CommonException.type(WikiErrorCode.INVALID_COLLEGE_NAME));
    }
}