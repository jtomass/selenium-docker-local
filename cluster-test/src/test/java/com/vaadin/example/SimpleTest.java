package com.vaadin.example;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.vaadin.teemusa.undertow.UndertowRule;

import com.vaadin.testbench.By;
import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbench.annotations.BrowserConfiguration;
import com.vaadin.testbench.annotations.RunOnHub;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.LabelElement;
import com.vaadin.testbench.elements.TextFieldElement;
import com.vaadin.testbench.parallel.BrowserUtil;
import com.vaadin.testbench.parallel.ParallelRunner;
import com.vaadin.testbench.parallel.ParallelTest;

@RunOnHub()
public class SimpleTest extends ParallelTest {

    @BrowserConfiguration
    public List<DesiredCapabilities> getBrowsersToTest() {
        return Arrays.asList(BrowserUtil.chrome(), BrowserUtil.firefox());
    }

    @ClassRule
    public static UndertowRule serverRule = UndertowRule.withUI(MyUI.class);

    @Test
    public void openPageAndEnterName() {
        getDriver().get(serverRule.getServer().getBaseURL() + "?debug");

        String userName = "Tim";
        $(TextFieldElement.class).first().setValue(userName);
        $(ButtonElement.class).first().click();
        Assert.assertEquals("Unexpected label text",
                MyUI.GREETER.apply(userName),
                $(LabelElement.class).first().getText());

        Assert.assertFalse(
                isElementPresent(By.className("v-Notification-error")));
    }
}
