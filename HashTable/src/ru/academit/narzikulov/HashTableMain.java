package ru.academit.narzikulov;

/**
 * Created by tim on 28.12.2015.
 */
public class HashTableMain {
    public static void main() {
        @SuppressWarnings("unchecked") HashTable<Object> hTable = new HashTable<Object>();

        hTable.add(-5);
        hTable.add("two");
        hTable.add("three");
        hTable.add(4);
        hTable.add(5);
        hTable.add("two");
        hTable.add("three");
        hTable.add(1);
        hTable.add("Hello world!");

        System.out.println(hTable.toString());
        System.out.println("Num of elements in Hash Table: " + hTable.size());
        System.out.println(hTable.contains(5));
        System.out.println(hTable.contains("5"));
        hTable.remove(5);
        hTable.remove("two");
        System.out.println("Deleted elements: " + "5" + " and " + "two");
        System.out.println(hTable.toString());
        System.out.println("Num of elements in Hash Table: " + hTable.size());

        //hTable.clear();
        System.out.println(hTable.toString());
        System.out.println(hTable.isEmpty());

        Object[] array = hTable.toArray();
        for (Object anArray : array) System.out.print(anArray + "; ");
    }
}
