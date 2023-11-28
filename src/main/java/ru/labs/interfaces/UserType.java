package ru.labs.interfaces;

import java.io.InputStreamReader;

public interface UserType<T> {
    String typeName();

    Object create();

    Object clone();

    Object readValue(InputStreamReader in);

    Object parseValue(String ss);

    Comparator<T> getTypeComparator();
}