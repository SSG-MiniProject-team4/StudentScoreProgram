import java.io.*;
import java.util.*;

public class StudentInput {

    // 데이터를 관리할 HashMap (Key: 학생 이름, Value: Student 객체)
    private static HashMap<String, Student> studentInfo = new HashMap<>();
    // 저장할 파일 객체
    private static final File file = new File("../student.dat");

    public static void main(String[] args) {
        loadCheck(); // 1. 기존 파일 로드 또는 신규 생성
        printUsage(); // 2. 사용법 안내
        checkKeyAndInputData(); // 3. 데이터 입력
        saveData(); // 4. 파일에 저장
    }

    @SuppressWarnings("unchecked")
    private static void loadCheck() {
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                studentInfo = (HashMap<String, Student>) ois.readObject();
                System.out.println("[알림] 기존 " + file.getName() + " 파일을 로드했습니다. " + studentInfo.size() + "명의 데이터가 존재합니다.");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("[오류] 기존 파일 로드에 실패했습니다. 새롭게 시작합니다.");
                studentInfo = new HashMap<>(); // 실패 시 초기화
            }
        } else {
            System.out.println("[알림] 기존 데이터 파일이 없어 새로 시작합니다.");
            studentInfo = new HashMap<>();
        }
    }

    private static void printUsage() {
        System.out.println("\n[학생 성적 입력 프로그램]");
        System.out.println("- 종료하려면 이름에 ^^ 를 입력하세요.");
        System.out.println("- 점수는 0~100 사이의 정수만 허용됩니다.\n");
    }

    private static void checkKeyAndInputData() {
        Scanner scanner = new Scanner(System.in);
        String[] subjects = {"국어", "영어", "수학", "과학"};

        while (true) {
            System.out.print("이름: ");
            String name = scanner.nextLine();

            if (name.equals("^^")) {
                System.out.println("입력을 종료합니다.");
                break;
            }

            if (studentInfo.containsKey(name)) {
                System.out.println("[오류] 이미 존재하는 이름입니다. 다른 이름을 입력하세요.\n");
                continue;
            }

            try {
                List<Integer> scores = new ArrayList<>();
                for (String subject : subjects) {
                    System.out.printf("%s: ", subject);
                    int score = Integer.parseInt(scanner.nextLine());
                    if (score < 0 || score > 100) {
                        throw new IllegalArgumentException("점수는 0에서 100 사이여야 합니다.");
                    }
                    scores.add(score);
                }

                Student student = new Student(name, scores);
                studentInfo.put(name, student);
                System.out.printf("=> 저장됨: %s (총점=%d, 평균=%.1f, 학점=%s)\n\n",
                        name, student.getTotal(), student.getAverage(), student.getGrade());

            } catch (NumberFormatException e) {
                System.out.println("[오류] 유효한 정수 점수를 입력하세요. 처음부터 다시 입력합니다.\n");
            } catch (IllegalArgumentException e) {
                System.out.println("[오류] " + e.getMessage() + " 처음부터 다시 입력합니다.\n");
            }
        }
        scanner.close();
    }

    private static void saveData() {
        // 파일이 위치할 디렉토리가 없으면 생성
        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(studentInfo);
            System.out.printf("[완료] %d명의 정보가 %s 에 저장되었습니다.\n", studentInfo.size(), file.getName());
        } catch (IOException e) {
            System.out.println("[오류] 파일 저장 중 문제가 발생했습니다: " + e.getMessage());
        }
    }
}