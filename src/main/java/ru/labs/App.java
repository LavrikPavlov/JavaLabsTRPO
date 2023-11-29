package ru.labs;


import ru.labs.classes.ArbitraryInteger;
import ru.labs.interfaces.Comparator;
import ru.labs.myType.BasicType;

import java.io.InputStreamReader;
import java.util.Arrays;

public class App
{
    public static void main( String[] args ) {

        ArbitraryInteger arbitraryIntegerValue = new ArbitraryInteger(Arrays.asList(
                (byte) 123,
                (byte) 21),
                true);


        BasicType<ArbitraryInteger> basicTypeData = new BasicType<>(arbitraryIntegerValue);
        BasicType<Integer> basicTypeInteger = new BasicType<>(100);
        BasicType<String> basicTypeString = new BasicType<>("Hello");

        // Test typeName
        System.out.println("TypeName: " + basicTypeString.typeName());
        System.out.println("TypeName: " + basicTypeInteger.typeName());
        System.out.println("TypeName: " + basicTypeData.typeName());
        System.out.println();

        // Test clone
        BasicType<String> cloneBasicTypeString = (BasicType<String>) basicTypeString.clone();
        BasicType<String> cloneBasicTypeInteger = (BasicType<String>) basicTypeInteger.clone();
        BasicType<String> cloneBasicTypeData = (BasicType<String>) basicTypeData.clone();
        System.out.println("Clone (String): " + cloneBasicTypeString);
        System.out.println("Clone (Integer): " + cloneBasicTypeInteger);
        System.out.println("Clone (ArbitraryInteger): " + cloneBasicTypeData);
        System.out.println();

        // Test create
        Object createdStringValue = basicTypeString.create();
        System.out.println("Created Value (String): " + createdStringValue);

        Object createdIntegerValue = basicTypeInteger.create();
        System.out.println("Created Value (Integer): " + createdIntegerValue);

        Object createdArbitraryIntegerValue = basicTypeData.create();
        System.out.println("Created Value (ArbitraryInteger): " + createdArbitraryIntegerValue);
        System.out.println();


        // Test readValue
        System.out.println("Enter an string value:");
        Object readStringValue = basicTypeString.readValue(new InputStreamReader(System.in));
        System.out.println("Read Value (String): " + readStringValue);
        System.out.println(basicTypeString + "\n");

        System.out.println("Enter an integer value:");
        Object readIntegerValue = basicTypeInteger.readValue(new InputStreamReader(System.in));
        System.out.println("Read Value (Integer): " + readIntegerValue);
        System.out.println(basicTypeInteger + "\n");

        System.out.println("Enter a binary stream for ArbitraryInteger:");
        Object readArbitraryIntegerValue = basicTypeData.readValue(new InputStreamReader(System.in));
        System.out.println("Read Value (ArbitraryInteger): " + readArbitraryIntegerValue);
        System.out.println(basicTypeData + "\n");


        // Test parseValue
        Object parsedValue = basicTypeString.parseValue("ParsedValue");
        System.out.println("ParsedValue: " + parsedValue);
        System.out.println(basicTypeString + "\n");

        System.out.println("Enter an integer representation for parsing (e.g., '42'):");
        Object parsedIntegerValue = basicTypeInteger.parseValue("42");
        System.out.println("Parsed Value (Integer): " + parsedIntegerValue);
        System.out.println(basicTypeInteger + "\n");

        System.out.println("Enter a string representation for parsing (e.g., '5,3,true'):");
        Object parsedArbitraryIntegerValue = basicTypeData.parseValue("5,74");
        System.out.println("Parsed Value (ArbitraryInteger): " + parsedArbitraryIntegerValue);
        System.out.println(basicTypeData + "\n");

        // Test getTypeComparator
        Comparator comparator = basicTypeString.getTypeComparator();
        int compareResult = comparator.compare("Hello", "World");
        System.out.println("Compare Result: " + compareResult);
        System.out.println();

        Comparator intComparator = basicTypeInteger.getTypeComparator();
        int intCompareResult = intComparator.compare(42, 56);
        System.out.println("Integer Compare Result: " + intCompareResult);
        System.out.println();

        Comparator arbitraryIntComparator = basicTypeData.getTypeComparator();
        int arbitraryIntCompareResult = arbitraryIntComparator.compare(
                new ArbitraryInteger(Arrays.asList((byte) 42), false),
                new ArbitraryInteger(Arrays.asList((byte) 56), false)
        );
        System.out.println("ArbitraryInteger Compare Result: " + arbitraryIntCompareResult);
    }
}
