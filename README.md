# 📊 학생 성적 관리 프로그램 (Student Score Management Program)

이 프로그램은 **Java 콘솔 애플리케이션**으로, 학생들의 성적 데이터를 효율적으로 관리할 수 있도록 설계되었습니다. 사용자가 직접 성적을 입력하고, 프로그램이 이를 계산 및 처리하여 파일로 저장합니다. 저장된 데이터는 필요에 따라 정렬하고 조회할 수 있습니다.

---

## ✨ 주요 기능

### ✅ 성적 입력 및 유효성 검사
- 학생의 이름과 **국어, 영어, 수학, 과학 점수**를 입력받습니다.
- 점수는 **0부터 100 사이의 정수**만 유효합니다.
- **동일한 이름의 학생은 중복 입력 불가**합니다.

### 🧮 자동 계산 및 저장
- 입력이 완료되면 자동으로 **총점, 평균, 학점(A~F)**이 계산됩니다.
- 모든 학생 정보는 `C:/Temp/student.dat` 파일에 **객체 직렬화 방식**으로 저장됩니다.

### 📈 데이터 정렬 및 조회
- `student.dat` 파일의 데이터를 불러와 **평균 점수 내림차순**으로 정렬하여 콘솔에 출력합니다.
- 평균 점수가 같을 경우, **이름 오름차순**으로 정렬됩니다.

### 💾 정렬된 데이터 파일 저장
- 정렬된 학생 정보는 `C:/Temp/orderByAvg.dat` 파일에 **별도로 저장**됩니다.

---

## 📂 클래스 구성 및 역할

| 파일명                    | 역할 및 상세 설명 |
|---------------------------|------------------|
| `StudentApp.java`         | 프로그램 실행의 시작점. `StudentInput`, `StudentOutput`, `SortedStudent` 클래스를 순차적으로 호출하여 전체 흐름 제어 |
| `StudentInput.java`       | 사용자 입력 및 초기 저장 담당. `BufferedReader`로 콘솔 입력 처리, 유효성 검사 후 `HashMap`에 저장. `student.dat`에 직렬화 |
| `StudentOutput.java`      | 데이터 조회 및 콘솔 출력 담당. `ObjectInputStream`으로 `student.dat` 읽고, `Arrays.stream()`으로 정렬 후 출력 |
| `SortedStudent.java`      | 데이터 정렬 및 파일 저장 담당. `StudentComparator`를 사용하여 `TreeSet`에 정렬 저장, `orderByAvg.dat`에 직렬화 |
| `Student.java`            | 학생 정보 모델 클래스. 이름, 점수, 총점, 평균, 학점 속성 포함. `Serializable` 구현 |
| `StudentComparator.java`  | 정렬 기준 정의 클래스. `Comparator` 인터페이스 구현. 평균 → 이름 기준으로 비교 |
| `ErrorCode.java` / `StudentText.java` | 상수 관리 열거형 클래스. 오류 메시지(`ErrorCode`)와 안내 문구(`StudentText`) 정의로 가독성 및 유지보수성 향상 |

---

# 📚 세부사항

## 🏁 StudentApp.java
- 프로그램의 **시작점** 역할을 하는 클래스
- `main()` 메서드에서 다음 클래스들을 순서대로 실행:
    - `StudentInput`
    - `StudentOutput`
    - `SortedStudent`
- 사용자 입력 → 성적 처리 → 파일 저장 → 정렬 및 출력까지 **전체 흐름 제어**

---

## ✍️ StudentInput.java
- 학생 정보를 콘솔에서 입력받고 초기 저장을 담당

### 주요 기능:
- `BufferedReader`를 사용하여 이름과 점수(국어, 영어, 수학, 과학) 입력
- 점수는 **0~100 사이의 정수**만 허용
- **중복 이름 입력 불가**

### 유효성 검사 후:
- **총점, 평균, 학점(A~F)** 계산
- `HashMap`에 저장
- `C:/Temp/student.dat`에 **객체 직렬화 방식**으로 저장

---

## 📤 StudentOutput.java
- 저장된 학생 데이터를 불러와 콘솔에 출력

### 주요 기능:
- `ObjectInputStream`으로 `student.dat` 파일 읽기
- `Arrays.stream()`을 사용하여 **평균 점수 기준 내림차순 정렬**
- 보기 좋은 형식으로 콘솔 출력

---

## 📊 SortedStudent.java
- 학생 데이터를 정렬 후 별도 파일로 저장

### 주요 기능:
- `StudentComparator`를 사용하여 `TreeSet`에 학생 객체 추가

### 정렬 기준:
- **평균 오름차순**
- 평균이 같을 경우 **이름 오름차순**

- 정렬된 데이터는 `C:/Temp/orderByAvg.dat`에 **객체 직렬화 방식**으로 저장

---

## 👤 Student.java
- 학생 한 명의 정보를 담는 **데이터 모델 클래스**

### 주요 속성:
- 이름
- 총점
- 평균
- 학점
- 과목별 점수 리스트 (`record`)

- `Serializable` 인터페이스 구현 → **파일 저장 및 불러오기 가능**

---

## ⚖️ StudentComparator.java
- 학생 객체의 **정렬 기준 정의 클래스**

### 기능:
- `Comparator` 인터페이스 구현
- `compare()` 메서드:
    - **평균 점수 기준 오름차순**
    - 평균이 같을 경우 **이름 기준 오름차순**

---

## ⚠️ ErrorCode.java / 📝 StudentText.java

### `ErrorCode.java`
- 프로그램에서 발생할 수 있는 **오류 메시지**를 관리하는 `Enum` 클래스
- 예: 파일 오류, 입력 오류 등
- 코드의 **가독성 향상 및 유지보수 용이**

### `StudentText.java`
- 사용자에게 보여주는 **텍스트 상수**를 관리하는 `Enum` 클래스
- 예: 안내 문구, 점수 등급, 입력 메시지 등
- 문자열을 **한 곳에서 쉽게 변경 가능**


---
## ▶️ 실행 방법

1. 모든 `.java` 파일을 **컴파일**합니다.
2. `StudentApp.java`를 **실행**하여 프로그램을 시작합니다.
3. 콘솔의 안내에 따라 **학생 정보를 입력**합니다.
4. 입력 종료를 원하면 **이름에 `^^`를 입력**합니다.
5. 입력이 완료되면 자동으로 **데이터 처리 및 파일 저장**이 진행됩니다.

---

## 📁 파일 저장 경로

- 원본 데이터: `C:/Temp/student.dat`
- 정렬된 데이터: `C:/Temp/orderByAvg.dat`

---

[//]: # (## 💡 향후 개선 아이디어)

[//]: # ()
[//]: # (- GUI 버전으로 확장)

[//]: # (- CSV 또는 Excel 파일로 내보내기 기능 추가)

[//]: # (- 웹 기반 성적 관리 시스템으로 확장)

