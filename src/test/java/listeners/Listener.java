package listeners;

import base.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class Listener extends BaseTest implements ITestListener {


    public void onTestStart(ITestResult iTestResult) {

    }

    public void onTestSuccess(ITestResult iTestResult) {

    }

    public void onTestFailure(ITestResult iTestResult) {
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        Date date = new Date();
        String screenshotName = date.toString().replace(":","_").replace(" " ,"_")+ ".jpg";
        String filePath = "C:\\Users\\kande\\Desktop\\DataDrivenFramefork\\src\\test\\resources\\screenshots\\" + screenshotName;

        try{
            FileUtils.copyFile(srcFile,new File("./src/test/resources/screenshots/" + screenshotName));
            Reporter.log("<br><img src = '"+filePath+"' height = '400' width = '400'/>");

        } catch (IOException e) {
            e.printStackTrace();
            logger.error("error while taking screenshots");
        }

    }

    public void onTestSkipped(ITestResult iTestResult) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {

    }

    public void onFinish(ITestContext iTestContext) {

    }
}
