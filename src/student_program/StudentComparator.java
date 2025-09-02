package student_program;

import java.io.Serializable;
import java.util.Comparator;

public class StudentComparator implements Comparator<Student>, Serializable {

    @Override
    public int compare(Student o1, Student o2) {
        //o1이 클 때 양수
        int cmp = Double.compare(o1.getAverage(), o2.getAverage()); //오름차순 정렬
        return (cmp != 0) ? cmp : o1.getName().compareTo(o2.getName()); //두 값이 같은 경우 이름 기준 정렬
    }
}
