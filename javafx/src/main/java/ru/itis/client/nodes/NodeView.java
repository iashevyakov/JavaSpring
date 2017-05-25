package ru.itis.client.nodes;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import ru.itis.client.auth.Menu;
import ru.itis.models.Firm;
import ru.itis.models.Node;

import java.io.FileNotFoundException;


public class NodeView {


    private static TableColumn id;

    private static TableColumn name;

    private static TableView table;

    private static ObservableList<Node> nodes;


    public static void showNodes() throws FileNotFoundException {

        Stage stage = new Stage();
        Scene scene = new Scene(new Group());
        stage.setTitle("Nodes");
        stage.setWidth(300);

        stage.setHeight(515);

        stage.getIcons().add(new Image("file:icon.png"));

        table = new TableView();

        table.setEditable(true);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        HBox hBox = new HBox(3);

        hBox.setAlignment(Pos.CENTER);

        hBox.setPadding(new Insets(5,0,0,0));

        Button delete = new Button("Delete");

        delete.setStyle("-fx-font: 12 italic;-fx-background-color: midnightblue;-fx-text-fill: lightcyan;");

        Button edit = new Button("Edit");

        edit.setStyle("-fx-font: 12 italic;-fx-background-color: darkgreen;-fx-text-fill: lightcyan;");

        Button add = new Button("Add");

        add.setStyle("-fx-font: 12 italic;-fx-background-color: darkred;-fx-text-fill: lightcyan;");

        hBox.getChildren().addAll(add,edit,delete);

        id = new TableColumn("ID");
        id.setCellValueFactory(new PropertyValueFactory<Firm,Integer>("id"));

        name = new TableColumn("NAME");
        name.setCellValueFactory(new PropertyValueFactory<Firm,Integer>("name"));

        nodes = FXCollections.observableArrayList(NodeOverviewController.show());

        table.setItems(nodes);

        table.getColumns().addAll(id,name);

        HBox backBox = new HBox(1);
        backBox.setAlignment(Pos.CENTER);
        Button back = new Button("Back");
        back.setStyle("-fx-font: 10 italic;-fx-background-color: darkmagenta;-fx-text-fill: lightcyan;");
        backBox.getChildren().add(back);
        backBox.setPadding(new Insets(0,0,5,0));
        VBox vbox = new VBox(3);
        vbox.setPadding(new Insets(5, 0, 0, 20));
        vbox.getChildren().addAll(backBox,table,hBox);

        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                NodeOverviewController.delete(table, nodes);
            }
        });


        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                NodeOverviewController.add(nodes);
            }
        });

        edit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                NodeOverviewController.edit(table);
            }
        });
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
                Menu.showMenu(new Stage());
            }
        });


        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        stage.setScene(scene);
        stage.show();

    }



}
