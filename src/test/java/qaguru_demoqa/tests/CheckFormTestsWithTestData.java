package qaguru_demoqa.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import qaguru_demoqa.pages.RegistrationPage;

import static qaguru_demoqa.tests.TestData.*;
import static qaguru_demoqa.utils.RandomUtils.getRandomEmail;
import static qaguru_demoqa.utils.RandomUtils.getRandomString;

public class CheckFormTestsWithTestData {

    RegistrationPage registrationPage = new RegistrationPage();
    public static String name = getRandomString(5);
    public static String lastName = getRandomString(10);
    public static String mobile = "1234567890";
    public static String gender = "Male";
    public static String email = getRandomEmail();
    public static String day = "05";
    public static String month = "April";
    public static String year = "1984";
    public static String subject = "Maths";
    public static String address = "street Test";

    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
        Configuration.baseUrl = "https://demoqa.com";
    }

    //проверяем все поля
    @Test
    void positiveAllRequiredFieldsTest() {

        registrationPage.openPage();
        registrationPage.typeFirstName(name);
        registrationPage.typeLastName(lastName);

        registrationPage.typeEmail(email)
                .selectGender(gender)
                .typeMobile(mobile)
                .setDateOfBirth(day, month, year) //выбор даты в календаре
                .typeSubject(subject)
                .setHobbies()
                .uploadPicture()
                .typeAddress(address)
                .setStateAndCity("NCR", "Delhi")
                .clickSubmit();

        //проверки
        registrationPage.checkResultsTitle();
        registrationPage.checkResultsValue(name + " " + lastName);
        registrationPage.checkResultsValue(email);
        registrationPage.checkResultsValue(gender);
        registrationPage.checkResultsValue(mobile);
        registrationPage.checkResultsValue(day + " " + month + "," + year);
        registrationPage.checkResultsValue(subject);
        registrationPage.checkResultsValue("Sports, Reading, Music");
        registrationPage.checkResultsValue("pic.jpg");
        registrationPage.checkResultsValue(address);
        registrationPage.checkResultsValue("NCR Delhi");
    }
}
