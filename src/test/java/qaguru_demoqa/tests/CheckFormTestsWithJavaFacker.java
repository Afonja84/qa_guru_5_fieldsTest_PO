package qaguru_demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import qaguru_demoqa.pages.RegistrationPage;

import static qaguru_demoqa.utils.RandomUtils.getRandomEmail;
import static qaguru_demoqa.utils.RandomUtils.getRandomString;

public class CheckFormTestsWithJavaFacker {

    RegistrationPage registrationPage = new RegistrationPage();
    Faker faker = new Faker();

    String name = faker.name().name();
    String lastName = faker.name().lastName();
    String mobile = faker.phoneNumber().subscriberNumber(10);
    String gender = "Male";
    String email = faker.internet().emailAddress();
    String day = "05";
    String month = "April";
    String year = "1984";
    String subject = "Maths";
    String address = faker.address().streetAddress();

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
