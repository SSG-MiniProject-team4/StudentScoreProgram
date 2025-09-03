import java.io.*;
import java.util.*;

public class SortedStudent {

    private static HashMap<String, Student> studentInfo;
    private static final File inputFile = new File("../student.dat");
    private static final File outputFile = new File("../orderByAvg.dat");

    public static void main(String[] args) {
        if (loadObjectFromFile()) {
            TreeSet<Student> sortedStudents = createTreeSet();
            printResult(sortedStudents);
            outputObject(sortedStudents);
        }
    }

    @SuppressWarnings("unchecked")
    private static boolean loadObjectFromFile() {
        if (!inputFile.exists()) {
            System.out.println("[오류] " + inputFile.getName() + " 파일이 존재하지 않습니다. StudentInput을 먼저 실행해주세요.");
            return false;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(inputFile))) {
            studentInfo = (HashMap<String, Student>) ois.readObject();
            return true;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("[오류] 파일을 읽는 중 문제가 발생했습니다: " + e.getMessage());
            return false;
        }
    }

    private static TreeSet<Student> createTreeSet() {
        Comparator<Student> comparator = Comparator
                .comparingDouble(Student::getAverage)
                .thenComparing(Student::getName);

        TreeSet<Student> sortedSet = new TreeSet<>(comparator);

        sortedSet.addAll(studentInfo.values());

        return sortedSet;
    }

    private static void printResult(TreeSet<Student> sortedData) {
        System.out.println("\n[정렬 및 저장: 평균 오름차순]");
        System.out.println("불러온 학생 수: " + studentInfo.size());
        System.out.println("정렬 규칙: 평균 ASC, 평균 동률이면 이름 사전순 ASC\n");
        System.out.println("저장 대상(미리보기):");

        sortedData.forEach(student ->
                System.out.printf("- %-10s (평균 %.1f)\n", student.getName(), student.getAverage())
        );
    }

    private static void outputObject(TreeSet<Student> sortedData) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(outputFile))) {
            oos.writeObject(sortedData);
            System.out.println("\n결과 파일: " + outputFile.getPath());
            System.out.println("[완료] 정렬된 결과를 파일로 저장했습니다.");
        } catch (IOException e) {
            System.out.println("[오류] 파일 저장 중 문제가 발생했습니다: " + e.getMessage());
        }
    }
}