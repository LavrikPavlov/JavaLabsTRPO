package ru.labs.MyClass;

import ru.labs.mySort.MySort;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class MyArrayOfList implements CustomeArrayOfList{

    private ArrayList<ArrayList<Integer>> data;

    public MyArrayOfList(){
        this.data = new ArrayList<>();
    }

    public MyArrayOfList(Collection<Collection<Integer>> collection){
        this.data = new ArrayList<>();

        for (Collection<Integer> innerList : collection) {
            ArrayList<Integer> copyInnerList = new ArrayList<>(innerList);
            this.data.add(copyInnerList);
        }
    }

    public ArrayList<ArrayList<Integer>> getData() {
        return data;
    }

    public void setData(ArrayList<ArrayList<Integer>> data) {
        this.data = data;
    }

    @Override
    public void add(int value) {
        ArrayList<Integer> innerList = new ArrayList<>();
        innerList.add(value);
        data.add(innerList);
    }

    public void add(List<Integer> value){
        ArrayList<Integer> innerList = new ArrayList<>(value.size());
        innerList.addAll(value);
        data.add(innerList);
    }

    @Override
    public int get(int index) {
        int current = 0;
        for(ArrayList<Integer> list : data){
            int size = list.size();
            if(index < current + size){
                return list.get(index - current);
            }
            current += size;
        }
        throw new IndexOutOfBoundsException("Ошибка: Index находиться за допустимым диапозоном");
    }

    public int get(int firstIndex, int secondIndex) {
        int current = 0;
        for (ArrayList<Integer> list : data) {
            int size = list.size();
            if (firstIndex < current + size) {
                if (secondIndex < list.size()) {
                    return list.get(secondIndex);
                } else {
                    throw new IndexOutOfBoundsException("Ошибка: Второй индекс находится за пределами допустимого диапазона");
                }
            }
            current += size;
        }
        throw new IndexOutOfBoundsException("Ошибка: Первый индекс находится за пределами допустимого диапазона");
    }

    @Override
    public void insert(int index, int value) {
        int current = 0;
        for(ArrayList<Integer> list : data){
            int size = list.size();
            if(index < current + size){
                list.add(index - current, value);
                return;
            }
            current += size;
        }
        throw new IndexOutOfBoundsException("Ошибка: Index находиться за допустимым диапозоном");
    }

    public void insert(int firstIndex, int secondIndex, int value) {
        int current = 0;
        for (ArrayList<Integer> list : data) {
            int size = list.size();
            if (firstIndex < current + size) {
                if (secondIndex <= list.size()) {
                    list.add(secondIndex, value);
                    return;
                } else {
                    throw new IndexOutOfBoundsException("Ошибка: Второй индекс находится за пределами допустимого диапазона");
                }
            }
            current += size;
        }

        if (firstIndex == current) {
            ArrayList<Integer> newList = new ArrayList<>();
            newList.add(value);
            data.add(newList);
            return;
        }

        throw new IndexOutOfBoundsException("Ошибка: Первый индекс находится за пределами допустимого диапазона");
    }

    @Override
    public void delete(int index) {
        int current = 0;
        for(ArrayList<Integer> list : data){
            int size = list.size();
            if(index < current + size){
                list.remove(index - current);
            }
            current += size;
        }
        throw new IndexOutOfBoundsException("Ошибка: Index находиться за допустимым диапозоном");
    }

    public void delete(int firstIndex, int secondIndex){
        int current = 0;
        for (ArrayList<Integer> list : data) {
            int size = list.size();
            if (firstIndex < current + size) {
                if (secondIndex <= list.size()) {
                    list.remove(secondIndex);
                    return;
                } else {
                    throw new IndexOutOfBoundsException("Ошибка: Второй индекс находится за пределами допустимого диапазона");
                }
            }
            current += size;
        }

        throw new IndexOutOfBoundsException("Ошибка: Первый индекс находится за пределами допустимого диапазона");
    }

    @Override
    public void insertOrder(int value) {
        int i = 0;
        for(ArrayList<Integer> list : data){
            if (list.isEmpty() || list.get(0) > value) {
                list.add(0, value);
                return;
            }

            for (int j = 0; j < list.size(); j++) {
                if (list.get(j) > value) {
                    list.add(j, value);
                    return;
                }
            }
        }
        add(value);
    }


    @Override
    public int getByOrder(int index) {
        int current = 0;
        for (ArrayList<Integer> list : data) {
            int size = list.size();
            if (index < current + size) {
                return list.get(index - current);
            }
            current += size;
        }
        throw new IndexOutOfBoundsException("Ошибка: Index находится за допустимым диапазоном");
    }

    @Override
    public void deleteByOrder(int index) {
        int current = 0;
        for (ArrayList<Integer> list : data) {
            int size = list.size();
            if (index < current + size) {
                list.remove(index - current);
                return;
            }
            current += size;
        }
        throw new IndexOutOfBoundsException("Ошибка: Index находится за допустимым диапазоном");
    }

    @Override
    public void forEach(Processor processor) {
        for (ArrayList<Integer> element : data) {
            for (Integer value : element) {
                processor.process(value);
            }
        }
    }

    public void sort() {
        MySort mySort = new MySort();
        mySort.sort(data);
    }

    @Override
    public String toString() {
        return "MyArrayOfList{" +
                "data=" + data +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyArrayOfList that = (MyArrayOfList) o;
        return Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }
}
