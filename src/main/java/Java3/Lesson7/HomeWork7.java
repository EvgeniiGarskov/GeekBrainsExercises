package Java3.Lesson7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HomeWork7 {

    /*
    1. Создать класс, который может выполнять «тесты».
    В качестве тестов выступают классы с наборами методов, снабженных аннотациями @Test. Класс,
    запускающий тесты, должен иметь статический метод start(Class testClass), которому в качестве
    аргумента передается объект типа Class. Из «класса-теста» вначале должен быть запущен метод с
    аннотацией @BeforeSuite, если он присутствует. Далее запускаются методы с аннотациями @Test, а
    по завершении всех тестов – метод с аннотацией @AfterSuite.
    К каждому тесту необходимо добавить приоритеты (int-числа от 1 до 10), в соответствии с которыми
    будет выбираться порядок их выполнения. Если приоритет одинаковый, то порядок не имеет
    значения. Методы с аннотациями @BeforeSuite и @AfterSuite должны присутствовать в
    единственном экземпляре. Если это не так – необходимо бросить RuntimeException при запуске
    «тестирования»
     */

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        processing(TestClass.class);
    }

    public static void processing(Class c) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = c.getDeclaredMethods();
        List<Method> list = new ArrayList<>();

        //проходим по всем методам и добавляем в список методы с анотацией
        for (Method o : methods) {
            //проверяем наличие требуемой нам анотации
            if (o.isAnnotationPresent(Test.class)) {
                //если попадаем в диапазон от 1 до 10, то добавляем методы в наш список
                int prio = o.getAnnotation(Test.class).priority();
                if (prio < 1 || prio > 10) throw new RuntimeException("Priority exception!");
                list.add(o);
            }
        }
        //далее нужно отсортировать методы по приоритету
        list.sort(new Comparator<Method>() {
            @Override
            public int compare(Method o1, Method o2) {
                //вычитаем один приоритет из другого
                //все методы отсортируются в убывающем порядке
                return o2.getAnnotation(Test.class).priority() - o1.getAnnotation(Test.class).priority();
            }
        });
        // далее делаем вторую пробежку по методам и смотрим на анотации BeforeSuite
        // и добавляем их на нулевую позицию, чтобы они были первыми в списке
        for (Method o: methods) {
            if(o.isAnnotationPresent(BeforeSuite.class)) {
                // при условии что на первом месте уже не стоит данный класс
                // так как BeforeSuite должен быть на первом месте
                if(list.get(0).isAnnotationPresent(BeforeSuite.class))
                    throw new RuntimeException("BeforeSuite exception");
                list.add(0, o);
            }
            // аналогично делаем для AfterSuite на последнем месте
            if(o.isAnnotationPresent(AfterSuite.class)) {
                if(list.get(list.size() - 1).isAnnotationPresent(AfterSuite.class))
                    throw new RuntimeException("AfterSuite exception");
                list.add(o);
            }
        }
        // вызываем каждый объект из списка
        for (Method o: list) {
            o.invoke(null);
        }
    }
}
