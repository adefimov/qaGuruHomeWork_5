package pages;

import com.codeborne.selenide.SelenideElement;
import utils.Calendar;

import static com.codeborne.selenide.Condition.text;
import static java.lang.String.format;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegPage {
  private SelenideElement modal = $("[role=dialog]");

  public void openPage() {
    open("/automation-practice-form");
  }

  public RegPage typeFirstName(String firstName) {
    $("#firstName").setValue(firstName);
    return this;
  }

  public RegPage typeLastName(String lastName) {
    $("#lastName").setValue(lastName);
    return this;
  }

  public RegPage typeEmail(String email) {
    $("#userEmail").setValue(email);
    return this;
  }

  public RegPage selectGender(String gender) {
    // $("#genterWrapper").$(byText(format("%s", gender))).click();
    $(format("[name=gender][value=%s]", gender)).parent().click();
    return this;
  }

  public RegPage typePhoneNum(String phoneNum) {
    $("#userNumber").setValue(String.valueOf(phoneNum));
    return this;
  }

  public RegPage setDateOfBirth(String day, String month, String year) {
    Calendar.setDate(day, month, year);
    return this;
  }

  public RegPage typeSubject(String subjects) {
    $("#subjectsInput").setValue(subjects).pressEnter();
    return this;
  }

  public RegPage typeHobbies(String hobbies) {
    $("#hobbiesWrapper").$(byText(hobbies)).scrollIntoView(true).click();
    return this;
  }

  public RegPage typeAddress(String address) {
    $("#currentAddress").setValue(address).pressEnter();
    return this;
  }

  public RegPage typeLocate(String state, String city) {
    $("#state").click();
    $(byText(state)).click();
    $("#city").click();
    $(byText(city)).click();
    return this;
  }

  public void checkResultsTitle(String title) {
    modal.$(".modal-title").shouldHave(text(title));
  }

  public RegPage checkResultsValue(String value) {
    modal.$(".table-responsive").shouldHave(text(value));
    return this;
  }
}
