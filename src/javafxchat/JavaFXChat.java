/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxchat;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFXChat extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        // Создаем экземпляр ChatUI
        ChatUI chatUI = new ChatUI();
        
        // Создаем сцену и добавляем ChatUI в корневой элемент
        Scene scene = new Scene(chatUI, 400, 300);
        
        // Устанавливаем сцену и отображаем окно
        primaryStage.setTitle("Chat App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
