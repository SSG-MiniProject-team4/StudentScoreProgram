package TeamMission1;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

@Data
@AllArgsConstructor
public class Student implements Serializable{
    private static final long serialVersionUID = 1;
    private String name;
    private int total;
    private double average;
    private String grade;
    private List<Integer> record;

    public Student(){
        name = "^^";
        total = 0;
        average = 0.0;
        grade = "";
    }

    public String toString(){
        return "점수: "+record+"\n총점: "+total+", 평균: "+average+", 학점: "+grade;
    }

}