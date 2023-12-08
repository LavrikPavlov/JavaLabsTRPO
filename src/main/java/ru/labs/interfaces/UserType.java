package ru.labs.interfaces;

import java.io.InputStreamReader;
import java.util.Comparator;

public interface UserType {
    String typeName();

    Object create();

    Object clone();

    Object readValue(InputStreamReader in);

    Object parseValue(String ss);

    Comparator<Object> getTypeComparator();
}