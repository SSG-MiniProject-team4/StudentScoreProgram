package student_program.common;

public enum StudentText {
    TITLE("[학생 성적 입력 프로그램]"),
    EXIT_INPUT_MSG("- 종료하려면 이름에 ^^ 를 입력하세요."),
    VALID_SCORE("- 점수는 0~100 사이의 정수만 허용됩니다."),
    NAME("\n이름 : "),
    EXIT_MSG("^^"),
    KOR("국어: "),
    ENG("영어: "),
    MAT("수학: "),
    SCI("과학: "),

    EXIT("exit"),
    INPUT_END("입력을 종료합니다."),
    NAME_EXIST("[오류] 이미 존재하는 이름입니다. 다른 이름을 입력하세요."),

    A("A"),
    B("B"),
    C("C"),
    D("D"),
    F("F"),
    WRONG_NUM("점수 입력이 잘못되었습니다."),

    ASC_SCORE("\n[평균 오름차순 성적표]\n"),
    SCORE("점수: "),
    TOTAL("총점: "),
    AVG(", 평균: "),
    GRADE(", 학점: "),
    SORT_AVG_MSG("평균 "),

    L_PAR(" ("),
    R_PAR(" ) "),
    SIGN("- "),

    SORT_MSG("[정렬 및 저장: 평균 오름차순]"),
    STUDENT_NUM("불러온 학생 수: "),
    SORT_RULE("정렬 규칙: 평균 ASC, 평균 동률이면 이름 사전순 ASC \n"),
    SORT_PREVIEW("저장 대상(미리보기 상위 10명): "),

    RESULT_FILE("결과 파일: "),
    RESULT_MSG("[완료] 정렬된 결과를 파일로 저장했습니다.");

    private final String text;

    StudentText (String text){
        this.text = text;
    }

    public String getText(){
        return text;
    }
}
