package Java3.Lesson6;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestHW6Task1 {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {new int[]{5, 7, 9}, new int[]{1, 2, 4, 3, 4, 5, 7, 9}},
                {new int[]{3, 5, 7, 9}, new int[]{4, 5, 7, 9}},
                {new int[]{5, 7, 9}, new int[]{1, 2, 4, 3, 4, 5, 7, 9, 1}},
                {new int[]{5, 7, 9}, new int[]{4, 4, 4, 4, 5, 7, 9}},
        });
    }
    private int[] expected;
    private int[] actual;

    public TestHW6Task1(int[] expected, int[] actual) {
        this.expected = expected;
        this.actual = actual;
    }

    HW6Task1 hw6Task1;

    @Before
    public void init() {
        hw6Task1 = new HW6Task1();
    }

    @Test
    public void massTestHW6Task1() {
        Assert.assertArrayEquals(expected, hw6Task1.returnAnArray(actual));
    }
//
//    @Test(expected = RuntimeException.class)
//    public void testHW6_2() {
//        Assert.assertArrayEquals(new int[] {3, 5, 7, 9}, hw6.checkArr(new int[]{3, 5, 7, 9}));
//    }
//
//    @Test
//    public void testHW6_3() {
//        Assert.assertArrayEquals(new int[] {5, 7, 9}, hw6.checkArr(new int[]{1, 2, 4, 3, 4, 5, 7, 9, 1}));
//    }
//
//    @Test
//    public void testHW6_4() {
//        Assert.assertArrayEquals(new int[] {5, 7, 9}, hw6.checkArr(new int[]{4, 4, 4, 4, 5, 7, 9}));
//    }
}
