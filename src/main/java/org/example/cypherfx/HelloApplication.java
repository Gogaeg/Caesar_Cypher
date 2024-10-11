package org.example.cypherfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        TextArea inputArea = new TextArea();
        TextArea outputArea = new TextArea();
        TextField stepField = new TextField();
        stepField.setPromptText("Введите шаг шифрования");

        Button encryptButton = new Button("Шифровать");
        Button decryptButton = new Button("Расшифровать");

        encryptButton.setOnAction(e -> {
            String input = inputArea.getText();
            int step = getStep(stepField.getText());
            String encrypted = encrypt(input, step);
            outputArea.setText(encrypted);
        });

        decryptButton.setOnAction(e -> {
            String input = outputArea.getText();
            int step = getStep(stepField.getText());
            String decrypted = decrypt(input, step);
            inputArea.setText(decrypted);
        });

        VBox layout = new VBox(10, inputArea, stepField, encryptButton, decryptButton, outputArea);
        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Шифратор");
        primaryStage.show();
    }

    private int getStep(String stepText) {
        try {
            return Integer.parseInt(stepText);
        } catch (NumberFormatException e) {
            return 3;
        }
    }

    private String encrypt(String text, int step) {
        StringBuilder encrypted = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base;
                if (c >= 'а' && c <= 'я') {
                    base = 'а';
                    encrypted.append((char) ((c - base + step) % 32 + base));
                } else if (c >= 'А' && c <= 'Я') {
                    base = 'А';
                    encrypted.append((char) ((c - base + step) % 32 + base));
                } else {
                    base = Character.isLowerCase(c) ? 'a' : 'A';
                    encrypted.append((char) ((c - base + step) % 26 + base));
                }
            } else {
                encrypted.append(c);
            }
        }
        return encrypted.toString();
    }

    private String decrypt(String text, int step) {
        StringBuilder decrypted = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base;
                if (c >= 'а' && c <= 'я') {
                    base = 'а';
                    decrypted.append((char) ((c - base - step + 32) % 32 + base));
                } else if (c >= 'А' && c <= 'Я') {
                    base = 'А';
                    decrypted.append((char) ((c - base - step + 32) % 32 + base));
                } else {
                    base = Character.isLowerCase(c) ? 'a' : 'A';
                    decrypted.append((char) ((c - base - step + 26) % 26 + base));
                }
            } else {
                decrypted.append(c);
            }
        }
        return decrypted.toString();
    }



    public static void main(String[] args) {
        launch();
    }
}