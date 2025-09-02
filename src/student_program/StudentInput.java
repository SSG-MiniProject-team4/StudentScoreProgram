package student_program;

import student_program.common.ErrorCode;
import student_program.common.StudentText;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class StudentInput {
    String fileName = "C:/Temp/student.dat";
    // 자료구조 객체 생성
    File file;
    Map<String, Student> studentInfo = new HashMap<>();

    class InnerClass {
        private void loadCheck(){
            File dir = new File("C:/Temp");
            if (!dir.exists()) { //디렉토리 없으면
                dir.mkdir(); // 디렉토리만 생성
            }

            file = new File(fileName); // 파일 로드
            if (!file.exists()) {
                saveData(); // 파일이 없으면 빈 파일 생성
            }
        }

        private void printUsage() {
            System.out.println(StudentText.TITLE.getText());
            System.out.println(StudentText.EXIT_INPUT_MSG.getText());
            System.out.println(StudentText.VALID_SCORE.getText());
        }

        private void inputInfo() {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
                while (true){
                    System.out.print(StudentText.NAME.getText());
                    String name = br.readLine();
                    //exit, ^^ 입력 시 종료
                    if(name.equalsIgnoreCase(StudentText.EXIT.getText()) || name.equalsIgnoreCase(StudentText.EXIT_MSG.getText())) {exit(); break;}

                    Student student = new Student(name);
                    //성적 입력
                    System.out.print(StudentText.KOR.getText());
                    student.getRecord().add(Integer.parseInt(br.readLine()));
                    System.out.print(StudentText.ENG.getText());
                    student.getRecord().add(Integer.parseInt(br.readLine()));
                    System.out.print(StudentText.MAT.getText());
                    student.getRecord().add(Integer.parseInt(br.readLine()));
                    System.out.print(StudentText.SCI.getText());
                    student.getRecord().add(Integer.parseInt(br.readLine()));
                    checkKeyAndInputData(student);
                }
            } catch (IOException e) {
                System.out.println(ErrorCode.INPUT_ERROR.getText());
            } catch (NumberFormatException e) {
                System.out.println(ErrorCode.NUMBER_ERROR.getText());
            } catch (Exception e) {
                System.out.println(ErrorCode.ERROR.getText());
            }
        }

        private void exit(){
            System.out.println(StudentText.EXIT.getText());
            System.out.println(StudentText.INPUT_END.getText());
            System.out.println("[완료] " + studentInfo.size() + "명의 정보가 " + fileName + " 에 저장되었습니다.");
        }

        private void checkKeyAndInputData(Student student) {
            if(studentInfo.containsKey(student.getName())) {
                System.out.println(StudentText.NAME_EXIST.getText());
            } else {
                if(student.getRecord().stream().allMatch(s -> s >= 0 && s <= 100)) {
                    calculateScores(student);

                    studentInfo.put(student.getName(), student);
                    System.out.printf("=> 저장됨: %s (총점=%d, 평균=%.1f, 학점=%s) \n",
                            student.getName(), student.getTotal(), student.getAverage(), student.getGrade());
                } else{
                    System.out.println(StudentText.WRONG_NUM.getText());
                }
            }
        }

        private void calculateScores(Student student) {
            //총점 계산
            student.setTotal(student.getRecord().stream().mapToInt(s -> Integer.valueOf(s)).sum());
            //평균 계산
            student.setAverage((float)student.getRecord().stream().mapToInt(s->Integer.valueOf(s)).average().getAsDouble());
            //학점 계산
            double score = student.getAverage();
            if(score >= 90) student.setGrade(StudentText.A.getText());
            else if(score >= 80) student.setGrade(StudentText.B.getText());
            else if(score >= 70) student.setGrade(StudentText.C.getText());
            else if(score >= 60) student.setGrade(StudentText.D.getText());
            else student.setGrade(StudentText.F.getText());
        }

        private void saveData(){
            try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
                oos.writeObject(studentInfo);
            } catch (FileNotFoundException e){
                System.out.println(ErrorCode.FILE_ERROR.getText());
            } catch (IOException e) {
                System.out.println(ErrorCode.OUTPUT_ERROR.getText());
            }catch (Exception e){
                System.out.println(ErrorCode.ERROR.getText());
            }
        }
    }

    public void run(){
        InnerClass innerClass = new InnerClass();
        innerClass.loadCheck();
        innerClass.printUsage();
        innerClass.inputInfo();
        innerClass.saveData();
    }
}
