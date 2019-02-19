import org.junit.Assert;
import org.junit.Test;

public class TestApp1 {

    @Test
    public void testPrintHelloWorld() {

        System.out.println("test demo。。。。");
        Assert.assertEquals(App.getHelloWorld(), "Hello World");

    }
}
