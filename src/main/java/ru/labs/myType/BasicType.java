package ru.labs.myType;

import ru.labs.classes.ArbitraryInteger;
import ru.labs.interfaces.Comparator;
import ru.labs.interfaces.UserType;

import java.io.InputStreamReader;
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
    public Object create() {
        return value;
    }

    @Override
    public Object clone() {
        return new BasicType<>(this.value);
    }

    @Override
    public Object readValue(InputStreamReader in) {
        Scanner scanner = new Scanner(in);
        if (value instanceof Integer) {
            return scanner.nextInt();
        } else if (value instanceof String || value instanceof ArbitraryInteger) {
            return scanner.nextLine();
        } else {
            throw new UnsupportedOperationException("Unsupported type: " + typeName());
        }
    }

    @Override
    public Object parseValue(String ss) {
        if (value instanceof Integer) {
            return Integer.parseInt(ss);
        } else if (value instanceof String) {
            return ss;
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
            return arbitraryInteger;
        } else {
            throw new UnsupportedOperationException("Unsupported type: " + typeName());
        }
    }


    @Override
    public Comparator<T> getTypeComparator() {
        return (o1, o2) -> {
            if (o1 instanceof Comparable && o2 instanceof Comparable) {
                return ((Comparable<T>) o1).compareTo((T) o2);
            } else {
                return 0;
            }
        };
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