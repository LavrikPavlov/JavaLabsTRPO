package ru.labs.MyClass;

public interface CustomeArrayOfList {

    void add(int value);

    int get(int index);

    void insert(int index, int value);

    void delete(int index);

    void insertOrder(int value);

    @Deprecated
    int getByOrder(int index);

    @Deprecated
    void deleteByOrder(int index);

    void forEach(Processor processor);

}
