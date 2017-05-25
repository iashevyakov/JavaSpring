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

import org.springframework.web.client.RestTemplate;
import ru.itis.dto.UserDataForRegistrationDto;
import ru.itis.dto.UserDto;

/**
 * Created by Ivan on 18.05.2017.
 */
public class Registration {
    public static void registration(Stage primaryStage){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Registration");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("Login:");
        grid.add(userName, 0, 1);

        final TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label name = new Label("Name");
        grid.add(name,0,2);

        final TextField nameField = new TextField();
        grid.add(nameField, 1, 2);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 3);

        final PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 3);

        Label pw2 = new Label("Confirm Password:");
        grid.add(pw2, 0, 4);

        final PasswordField pwBox2 = new PasswordField();
        grid.add(pwBox2, 1, 4);

        Label age = new Label("Age");
        grid.add(age, 0, 5);

        final TextField ageField = new TextField();
        grid.add(ageField, 1, 5);

        Button btn = new Button("Sign Up");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 6);

        Button btn2 = new Button("Authentication");
        HBox hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn2.getChildren().add(btn2);
        grid.add(hbBtn2, 1, 0);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 0, 6);

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                final String uri = "http://localhost:8080/users";
                if (ageField.getText().equals("") || userTextField.getText().equals("") || pwBox.getText().equals("")||name.getText().equals("")||pwBox2.getText().equals("")){
                    actiontarget.setText("CHECK YOUR ENTERED DATA!");actiontarget.setFill(Color.FIREBRICK);
                }
                else{
                    if (!pwBox.getText().equals(pwBox2.getText())){
                        actiontarget.setFill(Color.DARKMAGENTA);
                        actiontarget.setText("PASSWORDS DON'T MATCH!");
                    }
                    else{
                        UserDataForRegistrationDto userDataForRegistrationDto = new UserDataForRegistrationDto(userTextField.getText(),pwBox.getText(),Integer.valueOf(ageField.getText()),nameField.getText());

                        RestTemplate restTemplate = new RestTemplate();

                        UserDto result = restTemplate.postForObject(uri, userDataForRegistrationDto, UserDto.class);
                        actiontarget.setFill(Color.CRIMSON);
                        actiontarget.setText("Имя: "+result.getName()+" Возраст: "+result.getAge()+" ID: "+result.getId());}}
            }
        });
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Authentication.authentication(primaryStage);
            }
        });
        grid.setStyle("-fx-background-color: darkgrey");
        Scene scene = new Scene(grid, 325, 350);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("file:icon.png"));
        primaryStage.setTitle("Welcome");

        primaryStage.show();
    }

    }

