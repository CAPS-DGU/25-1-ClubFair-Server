package caps.tf.domain.wiki;

import caps.tf.exception.CommonException;
import caps.tf.exception.WikiErrorCode;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum EDepartment {
    // 불교대학
    BUDDHIST_STUDIES("불교학부", ECollege.BUDDHIST_COLLEGE),
    CULTURAL_HERITAGE("문화유산학과", ECollege.BUDDHIST_COLLEGE),

    // 문과대학
    KOREAN_LANGUAGE_LITERATURE("국어국문문예창작학부", ECollege.LIBERAL_ARTS_COLLEGE),
    ENGLISH_LANGUAGE_LITERATURE("영어영문학부", ECollege.LIBERAL_ARTS_COLLEGE),
    JAPANESE_STUDIES("일본학과", ECollege.LIBERAL_ARTS_COLLEGE),
    CHINESE_LANGUAGE_LITERATURE("중어중문학과", ECollege.LIBERAL_ARTS_COLLEGE),
    PHILOSOPHY("철학과", ECollege.LIBERAL_ARTS_COLLEGE),
    HISTORY("사학과", ECollege.LIBERAL_ARTS_COLLEGE),

    // 이과대학
    MATHEMATICS("수학과", ECollege.SCIENCE_COLLEGE),
    CHEMISTRY("화학과", ECollege.SCIENCE_COLLEGE),
    STATISTICS("통계학과", ECollege.SCIENCE_COLLEGE),
    PHYSICS("물리학과", ECollege.SCIENCE_COLLEGE),

    // 법과대학
    LAW_MAJOR("법학과", ECollege.LAW_COLLEGE),

    // 사회과학대학
    POLITICAL_ADMINISTRATION("정치행정학부", ECollege.SOCIAL_SCIENCE_COLLEGE),
    MEDIA_INFORMATION("언론정보학과", ECollege.SOCIAL_SCIENCE_COLLEGE),
    SOCIOLOGY("사회학과", ECollege.SOCIAL_SCIENCE_COLLEGE),
    MEDIA_COMMUNICATION("미디어커뮤니케이션학과", ECollege.SOCIAL_SCIENCE_COLLEGE),
    FOOD_INDUSTRY_MANAGEMENT("식품산업관리학과", ECollege.SOCIAL_SCIENCE_COLLEGE),
    ADVERTISING_PUBLIC_RELATIONS("광고홍보학과", ECollege.SOCIAL_SCIENCE_COLLEGE),
    SOCIAL_WELFARE("사회복지학과", ECollege.SOCIAL_SCIENCE_COLLEGE),

    // 경찰사법대학
    POLICE_ADMINISTRATION("경찰행정학부", ECollege.POLICE_JUDICIAL_COLLEGE),

    // 경영대학
    BUSINESS_ADMIN("경영학과", ECollege.BUSINESS_COLLEGE),
    ACCOUNTING("회계학과", ECollege.BUSINESS_COLLEGE),
    MANAGEMENT_INFORMATION("경영정보학과", ECollege.BUSINESS_COLLEGE),

    // 공과대학
    CIVIL_ENVIRONMENTAL_ENGINEERING("건설환경공학과", ECollege.ENGINEERING_COLLEGE),
    ARCHITECTURE("건축공학부", ECollege.ENGINEERING_COLLEGE),
    MECHANICAL_ROBOTICS_ENERGY("기계로봇에너지공학과", ECollege.ENGINEERING_COLLEGE),
    INDUSTRIAL_SYSTEMS_ENGINEERING("산업시스템공학과", ECollege.ENGINEERING_COLLEGE),
    ENERGY_ENGINEERING("에너지공학과", ECollege.ENGINEERING_COLLEGE),
    MATERIALS_ENGINEERING("신소재공학과", ECollege.ENGINEERING_COLLEGE),
    ELECTRICAL_ELECTRONIC_ENGINEERING("전자전기공학부", ECollege.ENGINEERING_COLLEGE),
    INFORMATION_COMMUNICATION_ENGINEERING("정보통신공학과", ECollege.ENGINEERING_COLLEGE),
    CHEMICAL_BIOMOLECULAR_ENGINEERING("화공생물공학과", ECollege.ENGINEERING_COLLEGE),

    // 사범대학
    EDUCATION_STUDIES("교육학과", ECollege.EDUCATION_COLLEGE),
    KOREAN_EDUCATION("국어교육과", ECollege.EDUCATION_COLLEGE),
    HISTORY_EDUCATION("역사교육과", ECollege.EDUCATION_COLLEGE),
    GEOGRAPHY_EDUCATION("지리교육과", ECollege.EDUCATION_COLLEGE),
    MATHEMATICS_EDUCATION("수학교육과", ECollege.EDUCATION_COLLEGE),
    HOME_ECONOMICS_EDUCATION("가정교육과", ECollege.EDUCATION_COLLEGE),
    PHYSICAL_EDUCATION("체육교육과", ECollege.EDUCATION_COLLEGE),

    // 예술대학
    FINE_ARTS("미술학부", ECollege.ARTS_COLLEGE),
    ORIENTAL_PAINTING("동양화과", ECollege.ARTS_COLLEGE),
    WESTERN_PAINTING("서양화과", ECollege.ARTS_COLLEGE),
    SCULPTURE("조소과", ECollege.ARTS_COLLEGE),
    THEATER("연극학과", ECollege.ARTS_COLLEGE),
    FILM_VIDEO("영화영상학과", ECollege.ARTS_COLLEGE),
    SPORTS_CULTURE("스포츠문화학과", ECollege.ARTS_COLLEGE),
    KOREAN_TRADITIONAL_MUSIC("국악과", ECollege.ARTS_COLLEGE),
    INSTRUMENTAL_MUSIC("기악과", ECollege.ARTS_COLLEGE),
    VOCAL_MUSIC("성악과", ECollege.ARTS_COLLEGE),
    POPULAR_MUSIC("실용음악과", ECollege.ARTS_COLLEGE),

    // 미래융합대학
    CONVERGENCE_SECURITY("융합보안학과", ECollege.FUTURE_CONVERGENCE_COLLEGE),
    SOCIAL_WELFARE_COUNSELING("사회복지상담학과", ECollege.FUTURE_CONVERGENCE_COLLEGE),
    GLOBAL_TRADE("글로벌무역학과", ECollege.FUTURE_CONVERGENCE_COLLEGE),

    // 첨단융합대학
    ARTIFICIAL_INTELLIGENCE("인공지능전공", ECollege.ADVANCED_CONVERGENCE_COLLEGE),
    DATA_SCIENCE("데이터사이언스전공", ECollege.ADVANCED_CONVERGENCE_COLLEGE),
    COMPUTER_ENGINEERING("컴퓨터공학전공", ECollege.ADVANCED_CONVERGENCE_COLLEGE),
    MULTIMEDIA_SOFTWARE_ENGINEERING("멀티미디어소프트웨어공학전공", ECollege.ADVANCED_CONVERGENCE_COLLEGE),
    ENTERTAINMENT_TECHNOLOGY("엔터테인먼트테크놀로지전공", ECollege.ADVANCED_CONVERGENCE_COLLEGE),
    SYSTEM_SEMICONDUCTOR("시스템반도체학부", ECollege.ADVANCED_CONVERGENCE_COLLEGE),

    // 약학대학
    PHARMACY("약학과", ECollege.PHARMACY_COLLEGE),

    // 바이오시스템대학
    BIO_ENVIRONMENTAL_SCIENCE("바이오환경과학과", ECollege.BIO_SYSTEMS_COLLEGE),
    LIFE_SCIENCE("생명과학과", ECollege.BIO_SYSTEMS_COLLEGE),
    FOOD_BIOTECHNOLOGY("식품생명공학과", ECollege.BIO_SYSTEMS_COLLEGE),
    BIOMEDICAL_ENGINEERING("의생명공학과", ECollege.BIO_SYSTEMS_COLLEGE),

    // 열린전공학부
    OPEN_MAJOR("열린전공학부", ECollege.OPEN_MAJOR_COLLEGE);

    private final String name;
    private final ECollege eCollege;

    @JsonValue
    public String getName() {
        return name;
    }

    public ECollege getECollege() { return eCollege; }

    public static ECollege getECollege(EDepartment eDepartment) {
        return Arrays.stream(EDepartment.values())
                .filter(dept -> dept == eDepartment)
                .map(dept -> dept.eCollege)
                .findFirst()
                .orElseThrow(() -> CommonException.type(WikiErrorCode.INVALID_DEPARTMENT_NAME));
    }

    @JsonCreator
    public static EDepartment fromDepartment(String departmentName) {
        return Arrays.stream(EDepartment.values())
                .filter(eDepartment -> eDepartment.getName().equals(departmentName))
                .findFirst()
                .orElseThrow(() -> CommonException.type(WikiErrorCode.INVALID_COLLEGE_NAME));
    }
}