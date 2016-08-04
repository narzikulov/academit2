package ru.academit.narzikulov;
/*
Задача 5. Сделать свою реализацию хэш-таблицы, сделать ее generic’ом.
Реализовать:
1.	Вставку элемента в таблицу
2.	Проверка, что элемент есть в таблице
3.	Получение числа элементов
4.	Удаление элемента из таблицы
5.	В одном из конструкторов сделать параметр, который задает размер массива хэш-таблицы
Дополнительно:
1.	Реализовать интерфейс Iterable<T>
2.	Реализовать интерфейс Collection<T> (включает в себя Iterable<T>)
 */


import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by tim on 28.12.2015.
 */
public class HashTableMain {
    public static void main() {
        HashTable<Object> hTable = new HashTable<>();

        hTable.add("ABCDEF4646");
        hTable.add(-5);
        hTable.add(98);
        hTable.add(38);
        hTable.add("a");
        hTable.add(97);
        hTable.add("two");
        hTable.add(76);
        hTable.add("three");
        hTable.add(86);
        hTable.add("--test");
        hTable.add(5);
        hTable.add(23);
        hTable.add("two");
        hTable.add("11");
        hTable.add("three");
        hTable.add(34);
        hTable.add("three");
        hTable.add(Math.PI);
        hTable.add(1);
        hTable.add(34);
        hTable.add("Hello world!");
        hTable.add("Hello world?");

        System.out.println(hTable.toString());
        System.out.println("Num of elements in Hash Table: " + hTable.size());

        System.out.println(hTable.contains(5));
        System.out.println(hTable.contains("5"));
        hTable.remove(5);
        hTable.remove(1);
        hTable.remove("two");
        System.out.println("Deleted elements: " + "5, " + "1" + " and " + "two");
        System.out.println(hTable.toString());
        System.out.println("Num of elements in Hash Table: " + hTable.size());

        //hTable.clear();
        System.out.println(hTable.toString());
        System.out.println(hTable.isEmpty());

        Object[] array = hTable.toArray();
        for (Object anArray : array) System.out.print(anArray + "; ");

        ArrayList<Object> retainCollection = new ArrayList<>();
        retainCollection.add("three");
        retainCollection.add("two");
        retainCollection.add(34);
        retainCollection.add(2);
        retainCollection.add(-5);
        retainCollection.add("ABCDEF4646");
        retainCollection.add(Math.PI);
        retainCollection.add("Hello world!");

        System.out.println();
        System.out.println();
        System.out.print("retain collection: ");
        System.out.println(retainCollection.toString());
        hTable.retainAll(retainCollection);
        System.out.println(hTable.toString());

        System.out.println();
        System.out.println("Iterator");
        Iterator iterator = hTable.iterator();
        while (iterator.hasNext()) {
            Object element = iterator.next();
            System.out.print(element.toString() + "; ");
        }
    }
}
