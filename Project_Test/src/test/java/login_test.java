/**
 * Created by houma on 13-Nov-16.
 */

import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class login_test {

        private WebDriver driver;
        private String baseUrl;
        private StringBuffer verificationErrors = new StringBuffer();

        @Before
        public void setUp() throws Exception
        {
            //Please change the webdriver's path
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\houma\\Desktop\\N11 Project\\chromedriver.exe");

            driver = new ChromeDriver();
            baseUrl = "http://www.n11.com/";
        }

        @Test
        public void test() throws Exception {
            //Opening the site
            driver.get(baseUrl + "/");

            //Logging in
            driver.findElement(By.linkText("Giriş Yap")).click();
            driver.findElement(By.xpath("//form[@id='loginForm']/div[4]")).click();
            Thread.sleep(5000);

            //Switching from the main window to the popup
            Set<String> windowId = driver.getWindowHandles();
            Iterator<String> iterator = windowId.iterator();
            String mainWinID = iterator.next();
            String newWinID = iterator.next();
            driver.switchTo().window(newWinID);

            //Entering login information
            driver.findElement(By.id("email")).click();
            driver.findElement(By.id("email")).clear();
            driver.findElement(By.id("email")).sendKeys("agatha.conan7@gmail.com");
            driver.findElement(By.id("pass")).clear();
            driver.findElement(By.id("pass")).sendKeys("zxcqweasd123");
            driver.findElement(By.id("u_0_2")).click();

            //Switching back to main window
            driver.switchTo().window(mainWinID);
            Thread.sleep(5000);

            //Opening the Kitap,Muzik,Film, Oyun Category and asserting the title
            driver.findElement(By.cssSelector("li.catMenuItem > a[title=\"Kitap, Müzik, Film, Oyun\"]")).click();
            assertEquals("Kitap, Müzik, Film, Oyun - PS4, Oyuncak, Puzzle - n11.com", driver.getTitle());

            //Opening the Kitap Sub-Category and asserting the title
            driver.findElement(By.cssSelector("li.mainCat > a[title=\"Kitap\"]")).click();
            assertEquals("Kitap - n11.com", driver.getTitle());

            //Pressing the Yazarlar link and asserting the title
            driver.findElement(By.linkText("Yazarlar")).click();
            assertEquals("Yazarlar - Türk ve Yabancı Yazarlar - n11.com", driver.getTitle());

            //Checking the listing validity
            List<WebElement> alphabetList =  driver.findElements(By.xpath("//div[@class='alphabetPaging']/span"));

            //Iterating between the credentials list
            for (int i =1 ; i<= alphabetList.size(); i++ )
            {
                //Going to the "i" credential page
                driver.findElement(By.xpath("//div[@class='alphabetPaging']/span["+i+"]")).click();
                String pageAlphabet = driver.findElement(By.xpath("//div[@class='alphabetPaging']/span["+i+"]")).getText();

                //Checking if the "i" credential is "0-9"
                if (i == 1)
                {
                    List<WebElement> authorList =  driver.findElements(By.xpath("//div[@id='authorsList']/div/ul/li"));
                    for (WebElement we: authorList)
                    {
                        //Fail Case
                        if (Character.isLetter(we.getText().charAt(0)))
                            Assert.fail("Wrong Credentials");
                    }

                    //Checking for navigation & Checking the first author from the 1st and 2nd navigation page
                    if (authorList.size()>=80)
                    {
                        String firstAuthor = driver.findElement(By.xpath("//div[@id='authorsList']/div/ul/li[1]")).getText();
                        driver.findElement(By.xpath("//div[@class='pagination']/a[2]")).click();
                        String secondAuthor = driver.findElement(By.xpath("//div[@id='authorsList']/div/ul/li[1]")).getText();

                        //Check
                        assertNotEquals(firstAuthor, secondAuthor);
                        //System.out.println(firstAuthor + " =/=" + secondAuthor);
                    }
                }

                //Other Alphabetic credentials
                else
                {
                    List<WebElement> authorList =  driver.findElements(By.xpath("//div[@id='authorsList']/div/ul/li"));
                    for (WebElement we: authorList)
                    {
                        String authorCredential = we.getText().charAt(0) + "";

                        //To check for the Upper and Lower case for every author
                        if (authorCredential.equals(pageAlphabet.toUpperCase()))
                            assertEquals(authorCredential,pageAlphabet.toUpperCase());
                        else if (authorCredential.equals(pageAlphabet.toLowerCase()))
                            assertEquals(authorCredential,pageAlphabet.toLowerCase());
                        //Fail case
                        else
                            Assert.fail("Wrong Credentials");
                    }

                    //Checking for navigation & Checking the first author from the 1st and 2nd navigation page
                    if (authorList.size()>=80)
                    {
                        String firstAuthor = driver.findElement(By.xpath("//div[@id='authorsList']/div/ul/li[1]")).getText();
                        driver.findElement(By.xpath("//div[@class='pagination']/a[2]")).click();
                        String secondAuthor = driver.findElement(By.xpath("//div[@id='authorsList']/div/ul/li[1]")).getText();

                        //Check
                        assertNotEquals(firstAuthor, secondAuthor);
                        //System.out.println(firstAuthor + " =/=" + secondAuthor);
                    }


                }

            }

        }

        @After
        public void tearDown() throws Exception {
            driver.quit();
            String verificationErrorString = verificationErrors.toString();
            if (!"".equals(verificationErrorString)) {
                fail(verificationErrorString);
            }
        }

    }
