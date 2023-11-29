package ru.labs.interfaces;

import java.io.InputStreamReader;

public interface UserType<T> {
    String typeName();

    T create();

    Object clone();

    T readValue(InputStreamReader in);

    T parseValue(String ss);

    MyComparator<T> getTypeComparator();
}



