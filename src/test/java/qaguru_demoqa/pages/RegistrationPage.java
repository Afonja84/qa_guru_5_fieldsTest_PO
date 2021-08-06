package qaguru_demoqa.pages;

import com.codeborne.selenide.SelenideElement;
import qaguru_demoqa.components.Calendar;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.lang.String.format;

public class RegistrationPage {

    private SelenideElement modal = $("[role=dialog]"); //для проверок

    private Calendar calendar = new Calendar();

    public void openPage() {
        open("/automation-practice-form");
        //   $("#practice-form-wrapper").shouldHave(text("Student Registration Form"));
    }

    public void typeFirstName(String firstName) {
        $("#firstName").setValue(firstName);

    }

    public void typeLastName(String email) {
        $("#lastName").setValue(email);

    }

    public RegistrationPage typeEmail(String email) {
        $("#userEmail").setValue(email);

        return this;
    }

    public RegistrationPage selectGender(String gender) {
//        $("[name=gender][value=" + gender + "]").parent().click();

        /*
        String a = "1234%s890";
        String b = format(a, 567);
        если вывести "b" в лог, то -> 1234567890
        String a = "1234%s8%s0";
        String b = format(a, 567, 9);
        если вывести "b" в лог, то -> 1234567890
        иначе будет выглядеть некрасиво
        String b = "1234" + "567" +  "8" + "9" + "0";
         */
        $(format("[name=gender][value=%s]", gender)).parent().click();

        return this;
    }

    public RegistrationPage typeMobile(String mobile) {
        $("#userNumber").setValue(mobile);

        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        calendar.setDate(day, month, year);
        return this;
    }

    public RegistrationPage typeSubject(String subject) {
        $("#subjectsInput").setValue(subject).pressEnter();
        return this;
    }

    public RegistrationPage setHobbies() {
        $(byText("Sports")).click();
        $(byText("Reading")).click();
        $(byText("Music")).click();
        return this;
    }

    public RegistrationPage uploadPicture() {
        $("#uploadPicture").uploadFromClasspath("pic.jpg");//uploadFile(new File("src/test/resources/pic.jpg"));
        return this;
    }

    public RegistrationPage typeAddress(String address) {
        $("#currentAddress").setValue(address);
        return this;
    }

    public RegistrationPage setStateAndCity(String state, String city) {
        $("#state").scrollTo().click(); //вместо scrollIntoView(true)
        $(byText(state)).click();
        $("#city").click();
        $(byText(city)).click();
        return this;
    }

    public RegistrationPage clickSubmit() {
        $("#submit").click();
        return this;
    }


    // проверки
    public void checkResultsTitle() {
        modal.$(".modal-title").shouldHave(text("Thanks for submitting the form"));
    }

    public void checkResultsValue(String value) {
        modal.$(".table-responsive").shouldHave(text(value));
    }

}
