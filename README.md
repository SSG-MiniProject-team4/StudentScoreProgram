# Java 학생 성적 관리 프로그램

##  프로젝트 개요

이 프로젝트는 Java를 사용하여 학생들의 성적을 관리하는 콘솔 기반 애플리케이션입니다. 객체 직렬화(Serialization)를 통해 학생 데이터를 파일에 영속적으로 저장하고, 다양한 정렬 방식으로 데이터를 처리 및 조회하는 기능을 구현합니다.

이 프로젝트를 통해 Java의 핵심 개념인 객체 지향 프로그래밍, 컬렉션 프레임워크, 파일 입출력, 예외 처리 등을 학습할 수 있습니다.

##  주요 기능

* **학생 정보 입력**: 콘솔을 통해 학생의 이름과 4개 과목(국어, 영어, 수학, 과학)의 점수를 입력받습니다.
* **데이터 유효성 검사**: 점수는 0~100 사이의 값만 허용하며, 중복된 이름은 입력할 수 없습니다.
* **데이터 영속화**: `HashMap`에 저장된 학생 정보를 `ObjectOutputStream`을 사용하여 `student.dat` 파일에 직렬화하여 저장합니다.
* **성적 조회 및 정렬**: `student.dat` 파일을 역직렬화하여 불러온 뒤, 학생들의 성적을 **평균 오름차순**으로 정렬하여 콘솔에 출력합니다.
* **고급 정렬 및 저장**: `TreeSet`과 `Comparator`를 사용하여 평균 오름차순 (평균 동일 시 이름순)으로 정렬하고, 정렬된 결과를 `orderByAvg.dat` 파일에 다시 저장합니다.

##  프로젝트 구조

프로젝트는 4개의 주요 자바 클래스로 구성됩니다.

* `Student.java`
    * 학생 한 명의 데이터(이름, 점수 리스트, 총점, 평균, 학점)를 담는 모델(DTO/VO) 클래스입니다.
    * `Serializable` 인터페이스를 구현하여 객체 직렬화가 가능합니다.

* `StudentInput.java`
    * 프로그램의 **입력** 파트를 담당합니다.
    * 사용자로부터 학생 정보를 입력받아 유효성을 검사하고, `Student` 객체를 생성하여 `HashMap`에 저장합니다.
    * 프로그램 종료 시 `HashMap` 객체를 `student.dat` 파일에 저장합니다.

* `StudentOutput.java`
    * 프로그램의 **조회** 파트를 담당합니다.
    * `student.dat` 파일의 데이터를 불러와 `ArrayList`로 변환한 뒤, 평균 점수를 기준으로 오름차순 정렬하여 화면에 출력합니다.

* `SortedStudent.java`
    * 프로그램의 **고급 정렬 및 파일 출력** 파트를 담당합니다.
    * `student.dat` 파일을 불러와 `TreeSet`과 `Comparator`를 이용해 정렬합니다.
    * 정렬된 결과를 `orderByAvg.dat`라는 새로운 파일에 저장합니다.

##  실행 방법

### 사전 준비 사항

* Java Development Kit (JDK) 설치

### 컴파일

터미널 또는 명령 프롬프트에서 아래 명령어를 사용하여 모든 `.java` 파일을 컴파일합니다.

```bash
javac *.java
```

### 실행 순서

이 프로그램은 각 파일이 특정 기능을 수행하므로, 아래 순서대로 실행하는 것을 권장합니다.

1.  **학생 정보 입력 (`StudentInput.java`)**
    * 먼저 `StudentInput`을 실행하여 학생 데이터를 입력하고 `student.dat` 파일을 생성합니다.
    ```bash
    java StudentInput
    ```

2.  **성적 확인 (`StudentOutput.java`)**
    * 입력된 학생들의 성적을 평균 오름차순으로 정렬하여 확인합니다.
    ```bash
    java StudentOutput
    ```

3.  **TreeSet으로 정렬 및 파일 저장 (`SortedStudent.java`)**
    * `student.dat`를 읽어 `TreeSet`으로 정렬하고, 그 결과를 `orderByAvg.dat` 파일로 저장합니다.
    ```bash
    java SortedStudent
    ```

### 데이터 파일

* `../student.dat`: `StudentInput`을 통해 생성되는 원본 데이터 파일입니다. (`HashMap<String, Student>`)
* `../orderByAvg.dat`: `SortedStudent`를 통해 생성되는, 정렬된 데이터 파일입니다. (`TreeSet<Student>`)

>  프로그램이 자동으로 생성합니다.

---

## 🛠 사용된 주요 기술

* **Java SE**
* **Object-Oriented Programming (OOP)**
* **Java Collections Framework**
    * `HashMap`: Key-Value 쌍으로 데이터 관리
    * `ArrayList`: 동적 배열 및 정렬에 사용
    * `TreeSet`: 자동 정렬 기능을 위한 Set
* **Java I/O & Serialization**
    * `File`, `FileInputStream`, `FileOutputStream`
    * `ObjectInputStream`, `ObjectOutputStream`
* **`Comparator` Interface**: 사용자 정의 정렬 기준 구현
* **Exception Handling**: `IOException`, `FileNotFoundException` 등 예외 처리