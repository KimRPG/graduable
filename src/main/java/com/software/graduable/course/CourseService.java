package com.software.graduable.course;

import com.software.graduable.grade.GradeEntity;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseJPA courseJPA;

    public List<Course> findAll() {
        return courseJPA.findAll();
    }

    public Long addCourseAtGrade(GradeEntity grade){
        Course course = new Course(grade.getCourseName(), grade.getCourseCode(), grade.getCategory(), grade.getCredit(), grade.getClassification());
        courseJPA.save(course);
        return course.getCourseId();
    }

    @PostConstruct
    public void insertAllData(){
        List<Course> courseList = new ArrayList<>();

        // 신앙
        courseList.add(new Course("채플(한국어) 1", "GEK10001", "교필", 0.0, "신앙및세계관"));
        courseList.add(new Course("채플(한국어) 2", "GEK10002", "교필", 0.0, "신앙및세계관"));
        courseList.add(new Course("채플(한국어) 3", "GEK20001", "교필", 0.0, "신앙및세계관"));
        courseList.add(new Course("채플(한국어) 4", "GEK20002", "교필", 0.0, "신앙및세계관"));
        courseList.add(new Course("채플(한국어) 5", "GEK30001", "교필", 0.0, "신앙및세계관"));
        courseList.add(new Course("채플(한국어) 6", "GEK30002", "교필", 0.0, "신앙및세계관"));

        courseList.add(new Course("채플(한국어) 7", "GEK40001", "교선", 1.0, "신앙및세계관"));
        courseList.add(new Course("채플(한국어) 8", "GEK40002", "교선", 1.0, "신앙및세계관"));

        courseList.add(new Course("성경의 이해", "GEK20058", "교선필", 2.0, "신앙및세계관"));
        courseList.add(new Course("성경과 삶", "GEK20063", "교선필", 2.0, "신앙및세계관"));
        courseList.add(new Course("성경과 영적 성장", "GEK20064", "교선필", 2.0, "신앙및세계관"));

        courseList.add(new Course("기독교의 이해", "GEK20059", "교선필", 2.0, "신앙및세계관"));
        courseList.add(new Course("기독교와 비교종교", "GEK20065", "교선필", 2.0, "신앙및세계관"));
        courseList.add(new Course("기독교와 포스트모더니즘", "GEK20060", "교선필", 2.0, "신앙및세계관"));

        courseList.add(new Course("창조와 진화", "GEK10011", "교선필", 2.0, "신앙및세계관"));
        courseList.add(new Course("그리스도인과 선교", "GEK20069", "교선필", 2.0, "신앙및세계관"));
        courseList.add(new Course("기독교세계관", "GEK20011", "교선필", 2.0, "신앙및세계관"));

        courseList.add(new Course("기독교와 현대사상", "GEK30014", "교선필", 3.0, "신앙및세계관"));
        courseList.add(new Course("교회사의 이해", "GEK20019", "교선필", 3.0, "신앙및세계관"));
        courseList.add(new Course("현대사회와 선교", "GEK30015", "교선필", 3.0, "신앙및세계관"));
        courseList.add(new Course("기독교윤리학", "GEK20067", "교선필", 3.0, "신앙및세계관"));
        courseList.add(new Course("기독교 변증학", "GEK20062", "교선필", 3.0, "신앙및세계관"));
        courseList.add(new Course("전공과 신앙", "GEK20066", "교선필", 3.0, "신앙및세계관"));
        courseList.add(new Course("신앙특론", "GEK20016", "교선필", 3.0, "신앙및세계관")); // 1~3학점 중간값으로 3.0 사용
        courseList.add(new Course("신앙과 학문의 통합", "GEK20035", "교선필", 3.0, "신앙및세계관"));
        courseList.add(new Course("기원과학 논쟁 탐구", "GEK20068", "교선필", 3.0, "신앙및세계관"));
        courseList.add(new Course("성서해석학", "GEK20070", "교선필", 3.0, "신앙및세계관"));

        // 인성및리더십
        courseList.add(new Course("공동체리더십훈련1", "GEK10008", "교필", 0.5, "인성및리더십"));
        courseList.add(new Course("공동체리더십훈련2", "GEK10009", "교필", 0.5, "인성및리더십"));
        courseList.add(new Course("공동체리더십훈련3", "GEK20008", "교필", 0.5, "인성및리더십"));
        courseList.add(new Course("공동체리더십훈련4", "GEK20009", "교필", 0.5, "인성및리더십"));
        courseList.add(new Course("공동체리더십훈련5", "GEK30008", "교필", 0.5, "인성및리더십"));
        courseList.add(new Course("공동체리더십훈련6", "GEK30009", "교필", 0.5, "인성및리더십"));
        courseList.add(new Course("공동체리더십훈련7", "GEK40008", "교선", 0.5, "인성및리더십"));
        courseList.add(new Course("공동체리더십훈련8", "GEK40009", "교선", 0.5, "인성및리더십"));

        courseList.add(new Course("한동인성교육", "GEK10015", "교필", 1.0, "인성및리더십"));

        courseList.add(new Course("사회봉사 1", "GEK10046", "교선필", 1.0, "인성및리더십"));
        courseList.add(new Course("사회봉사 2", "GEK20046", "교선필", 1.0, "인성및리더십"));
        courseList.add(new Course("사회봉사 3", "GEK20047", "교선필", 1.0, "인성및리더십"));
        courseList.add(new Course("사회봉사 4", "GEK30047", "교선필", 1.0, "인성및리더십"));
        courseList.add(new Course("사회봉사 5", "GEK30048", "교선", 1.0, "인성및리더십"));

        // 실무영어
        courseList.add(new Course("English Foundation", "GCS10052", "교선필", 3.0, "실무영어"));
        courseList.add(new Course("English Communication", "GCS10053", "교선필", 3.0, "실무영어"));
        courseList.add(new Course("English Reading and Composition", "GCS20003", "교선필", 3.0, "실무영어"));
        courseList.add(new Course("EAP", "GCS30016", "교선필", 3.0, "실무영어"));

        // 전문교양
        courseList.add(new Course("창의적 문제해결 리더십", "GEK10077", "교선필", 2.0, "전문교양"));
        courseList.add(new Course("기독교 세계관", "GEK20011", "교선필", 2.0, "전문교양"));
        courseList.add(new Course("공학윤리", "GEK20043", "교선필", 3.0, "전문교양"));
        courseList.add(new Course("현대과학과 기술의 철학", "GEK30030", "교선필", 3.0, "전문교양"));
        courseList.add(new Course("철학개론", "GEK10030", "교선필", 3.0, "전문교양"));
        courseList.add(new Course("한국사(근현대사)", "GEK10035", "교선필", 3.0, "전문교양"));
        courseList.add(new Course("사회학개론", "GEK10040", "교선필", 3.0, "전문교양"));
        courseList.add(new Course("경영학입문", "MEC10002", "교선필", 3.0, "전문교양"));
        courseList.add(new Course("경제학입문", "MEC10001", "교선필", 3.0, "전문교양"));
        courseList.add(new Course("Cross-cultural Global Perspectives", "GEE20034", "교선필", 3.0, "전문교양"));
        courseList.add(new Course("이공계글쓰기", "GCS10011", "교선필", 3.0, "전문교양"));
        courseList.add(new Course("심리학개론", "CSW10003", "교선필", 3.0, "전문교양"));

        // BSM
        courseList.add(new Course("물리학 개론", "GEK10090", "교선필", 3.0, "BSM"));
        courseList.add(new Course("물리학 1", "GEK10055", "교선필", 3.0, "BSM"));
        courseList.add(new Course("물리학 2", "GEK10056", "교선필", 3.0, "BSM"));
        courseList.add(new Course("물리학실험1", "GEK10038", "교선필", 1.0, "BSM"));
        courseList.add(new Course("일반생물학", "GEK10057", "교선필", 3.0, "BSM"));
        courseList.add(new Course("일반화학", "GEK10058", "교선필", 3.0, "BSM"));
        courseList.add(new Course("일반화학실험", "GEK10094", "교선필", 1.0, "BSM"));
        courseList.add(new Course("Calculus 1", "GEK10095", "교선필", 3.0, "BSM"));
        courseList.add(new Course("Calculus 2", "GEK10096", "교선필", 3.0, "BSM"));
        courseList.add(new Course("Calculus 3", "GEK10097", "교선필", 3.0, "BSM"));
        courseList.add(new Course("미분방정식과 응용", "GEK10053", "교선필", 3.0, "BSM"));
        courseList.add(new Course("공학수학", "GEK10081", "교선필", 3.0, "BSM"));
        courseList.add(new Course("이산수학", "ECE20042", "교선필", 3.0, "BSM"));
        courseList.add(new Course("선형대수학", "GEK10082", "교선필", 3.0, "BSM"));
        courseList.add(new Course("통계학", "GEK20053", "교선필", 3.0, "BSM"));
        courseList.add(new Course("정수론", "CCE30023", "교선필", 3.0, "BSM"));
        courseList.add(new Course("실해석학개론", "CCE20011", "교선필", 3.0, "BSM"));

        // ICT융합기초
        courseList.add(new Course("소프트웨어 입문", "GCS10001", "교선필", 2.0, "ICT융합기초"));
        courseList.add(new Course("R 을 이용한 빅데이터 분석", "GCS10080", "교선필", 3.0, "ICT융합기초"));
        courseList.add(new Course("파이썬 프로그래밍", "GCS10004", "교선필", 3.0, "ICT융합기초"));
        courseList.add(new Course("AI 데이터입문", "GCS20012", "교선필", 3.0, "ICT융합기초"));
        courseList.add(new Course("C 프로그래밍", "GCS10058", "전공", 3.0, "ICT융합기초"));
        courseList.add(new Course("C 프로그래밍(전산전자)", "ECE10002", "전공", 3.0, "ICT융합기초"));

        // 자유선택(교양)
        courseList.add(new Course("오피스활용", "GCS10007", "교선필", 2.0, "자유선택(교양)"));
        courseList.add(new Course("대학 글쓰기 기초", "GCS10010", "교선필", 3.0, "자유선택(교양)"));
        courseList.add(new Course("인문사회계 글쓰기", "GCS10012", "교필", 3.0, "자유선택(교양)"));
        courseList.add(new Course("Discussion and Presentation", "GCS10013", "교선필", 3.0, "자유선택(교양)"));
        courseList.add(new Course("논리와 비판적 사고", "GCS10014", "교선필", 3.0, "자유선택(교양)"));
        courseList.add(new Course("토론과 발표", "GCS10015", "교선필", 3.0, "자유선택(교양)"));
        courseList.add(new Course("독일어 1", "GCS10019", "교선", 3.0, "자유선택(교양)"));
        courseList.add(new Course("스페인어1", "GCS10027", "교선", 3.0, "자유선택(교양)"));
        courseList.add(new Course("일본어1", "GCS10033", "교선", 3.0, "자유선택(교양)"));
        courseList.add(new Course("일본어2", "GCS10034", "교선", 3.0, "자유선택(교양)"));
        courseList.add(new Course("중국어1", "GCS10037", "교선", 3.0, "자유선택(교양)"));
        courseList.add(new Course("한국어 1", "GCS10054", "교선필", 3.0, "자유선택(교양)"));
        courseList.add(new Course("한국어 2", "GCS10055", "교선필", 3.0, "자유선택(교양)"));
        courseList.add(new Course("한국어 3", "GCS10056", "교선필", 3.0, "자유선택(교양)"));
        courseList.add(new Course("한국어 4", "GCS10057", "교선필", 3.0, "자유선택(교양)"));
        courseList.add(new Course("중국 문화와 언어", "GCS10066", "교선필", 3.0, "자유선택(교양)"));
        courseList.add(new Course("R을 이용한 빅데이터 분석", "GCS10080", "교선필", 3.0, "자유선택(교양)"));
        courseList.add(new Course("프론트엔드입문", "GCS10081", "교선필", 2.0, "자유선택(교양)"));
        courseList.add(new Course("데이타통계처리", "GCS20005", "교선필", 2.0, "자유선택(교양)"));
        courseList.add(new Course("컴퓨터그래픽 기초", "GCS20008", "교선", 2.0, "자유선택(교양)"));
        courseList.add(new Course("하이브리드웹 설계", "GCS20009", "교선필", 2.0, "자유선택(교양)"));
        courseList.add(new Course("생활 속의 AI", "GCS20013", "교선필", 3.0, "자유선택(교양)"));
        courseList.add(new Course("생성형AI를 활용한 문제해결 프로그래밍", "GCS20014", "교선필", 3.0, "자유선택(교양)"));
        courseList.add(new Course("한국어5", "GCS20054", "교선필", 3.0, "자유선택(교양)"));
        courseList.add(new Course("한국어6", "GCS20055", "교선필", 3.0, "자유선택(교양)"));
        courseList.add(new Course("한국어5", "GCS20054", "교선필", 3.0, "자유선택(교양)"));
        courseList.add(new Course("한국어6", "GCS20055", "교선필", 3.0, "자유선택(교양)"));
        courseList.add(new Course("Essentials of English Communication", "GCS40001", "교선", 3.0, "자유선택(교양)"));
        courseList.add(new Course("교양독서", "GEK10007", "교선필", 1.0, "자유선택(교양)"));
        courseList.add(new Course("한동인성교육", "GEK10015", "교필", 1.0, "자유선택(교양)"));
        courseList.add(new Course("역사와 인간", "GEK10039", "교선필", 3.0, "자유선택(교양)"));
        courseList.add(new Course("과학기술과 인간정신", "GEK10032", "교선필", 3.0, "자유선택(교양)"));
        courseList.add(new Course("한국문학의이해", "GEK10033", "교선필", 3.0, "자유선택(교양)"));



        // 전공주제(AI컴퓨터심화)
        courseList.add(new Course("컴퓨터 및 전자공학개론", "ECE10004", "전선", 2.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("데이타구조", "ECE20010", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("자바프로그래밍언어", "ECE20016", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("프로그래밍 스튜디오", "ECE20025", "전선", 2.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("논리설계", "ECE20057", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("회로이론", "ECE20064", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("기초회로 및 논리실습", "ECE20065", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("모바일 앱 개발", "ECE30002", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("AI개론", "ECE30008", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("알고리듬분석", "ECE30011", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("객체지향 설계패턴", "ECE30012", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("운영체제", "ECE30021", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("직업과 진로설계(전산전자)", "ECE30039", "전선", 1.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("전자회로 1", "ECE30051", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("전자회로 및 통신실습", "ECE30054", "전선", 2.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("반도체소자", "ECE30063", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("마이크로프로세서응용", "ECE30070", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("캡스톤디자인1", "ECE30079", "전선", 2.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("통신이론", "ECE30083", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("RF회로 설계", "ECE40008", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("지역혁신프로젝트(컴퓨터공학)", "ECE40018", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("포스트 캡스톤 연구", "ECE40027", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("딥러닝 개론", "ECE40035", "전선", 2.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("컴퓨터그래픽스", "ECE40042", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("IoT실습", "ECE40066", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("캡스톤디자인2", "ECE40079", "전선", 4.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("디지털통신", "ECE40081", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("디지털신호처리", "ECE40082", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("Data Structures", "ITP20001", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("Java Programming", "ITP20003", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("Logic Design", "ITP20007", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("Operating Systems", "ITP30002", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("Algorithms Analysis", "ITP30005", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("Database System", "ITP30010", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("Software Engineering", "ITP40002", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("Compiler Theory", "ITP40004", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("C 프로그래밍(전산전자)", "ECE10002", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("코딩 스튜디오", "ECE10005", "전선", 1.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("공학설계입문", "ECE10020", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("신호 및 시스템", "ECE20006", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("웹 서비스 개발", "ECE20009", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("자바프로그래밍언어", "ECE20016", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("오픈소스 스튜디오", "ECE20026", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("시스템프로그래밍", "ECE20027", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("전자기학", "ECE20061", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("디지털시스템설계", "ECE20063", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("기초신호처리실습", "ECE20066", "전선", 2.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("문제해결 스튜디오", "ECE30018", "전선", 2.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("전자회로2", "ECE30052", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("회로설계 및 제작", "ECE30055", "전선", 2.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("지능로봇제어", "ECE30078", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("컴퓨터네트워크", "ECE30086", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("확률변수론", "ECE30087", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("지능형 신호처리", "ECE40013", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("실전 스튜디오", "ECE40014", "전선", 1.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("컴퓨터보안", "ECE40044", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("집적회로설계", "ECE40052", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("반도체공정과 나노기술", "ECE40065", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("C Programming", "ITP10003", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("Discrete Mathematics", "ITP20002", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("Web Development Programming", "ITP20006", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("Computer Vision", "ITP20010", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("Computer Architecture and Organization", "ITP30003", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("Programming Language Theory", "ITP30011", "전선", 3.0, "전공주제(AI컴퓨터심화)"));
        courseList.add(new Course("Machine Learning", "ITP40010", "전선", 3.0, "전공주제(AI컴퓨터심화)"));

        courseJPA.saveAll(courseList);
    }

}
