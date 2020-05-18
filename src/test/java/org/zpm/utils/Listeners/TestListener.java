package org.zpm.utils.Listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.service.ExtentTestManager;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerAdapter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.zpm.Driver.DriverHolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class TestListener extends ExtentITestListenerAdapter {
    private String scrFolder = System.getProperty("user.dir") + "\\test-output\\Screenshots\\";

    @Override
    public synchronized void onStart(ITestContext context) {
        super.onStart(context);

        if(Files.isDirectory(Paths.get(scrFolder))) {
            try {
                FileUtils.cleanDirectory(new File(scrFolder));
                System.out.println("Previous screenshots were deleted");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        super.onTestSkipped(result);
        ExtentTest test = ExtentTestManager.getTest();
        test.skip(result.getThrowable().getMessage());
        String scrFileName = result.getName() + "_" + test.getStatus();
        try {
            String base64Screenshot = encodeBase64(takeScr(scrFileName));
            MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build();
            ExtentTestManager.getTest().skip("Test fail image:", mediaModel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        super.onTestFailure(result);
        ExtentTest test = ExtentTestManager.getTest();
        test.fail(result.getThrowable().getMessage());
        String scrFileName = result.getName() + "_" + test.getStatus();
        try {
            String base64Screenshot = encodeBase64(takeScr(scrFileName));
            MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build();
            ExtentTestManager.getTest().fail("Test fail image:", mediaModel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File takeScr(String scrName) {
        TakesScreenshot ts = (TakesScreenshot) DriverHolder.INSTANCE.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        String filePath = scrFolder + scrName + ".png";
        File scrFile = new File(filePath);
        try {
            FileUtils.copyFile(source, scrFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scrFile;
    }

    public static String encodeBase64(File scrName) {
        String encodedBase64 = null;
        try {
            encodedBase64 = Base64.getEncoder().encodeToString(
                    FileUtils.readFileToByteArray(scrName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encodedBase64;
    }
}