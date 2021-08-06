package qaguru_demoqa.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import qaguru_demoqa.pages.RegistrationPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CheckFormTestsWithPageObject {

    RegistrationPage registrationPage = new RegistrationPage();

    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
        Configuration.baseUrl = "https://demoqa.com";
    }

    //проверяем все поля
    @Test
    void positiveAllRequiredFieldsTest() {
        String name = "Evgeniy";
        String lastName = "Usatenko";
        String mobile = "1234567890";
        String gender = "Male";
        String email = "Usatenko@sdasd.ru";
        String day = "05";
        String month = "April";
        String year = "1984";
        String subject = "Maths";
        String address = "street Test";

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
