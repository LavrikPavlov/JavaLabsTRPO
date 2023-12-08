package ru.labs;

import ru.labs.controller.MainController;
import javax.swing.*;

public class App
{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainController().setVisible(true);
            }
        });
    }

}
