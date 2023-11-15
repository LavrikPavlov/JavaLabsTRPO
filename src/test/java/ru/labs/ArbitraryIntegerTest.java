package ru.labs;

import org.junit.Assert;
import org.junit.Test;
import ru.labs.MyType.ArbitraryInteger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

public class ArbitraryIntegerTest {

    @Test
    public void testAddition() {
        ArbitraryInteger num1 = new ArbitraryInteger(List.of((byte) 1, (byte) 2), false);
        ArbitraryInteger num2 = new ArbitraryInteger(List.of((byte) 3, (byte) 4), false);

        num1.add(num2);

        Assert.assertEquals("00000100 00000110 ", num1.toString());
    }

    @Test
    public void testSubtraction() {
        ArbitraryInteger num1 = new ArbitraryInteger(List.of((byte) 5, (byte) 0), false);
        ArbitraryInteger num2 = new ArbitraryInteger(List.of((byte) 2, (byte) 0), false);

        num1.subtract(num2);

        Assert.assertEquals("00000011 ", num1.toString());
    }

    @Test
    public void testMultiplication() {
        ArbitraryInteger num1 = new ArbitraryInteger(List.of((byte) 2), false);
        ArbitraryInteger num2 = new ArbitraryInteger(List.of((byte) 3), false);

        num1.multiply(num2);

        Assert.assertEquals("00000110 ", num1.toString());
    }

    @Test
    public void testSaveAndLoadBinaryStream() {
        ArbitraryInteger original = new ArbitraryInteger(List.of((byte) 1, (byte) 2, (byte) 3), true);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        original.saveToBinaryStream(outputStream);

        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

        ArbitraryInteger loaded = new ArbitraryInteger();
        loaded.loadFromBinaryStream(inputStream);

        Assert.assertEquals(original.toString(), loaded.toString());
    }

    @Test
    public void testSaveAndLoadTextFile() {
        ArbitraryInteger original = new ArbitraryInteger(List.of((byte) 4, (byte) 5, (byte) 6), false);

        String filePath = "test.txt";
        original.saveToTextFile(filePath);

        ArbitraryInteger loaded = new ArbitraryInteger();
        loaded.loadFromTextFile(filePath);

        Assert.assertEquals(original.toString(), loaded.toString());
    }

    // Add more tests as needed

    @Test
    public void testToString() {
        ArbitraryInteger num = new ArbitraryInteger(List.of((byte) 1, (byte) 2), false);
        Assert.assertEquals("00000001 00000010 ", num.toString());
    }
}
