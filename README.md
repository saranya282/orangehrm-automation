🧪 OrangeHRM Automation Suite

This project automates core workflows of the OrangeHRM Demo Application using Selenium WebDriver, TestNG, Rest Assured, and Allure reporting.

📌 Features Covered

  🔹 UI Automation

    ✔ Login
    
    ✔ Add Employee
    
    ✔ Edit Employee Job Details
    
    ✔ Delete Employee
    
    ✔ Validate Employee details from UI

  🔹 API Validation

    ✔ Call Employee API
    
    ✔ Validate Job Title using API
    
    ✔ Validate Employment Status using API
    
    ✔ Validate employee record deletion after UI delete
    
| Component    | Version |
| ------------ | ------- |
| Java         | 17      |
| Selenium     | 4.25.0  |
| TestNG       | 7.10.2  |
| Rest Assured | 5.5.0   |
| Maven        | Latest  |
| Allure       | 2.x     |




          orangehrms-automation
          │
          ├── pom.xml
          ├── testng.xml
          ├── README.md
          │
          ├── src
          │   ├── main
          │   │   └── java
          │   │       └── compareclub.orangehrms
          │   │           ├── api
          │   │           │   └── EmployeeApiValidator.java
          │   │           │
          │   │           └── pages
          │   │               ├── BasePage.java
          │   │               ├── DashboardPage.java
          │   │               ├── LoginPage.java
          │   │               └── PimPage.java
          │   │
          │   └── test
          │       ├── java
          │       │   ├── com.compareclub.orangehrms.utils
          │       │   │   ├── ConfigReader.java
          │       │   │   └── JsonDataReader.java
          │       │   │
          │       │   ├── compareclub.orangehrms
          │       │   │   ├── LoginTest.java
          │       │   │   └── PimTest.java
          │       │   │
          │       │   └── compareclub.orangehrms.base
          │       │       └── BaseTest.java
          │       │
          │       └── resources
          │           └── employeeData.json
          │
          ├── allure-results
          ├── reports
          │   ├── allure-report-screenshot.png
          │   └── execution-video.mp4
          │
          ├── test-output
          └── target
          
▶️ How to Run Test Suite

    👉 Step 1: Clone the Repo
    git clone <repo-url>
    cd orangehrms-automation
    
    👉 Step 2: Install Dependencies
    mvn clean install
    
    👉 Step 3: Run Tests
    mvn clean test

    🧾 Generate Allure Report
      Option-1 (Generate HTML report)
      mvn allure:report

Report will be generated at:

  📍 target/allure-report/index.html
  
    Option-2 (Run interactive live report)
    allure serve allure-results


🧪 Test Cases Summary

| Test Name              | Purpose                             |
| ---------------------- | ----------------------------------- |
| verifyAddEmployee      | Validate employee creation          |
| verifyEditEmployee     | Modify and update employee job info |
| verifyEmployeeUsingApi | Validate employee data from API     |
| verifyDeleteEmployee   | Delete and verify from UI and API   |

📡 API Used

    👉 API Endpoint
    
      https://opensource-demo.orangehrmlive.com/web/index.php/api/v2/pim/employees
    
    
    👉 Query Params Used
    
      limit=50
      offset=0
      model=detailed
      employeeId=<ID>
      includeEmployees=onlyCurrent
      sortField=employee.firstName
      sortOrder=ASC
      
    
    Used For:
      ✔ Verifying employee exists
      ✔ Checking job title
      ✔ Checking employment status
      ✔ Confirming record deletion

📁 Reports & Evidence

  📌 Test Execution Video
    ✔ Available inside repository
    👉 reports/execution-video.mp4
  
  📌 Allure Report Screenshot
    ✔ Live UI snapshot added
    👉 reports/allure-report-screenshot.png
  
  📌 HTML Report
    ✔ Located in
    👉 target/allure-report/index.html

🚀 Suggested Improvements (Future Enhancements)

  🔍 TestNG Listeners
  
    Implementation can:
    
    log detailed execution status
    
    capture failures
    
    attach logs
  
  🔄 Retry Mechanism
  
    Using IRetryAnalyzer
    To auto-rerun flaky failures due to:
    
    element not clickable
    
    network delays
    
    dynamic wait issues
    
    Example: each failed test retries up to 2 times
  
  📸 Screenshot Capture on Failure
  
    Automatically capture screenshot and attach to:
    
    Allure report
    
    TestNG report
    
  ⚙️ CI/CD Support
  
    Integration with:
    
    GitHub Actions
    
    Jenkins
    
    For scheduled builds
  
  🚀 Parallel Execution
    
    Using TestNG XML:
    
    parallel="methods"
    thread-count="4"

  
👤 Author
  Saranya Mallu
  💼 Automation Engineer

🧪 Skilled In:
✔ Selenium | TestNG | Rest Assured
✔ Playwright | Java | Automation Framework Design
