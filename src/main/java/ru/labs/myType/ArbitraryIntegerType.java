package ru.labs.myType;

import ru.labs.classes.ArbitraryInteger;
import ru.labs.interfaces.Comparator;
import ru.labs.interfaces.UserType;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArbitraryIntegerType implements UserType {

    private List<Byte> bytes;
    private boolean isNegative;

    public ArbitraryIntegerType(ArbitraryInteger arbitraryInteger) {
        this.bytes = arbitraryInteger.getBytes();
        this.isNegative = arbitraryInteger.isNegative();
    }

    public ArbitraryIntegerType() {
        this.bytes = new ArrayList<>();
        this.isNegative = false;
    }

    @Override
    public String typeName() {
        return "ArbitraryInteger";
    }

    @Override
    public Object create() {
        return new ArbitraryInteger();
    }

    @Override
    public Object clone() {
        ArbitraryIntegerType clone = new ArbitraryIntegerType();
        clone.bytes = new ArrayList<>(this.bytes);
        clone.isNegative = this.isNegative;
        return clone;
    }

    @Override
    public Object readValue(InputStreamReader in) {
        ArbitraryInteger arbitraryInteger = new ArbitraryInteger();
        arbitraryInteger.loadFromBinaryStream(new DataInputStream(new BufferedInputStream(System.in)));
        return arbitraryInteger;
    }

    @Override
    public Object parseValue(String ss) {
        ArbitraryInteger arbitraryInteger = new ArbitraryInteger();
        try (Scanner scanner = new Scanner(ss)) {
            while (scanner.hasNextByte()) {
                arbitraryInteger.getBytes().add(scanner.nextByte());
            }
        }
        return arbitraryInteger;
    }

    @Override
    public Comparator getTypeComparator() {
        return (o1, o2) -> {
            if (o1 instanceof Comparable && o2 instanceof Comparable) {
                return ((Comparable) o1).compareTo(o2);
            } else {
                throw new IllegalArgumentException("Objects do not implement Comparable");
            }
        };
    }
}