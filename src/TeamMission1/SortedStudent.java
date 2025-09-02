package TeamMission1;

import java.nio.file.*;
import java.util.*;
import java.io.*;

class StudentComparator implements Comparator<Student>, Serializable {
    public int compare(Student s1, Student s2) {
        if(s1.getAverage() > s2.getAverage()) return 1;
        else if(s1.getAverage() < s2.getAverage()) return -1;
        else{//평균이 같은 경우 이름 순..
            if(s1.getName().compareTo(s2.getName()) > 0) return 1;
            else if(s1.getName().compareTo(s2.getName()) < 0) return -1;
            return 0;
        }
    }
}

public class SortedStudent {
    static Map<String, Student> studentInfo;
    static File file;
    static Set<Student> students;


    public static void main(String[] args) {
        loadObjectFromFile();
        createTreeSet();
        printResult();
        outputObject();
    }

    public static void loadObjectFromFile() {
        Path path = Paths.get("C:/Temp/student.dat");

        if(!Files.exists(path)){
            System.out.println("[오류] 해당 파일이 존재하지 않습니다.");
            System.exit(0);
        }
        try(ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path))){
            Object o = ois.readObject();
            studentInfo = (Map<String, Student>) o;

        } catch (ClassNotFoundException e) {
            System.out.println("[실패] "+ e.getMessage());
        } catch (IOException e) {
            System.out.println("[실패] "+ e.getMessage());
        }
    }

    public static void createTreeSet(){
        students = new TreeSet<>(new StudentComparator());

        for (Map.Entry<String, Student> entry : studentInfo.entrySet()) {
            Student current = entry.getValue();
            students.add(current);
        }
    }

    public static void printResult(){
        System.out.println("[정렬 및 저장: 평균 오름차순]");
        System.out.println("불러온 학생 수: "+ studentInfo.size());
        System.out.println("정렬규칙: 평균 ASC, 평균 동률이면 이름 사전순 ASC");

        System.out.println("\n저장 대상(미리보기 상위 10명):");
        int count = 0;
        Iterator<Student> iter = students.iterator();
        while(iter.hasNext()){
            Student student = iter.next();
            if(count==10) break;
            System.out.printf("- %s (평균 %.1f)\n", student.getName(), student.getAverage());
        }
    }

    public static void outputObject(){
        file = new File("C:/Temp/orderByAvg.dat");

        if(!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
                System.out.println("파일 생성 중 오류 발생: "+ e.getMessage());
            }
        }

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
            oos.writeObject(students);

            System.out.println("\n결과 파일: ./orderByAvg.dat");
            System.out.println("[완료] 정렬된 결과를 파일로 저장했습니다.");
        } catch (FileNotFoundException e) {
            System.out.println("[오류] "+ e.getMessage());
        } catch (IOException e) {
            System.out.println("[오류] "+ e.getMessage());
        }
    }
}
