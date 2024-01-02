package Java3.Lesson6;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestHW6Task2 {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {true, new int[]{1, 1, 1, 4, 4, 1, 4, 4}},
                {true, new int[]{1, 1, 1, 1, 1, 1}},
                {true, new int[]{4, 4, 4, 4}},
                {true, new int[]{1, 4, 4, 1, 1, 4, 3}},
        });
    }

    private boolean a;
    private int[] b;

    public TestHW6Task2(boolean a, int[] b) {
        this.a = a;
        this.b = b;
    }

    HW6Task2 hw6Task2;

    @Before
    public void init() {
        hw6Task2 = new HW6Task2();
    }

    @Test
    public void massTestHW6Task2() {
        Assert.assertEquals(a, hw6Task2.checkArray(b));
    }
}
