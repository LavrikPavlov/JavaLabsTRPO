package ru.labs.myType;

import ru.labs.classes.ArbitraryInteger;
import ru.labs.interfaces.MyComparator;
import ru.labs.interfaces.UserType;

import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.Objects;
import java.util.Scanner;

public class BasicType<T> implements UserType<T> {
    private T value;

    public BasicType(T value) {
        this.value = value;
    }

    @Override
    public String typeName() {
        return value.getClass().getSimpleName();
    }

    @Override
    public T create() {
        return value;
    }

    @Override
    public BasicType<T> clone() {
        return new BasicType<>(this.value);
    }

    @Override
    public T readValue(InputStreamReader in) {
        Scanner scanner = new Scanner(in);
        if (value instanceof Integer) {
            return (T) Integer.valueOf(scanner.nextInt());
        } else if (value instanceof String || value instanceof ArbitraryInteger) {
            return (T) scanner.nextLine();
        } else {
            throw new UnsupportedOperationException("Unsupported type: " + typeName());
        }
    }

    @Override
    public T parseValue(String ss) {
        if (value instanceof Integer) {
            return (T) Integer.valueOf(Integer.parseInt(ss));
        } else if (value instanceof String) {
            return (T) ss;
        } else if (value instanceof ArbitraryInteger) {
            ArbitraryInteger arbitraryInteger = new ArbitraryInteger();
            try (Scanner scanner = new Scanner(ss)) {
                scanner.useDelimiter(",");
                while (scanner.hasNext()) {
                    int intValue = Integer.parseInt(scanner.next());
                    if (intValue < Byte.MIN_VALUE || intValue > Byte.MAX_VALUE) {
                        throw new RuntimeException("Error parsing ArbitraryInteger: Value out of range. Value:" + intValue);
                    }
                    byte byteValue = (byte) intValue;
                    arbitraryInteger.getBytes().add(byteValue);
                }
            } catch (Exception e) {
                throw new RuntimeException("Error parsing ArbitraryInteger: " + e.getMessage());
            }
            return (T) arbitraryInteger;
        } else {
            throw new UnsupportedOperationException("Unsupported type: " + typeName());
        }
    }


    @Override
    public MyComparator getTypeComparator() {
        if (value instanceof String) {
            return new MyComparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    String s1 = o1;
                    String s2 = o2;
                    return Integer.compare(s1.length(), s2.length());
                }
            };
        } else if (value instanceof Integer) {
            return new MyComparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return Integer.compare(o1, o2);
                }
            };
        } else if (value instanceof ArbitraryInteger) {
            return new MyComparator<ArbitraryInteger>() {
                @Override
                public int compare(ArbitraryInteger o1, ArbitraryInteger o2) {
                    ArbitraryInteger ai1 =  o1;
                    ArbitraryInteger ai2 =  o2;
                    return Integer.compare(ai1.getBytes().size(), ai2.getBytes().size());
                }
            };
        } else {
            throw new UnsupportedOperationException("Unsupported type: " + typeName());
        }
    }

    @Override
    public String toString() {
        return "BasicType{" +
                "value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasicType<?> basicType = (BasicType<?>) o;
        return Objects.equals(value, basicType.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}