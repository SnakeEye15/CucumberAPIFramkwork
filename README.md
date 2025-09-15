# Cucumber API Framework 🚀

This project is a **BDD-style API testing framework** built using:

* **Java**
* **Cucumber**
* **RestAssured**
* **JUnit**
* **Maven**
* **Cucumber Reporting**

It validates **Google Maps Place APIs** (Add, Get, Delete) as part of a sample test automation suite.

---

## 📂 Project Structure

```
CucumberAPIFramkwork
│── src
│   ├── test
│   │   ├── java
│   │   │   ├── stepDefinitions/   # Step Definitions
│   │   │   ├── resources/         # Hooks, Utils, APIResources, TestDataBuild
│   │   │   ├── Pojos/             # Request/Response POJOs
│   │   │   └── runners/           # Test Runner classes
│   │   └── resources
│   │       └── features/          # Feature files
│── pom.xml
│── README.md
```

---

## ⚙️ Prerequisites

* Java **17+** (JDK installed & `JAVA_HOME` configured)
* Maven **3.8+**
* IntelliJ IDEA (recommended) or Eclipse
* Internet connection (for dependency downloads)

---

## ▶️ How to Run Tests

Run using **Maven command**:

```bash
mvn clean test
```

Or run via **JUnit Test Runner** inside IntelliJ.

---

## 📝 Sample Feature File

```gherkin
Feature: Validating place API's

  @AddPlaceAPI
  Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<Address>"
    When user call "AddPlaceAPI" with "POST" Http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created maps to "<name>" using "GetPlaceAPI"

    Examples:
      | name    | language | Address |
      | Dheeraj | English  | Gohana  |

  @DeletePlaceAPI
  Scenario: Verify if Delete Place API is working properly
    Given DeletePlace Payload
    When user call "DeletePlaceAPI" with "POST" Http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
```

---

## 📊 Test Reports

After execution, a **Cucumber HTML report** is generated in:

```
target/cucumber-html-reports/
```

Or via **cucumber-reporting plugin**:

```bash
mvn verify
```

The report will be available at:

```
target/cucumber-html-reports/overview-features.html
```

---

## 🔑 Key Features

* BDD with **Cucumber**
* API testing with **RestAssured**
* Modular design (POJOs, TestDataBuilder, Utils)
* Data-driven testing using **Scenario Outlines**
* Hooks for **preconditions**
* Extensible for multiple APIs

---

## 🛠️ Useful Shortcuts (IntelliJ)

* Format JSON / Code → `⌘ + ⌥ + L` (Mac) / `Ctrl + Alt + L` (Win/Linux)
* Run test → Right-click on runner class → `Run`

---

## 📌 Future Enhancements

* Add support for parallel execution with **TestNG**
* Integrate with **Allure Reports**
* Add CI/CD pipeline (Jenkins/GitHub Actions)

---

## 👨‍💻 Author

**Dheeraj Saini**
Automation Tester | API & UI Test Automation | Java | Cucumber | RestAssured

