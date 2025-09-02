package TeamMission1;

import java.nio.file.*;
import java.util.*;
import java.io.*;

public class StudentOutput {
    static Path path;
    static Map<String, Student> studentInfo;
    static List<Student> datas = new ArrayList<>();

    public static void main(String[] args) {
        loadObjectFromFile();
        rearrangeData();
        printInfo();
    }

    public static void loadObjectFromFile(){
        path = Paths.get("C:/Temp/student.dat");
        if(!Files.exists(path)){
            System.out.println("[오류] 해당 파일이 존재하지 않습니다.");
            System.exit(0);
        }
        try(ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path))){
            Object o = ois.readObject();
            studentInfo = (Map<String, Student>) o;

        }catch(ClassNotFoundException e) {
            System.out.println("[실패] "+ e.getMessage());
        } catch(IOException e){
            System.out.println("[실패] "+ e.getMessage());
        }
    }

    public static void rearrangeData(){
        while(!studentInfo.isEmpty()){
            Student student = null;
            String name = null;

            for(Map.Entry<String, Student> entry : studentInfo.entrySet()){
                Student temp = entry.getValue();
                if(student == null || student.getAverage() > temp.getAverage()){
                    student = temp;
                    name = entry.getKey();
                }
            }

            datas.add(student);
            studentInfo.remove(name);
        }
    }

    public static void printInfo(){
        System.out.println("[평균 오름차순 성적표]");
        for(int i=1; i<=datas.size(); i++){
            System.out.printf("\n%d) %s\n",i,datas.get(i-1).getName());
            System.out.println(datas.get(i-1).toString());
        }
    }
}