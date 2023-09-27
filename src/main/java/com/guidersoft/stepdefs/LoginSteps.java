package com.guidersoft.stepdefs;

import com.guidersoft.config.TestConfig;
import com.guidersoft.config.TestConfigReader;
import com.guidersoft.pageobjects.Homepage;
import com.guidersoft.pageobjects.LoginSignup;
import com.guidersoft.pageobjects.Menu;
import com.guidersoft.pageobjects.MenuObjects;
import com.guidersoft.webdriver.Driver;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.util.Map;
import java.util.Properties;

public class LoginSteps {

    Homepage home = new Homepage();
    MenuObjects menu = new MenuObjects();

    LoginSignup loginSignup = new LoginSignup();

    @Given("user on homepage")
    public void userOnHomepage() {
        home.gotoPage();
    }


    @When("^user clicks (LOGIN|SIGNUP|HOME|CART|PRODUCTS|CONTACTUS) on menu$")
    public void userClicksSignupLoginOnMenu(String menuText) {

        switch (menuText){
            case "LOGIN":
            case "SIGNUP":
                menu.signupLogin.click();
                break;
            case "HOME":
                menu.home.click();
                break;
            case "CART":
                menu.cart.click();
                break;
            case "PRODUCTS":
                menu.products.click();
                break;
            case "CONTACTUS":
                menu.contactUs.click();
        }

        /*
        String locatorStr = "//ul[@class='nav navbar-nav']//a[contains(., '%s')]";
        By locator = null;
        switch (menuText){
            case "LOGIN":
            case "SIGNUP":
                locatorStr = String.format(locatorStr, "Signup / Login");
                locator = By.xpath(locatorStr);
                break;
            case "HOME":

                break;

        }
         */



    }

    @When("^user clicks (LOGIN|SIGNUP|HOME|CART|PRODUCTS|CONTACTUS) on menu with Enum$")
    public void userClicksSignupLoginOnMenuWithEnum(String menuText) {

        Menu.valueOf(menuText).click();

        /*
        switch (menuText){
            case "LOGIN":
            case "SIGNUP":
                Menu.LOGIN.click();
                break;
            case "HOME":
                Menu.HOME.click();
                break;
            case "CART":
                Menu.CART.click();
                break;
            case "PRODUCTS":
                Menu.PRODUCTS.click();
                break;
            case "CONTACTUS":
                Menu.CONTACTUS.click();
        }
         */

    }

    @And("user login as {string}")
    public void userFillsTheLoginFormAs(String userType) {
        /*
        TestConfig.User user = TestConfigReader.instance().getConfig()
                .getUsers().stream().filter(u -> u.getType().equals(userType))
                .findFirst().get();
        */

        /*
        TestConfig.User user = TestConfigReader.instance().getConfig().getUser(userType);

        loginSignup.login(user.getUsername(), user.getPassword());
         */

        loginSignup.login(userType);

    }

    @Then("login should be successful")
    public void loginShouldBeSuccessful() {
        Menu.LOGOUT.shouldBeVisible();
    }

    @And("user login email as {string} and password as {string}")
    public void userLoginEmailAsAndPasswordAs(String email, String password) {
        loginSignup.login(email, password);
    }

    @And("user login with following credentials")
    public void userLoginWithFollowingCredentials(DataTable table) {
        Map<String, String> data = table.asMap();
        String email = data.get("email");
        String password = data.get("password");
        loginSignup.login(email, password);
    }

    @And("user fills the New User Signup form as follows then clicks signup")
    public void userFillsTheNewUserSignupFormAsFollows(DataTable table) {
        Map<String, String> data = table.asMap();

        String name = data.get("name");
        String email = data.get("email");
        loginSignup.signup(name, email);

    }

    Properties properties = new Properties();
    @When("user save {string} as {string}")
    public void userSaveAs(String value, String variable) {
        properties.setProperty(variable, value);
    }

    @Then("{string} degeri {string} olmali")
    public void degeriOlmali(String variable, String value) {
        String val = properties.getProperty(variable);
        Assert.assertEquals(val, value);
    }
}
