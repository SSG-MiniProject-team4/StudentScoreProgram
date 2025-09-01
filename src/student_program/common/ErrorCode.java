package student_program.common;

public enum ErrorCode {
    INPUT_ERROR("입력이 잘못되었습니다."),
    NUMBER_ERROR("숫자를 입력해주세요."),
    ERROR("오류가 발생했습니다."),
    FILE_ERROR("파일을 찾을 수 없습니다."),
    TYPE_ERROR("파일의 자료 타입이 적합하지 않습니다."),
    FILE_INPUT_ERROR("학생 정보를 불러오지 못했습니다."),
    OUTPUT_ERROR("저장이 잘못되었습니다.");

    private final String text;

    ErrorCode(String text){
        this.text = text;
    }

    public String getText(){
        return text;
    }
}
