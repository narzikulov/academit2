package ru.academit.narzikulov;

/**
 * Created by tim on 28.12.2015.
 */
public class HashTableMain {
    public static void main() {
        HashTable hTable = new HashTable();

        hTable.addElement("one");
        hTable.addElement("two");
        hTable.addElement("three");
        hTable.addElement(4);
        hTable.addElement(5);

        System.out.println(hTable.getLastIndex());
        System.out.println(hTable.toString());
    }
}
