package student_program;

import java.io.Serializable;
import java.util.Comparator;

public class StudentComparator implements Comparator<Student>, Serializable {

    @Override
    public int compare(Student o1, Student o2) {
        int cmp = Double.compare(o2.getAverage(), o1.getAverage());
        return (cmp != 0) ? cmp : o1.getName().compareTo(o2.getName());
    }
}
