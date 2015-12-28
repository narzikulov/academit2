package ru.academit.narzikulov;

/**
 * Created by tim on 28.12.2015.
 */
public class HashTableMain {
    public static void main() {
        HashTable hTable = new HashTable();

        hTable.addElement(-5);
        hTable.addElement("two");
        hTable.addElement("three");
        hTable.addElement(4);
        hTable.addElement(5);
        hTable.addElement("two");
        hTable.addElement("three");
        hTable.addElement(1);
        hTable.addElement("Hello world!");


        System.out.println(hTable.toString());
        System.out.println("Num of elements in Hash Table: " + hTable.countNumOfElements());
        System.out.println(hTable.findElement("three"));
    }
}
