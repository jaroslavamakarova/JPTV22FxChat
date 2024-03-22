/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxchat;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javafx.application.Platform;

public class ChatUI extends BorderPane {
    private PrintWriter out;
    private BufferedReader in;
    private TextArea chatArea;

    public ChatUI() {
        chatArea = new TextArea();
        chatArea.setEditable(false);

        TextField messageField = new TextField();
        messageField.setPromptText("Введите сообщение...");

        Button sendButton = new Button("Отправить");
        sendButton.setOnAction(e -> {
            String message = messageField.getText();
            if (!message.isEmpty()) {
                out.println(message);
                messageField.clear();
            }
        });

        VBox bottomBox = new VBox(5, messageField, sendButton);
        bottomBox.setAlignment(Pos.CENTER);

        setCenter(chatArea);
        setBottom(bottomBox);

        new Thread(this::listenToServer).start();
    }

    private void listenToServer() {
        try {
            Socket socket = new Socket("localhost", 8890);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String serverMessage;
            while ((serverMessage = in.readLine()) != null) {
                updateChatArea(serverMessage + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateChatArea(String message) {
        chatArea.appendText(message);
    }
}


