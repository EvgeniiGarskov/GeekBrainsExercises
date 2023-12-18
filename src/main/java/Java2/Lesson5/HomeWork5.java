package Java2.Lesson5;

import java.util.Arrays;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.System.currentTimeMillis;

public class HomeWork5 {

    /*
    Необходимо написать два метода, которые делают следующее:
    1) Создают одномерный длинный массив.
    2) Заполняют этот массив единицами.
    3) Засекают время выполнения: long a = System.currentTimeMillis().
    4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
    arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
    5) Проверяется время окончания метода System.currentTimeMillis().
    6) В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a).
    Отличие первого метода от второго:
        ● Первый просто бежит по массиву и вычисляет значения (это видно из кода выше).
        ● Второй разбивает массив на два массива, в двух потоках высчитывает новые значения и
        потом склеивает эти массивы обратно в один.
     */

    public static void main(String[] args) {

        test();
    }

    public static void test() {
        final int SIZE = 10000000;
        final int THREADS_COUNT = 2;
        // определяем размерность двумерного массива
        final int PART_SIZE = SIZE / THREADS_COUNT;
        float[] mas = new float[SIZE];
        Arrays.fill(mas, 1f);
        long a = currentTimeMillis();
        // разделяем данные
        final float[][] m = new float[THREADS_COUNT][PART_SIZE];
        // создадим массив потоков
        Thread[] t = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            // будем копировать в двумерный массив данные из основного потока со сдвигом
            System.arraycopy(mas, PART_SIZE * i, m[i], 0, PART_SIZE);
            final int u = i;
            t[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    // считаем массив со сдвигом
                    int n = u * PART_SIZE;
                    for (int j = 0; j < PART_SIZE; j++, n++) {
                        // arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                        m[u][j] = (float) (m[u][j] * sin(0.2f + n / 5) * cos(0.2f + n / 5) * cos(0.4f + n / 2));
                    }
                }
            });
            t[i].start();
        }
        try {
            for (int i = 0; i < THREADS_COUNT; i++) {
                t[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // складываем массив обратно в исходный массив
        for (int i = 0; i < THREADS_COUNT; i++) {
            System.arraycopy(m[i], 0, mas, i * PART_SIZE, PART_SIZE);
        }
        // определяем время
        System.out.println(currentTimeMillis() - a);
    }
}
