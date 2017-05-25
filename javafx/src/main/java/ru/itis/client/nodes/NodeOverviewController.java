package ru.itis.client.nodes;

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
import ru.itis.models.Firm;
import ru.itis.models.Node;
import ru.itis.utils.FileWorker;

import java.io.FileNotFoundException;
import java.util.List;



public class NodeOverviewController {

    private static Stage dialog;

    private static GridPane auth;

    private static HttpEntity<String> entity;

    private static RestTemplate restTemplate;

    private static TableColumn id;

    private static TableColumn name;

    private static HttpHeaders headers;

    private static Alert alert;

    public static final String URI = "http://localhost:8080/nodes";


    public static void add(ObservableList<Node> nodes){
        dialog = new Stage();
        dialog.setTitle("Adding");
        dialog.setHeight(200);
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

        Button ok = new Button("OK");
        auth.add(ok, 1, 1);

        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (nameField.getText().equals("")){
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Ошибка");
                    alert.setContentText(Error.FIELD_ERROR);
                    alert.showAndWait();
                }
                else {
                    Node node = new Node(0, nameField.getText());
                    restTemplate = new RestTemplate();
                    HttpHeaders headers = new HttpHeaders();
                    try {
                        headers.add("Auth-Token", FileWorker.read());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    HttpEntity<Node> entity = new HttpEntity<Node>(node, headers);
                    ResponseEntity<Node> nodeResponseEntity = restTemplate.exchange(URI, HttpMethod.POST, entity, Node.class);
                    nodes.add(nodeResponseEntity.getBody());
                    dialog.close();
                }
            }
        });

        Scene scene = new Scene(auth);
        dialog.setScene(scene);
        dialog.show();

    }
    public static void edit(TableView table){
        Node node= (Node) table.getSelectionModel().getSelectedItem();
        if (node==null){
            alert = new Alert(Alert.AlertType.INFORMATION);
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
            dialog.setHeight(200);
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
            nameField.setText(node.getName());
            auth.add(nameField, 1, 0);

            Button ok = new Button("OK");
            auth.add(ok, 1, 1);
            ok.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    String text = nameField.getText();
                    if (text.equals("")){
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("Ошибка");
                        alert.setContentText(Error.FIELD_ERROR);
                        alert.showAndWait();
                    }
                    else {
                        node.setName(nameField.getText());

                        HttpHeaders headers = new HttpHeaders();
                        try {
                            headers.add("Auth-Token", FileWorker.read());
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        HttpEntity<Node> entity = new HttpEntity<>(node, headers);
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

    public static void delete(TableView table, ObservableList<Node> nodes){
        Node node = (Node) table.getSelectionModel().getSelectedItem();
        if (node==null){

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Выберите элемент!");
            alert.showAndWait();

        }
        else {
            final String uri = "http://localhost:8080/nodes/" + String.valueOf(node.getId());
            nodes.remove(node);
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

    public static List<Node> show() throws FileNotFoundException {
        restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.add("Auth-Token", FileWorker.read());
        entity = new HttpEntity<>(null,headers);
        ResponseEntity<List<Node>> nodeList = restTemplate.exchange(URI, HttpMethod.GET,entity, new ParameterizedTypeReference<List<Node>>() {});
        return nodeList.getBody();

    }

}
