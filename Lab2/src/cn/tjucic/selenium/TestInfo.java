package cn.tjucic.selenium;

import static org.junit.Assert.assertEquals;
import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.*;
import com.csvreader.CsvReader;

public class TestInfo {
	private String driverPath;
	private String baseUrl;

	@Before
	public void setUp() throws Exception {
		driverPath = System.getProperty("user.dir") + "/src/sr/geckodriver.exec";
		System.setProperty("webdriver.gecko.driver", driverPath);
	}
	
	@Test
	  public void testBaidu() throws Exception {
		// 生成CsvReader对象，以，为分隔符，GBK编码方式
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

			// 打开火狐浏览器
			String driverPath = System.getProperty("user.dir") + "/src/sr/geckodriver.exec";
			System.setProperty("webdriver.gecko.driver", driverPath);
			WebDriver driver = new FirefoxDriver();
//			// 访问给定网址
			driver.get(baseUrl);
			driver.manage().window().maximize();
			// 输入用户名
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
			assertEquals(name_csv, driver.findElement(By.id("student-name")).getText());
			assertEquals(number_csv, driver.findElement(By.id("student-id")).getText());
			assertEquals(address_csv, driver.findElement(By.id("student-git")).getText());
			driver.close();
		}
		r.close();

	}

}
