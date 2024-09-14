package by.poit.lab7;

import by.poit.lab7.CEO;
import by.poit.lab7.SysAdmin;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.testng.Assert;
import org.testng.annotations.*;
import org.junit.jupiter.api.Assertions;

@RunWith(value= Suite.class)
@Suite.SuiteClasses(value={TestClass.class, TestClass2.class})
public class SysAdminTest {
    SysAdmin sysAdmin;

    @BeforeSuite
    public void start(){
        System.out.println("Test started");
    }
    @BeforeTest
    public void startTest(){
        System.out.println("Started first @Test");
    }
    @BeforeClass
    public void startTestClass(){
        System.out.println("initialization before class test");
        sysAdmin = new SysAdmin("Ivan Ivanov Ivanovich", 1235.23);
    }

    @BeforeMethod
    public void methods(){
        System.out.println("Before method");
    }

    @DataProvider(name = "testdata")
    public Object[][] createData1() {
        Object[][] objects = {
                {Double.valueOf(123.45), Double.valueOf(123.45)},
                {Double.valueOf(-456.34), Double.valueOf(0)}
        };
        return objects;
    }

    @Test
    void fixTest(){
        Assert.assertTrue(sysAdmin.fix());
    }

    @Test(dataProvider = "testdata")
    void upSalaryTest(double plus, double result){
        sysAdmin.salary = 0;
        Assertions.assertEquals(sysAdmin.upSalary(plus), result);
    }

    @Test
    void downSalaryTest(){
        Assertions.assertThrows(ArithmeticException.class, () -> sysAdmin.downSalary(0));
    }

    @Test(timeOut = 400)
    public void sleepMethod(){
        try {
            Thread.sleep(500);
            System.out.println("Test first sleepMethod");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(timeOut = 400)
    public void sleepMethod2(){
        try {
            Thread.sleep(100);
            System.out.println("Test second sleepMethod");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void addSysAdmin(){
        CEO ceo = new CEO("New CEO", 5323.53);
        ceo.addSysAdmin(sysAdmin);
        Assert.assertEquals(ceo.sysAdmins.size(), 1);
    }

    @Ignore
    @Test
    public void testIgnore(){
        System.out.println("Test ignored");
    }

    @AfterClass
    public void endTestClass(){
        System.out.println("Class test ended");
    }

    @AfterTest
    public void endTest(){
        System.out.println("End of the test");
    }

    @AfterSuite
    public void end(){
        System.out.println("Test ended");
    }
}
