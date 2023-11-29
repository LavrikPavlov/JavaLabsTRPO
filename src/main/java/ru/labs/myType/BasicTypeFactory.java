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

    public static <T> BasicType<T> createTypeFromString(String ss, Class<T> typeClass) {
        try {
            T value = (T) Integer.valueOf(ss);
            return new BasicType<>(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid format for type conversion", e);
        }
    }
}