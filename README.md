# Quiz Application – Java

A modular, scalable, and extensible **Java-based assessment and quiz system** designed and implemented as part of the **Advanced Programming in Java** module in the MSc Cloud Computing program at Newcastle University.

This project showcases object-oriented design, interface-driven architecture, design patterns, defensive programming, and extensive unit testing using JUnit.

---

## 1. Key Features

- **Factory Design Pattern** – For creating quiz questions, quizzes, and student entities  
- **Interface-Based Design** – Enables modularity and future extensibility  
- **Defensive Programming** – Validates inputs and maintains system robustness  
- **Free-Response & Multiple-Choice Questions** – Handles validation, formatting, and multiple correct answers  
- **Student Statistics Tracking** – Tracks scores, attempts, averages, and revision eligibility  
- **Revision Quiz Generator** – Based on student’s incorrect responses  
- **JUnit Tests** – Covers factories, scoring, student objects, and edge cases  

---

## 2. Technologies Used

| Category         | Tools/Concepts                                  |
|------------------|--------------------------------------------------|
| Programming      | Java, Java Collections Framework                 |
| Design Patterns  | Factory Pattern                                  |
| OOP Principles   | Interfaces, Encapsulation, Polymorphism         |
| Testing          | JUnit 5, Defensive Programming                   |
| Documentation    | UML (draw.io), JavaDoc, PDF Reports              |

---

## 3. Project Structure

QuizApplication/
├── src/
│   ├── Factory/               # QuestionFactory, StudentFactory
│   ├── Implementations/       # FreeResponseQuestion, QuizImpl, StudentImpl
│   ├── Interfaces/            # Question, QuizGenerator, Student, etc.
│   ├── Model/                 # QuizSystem, StudentStatisticsImpl
│   └── JunitTesting/          # FactoryTest, QuizSystemTest, etc.
│
├── docs/
│   ├── QuizApplicationDocumentation.pdf
│   ├── UML-Diagram.drawio
│   └── Screenshot-Overview.png
│
├── .gitignore
├── LICENSE
└── README.md

---

## 4. How to Run the Application

> This application runs as a **command-line (CLI)** program. You can run it via IntelliJ or any Java IDE.

### Steps:
1. Clone this repository:
   ```bash
   git clone https://github.com/yourusername/QuizApplication.git
   cd QuizApplication

	2.	Open the project in IntelliJ IDEA or Eclipse.
	3.	Run the following file:

src/Model/QuizSystem.java



⸻

 5. Running the Unit Tests

All tests are written using JUnit 5 and are located in the src/JunitTesting/ directory.

Main Tests:
	•	FactoryTest.java – Factory creation tests
	•	QuestionTest.java – Free-response and multiple-choice answer validation
	•	QuizSystemTest.java – End-to-end quiz execution
	•	StudentStatisticsTest.java – Performance tracking
	•	StudentTest.java – Identity and detail verification

Tests can be run directly in your IDE or through a build system (e.g., Maven, Gradle if integrated).

⸻

 6. Documentation
	•	 Quiz Application – PDF Documentation
	•	 UML Diagram – Class Design (draw.io)
Let me know if you’d like help exporting your .drawio file as .png so it shows inline in GitHub for better visual preview.
	•	 Screenshot of application structure:


⸻

 7. License

This project is licensed under the MIT License.
Feel free to use and adapt with attribution.

⸻

 8. Author

Samuel Pillai Sathiyamoorthy
MSc Cloud Computing, Newcastle University

⸻

 9. Note for Recruiters

This coursework project demonstrates:
	•	 Advanced understanding of Java and OOP principles
	•	 Ability to design modular, testable backend systems
	•	 Real-world application of design patterns and test-driven development

Please feel free to explore the repo, review the UML, and reach out for more information.

⸻

---
