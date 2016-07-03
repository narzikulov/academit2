package ru.academit.narzikulov.reflection;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;
/**
 * Created by tim on 02.07.2016.
 */
public class ReflectionMain {
    public static void main() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Scanner scn = new Scanner(System.in);
        String className = scn.nextLine();
        className = "ru.academit.narzikulov.reflection." + className;

        //Получаем имя класса
        Class claz = Class.forName(className);

        // Получаем конструктор
        Constructor constructor = claz.getConstructor(long.class, long.class);

        //создаем объект своего класса методом Reflection
        MyClass1 ourClass = (MyClass1) constructor.newInstance(3, 2);

        System.out.println(ourClass.mult());

        Field f = claz.getDeclaredField("field1");
        f.setAccessible(true);
        f.setInt(ourClass, 4);

        System.out.println("Метод вызванный напрямую");
        System.out.println(ourClass.mult());

        //Вызов метода через Reflection
        Method m = claz.getMethod("sum");
        System.out.println("Метод вызванный через Reflection");
        System.out.println(m.invoke(ourClass));

        System.out.println(f.getLong(ourClass) + 10);

        long sum = 0;
        for (long i = 0; i < 100000000; ++i) {
            //создаем объект своего класса методом Reflection
            MyClass1 ourClass1 = (MyClass1) constructor.newInstance(sum, i);
            sum += (long) m.invoke(ourClass1);
        }
        System.out.println(sum);

        // Подсчет времени исполнения метода путем вызова через invoke и напрямую
        long sum2 = 0;
        for (long i = 0; i < 100000000; ++i) {
            //создаем объект своего класса методом Reflection
            MyClass1 ourClass1 = (MyClass1) constructor.newInstance(sum2, i);
            sum2 += ourClass1.sum();
        }
        System.out.println(sum);

    }
}
