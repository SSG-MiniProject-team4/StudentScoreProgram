package TeamMission1;

import java.util.*;
import java.io.*;

public class StudentInput {
    static Scanner sc = new Scanner(System.in);
    static Map<String, Student> studentInfo = new HashMap<>();
    static File file = new File("C:/Temp/student.dat");

    public static void main(String[] args) {
        printUsage();
        loadCheck();
        userInput();
        saveData();
    }

    public static void printUsage(){
        System.out.println("[학생 성적 입력 프로그램]");
        System.out.println("- 종료하려면 이름에 ^^를 입력하세요.");
        System.out.println("- 점수는 0~100 사이의 정수만 허용됩니다.");
    }

    public static void userInput(){
        while(true){
            System.out.print("\n이름: ");
            String name = sc.nextLine();
            if(name.equals("^^")){
                System.out.println("exit\n입력을 종료합니다.");
                return;
            }

            System.out.print("국어: ");
            int subject1 = Integer.parseInt(sc.nextLine());
            System.out.print("영어: ");
            int subject2 = Integer.parseInt(sc.nextLine());
            System.out.print("수학: ");
            int subject3 = Integer.parseInt(sc.nextLine());
            System.out.print("과학: ");
            int subject4 = Integer.parseInt(sc.nextLine());

            List<Integer> grades = Arrays.asList(subject1, subject2, subject3, subject4);
            checkKeyAndInputData(name, grades);
        }
    }

    public static void loadCheck() {
        if(!file.exists()){
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
                studentInfo = new HashMap<>();
            }catch(IOException e){
                System.out.println("파일 생성 중 오류 발생: "+ e.getMessage());
            }
        }
    }

    public static void checkKeyAndInputData(String name, List<Integer> subjects) {
        if(studentInfo.containsKey(name)){
            System.out.println("[오류] 이미 존재하는 이름입니다. 다른 이름을 입력하세요.");
            return;
        }
        for (int subject : subjects) {
            if(subject < 0 || subject > 100){
                System.out.println("[오류] 점수는 0-100 사이만 가능합니다. 다시 입력해주세요.");
                return;
            }
        }

        int total = subjects.stream().mapToInt(subject->subject).sum();
        double avg = subjects.stream().mapToInt(subject->subject).average().getAsDouble();
        String grade = getGrade(avg);

        Student student = new Student(name,total,avg,grade,subjects);
        System.out.printf("=> 저장됨: %s (총점=%d, 평균=%.1f, 학점=%s)\n", name, total, avg, grade);
        studentInfo.put(name, student);
    }

    public static void saveData(){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
            oos.writeObject(studentInfo);
            System.out.println("[완료] "+ studentInfo.size() +"명의 정보가 student.dat 에 저장되었습니다.");
        }catch (IOException e){
            System.err.println("[실패] " + e.getMessage());
        }
    }

    private static String getGrade(double avg){
        if(avg >= 90){
            return "A";
        }else if(avg >= 80){
            return "B";
        }else if(avg >= 70){
            return "C";
        }else if(avg >= 60){
            return "D";
        }else return "F";
    }
}