package ru.labs.controller;

import ru.labs.classes.ArbitraryInteger;
import ru.labs.myType.BasicType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class MainController extends JFrame {
    private final JTextField inputField;
    private final JTextArea outputArea;
    private BasicType<?> selectedBasicType;
    private final ArrayList<Object> dataList;

    public MainController() {
        setTitle("Оконное приложения");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JComboBox<String> typeComboBox = new JComboBox<>(new String[]{"String", "Integer", "ArbitraryInteger"});
        inputField = new JTextField(20);
        JButton addButton = new JButton("Добавить");
        JButton showDataButton = new JButton("Показать");
        JButton sortDataButton = new JButton("Отсортировать");
        JButton clearDataButton = new JButton("Очистить");

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.add(typeComboBox);
        panel.add(inputField);
        panel.add(addButton);
        panel.add(showDataButton);
        panel.add(sortDataButton);
        panel.add(clearDataButton);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        clearDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dataList.clear();
                outputArea.setText("");
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String typeName = (String) typeComboBox.getSelectedItem();
                selectedBasicType = createBasicType(typeName, inputField.getText());
                if (selectedBasicType != null) {
                    Object createdData = selectedBasicType.create();
                    addDataOperation(createdData);
                    outputArea.append("Добавленный элемент: " + createdData + "\n");
                }
            }
        });

        showDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedBasicType != null) {
                    for (Object o : dataList)
                        outputArea.append("Обьект: "
                                + " [" + o.getClass().getSimpleName() + "] - "
                                + o + "\n");
                }
            }
        });

        sortDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedBasicType != null && dataList != null) {
                    Comparator<Object> comparator = selectedBasicType.getTypeComparator();
                    try {
                        if (comparator != null) {
                            sortDataOperation(comparator);
                            outputArea.append("Отсортированные данные: " + Arrays.toString(dataList.toArray()) + "\n");
                        } else {
                            outputArea.append("Сортировка не поддерживается для этого типа\n");
                        }
                    } catch (RuntimeException run){
                        throw new RuntimeException("Ошибка в работе с сортировкой\n" + run);
                    }

                }
            }
        });

        pack();
        setLocationRelativeTo(null);

        dataList = new ArrayList<>();
    }

    private void addDataOperation(Object data) {
        dataList.add(data);
    }

    private void sortDataOperation(Comparator<Object> comparator) {
        if (comparator != null) {
            Collections.sort(dataList, comparator);
        }
    }

    private BasicType<?> createBasicType(String typeName, String inputValue) {
        switch (typeName) {
            case "String":
                return new BasicType<>(inputValue);
            case "Integer":
                try {
                    return new BasicType<>(Integer.parseInt(inputValue));
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Данные не похожи на тип Integer", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "ArbitraryInteger":
                return createArbitraryInteger(inputValue);
        }
        return null;
    }

    private BasicType<ArbitraryInteger> createArbitraryInteger(String inputValue) {
        try {
            String[] byteValues = inputValue.split(",");
            byte[] bytes = new byte[byteValues.length];
            for (int i = 0; i < byteValues.length; i++) {
                bytes[i] = Byte.parseByte(byteValues[i].trim());
            }

            ArrayList<Byte> byteList = new ArrayList<>();
            for (byte b : bytes) {
                byteList.add(b);
            }

            ArbitraryInteger arbitraryInteger = new ArbitraryInteger(byteList, true);
            return new BasicType<>(arbitraryInteger);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this,
                    "Ошибка в создании ArbitraryInteger: " + e.getMessage(),
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
}