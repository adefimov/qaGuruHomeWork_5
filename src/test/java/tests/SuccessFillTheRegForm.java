package tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegPage;

import java.io.File;
import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static utils.TextUtils.capitalize;

public class SuccessFillTheRegForm {

  private static final String FORM_TITLE = "Student Registration Form";
  private static final String RESULTS_TITLE = "Thanks for submitting the form";

  RegPage registrationPage = new RegPage();
  Faker faker = new Faker(new Locale("de"));
  // Test data
  String firstName = faker.name().firstName();
  String lastName = faker.name().lastName();
  String email = faker.internet().emailAddress();
  String gender = capitalize(faker.dog().gender());
  String phoneNum = faker.numerify("##########");
  //   String phoneNum = faker.phoneNumber().phoneNumber(); don't work because bug on form
  String dayOfBirth = "09", monthOfBirth = "February", yearOfBirth = "1980";
  String subjects1 = "Computer", subjects2 = "Eng";
  String hobbie1 = "Sports", hobbie2 = "Music";
  String state = "NCR", city = "Delhi";
  String curAddress = faker.address().fullAddress();

  @BeforeAll
  static void setup() {
    Configuration.baseUrl = "https://demoqa.com";
    Configuration.startMaximized = true;
  }

  @Test
  void positiveFillTextFormTest() {
    registrationPage.openPage();
    $(".practice-form-wrapper").shouldHave(text(FORM_TITLE));

    registrationPage
        .typeFirstName(firstName)
        .typeLastName(lastName)
        .typeEmail(email)
        .selectGender(gender)
        .typePhoneNum(phoneNum)
        .setDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth)
        .typeSubject(subjects1)
        .typeSubject(subjects2)
        .typeHobbies(hobbie1)
        .typeHobbies(hobbie2)
        .typeAddress(curAddress)
        .typeLocate(state, city);

    $("#uploadPicture").uploadFile(new File("src/test/resources/gravatar.png"));

    $("#submit").scrollIntoView(true).click();

    // check set values
    registrationPage.checkResultsTitle(RESULTS_TITLE);
    registrationPage
        .checkResultsValue(firstName + " " + lastName)
        .checkResultsValue(email)
        .checkResultsValue(gender)
        .checkResultsValue(phoneNum)
        .checkResultsValue(dayOfBirth + " " + monthOfBirth + "," + yearOfBirth)
        .checkResultsValue(hobbie1 + ", " + hobbie2)
        .checkResultsValue(curAddress)
        .checkResultsValue(state + " " + city);

    $("tbody").$(byText("Subjects")).parent().shouldHave(text("Computer Science, English"));
    $("tbody").$(byText("Picture")).parent().shouldHave(text("gravatar.png"));
  }
}
