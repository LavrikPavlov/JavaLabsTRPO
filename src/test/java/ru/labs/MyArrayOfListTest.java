package ru.labs;

import org.junit.Test;
import ru.labs.MyClass.MyArrayOfList;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MyArrayOfListTest {

    @Test
    public void addAndGet() {
        MyArrayOfList myArray = new MyArrayOfList();
        myArray.add(5);
        myArray.add(10);

        assertEquals(List.of(5), myArray.get(0));
        assertEquals(List.of(10), myArray.get(1));
    }

    @Test
    public void addListAndGet() {
        MyArrayOfList myArray = new MyArrayOfList();
        myArray.add(List.of(5, 10));
        myArray.add(List.of(15, 20));

        assertEquals(5, myArray.get(0, 0));
        assertEquals(10, myArray.get(0, 1));
        assertEquals(15, myArray.get(1, 0));
        assertEquals(20, myArray.get(1, 1));
    }

    @Test
    public void insert() {
        MyArrayOfList myArray = new MyArrayOfList();
        myArray.add(5);
        myArray.add(10);

        myArray.insert(1, 7);

        assertEquals(List.of(5), myArray.get(0));
        assertEquals(List.of(7, 10), myArray.get(1));
    }

    @Test
    public void insertList() {
        MyArrayOfList myArray = new MyArrayOfList();
        myArray.add(List.of(15, 20));

        myArray.insertByIndex(0, 0, 7);

        assertEquals(7, myArray.get(0, 0));
        assertEquals(15, myArray.get(0, 1));
        assertEquals(20, myArray.get(0, 2));
    }

    @Test
    public void delete() {
        MyArrayOfList myArray = new MyArrayOfList();
        myArray.add(5);
        myArray.add(10);
        myArray.add(15);

        myArray.delete(1);

        assertEquals(List.of(5), myArray.get(0));
        assertEquals(List.of(15), myArray.get(1));
    }

    @Test
    public void deleteList() {
        MyArrayOfList myArray = new MyArrayOfList();
        myArray.add(List.of(5, 10, 15));

        myArray.deleteByIndex(0, 0);

        assertNotEquals(5, myArray.get(0, 0));
        assertEquals(10, myArray.get(0, 0));
        assertEquals(15, myArray.get(0, 1));

    }

    @Test
    public void insertOrderByOrder() {
        MyArrayOfList myArray = new MyArrayOfList();
        myArray.add(10);
        myArray.add(20);
        myArray.add(5);

        myArray.sort();

        myArray.insertOrder(15);

        assertEquals(List.of(5,10,15,20), myArray.get(0));
    }

    @Test
    public void getByOrderByOrder() {
        MyArrayOfList myArray = new MyArrayOfList();
        myArray.add(10);
        myArray.add(20);
        myArray.add(5);

        myArray.sort();

        assertEquals(5, myArray.getByOrder(0));
        assertEquals(10, myArray.getByOrder(1));
        assertEquals(20, myArray.getByOrder(2));
    }

    @Test
    public void deleteByOrderByOrder() {
        MyArrayOfList myArray = new MyArrayOfList();
        myArray.add(10);
        myArray.add(20);
        myArray.add(5);

        myArray.sort();

        myArray.deleteByOrder(1);


        assertEquals(List.of(5,20), myArray.get(0));
    }

    @Test
    public void forEach() {
        MyArrayOfList myArray = new MyArrayOfList();
        myArray.add(5);
        myArray.add(10);
        myArray.add(15);

        StringBuilder result = new StringBuilder();
        myArray.forEach(value -> result.append(value).append(","));

        assertEquals("5,10,15,", result.toString());
    }

}