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

            맵에서 값만 추출해 평균을 기준으로 내림차순 정렬한 스트림을 리스트로 만들어서 data에 add
            List<Student> sortedStudents = studentInfo.values().stream()
            .sorted(Comparator.comparingDouble(Student::getAverage).reversed()).toList();
            datas.addAll(sortedStudents);
            names = sortedStudents.stream().map(Student::getName).toArray(String[]::new);

            정렬 알고리즘(보이는 범위)
            - 해시맵에서 키를 꺼내 가장 높은 평균(highest AVG) 학생을 찾아
            `datas`에 추가하고 키 제거하는 선택
            - names 배열 정렬된 이름 저장하는 데 사용
            - 키 값을 저장한 set 임시로 사용
            */
            while (!keys.isEmpty()) {
                //set 하나를 만들어 키 저장 후 하나씩 추출해 맵에서 가장 평균이 큰 값을 구함
                Student student = keys.stream()
                        .map(key -> studentInfo.get(key))
                        .max(Comparator.comparingDouble(Student::getAverage)).get();
                // datas에 추가
                datas.add(student);
                // 오름차순 정렬을 위해 뒤에서부터 이름 배열 채움
                names[--index] = student.getName();
                // 키 제거
                keys.remove(student.getName());
            }
        }


        private void printInfo(){
            System.out.println(StudentText.ASC_SCORE.getText());

            AtomicInteger i = new AtomicInteger(1);

            //오름차순 정렬된 names 배열을 스트림으로 만들어 이 값을 통해 맵에서 student 객체 얻어 정보 출력
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
