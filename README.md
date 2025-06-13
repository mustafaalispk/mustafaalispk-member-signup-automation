# Member Signup Automation

This is an automated testing project for a web registration form using:

- **Selenium WebDriver**
- **Cucumber (BDD)**
- **JUnit**
- **Maven**

The tests simulate user actions on the [Basketball England Registration Page](https://membership.basketballengland.co.uk/NewSupporterAccount) and verify different signup scenarios.

## ✅ Test Scenarios Covered

- Successful registration with valid inputs
- Registration with missing last name
- Mismatched passwords
- Terms and conditions not accepted

## 🛠 Project Structure

src/  
└── test  
  ├── java  
  │  ├── runner  
  │  │  └── CucumberTestRunner.java  
  │  └── stepdefs  
  │    └── SignupSteps.java  
  └── resources  
    └── features  
      └── signup.feature  

pom.xml  
.gitignore  

## ▶️ How to Run the Tests

1. Clone the repository to your local machine.
2. Open the project in IntelliJ IDEA or any Java IDE.
3. Make sure ChromeDriver is installed and on PATH.
4. Right-click on `CucumberTestRunner.java` and select **Run**.

## 💡 Tools Used

- Java 17 or later
- Maven
- Selenium WebDriver
- Cucumber
- GitHub
