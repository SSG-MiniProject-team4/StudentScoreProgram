package student_program;

import student_program.common.ErrorCode;
import student_program.common.StudentText;

import java.io.*;
import java.util.*;

public class SortedStudent {
    private final String fileName = "C:/Temp/student.dat";
    private final String SortFileName = "C:/Temp/orderByAvg.dat";
    private Map<String, Student> studentInfo = null;
    private Set<Student> sortedStudents = null;

    class InnerClass {

        //파일 불러오기
        private void loadObjectFromFile() {
            try (ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(fileName))) {
                Object obj = ois.readObject();
                if (obj instanceof HashMap) {
                    studentInfo = (HashMap<String, Student>) obj;
                } else {
                    System.out.println(ErrorCode.TYPE_ERROR.getText());
                }
            } catch (FileNotFoundException e){
                System.out.println(ErrorCode.FILE_ERROR.getText());
                System.exit(0);
            } catch (IOException e) {
                System.out.println(ErrorCode.INPUT_ERROR.getText());
            }catch (Exception e){
                System.out.println(ErrorCode.ERROR.getText());
            }
        }

        private void createTreeeSet() {
            //tree set 생성
            //람다식으로 구현했으나 저장에 오류가 발생해서 comparator 클래스를 명시적으로 분리
            sortedStudents = new TreeSet<Student>(new StudentComparator());
            //학생 객체 treeset에 저장
            sortedStudents.addAll(studentInfo.values());
        }

        //미리보기 10개 출력
        private void printResult() {
            System.out.println(StudentText.SORT_MSG.getText());
            System.out.println(StudentText.STUDENT_NUM.getText() + studentInfo.size());
            System.out.println(StudentText.SORT_RULE.getText());
            System.out.println(StudentText.SORT_PREVIEW.getText());
            //limit 개수 제한
            sortedStudents.stream().limit(10)
                    .forEach(s -> System.out.println(StudentText.SIGN.getText() + s.getName() +
                            StudentText.L_PAR.getText() + StudentText.SORT_AVG_MSG.getText() + String.format("%.1f", s.getAverage()) +
                            StudentText.R_PAR.getText()));
            System.out.println();
        }

        //파일 저장
        private void outputObject(){
            try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SortFileName))){
                oos.writeObject(sortedStudents);
                System.out.println(StudentText.RESULT_FILE.getText() + SortFileName);
                System.out.println(StudentText.RESULT_MSG.getText());
            } catch (FileNotFoundException e){
                System.out.println(ErrorCode.FILE_ERROR.getText());
                System.exit(0);
            } catch (IOException e) {
                System.out.println(ErrorCode.OUTPUT_ERROR.getText());
                //e.printStackTrace();
            }catch (Exception e){
                System.out.println(ErrorCode.ERROR.getText());
            }
        }
    }

    public void run(){
        InnerClass innerClass = new InnerClass();
        innerClass.loadObjectFromFile();
        innerClass.createTreeeSet();
        innerClass.printResult();
        innerClass.outputObject();
    }
}
