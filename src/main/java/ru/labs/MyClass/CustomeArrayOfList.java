package ru.labs.MyClass;

import java.util.Collection;

public interface CustomeArrayOfList {

    void add(int value);

    int get(int firstIndex, int secondIndex);

    Collection<Integer> get(int index);

    void insert(int index, int value);

    void delete(int index);

    void insertOrder(int value);

    @Deprecated
    int getByOrder(int index);

    @Deprecated
    void deleteByOrder(int index);

    void forEach(Processor processor);

}
