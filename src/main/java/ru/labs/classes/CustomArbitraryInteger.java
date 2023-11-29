package ru.labs.classes;

import ru.labs.classes.ArbitraryInteger;

import java.io.InputStream;
import java.io.OutputStream;

public interface CustomArbitraryInteger {

    void add(ArbitraryInteger other);

    void subtract(ArbitraryInteger other);

    void multiply(ArbitraryInteger  other);

    void setElement(int index, byte value);

    void saveToTextFile(String filePath);

    void loadFromTextFile(String filePath);

    void saveToBinaryStream(OutputStream os);

    void loadFromBinaryStream(InputStream is);

    int size();


}