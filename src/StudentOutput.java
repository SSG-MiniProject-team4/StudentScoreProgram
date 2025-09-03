import java.io.*;
import java.util.*;

public class StudentOutput {

    private static HashMap<String, Student> studentInfo;
    private static final File file = new File("../student.dat");

    public static void main(String[] args) {
        if (loadObjectFromFile()) { // 1. 파일 로드
            List<Student> sortedList = rearrangeData(); // 2. 데이터 정렬
            printInfo(sortedList); // 3. 정렬 결과 출력
        }
    }

    @SuppressWarnings("unchecked")
    private static boolean loadObjectFromFile() {
        if (!file.exists()) {
            System.out.println("[오류] " + file.getName() + " 파일이 존재하지 않습니다. StudentInput을 먼저 실행해주세요.");
            return false;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            studentInfo = (HashMap<String, Student>) ois.readObject();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("[오류] 파일을 찾을 수 없습니다.");
            return false;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("[오류] 파일을 읽는 중 문제가 발생했습니다: " + e.getMessage());
            return false;
        }
    }

    private static List<Student> rearrangeData() {
        List<Student> studentList = new ArrayList<>(studentInfo.values());
        studentList.sort(Comparator.comparingDouble(Student::getAverage));

        return studentList;
    }

    private static void printInfo(List<Student> datas) {
        System.out.println("\n[평균 오름차순 성적표]\n");
        int rank = 1;
        for (Student student : datas) {
            System.out.printf("%d) %s\n%s\n\n", rank++, student.getName(), student.toString());
        }
    }
}