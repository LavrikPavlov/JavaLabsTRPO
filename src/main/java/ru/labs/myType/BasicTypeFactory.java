package ru.labs.myType;

import ru.labs.classes.ArbitraryInteger;


public class BasicTypeFactory {

    public static BasicType<Integer> createIntegerType(int value) {
        return new BasicType<>(value);
    }

    public static BasicType<String> createStringType(String value) {
        return new BasicType<>(value);
    }

    public static BasicType<ArbitraryInteger> createArbitraryIntegerType(ArbitraryInteger value) {
        return new BasicType<>(value);
    }


}