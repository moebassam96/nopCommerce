package nopcommerce;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCases extends Parameters {
	String FirstNameForemail = FirstName[Randomfirsrname];
	String EmailLastName = LastName[RandomLastname];
	int a = RandomEmail;

	@BeforeTest
	public void MyBeforeTest() {
		driver.get(TheWebsite);
		driver.manage().window().maximize();

	}

	@Test(priority = 1)
	public void Register() {
		driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/div[2]/div[1]/ul/li[1]/a")).click();
		WebElement Gender = driver.findElement(By.xpath("//*[@id=\"gender-male\"]"));
		Gender.click();
		WebElement Fname = driver.findElement(By.xpath("//*[@id=\"FirstName\"]"));

		Fname.sendKeys(FirstNameForemail);
		WebElement Lname = driver.findElement(By.xpath("//*[@id=\"LastName\"]"));

		Lname.sendKeys(EmailLastName);
		WebElement DayDropDown = driver.findElement(
				By.xpath("/html/body/div[6]/div[3]/div/div/div/div[2]/form/div[1]/div[2]/div[4]/div/select[1]"));
		Select selector = new Select(DayDropDown);
		selector.selectByValue("21");
		WebElement MonthDropDown = driver.findElement(
				By.xpath("/html/body/div[6]/div[3]/div/div/div/div[2]/form/div[1]/div[2]/div[4]/div/select[2]"));
		Select selector1 = new Select(MonthDropDown);
		selector1.selectByValue("3");
		WebElement YearDropDown = driver.findElement(
				By.xpath("/html/body/div[6]/div[3]/div/div/div/div[2]/form/div[1]/div[2]/div[4]/div/select[3]"));
		Select selector2 = new Select(YearDropDown);
		selector2.selectByValue("1996");
		WebElement EmailRegistration = driver.findElement(By.xpath("//*[@id=\"Email\"]"));
		EmailRegistration.sendKeys(FirstNameForemail + a + EmailLastName + DummyEmail);
		WebElement Password = driver.findElement(By.xpath("//*[@id=\"Password\"]"));
		Password.sendKeys(Pass);
		WebElement PasswordConfirmation = driver.findElement(By.xpath("//*[@id=\"ConfirmPassword\"]"));
		PasswordConfirmation.sendKeys(Pass);
		driver.findElement(By.xpath("//*[@id=\"register-button\"]")).click();
		String Expected = "Your registration completed";
		String Actual = driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[2]/div[1]")).getText();
		MyAssert.assertEquals(Actual, Expected);

	}

	@Test(priority = 2)
	public void Login() throws InterruptedException {
		driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/div[2]/div[1]/ul/li[2]/a")).click();
		WebElement LoginEmail = driver.findElement(By.xpath("//*[@id=\"Email\"]"));
		LoginEmail.sendKeys(FirstNameForemail + a + EmailLastName + DummyEmail);
		WebElement PasswordConfirmation = driver.findElement(By.xpath("//*[@id=\"Password\"]"));
		PasswordConfirmation.sendKeys(Pass);
		driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[2]/div[1]/div[2]/form/div[3]/button"))
				.click();
		String Expected = "Log out";
		String Actual = driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/div[2]/div[1]/ul/li[2]/a"))
				.getText();
		MyAssert.assertEquals(Actual, Expected);
		Thread.sleep(3000);
	}

	@Test(priority = 3)
	public void NoProductMessage() {
		driver.findElement(By.xpath("//*[@id=\"small-searchterms\"]")).sendKeys("test");
		driver.findElement(By.xpath("//*[@id=\"small-search-box-form\"]/button")).submit();
		String Expected = "No products were found that matched your criteria.";
		String Actual = driver
				.findElement(By.xpath("/html/body/div[6]/div[3]/div/div[2]/div/div[2]/div[2]/div/div[2]/div"))
				.getText();
		MyAssert.assertEquals(Actual, Expected);

	}

	@Test(priority = 4)
	public void AddToCart() throws InterruptedException {
		WebElement Searchbox = driver.findElement(By.xpath("//*[@id=\"small-searchterms\"]"));
		Searchbox.clear();
		Searchbox.sendKeys("Macbook");
		driver.findElement(By.xpath("//*[@id=\"small-search-box-form\"]/button")).submit();
		driver.findElement(By.xpath(
				"/html/body/div[6]/div[3]/div/div[2]/div/div[2]/div[3]/div/div[2]/div/div/div/div/div[2]/div[3]/div[2]/button[1]"))
				.click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"add-to-cart-button-4\"]")).click();
		Thread.sleep(3000);
		WebElement AddingProduct = driver.findElement(By.xpath("//*[@id=\"bar-notification\"]/div"));
		String Actual = AddingProduct.getText();
		String Expected = "The product has been added to your shopping cart";
		MyAssert.assertEquals(Actual, Expected);

	}

	@SuppressWarnings("deprecation")
	@Test(priority = 5)
	public void Checkout() throws InterruptedException {
		driver.get("https://demo.nopcommerce.com/cart");
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"termsofservice\"]")).click();
		driver.findElement(By.id("checkout")).click();
		WebElement CityDropDown=driver.findElement(By.xpath("//*[@id=\"BillingNewAddress_CountryId\"]"));
		Select myselector=new Select(CityDropDown);
		myselector.selectByValue("140");
		driver.findElement(By.xpath("//*[@id=\"BillingNewAddress_City\"]")).sendKeys("Amman");
		driver.findElement(By.xpath("//*[@id=\"BillingNewAddress_Address1\"]")).sendKeys("jubeha");
		driver.findElement(By.xpath("//*[@id=\"BillingNewAddress_ZipPostalCode\"]")).sendKeys("123456");
		driver.findElement(By.xpath("//*[@id=\"BillingNewAddress_PhoneNumber\"]")).sendKeys("0781057017");
		driver.findElement(By.xpath("//*[@id=\"billing-buttons-container\"]/button[4]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"shipping-method-buttons-container\"]/button")).click();
		driver.findElement(By.xpath("//*[@id=\"payment-method-buttons-container\"]/button")).click();
		driver.findElement(By.xpath("//*[@id=\"payment-info-buttons-container\"]/button")).click();
		driver.findElement(By.xpath("//*[@id=\"confirm-order-buttons-container\"]/button")).click();
		String Actual=driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[2]/div/div[1]/strong")).getText();
		String Expected="Your order has been successfully processed!";
		MyAssert.assertEquals(Actual, Expected);


		
	}
	@AfterTest
	public void MyAfterTest() throws InterruptedException {
		Thread.sleep(10000);
		driver.close();
	}

}
