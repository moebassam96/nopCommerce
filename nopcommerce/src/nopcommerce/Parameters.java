package nopcommerce;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.Assertion;

public class Parameters {
WebDriver driver=new ChromeDriver();
String TheWebsite="https://demo.nopcommerce.com/";
Assertion MyAssert = new Assertion();
Random rand=new Random();
String[] FirstName = { "ahmad", "mohammad", "zain", "johhny" };
int Randomfirsrname = rand.nextInt(0, 4);
String[] LastName = { "ahmad", "mohammad", "zain", "johhny" };
int RandomLastname = rand.nextInt(0,4);
String DummyEmail="@Gmail.com";
int RandomEmail=rand.nextInt(0,100);
String Pass="MBD12345**";
}
