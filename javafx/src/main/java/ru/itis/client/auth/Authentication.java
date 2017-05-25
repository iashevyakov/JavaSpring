package ru.itis.client.auth;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.itis.utils.FileWorker;


public class Authentication {
    public static void authentication(Stage stage){
        GridPane auth = new GridPane();
        auth.setAlignment(Pos.CENTER);
        auth.setHgap(10);
        auth.setVgap(10);
        auth.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Authentication");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        auth.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("Login:");
        auth.add(userName, 0, 1);
        final TextField userTextField = new TextField();
        auth.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        auth.add(pw, 0, 2);

        final PasswordField pwBox = new PasswordField();
        auth.add(pwBox, 1, 2);

        Button btn = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        auth.add(hbBtn, 1, 3);
        final Text actiontarget = new Text();
        auth.add(actiontarget, 0, 4);

        Button btn2 = new Button("Registration");
        HBox hbBtn2 = new HBox(20);
        hbBtn2.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn2.getChildren().add(btn2);
        auth.add(hbBtn2, 0, 3);


        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (userTextField.getText().equals("")||pwBox.getText().equals("")){
                    actiontarget.setFill(Color.DARKMAGENTA);
                    actiontarget.setText("CHECK YOU ENTERED DATA");
                }
                else{
                    final String uri = "http://localhost:8080/login";
                    HttpHeaders headers = new HttpHeaders();
                    headers.add("password",pwBox.getText());
                    headers.add("login",userTextField.getText());
                    HttpEntity<String> httpEntity = new HttpEntity<>(null,headers);
                    RestTemplate restTemplate = new RestTemplate();
                    try {
                        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, httpEntity, String.class);
                        actiontarget.setText(result.getHeaders().getFirst("Auth-Token"));
                        FileWorker.write(result.getHeaders().getFirst("Auth-Token"));
                        Menu.showMenu(stage);

                    }
                    catch (Exception e){
                        actiontarget.setFill(Color.FIREBRICK);
                        actiontarget.setText("Login or Password are incorrect!");
                    }

                }
            }
        });
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Registration.registration(stage);
            }
        });
        auth.setStyle("-fx-background-color: darkgrey");
        Scene scene = new Scene(auth, 325, 350);
        stage.setScene(scene);
        stage.setTitle("Welcome");
        stage.getIcons().add(new Image("file:icon.png"));
        stage.show();

    }
    }

