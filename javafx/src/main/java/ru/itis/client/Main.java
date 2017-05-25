package ru.itis.client;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.itis.client.auth.Authentication;
import ru.itis.client.auth.Menu;
import ru.itis.utils.FileWorker;

import java.io.FileNotFoundException;


public class Main extends Application {

    FileWorker fileWorker;
    final String uri = "http://localhost:8080/token";
    HttpHeaders httpHeaders;
    HttpEntity<String> httpEntity;
    RestTemplate restTemplate;
    ResponseEntity<Boolean> result;

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        if (FileWorker.fileExists()) {
            if (FileWorker.fileIsEmpty()) {
                Authentication.authentication(primaryStage);
            } else {
                httpHeaders = new HttpHeaders();
                httpHeaders.add("token",FileWorker.read());
                System.out.println(FileWorker.read());
                httpEntity = new HttpEntity<>(null,httpHeaders);
                restTemplate = new RestTemplate();
                result = restTemplate.exchange(uri, HttpMethod.POST, httpEntity, Boolean.class);
                   if (result.getBody()){
                       Menu.showMenu(primaryStage);
                   }
                   else {
                       Authentication.authentication(primaryStage);
                   }
            }

        }
        else{
            Authentication.authentication(primaryStage);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
