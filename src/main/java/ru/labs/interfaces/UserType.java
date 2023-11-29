package ru.labs.interfaces;

import java.io.InputStreamReader;

public interface UserType {
    String typeName();

    Object create();

    Object clone();

    Object readValue(InputStreamReader in);

    Object parseValue(String ss);

    Comparator getTypeComparator();
}