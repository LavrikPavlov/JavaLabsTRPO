package ru.labs.mySort;

import java.util.ArrayList;
import java.util.Arrays;

public class MySort {

    public void sort(ArrayList<ArrayList<Integer>> data) {
        ArrayList<Integer> sortedList = new ArrayList<>();
        for (ArrayList<Integer> element : data) {
            sortedList.addAll(element);
        }

        Integer[] sortedArray = sortedList.toArray(new Integer[0]);
        mergeSort(sortedArray, 0, sortedArray.length - 1);

        updateData(data, sortedArray);
    }

    private void updateData(ArrayList<ArrayList<Integer>> data, Integer[] sortedArray) {
        data.clear();
        data.add(new ArrayList<>(Arrays.asList(sortedArray)));
    }

    private void mergeSort(Integer[] array, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;

            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);

            merge(array, left, middle, right);
        }
    }

    private void merge(Integer[] array, int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        Integer[] leftArray = Arrays.copyOfRange(array, left, left + n1);
        Integer[] rightArray = Arrays.copyOfRange(array, middle + 1, middle + 1 + n2);

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }
}
