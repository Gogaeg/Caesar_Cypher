package org.example.cypherfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        TextArea inputArea = new TextArea();
        TextArea outputArea = new TextArea();
        Button encryptButton = new Button("Шифровать");
        Button decryptButton = new Button("Расшифровать");

        encryptButton.setOnAction(e -> {
            String input = inputArea.getText();
            String encrypted = encrypt(input);
            outputArea.setText(encrypted);
        });

        decryptButton.setOnAction(e -> {
            String input = outputArea.getText();
            String decrypted = decrypt(input);
            inputArea.setText(decrypted);
        });

        VBox layout = new VBox(10, inputArea, encryptButton, decryptButton, outputArea);
        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Шифратор");
        primaryStage.show();
    }

    private String encrypt(String text) {
        StringBuilder encrypted = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base;
                if (c >= 'а' && c <= 'я') {
                    base = 'а';
                    encrypted.append((char) ((c - base + 3) % 32 + base));
                } else if (c >= 'А' && c <= 'Я') {
                    base = 'А';
                    encrypted.append((char) ((c - base + 3) % 32 + base));
                } else {
                    base = Character.isLowerCase(c) ? 'a' : 'A';
                    encrypted.append((char) ((c - base + 3) % 26 + base));
                }
            } else {
                encrypted.append(c);
            }
        }
        return encrypted.toString();
    }

    private String decrypt(String text) {
        StringBuilder decrypted = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base;
                if (c >= 'а' && c <= 'я') {
                    base = 'а';
                    decrypted.append((char) ((c - base - 3 + 32) % 32 + base));
                } else if (c >= 'А' && c <= 'Я') {
                    base = 'А';
                    decrypted.append((char) ((c - base - 3 + 32) % 32 + base));
                } else {
                    base = Character.isLowerCase(c) ? 'a' : 'A';
                    decrypted.append((char) ((c - base - 3 + 26) % 26 + base));
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