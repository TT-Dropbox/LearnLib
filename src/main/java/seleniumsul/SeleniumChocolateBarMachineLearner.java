package seleniumsul;

import com.google.common.collect.ImmutableSet;
import de.learnlib.api.SUL;
import basiclearner.BasicLearner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.util.Collection;

/**
 * Created by ramon on 13-12-16.
 */
public class SeleniumChocolateBarMachineLearner {
    public static void main(String[] args) throws IOException {
        /* Ensure that the following parameters are correctly set! */
        String candyMachineURI = "file:C:/School/Master Jaar 1/ATT/learnlib-assignment/candy-machine-website/website.htm";
        String geckoDriverLocation = "C:/Users/Sam/Downloads/geckodriver-v0.23.0-win64/geckodriver.exe"; // e.g. for firefox, the path of the geckodriver-file
        System.setProperty("webdriver.gecko.driver", geckoDriverLocation);
        WebDriver driver = new FirefoxDriver();

        /* If all is set, we can start learning */
        Collection<String> inputAlphabet = ImmutableSet.of("5ct", "10ct", "mars", "snickers", "twix");
        SUL<String, String> sul = new SeleniumSUL(candyMachineURI, driver);
        BasicLearner.runControlledExperiment(
                sul,
                BasicLearner.LearningMethod.LStar,
                BasicLearner.TestingMethod.RandomWalk,
                inputAlphabet);

        driver.close();
    }

}
