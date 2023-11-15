package ru.labs;

import ru.labs.MyType.ArbitraryInteger;

import java.io.*;
import java.util.Arrays;

public class App 
{
    public static void main(String[] args) {
        // Число: 360
        ArbitraryInteger num1 = new ArbitraryInteger(
                Arrays.asList((byte) 0b00111001, (byte) 0b00110000),
                        false
        );

        // Число: -6789
        ArbitraryInteger num2 = new ArbitraryInteger(
                Arrays.asList((byte) 0b01001101, (byte) 0b10111100),
                true
        );

        // Задаем значения num1 и num2
        num1.setElement(0, (byte) 0b11011010);
        num2.setElement(0, (byte) 0b01100101);


        // Выводим значения num1 и num2
        System.out.println("num1: " + num1);
        System.out.println("num2: " + num2);

        num1.add(num2);
        System.out.println("Сумма num1 и num2: " + num1);

        num1.subtract(num2);
        System.out.println("Разность num1 и num2: " + num1);

        num1.multiply(num2);
        System.out.println("Произведение num1 и num2: " + num1);

        // Сохранение в двоичный файл
        try (OutputStream os = new FileOutputStream("output.bin")) {
            num2.saveToBinaryStream(os);
            System.out.println("Сохраненное число: " + num2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Загрузка из двоичного файла
        try (InputStream is = new FileInputStream("output.bin")) {
            ArbitraryInteger loadedNum = new ArbitraryInteger();
            loadedNum.loadFromBinaryStream(is);
            System.out.println("Загруженное число: " + loadedNum);
        } catch (IOException e) {
            e.printStackTrace();
        }

        num1.saveToTextFile("output.txt");

        // Загрузка из файла
        ArbitraryInteger loadedNumber = new ArbitraryInteger();
        loadedNumber.loadFromTextFile("output.txt");
        System.out.println("Загруженное число txt: " + loadedNumber);

    }
}
