package ru.itis.client.auth;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import ru.itis.client.details.DetailView;
import ru.itis.client.firms.FirmView;
import ru.itis.client.nodes.NodeView;

import java.io.File;
import java.io.FileNotFoundException;

public class Menu {
    public static void showMenu(Stage stage){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Button nodeBtn = new Button("NODES");
        nodeBtn.setStyle("-fx-font: 20 italic;-fx-background-color: white;-fx-text-fill: black; -fx-border-color: midnightblue");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(nodeBtn);
        grid.add(hbBtn, 0, 0);

        Button firmBtn = new Button("FIRMS");
        firmBtn.setStyle("-fx-font: 20 italic;-fx-background-color: white;-fx-text-fill: black; -fx-border-color: midnightblue");
        HBox hbBtn2 = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(firmBtn);
        grid.add(hbBtn2, 0, 1);

        Button detailBtn = new Button("DETAILS");
        detailBtn.setStyle("-fx-font: 20 italic;-fx-background-color: white;-fx-text-fill: black; -fx-border-color: midnightblue");
        HBox hbBtn3 = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(detailBtn);
        grid.add(hbBtn3, 0, 2);

        grid.setStyle("-fx-background-color: gray");

        Scene scene = new Scene(grid, 400, 200);

        stage.setScene(scene);
        stage.setTitle("Welcome");
        stage.getIcons().add(new Image("file:icon.png"));
        stage.show();
        firmBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    stage.close();
                    FirmView.showFirms();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        nodeBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    stage.close();
                    NodeView.showNodes();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        detailBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    stage.close();
                    DetailView.showDetails();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
