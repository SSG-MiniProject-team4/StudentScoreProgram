package student_program;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import student_program.common.StudentText;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Student implements Serializable{
    private static final long serialVersionUID = 1L;
    private String name;
    private int total;
    private double average;
    private String grade;
    private List<Integer> record;

    public Student(String name){
        this.name = name;
        total = 0;
        average = 0.0;
        grade = "";
        record = new ArrayList<>();
    }

    @Override
    public String toString() {
        return name + StudentText.L_PAR.getText() + StudentText.TOTAL.getText() + total +
                StudentText.AVG.getText() + String.format("%.1f", average) +
                StudentText.GRADE.getText() + grade + ")";
    }
}
