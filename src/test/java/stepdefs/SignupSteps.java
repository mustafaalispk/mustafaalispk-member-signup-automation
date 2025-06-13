package stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static org.junit.Assert.*;

public class SignupSteps {
    WebDriver driver;

    @Given("I open the registration page")
    public void i_open_the_registration_page() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
    }

    @When("I enter valid registration details")
    public void i_enter_valid_registration_details() {
        String email = "mustafaali.dev" + System.currentTimeMillis() + "@testmail.com";
        fillBasicDetails("07/11/1981", "Mustafa", "Ali", email, email, "Test@1234", "Test@1234");
    }

    @When("I enter registration details with missing last name")
    public void i_enter_registration_details_with_missing_last_name() {
        String email = "mustafaali.dev" + System.currentTimeMillis() + "@testmail.com";
        fillBasicDetails("07/11/1981", "Mustafa", "", email, email, "Test@1234", "Test@1234");
    }

    @When("I enter registration details with mismatched passwords")
    public void i_enter_registration_details_with_mismatched_passwords() {
        String email = "mustafaali.dev" + System.currentTimeMillis() + "@testmail.com";
        fillBasicDetails("07/11/1981", "Mustafa", "Ali", email, email, "Test@1234", "Wrong1234");
    }

    @When("I accept terms and conditions")
    public void i_accept_terms_and_conditions() {
        acceptAllTerms();
    }

    @When("I do not accept terms and conditions")
    public void i_do_not_accept_terms_and_conditions() {
        WebElement checkbox = driver.findElement(By.id("sign_up_25"));
        if (checkbox.isSelected()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
        }
    }

    @When("I submit the form")
    public void i_submit_the_form() {
        driver.findElement(By.xpath("//input[@type='submit' and @value='CONFIRM AND JOIN']")).click();
    }

    @Then("I should see a confirmation message")
    public void i_should_see_a_confirmation_message() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("h2.bold.gray.text-center.margin-bottom-40")));

        String actualText = heading.getText().trim();
        assertEquals("THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND", actualText);

    }

    @Then("I should see an error for last name")
    public void i_should_see_an_error_for_last_name() {
        boolean errorDisplayed = driver.getPageSource().contains("Last Name is required");
        assertTrue(errorDisplayed);
    }

    @Then("I should see an error for password mismatch")
    public void i_should_see_an_error_for_password_mismatch() {
        WebElement confirmPasswordInput = driver.findElement(By.id("signupunlicenced_confirmpassword"));
        String validationMessage = confirmPasswordInput.getAttribute("data-val-equalto");

        assertEquals("Password did not match", validationMessage);
    }

    @Then("I should see an error about accepting terms")
    public void i_should_see_an_error_about_accepting_terms() {
        boolean errorDisplayed = driver.getPageSource().contains(
                "You must confirm that you have read and accepted our Terms and Conditions");

        assertTrue(errorDisplayed);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


    private void fillBasicDetails(String dob, String firstName, String lastName, String email, String confirmEmail,
                                  String password, String confirmPassword) {
        driver.findElement(By.id("dp")).sendKeys(dob);
        driver.findElement(By.id("member_firstname")).sendKeys(firstName);
        driver.findElement(By.id("member_lastname")).sendKeys(lastName);
        driver.findElement(By.id("member_emailaddress")).sendKeys(email);
        driver.findElement(By.id("member_confirmemailaddress")).sendKeys(confirmEmail);
        driver.findElement(By.id("signupunlicenced_password")).sendKeys(password);
        driver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys(confirmPassword);
    }

    private void acceptAllTerms() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[for='sign_up_25']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[for='sign_up_26']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct']"))).click();
    }
}

