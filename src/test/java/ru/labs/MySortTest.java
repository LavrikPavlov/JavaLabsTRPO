package ru.labs;

import org.junit.Test;
import ru.labs.MyClass.MyArrayOfList;
import ru.labs.mySort.MySort;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class MySortTest {

    @Test
    public void testSort() {
        MySort mySort = new MySort();

        ArrayList<ArrayList<Integer>> data = new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList(5, 2, 8)),
                new ArrayList<>(Arrays.asList(3, 7, 1)),
                new ArrayList<>(Arrays.asList(9, 4, 6))
        ));

        mySort.sort(data);

        Integer[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Integer[] actual = data.get(0).toArray(new Integer[0]);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testEmptyList() {
        MySort mySort = new MySort();
        MyArrayOfList data = new MyArrayOfList();

        mySort.sort(data.getData());

        assertTrue(data.isEmpty());
    }

    @Test
    public void testSingleList() {
        MySort mySort = new MySort();

        ArrayList<ArrayList<Integer>> data = new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList(5, 2, 8))
        ));

        mySort.sort(data);

        Integer[] expected = {2, 5, 8};
        Integer[] actual = data.get(0).toArray(new Integer[0]);

        assertArrayEquals(expected, actual);
    }

}
