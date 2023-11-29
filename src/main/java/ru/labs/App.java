package ru.labs;


import ru.labs.classes.ArbitraryInteger;
import ru.labs.interfaces.MyComparator;
import ru.labs.myType.BasicType;
import ru.labs.myType.BasicTypeFactory;

import java.io.InputStreamReader;
import java.util.Arrays;

public class App
{
    public static void main( String[] args ) {

        ArbitraryInteger arbitraryIntegerValue = new ArbitraryInteger(Arrays.asList(
                (byte) 123,
                (byte) 21),
                true);

        BasicType<ArbitraryInteger> basicTypeData = BasicTypeFactory.createArbitraryIntegerType(arbitraryIntegerValue);
        BasicType<Integer> basicTypeInteger = BasicTypeFactory.createIntegerType(100);
        BasicType<String> basicTypeString = BasicTypeFactory.createStringType("Привет");

        // Тестирование typeName
        System.out.println("Тип: " + basicTypeString.typeName());
        System.out.println("Тип: " + basicTypeInteger.typeName());
        System.out.println("Тип: " + basicTypeData.typeName());
        System.out.println();

        // Тестирование clone
        BasicType<String> cloneBasicTypeString = basicTypeString.clone();
        BasicType<Integer> cloneBasicTypeInteger = basicTypeInteger.clone();
        BasicType<ArbitraryInteger> cloneBasicTypeData = basicTypeData.clone();

        System.out.println("Клон (Строка): " + cloneBasicTypeString);
        System.out.println("Клон (Целое число): " + cloneBasicTypeInteger);
        System.out.println("Клон (Произвольное целое число): " + cloneBasicTypeData);
        System.out.println();

        // Тестирование create
        Object createdStringValue = basicTypeString.create();
        System.out.println("Созданное значение (Строка): " + createdStringValue);

        Object createdIntegerValue = basicTypeInteger.create();
        System.out.println("Созданное значение (Целое число): " + createdIntegerValue);

        Object createdArbitraryIntegerValue = basicTypeData.create();
        System.out.println("Созданное значение (Произвольное целое число): " + createdArbitraryIntegerValue);
        System.out.println();

        // Тестирование readValue
        System.out.println("Введите строковое значение:");
        Object readStringValue = basicTypeString.readValue(new InputStreamReader(System.in));
        System.out.println("Считанное значение (Строка): " + readStringValue);
        System.out.println(basicTypeString + "\n");

        System.out.println("Введите целочисленное значение:");
        Object readIntegerValue = basicTypeInteger.readValue(new InputStreamReader(System.in));
        System.out.println("Считанное значение (Целое число): " + readIntegerValue);
        System.out.println(basicTypeInteger + "\n");

        System.out.println("Введите двоичное представление для Произвольного целого числа:");
        Object readArbitraryIntegerValue = basicTypeData.readValue(new InputStreamReader(System.in));
        System.out.println("Считанное значение (Произвольное целое число): " + readArbitraryIntegerValue);
        System.out.println(basicTypeData + "\n");

        // Test getTypeComparator
        MyComparator comparator = basicTypeString.getTypeComparator();
        int compareResult = comparator.compare("Hello", "World");
        System.out.println("Compare Результат: " + compareResult);
        System.out.println();

        MyComparator intComparator = basicTypeInteger.getTypeComparator();
        int intCompareResult = intComparator.compare(42, 56);
        System.out.println("Integer Compare Результат: " + intCompareResult);
        System.out.println();

        MyComparator arbitraryIntComparator = basicTypeData.getTypeComparator();
        int arbitraryIntCompareResult = arbitraryIntComparator.compare(
                new ArbitraryInteger(Arrays.asList((byte) 42), false),
                new ArbitraryInteger(Arrays.asList((byte) 56), false)
        );
        System.out.println("ArbitraryInteger Compare Результат: " + arbitraryIntCompareResult);
    }
}
