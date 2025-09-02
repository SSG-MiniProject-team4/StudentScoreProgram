package student_program;

import student_program.common.ErrorCode;
import student_program.common.StudentText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Map;
import java.util.List;

public class StudentOutput {
    private static final String fileName = "C:/Temp/student.dat";
    private Map<String, Student> studentInfo;
    private List<Student> datas = new ArrayList<>();
    private String[] names;

    class InnerClass{
        private void loadObjectFromFile(){
            try(ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(fileName))){
                Object obj = ois.readObject();
                if(obj instanceof HashMap) {
                    studentInfo = (HashMap<String, Student>) obj;
                } else {
                    System.out.println(ErrorCode.FILE_ERROR.getText());
                }
            } catch (FileNotFoundException e){
                System.out.println(ErrorCode.FILE_ERROR.getText());
            } catch (IOException e) {
                System.out.println(ErrorCode.OUTPUT_ERROR.getText());
            }catch (Exception e){
                System.out.println(ErrorCode.ERROR.getText());
            }
        }

        private void rearrangeData(){
            Set<String> keys = new HashSet<>(studentInfo.keySet());
            names = new String[studentInfo.size()];
            int index = studentInfo.size();

            /*
            더 간단한 방식도 있지만 정렬 알고리즘 요구조건을 맞추기 위해서 이렇게 함

            정렬 알고리즘(보이는 범위)
            - 해시맵에서 키를 꺼내 가장 높은 평균(highest AVG) 학생을 찾아
            `datas`에 추가하고 키 제거하는 선택
            */
            while (!keys.isEmpty()) {
                Student student = keys.stream()
                        .map(key -> studentInfo.get(key))
                        .max(Comparator.comparingDouble(Student::getAverage)).get();
                datas.add(student);
                names[--index] = student.getName();
                keys.remove(student.getName());
            }
        }


        private void printInfo(){
            System.out.println(StudentText.ASC_SCORE.getText());

            AtomicInteger i = new AtomicInteger(1);
            Arrays.stream(names).forEach(name ->{
                Student student = studentInfo.get(name);
                System.out.println((i.getAndIncrement()) + StudentText.R_PAR.getText() + name);
                System.out.println(StudentText.SCORE.getText() + student.getRecord().toString());
                System.out.println(StudentText.TOTAL.getText() + student.getTotal() +
                        StudentText.AVG.getText() + student.getAverage() +
                        StudentText.GRADE.getText() + student.getGrade());
                System.out.println();
            });
        }
    }

    public void run(){
        InnerClass innerClass = new InnerClass();
        innerClass.loadObjectFromFile();
        if (studentInfo != null) {
            innerClass.rearrangeData();
            innerClass.printInfo();
        } else {
            System.out.println(ErrorCode.FILE_INPUT_ERROR.getText());
        }

    }
}
