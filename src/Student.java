import lombok.Getter;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private List<Integer> record;
    private int total;
    private double average;
    private String grade;

    public Student(String name, List<Integer> record) {
        this.name = name;
        this.record = record;
        calculate(); // 객체 생성 시 총점, 평균, 학점 계산
    }

    private void calculate() {
        this.total = record.stream().mapToInt(Integer::intValue).sum();

        this.average = (double) total / record.size();

        if (average >= 90) {
            this.grade = "A";
        } else if (average >= 80) {
            this.grade = "B";
        } else if (average >= 70) {
            this.grade = "C";
        } else if (average >= 60) {
            this.grade = "D";
        } else {
            this.grade = "F";
        }
    }

    @Override
    public String toString() {
        String recordStr = record.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));

        return String.format("   점수: [%s]\n   총점: %d, 평균: %.1f, 학점: %s",
                recordStr, total, average, grade);
    }
}