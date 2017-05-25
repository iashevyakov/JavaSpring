package ru.itis.client.details;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.itis.error.Error;
import ru.itis.models.Detail;
import ru.itis.models.Firm;
import ru.itis.models.Node;
import ru.itis.utils.FileWorker;

import java.io.FileNotFoundException;
import java.util.List;



public class DetailOverviewController {

    private static Stage dialog;

    private static GridPane auth;

    private static HttpEntity<String> entity;

    private static RestTemplate restTemplate;


    private static HttpHeaders headers;

    private static Alert alert;

    public static final String URI = "http://localhost:8080/details";


    public static void add(ObservableList<Detail> details,ListView<Firm> firmListView,ListView<Node> nodeListView){
        dialog = new Stage();
        dialog.setTitle("Adding");
        dialog.setHeight(270);
        dialog.setWidth(270);
        dialog.initStyle(StageStyle.UTILITY);
        auth = new GridPane();

        auth.setAlignment(Pos.CENTER);
        auth.setHgap(10);
        auth.setVgap(10);
        auth.setPadding(new Insets(25, 25, 25, 25));

        Label name = new Label("Name");
        auth.add(name, 0, 0);
        final TextField nameField = new TextField();
        auth.add(nameField, 1, 0);

        firmListView.setPrefWidth(200);
        firmListView.setPrefHeight(400);
        nodeListView.setPrefHeight(400);
        nodeListView.setPrefWidth(200);

        Label firm = new Label("Firm");
        Label node = new Label("Node");
        auth.add(firm,0,1);
        auth.add(node,1,1);
        auth.add(firmListView,0,2);
        auth.add(nodeListView,1,2);

        Button ok = new Button("OK");
        auth.add(ok, 1, 3);

        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (nameField.getText().equals("") || firmListView.getSelectionModel().getSelectedItem()==null || nodeListView.getSelectionModel().getSelectedItem()==null){
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Ошибка");
                    alert.setContentText(Error.FIELD_ERROR);
                    alert.showAndWait();
                }
                else {
                    Detail detail = new Detail();
                    detail.setName(nameField.getText());
                    detail.setId(0);
                    detail.setFirm(firmListView.getSelectionModel().getSelectedItem());
                    detail.setNode(nodeListView.getSelectionModel().getSelectedItem());
                    restTemplate = new RestTemplate();
                    HttpHeaders headers = new HttpHeaders();
                    try {
                        headers.add("Auth-Token", FileWorker.read());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    HttpEntity<Detail> entity = new HttpEntity<Detail>(detail, headers);
                    ResponseEntity<Detail> detailResponseEntity = restTemplate.exchange(URI, HttpMethod.POST, entity, Detail.class);
                    details.add(detailResponseEntity.getBody());
                    dialog.close();
                }
            }
        });

        Scene scene = new Scene(auth);
        dialog.setScene(scene);
        dialog.show();

    }
    public static void edit(TableView table,ListView<Firm> firmListView,ListView<Node> nodeListView){
        Detail detail = (Detail) table.getSelectionModel().getSelectedItem();
        if (detail==null){
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setWidth(250);
            alert.setHeight(200);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Выберите элемент!");
            alert.showAndWait();
        }
        else {
            dialog = new Stage();
            dialog.setTitle("Editing");
            dialog.setHeight(270);
            dialog.setWidth(270);
            dialog.initStyle(StageStyle.UTILITY);
            auth = new GridPane();

            auth.setAlignment(Pos.CENTER);
            auth.setHgap(10);
            auth.setVgap(10);
            auth.setPadding(new Insets(25, 25, 25, 25));

            Label name = new Label("Name");
            auth.add(name, 0, 0);
            final TextField nameField = new TextField();
            nameField.setText(detail.getName());
            auth.add(nameField, 1, 0);

            firmListView.setPrefWidth(200);
            firmListView.setPrefHeight(400);
            nodeListView.setPrefHeight(400);
            nodeListView.setPrefWidth(200);

            Label firm = new Label("Firm");
            Label node = new Label("Node");
            auth.add(firm,0,1);
            auth.add(node,1,1);
            auth.add(firmListView,0,2);
            auth.add(nodeListView,1,2);

            Button ok = new Button("OK");
            auth.add(ok, 1, 3);
            ok.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    String text = nameField.getText();
                    if (text.equals("") || firmListView.getSelectionModel().getSelectedItem()==null || nodeListView.getSelectionModel().getSelectedItem()==null){
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("Ошибка");
                        alert.setContentText(Error.FIELD_ERROR);
                        alert.showAndWait();
                    }
                    else {
                        detail.setName(nameField.getText());
                        detail.setFirm(firmListView.getSelectionModel().getSelectedItem());
                        detail.setNode(nodeListView.getSelectionModel().getSelectedItem());

                        HttpHeaders headers = new HttpHeaders();
                        try {
                            headers.add("Auth-Token", FileWorker.read());
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        HttpEntity<Detail> entity = new HttpEntity<>(detail, headers);
                        restTemplate.exchange(URI, HttpMethod.PUT, entity, String.class);
                        dialog.close();
                    }
                }
            });
            Scene scene = new Scene(auth);
            dialog.setScene(scene);
            dialog.show();
        }
    }

    public static void delete(TableView table, ObservableList<Detail> details){
        Detail detail= (Detail) table.getSelectionModel().getSelectedItem();
        if (detail==null){

            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Выберите элемент!");
            alert.showAndWait();

        }
        else {
            final String uri = "http://localhost:8080/details/" + String.valueOf(detail.getId());
            details.remove(detail);
            HttpHeaders headers = new HttpHeaders();
            try {
                headers.add("Auth-Token", FileWorker.read());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            HttpEntity<String> entity = new HttpEntity<>(null, headers);
            restTemplate.exchange(uri, HttpMethod.DELETE, entity, String.class);
        }
    }

    public static List<Detail> show() throws FileNotFoundException {
        restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.add("Auth-Token", FileWorker.read());
        entity = new HttpEntity<>(null,headers);
        ResponseEntity<List<Detail>> nodeList = restTemplate.exchange(URI, HttpMethod.GET,entity, new ParameterizedTypeReference<List<Detail>>() {});
        return nodeList.getBody();

    }

}