package cn.tjucic.selenium;

import java.util.regex.Pattern;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.csvreader.CsvReader;

public class TestBaidu {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		String driverPath = System.getProperty("user.dir") + "/src/sr/geckodriver.exec";
		System.setProperty("webdriver.gecko.driver", driverPath);
		driver = new FirefoxDriver();
		baseUrl = "http://121.193.130.195:8800//";
	}

	@Test
  public void testBaidu() throws Exception {
//    driver.get(baseUrl + "/");
//    WebElement input_name = driver.findElement(By.name("id"));
//	input_name.clear();
//	input_name.sendKeys("3016218051");
//	WebElement input_pwd = driver.findElement(By.name("password"));
//	input_pwd.clear();
//	input_pwd.sendKeys("218051");
//	WebElement btn = driver.findElement(By.id("btn_login"));
//	btn.click();
//	assertEquals("3016218051", driver.findElement(By.id("student-id")).getText());
	  String infoPath = System.getProperty("user.dir") + "/src/sr/records.csv";
		CsvReader r = new CsvReader(infoPath, ',', Charset.forName("UTF-8"));
		// 读取表头
		r.readHeaders();
		r.readHeaders();
		String[] head = r.getHeaders();
		// 逐条读取记录，直至读完
		while (r.readRecord()) {
			// 读取一条记录
			// 按列名读取这条记录的值
			String number_csv = r.get(head[1]);
			String name_csv = r.get(head[2]);
			String address_csv = r.get(head[3]);
//			System.out.println(number_csv + " " + name_csv + " " + address_csv);
			String pwd_csv = number_csv.substring(number_csv.length() - 6, number_csv.length());
			// 输入用户名
			driver.get(baseUrl);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement input_name = driver.findElement(By.name("id"));
			input_name.clear();
			input_name.sendKeys(number_csv);
			// 输入密码
			WebElement input_pwd = driver.findElement(By.name("password"));
			input_pwd.clear();
			input_pwd.sendKeys(pwd_csv);
			// 点击登录按钮
			WebElement btn = driver.findElement(By.id("btn_login"));
			btn.click();
			// 比较查询信息
			assertEquals(name_csv, driver.findElement(By.id("student-name")).getText());
			assertEquals(number_csv, driver.findElement(By.id("student-id")).getText());
			assertEquals(address_csv, driver.findElement(By.id("student-git")).getText());
			driver.quit();
		}
		r.close();
//    WebElement we = driver.findElement(By.id("kw"));
//    we.click();
////    driver.findElement(By.id("kw")).click();
//    driver.findElement(By.id("kw")).clear();
//    driver.findElement(By.id("kw")).sendKeys("天津大学");
//    driver.findElement(By.id("su")).click();
//    assertEquals("天津大学_百度搜索", driver.getTitle());
  }

	@After
	public void tearDown() throws Exception {
//    driver.quit();
//    String verificationErrorString = verificationErrors.toString();
//    if (!"".equals(verificationErrorString)) {
//      fail(verificationErrorString);
//    }
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
