package tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegPage;
import java.util.Locale;

import static utils.TextUtils.capitalize;

public class SuccessFillTheRegForm {

  RegPage registrationPage = new RegPage();
  Faker faker = new Faker(new Locale("de"));
  // Test data
  String firstName = faker.name().firstName();
  String lastName = faker.name().lastName();
  String email = faker.internet().emailAddress();
  String gender = capitalize(faker.dog().gender());
  String phoneNum = faker.phoneNumber().subscriberNumber(10);
  String dayOfBirth = "09", monthOfBirth = "February", yearOfBirth = "1980";
  String subjects1 = "Computer Science", subjects2 = "English";
  String hobbie1 = "Sports", hobbie2 = "Music";
  String avatar = "gravatar.png";
  String curAddress = faker.address().fullAddress();
  String state = "NCR", city = "Delhi";

  @BeforeAll
  static void setup() {
    Configuration.baseUrl = "https://demoqa.com";
    Configuration.startMaximized = true;
  }

  @Test
  void positiveFillTextFormTest() {
    registrationPage.openPage();

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
        .uploadFile(avatar)
        .typeAddress(curAddress)
        .typeLocate(state, city);

    registrationPage.submitForm();

    // check set values
    registrationPage.checkResultsTitle();
    registrationPage
        .checkResultsValue(firstName + " " + lastName)
        .checkResultsValue(email)
        .checkResultsValue(gender)
        .checkResultsValue(phoneNum)
        .checkResultsValue(dayOfBirth + " " + monthOfBirth + "," + yearOfBirth)
        .checkResultsValue(subjects1 + ", " + subjects2)
        .checkResultsValue(hobbie1 + ", " + hobbie2)
        .checkResultsValue(avatar)
        .checkResultsValue(curAddress)
        .checkResultsValue(state + " " + city);
  }
}
