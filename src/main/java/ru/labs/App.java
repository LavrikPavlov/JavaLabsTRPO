package ru.labs;

import ru.labs.MyClass.MyArrayOfList;

import java.util.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        List<List<Integer>> initialData = Arrays.asList(
                Arrays.asList(5, 2, 8),
                Arrays.asList(3, 7, 1),
                Arrays.asList(9, 4, 6)
        );

        // Создаем экземпляр вашей структуры данных
        MyArrayOfList myArrayFirst = new MyArrayOfList();
        MyArrayOfList myArraySecond = new MyArrayOfList(List.copyOf(initialData));

        // Добавляем элементы
        myArrayFirst.add(5);
        myArrayFirst.add(3);
        myArrayFirst.add(7);
        myArrayFirst.add(2);

        System.out.println("До сортировки и изменений №1: " + myArrayFirst.getData());
        System.out.println("До сортировки и изменений №2: " + myArraySecond.getData());

        myArrayFirst.insert(1,0);

        myArraySecond.add(Arrays.asList(10,12,11));
        myArraySecond.insert(1,2,0);

        // Выводим содержимое до сортировки
        System.out.println("До сортировки №1: " + myArrayFirst.getData());
        System.out.println("До сортировки №2: " + myArraySecond.getData());

        // Сортируем
        myArrayFirst.sort();
        myArraySecond.sort();

        // Выводим содержимое после сортировки
        System.out.println("После сортировки №1: " + myArrayFirst.getData());
        System.out.println("После сортировки №1: " + myArraySecond.getData());


        // Пример использования forEach
        System.out.println("\nПример работы forEach");
        myArrayFirst.forEach(value -> System.out.print(value + " "));
        System.out.println();
        myArraySecond.forEach(value -> System.out.print(value + " "));
    }
}
