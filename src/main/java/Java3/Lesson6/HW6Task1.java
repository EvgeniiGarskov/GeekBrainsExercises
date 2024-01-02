package Java3.Lesson6;

import java.util.Arrays;

public class HW6Task1 {

    /*
    1. Написать метод, которому в качестве аргумента передается не пустой одномерный
    целочисленный массив. Метод должен вернуть новый массив, который получен путем
    вытаскивания из исходного массива элементов, идущих после последней четверки. Входной
    массив должен содержать хотя бы одну четверку, иначе в методе необходимо выбросить
    RuntimeException.
    Написать набор тестов для этого метода (по 3-4 варианта входных данных).
    Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ].

    2. Написать метод, который проверяет состав массива из чисел 1 и 4. Если в нем нет хоть одной
    четверки или единицы, то метод вернет false; Написать набор тестов для этого метода (по 3-4
    варианта входных данных).
    [ 1 1 1 4 4 1 4 4 ] -> true
    [ 1 1 1 1 1 1 ] -> false
    [ 4 4 4 4 ] -> false
    [ 1 4 4 1 1 4 3 ] -> false
     */

    public static void main(String[] args) {

//        int[] arr = {1, 4, 3, 4, 5, 6, 7};
//        int[] arr = {3, 5, 6, 7};
//        int[] arr = {4};
//        System.out.println(Arrays.toString(checkArr(arr)));
    }

    public static int[] returnAnArray(int[] array) {
//        if (array.length <= 1) {
//            return array;
//        }
//        try {
        boolean test = false;
        for (int o : array) {
            if (o == 4) {
                test = true;
                break;
            }
        }
        if (test == false) {
            throw new RuntimeException("В массиве нет числа 4");
        }

        int a = 0;

        for (int i = 0; i < array.length; i++) {
            while (array[i] == 4) {
                a = i;
                break;
            }
        }
        int[] copy = Arrays.copyOfRange(array, a + 1, array.length);
        return copy;
//        } catch (RuntimeException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//        return array;
    }
}
