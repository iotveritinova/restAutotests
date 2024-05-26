package ThreadLocaTrainingGround;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ThreadTest {
    private String myVar1;
    private String myVar2;
    private String combo1;
    private ThreadLocal<String> comboTL = new ThreadLocal<>();
    private ThreadLocal<String> myVar1TL= new ThreadLocal<>();
    private ThreadLocal<String> myVar2TL= new ThreadLocal<>();


    @BeforeMethod
    public void init() {
        myVar1 = RandomStringUtils.random(3, false, true);
        myVar2 = RandomStringUtils.random(3, true, false);
        combo1 = myVar1 + myVar2;
        comboTL.set(combo1);
        myVar1TL.set(myVar1);
        myVar2TL.set(myVar2);
        System.out.println(myVar1TL.get());
    }

    @Test(threadPoolSize = 3, invocationCount = 6, invocationTimeOut = 10000)
    public void threadyTest() {
        String combo2 = myVar1TL.get() + myVar2TL.get();
        String combo1New = comboTL.get();
        System.out.println(String.format("combo1 = %s with combo2 = %s", combo1New, combo2));
        Assert.assertEquals(combo1New, combo2);
    }

    @AfterMethod
    public void down() {
        System.out.println(String.format("removed:%s,%s,%s",myVar1TL.get(),myVar2TL.get(),comboTL.get()));
        myVar1TL.remove();
        myVar2TL.remove();
        comboTL.remove();

    }
}