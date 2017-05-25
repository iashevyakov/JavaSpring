package ru.itis.client.details;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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

import javafx.util.Callback;
import ru.itis.client.auth.Menu;
import ru.itis.client.firms.FirmOverviewController;
import ru.itis.client.nodes.NodeOverviewController;
import ru.itis.models.Detail;
import ru.itis.models.Firm;
import ru.itis.models.Node;

import java.io.FileNotFoundException;


public class DetailView {

    private static TableColumn id;

    private static TableColumn name;

    private static TableColumn firm;

    private static TableColumn node;

    private static  ListView<Firm> firmListView;

    private static ListView<Node> nodeListView;

    private static ObservableList<Firm> firms;

    private static ObservableList<Node> nodes;

    private static TableView table;

    private static ObservableList<Detail> details;


    public static void showDetails() throws FileNotFoundException {

        Stage stage = new Stage();
        Scene scene = new Scene(new Group());
        stage.getIcons().add(new Image("file:icon.png"));
        stage.setTitle("Details");
        stage.setWidth(375);

        stage.setHeight(515);

        table = new TableView();

        firmListView = new ListView<>();

        nodeListView = new ListView<>();

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
        id.setCellValueFactory(new PropertyValueFactory<Detail,Integer>("id"));

        name = new TableColumn("NAME");
        name.setCellValueFactory(new PropertyValueFactory<Detail,Integer>("name"));

        firm = new TableColumn("FIRM");
        firm.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Detail,String>,ObservableValue<String>>(){


            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Detail, String> param) {
                return new SimpleStringProperty(param.getValue().getFirm().getName());
            }
        });


        node = new TableColumn("NODE");
        node.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Detail,String>,ObservableValue<String>>(){

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Detail, String> param) {
                return new SimpleStringProperty(param.getValue().getNode().getName());
            }
        });


        firms = FXCollections.observableArrayList(FirmOverviewController.show());


        nodes = FXCollections.observableArrayList(NodeOverviewController.show());

        details = FXCollections.observableArrayList(DetailOverviewController.show());

        firmListView.setItems(firms);

        nodeListView.setItems(nodes);

        table.setItems(details);


        table.getColumns().addAll(id,name,firm,node);

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
                DetailOverviewController.delete(table, details);
            }
        });


        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DetailOverviewController.add(details,firmListView,nodeListView);
            }
        });

        edit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DetailOverviewController.edit(table,firmListView,nodeListView);
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
