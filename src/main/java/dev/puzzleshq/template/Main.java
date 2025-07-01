package dev.puzzleshq.template;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Template Window");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ((Label)frame.add(new Label("Hello World"))).setAlignment(1);
        frame.setVisible(true);
    }

}
