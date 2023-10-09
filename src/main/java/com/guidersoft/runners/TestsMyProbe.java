package com.guidersoft.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/main/resources/features"}
        ,glue = {"com/guidersoft/stepdefs"}
        ,tags = "@ContactUs"
        ,plugin = {"pretty"}
)

public class TestsMyProbe {

}