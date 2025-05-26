package ui.tp3_poo;

import classes.Concert;
import classes.Conference;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.util.List;
import java.util.ResourceBundle;


public class Page1Controller implements Initializable {
    static List<List<Object>> liste;

    @FXML
    private Label nom_orga;

    @FXML
    private VBox vbox;

    public void initialize(URL location, ResourceBundle resources) {
        vbox.getChildren().clear();
        nom_orga.setText("Nom organisateur : " + MainPageController.nomutilisateur);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        try {
            liste = mapper.readValue(
                new File("liste_event.json"),
                new TypeReference<List<List<Object>>>() {}
            );

            Integer n = 0;
            for (List<Object> i : liste) {
                if (i.get(1).equals(MainPageController.nomutilisateur)) {
                    if (i.get(0).equals("conference")) {
                        Conference conference = mapper.convertValue(i.get(2), Conference.class);
                        Button button = new Button();
                        button.setOnAction(e -> evenement(e));
                        button.setMnemonicParsing(false);
                        button.setPrefHeight(84);
                        button.setPrefWidth(810);
                        button.setStyle("-fx-background-color : #fafbfa; -fx-cursor : hand; -fx-text-fill : white; -fx-border-color : gray; -fx-border-width : 0 0 1 0;");
                        button.setText(n.toString());
                        HBox hBox = new HBox();
                        hBox.setAlignment(Pos.CENTER);
                        hBox.setPrefHeight(100);
                        hBox.setPrefWidth(775.0);
                        hBox.setPadding(new Insets(0, 0, 0, 10));
                        button.setGraphic(hBox);
                        ImageView imageView = new ImageView(new Image(getClass().getResource("images/conference.png").toString()));
                        imageView.setFitWidth(65);
                        imageView.setFitHeight(54);
                        imageView.setPreserveRatio(true);
                        imageView.setPickOnBounds(true);
                        Label label1 = new Label("numero id : " + conference.getId());
                        label1.setPadding(new Insets(0, 50, 0, 50));
                        Label label2 = new Label("nom conférence : " + conference.getNom());
                        label2.setPadding(new Insets(0, 50, 0, 0));
                        Label label3 = new Label("capacité max : " +conference.getCapaciteMax().toString());
                        label3.setPadding(new Insets(0, 50, 0, 0));
                        label1.setStyle("-fx-font-size : 15; -fx-text-fill : \"black\"; -fx-font-family : \"cambria\";");
                        label2.setStyle("-fx-font-size : 15; -fx-text-fill : \"black\"; -fx-font-family : \"cambria\";");
                        label3.setStyle("-fx-font-size : 15; -fx-text-fill : \"black\"; -fx-font-family : \"cambria\";");
                        hBox.getChildren().add(imageView);
                        hBox.getChildren().add(label1);
                        hBox.getChildren().add(label2);
                        hBox.getChildren().add(label3);
                        vbox.getChildren().add(button);
                    } else {
                        Concert concert = mapper.convertValue(i.get(2), Concert.class);
                        Button button = new Button();
                        button.setOnAction(e -> evenement(e));
                        button.setMnemonicParsing(false);
                        button.setPrefHeight(84);
                        button.setPrefWidth(810);
                        button.setStyle("-fx-background-color : #fafbfa; -fx-cursor : hand; -fx-text-fill : white; -fx-border-color : gray; -fx-border-width : 0 0 1 0;");
                        button.setText(n.toString());
                        HBox hBox = new HBox();
                        hBox.setAlignment(Pos.CENTER);
                        hBox.setPrefHeight(100);
                        hBox.setPrefWidth(775.0);
                        hBox.setPadding(new Insets(0, 0, 0, 10));
                        button.setGraphic(hBox);
                        ImageView imageView = new ImageView(new Image(getClass().getResource("images/festival.png").toString()));
                        imageView.setFitWidth(65);
                        imageView.setFitHeight(54);
                        imageView.setPreserveRatio(true);
                        imageView.setPickOnBounds(true);
                        Label label1 = new Label("numero id : " + concert.getId());
                        label1.setPadding(new Insets(0, 50, 0, 50));
                        Label label2 = new Label("nom concert : " + concert.getNom());
                        label2.setPadding(new Insets(0, 50, 0, 0));
                        Label label3 = new Label("capacité max : " +concert.getCapaciteMax().toString());
                        label3.setPadding(new Insets(0, 50, 0, 0));
                        label1.setStyle("-fx-font-size : 15; -fx-text-fill : \"black\"; -fx-font-family : \"cambria\";");
                        label2.setStyle("-fx-font-size : 15; -fx-text-fill : \"black\"; -fx-font-family : \"cambria\";");
                        label3.setStyle("-fx-font-size : 15; -fx-text-fill : \"black\"; -fx-font-family : \"cambria\";");
                        hBox.getChildren().add(imageView);
                        hBox.getChildren().add(label1);
                        hBox.getChildren().add(label2);
                        hBox.getChildren().add(label3);
                        vbox.getChildren().add(button);
                    }
                }
                n++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void rechercher() {

    }

    @FXML
    void ajouter_confer() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainPage.class.getResource("pageconference.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 451, 271);
        scene.getStylesheets().add(getClass().getResource("pageevent.css").toExternalForm());
        Stage stage = new Stage();
        stage.setTitle("MonApp");
        stage.setScene(scene);
        stage.setOnCloseRequest(e -> {
            try {
                FXMLLoader fxmlLoader2 = new FXMLLoader(MainPage.class.getResource("page1.fxml"));
                Scene scene2 = new Scene(fxmlLoader2.load(), 950, 600);
                scene2.getStylesheets().add(getClass().getResource("page1.css").toExternalForm());
                Stage stage2 = new Stage();
                stage2.setTitle("MonApp");
                stage2.setScene(scene2);
                stage2.show();
            } catch (IOException err) {}
        });
        stage.show();
        Stage stage1 = (Stage) vbox.getScene().getWindow();
        stage1.close();
    }

    @FXML
    void ajouter_concert() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainPage.class.getResource("pageconcert.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 451, 271);
        scene.getStylesheets().add(getClass().getResource("pageevent.css").toExternalForm());
        Stage stage = new Stage();
        stage.setTitle("MonApp");
        stage.setScene(scene);
        stage.setOnCloseRequest(e -> {
            try {
                FXMLLoader fxmlLoader2 = new FXMLLoader(MainPage.class.getResource("page1.fxml"));
                Scene scene2 = new Scene(fxmlLoader2.load(), 950, 600);
                scene2.getStylesheets().add(getClass().getResource("page1.css").toExternalForm());
                Stage stage2 = new Stage();
                stage2.setTitle("MonApp");
                stage2.setScene(scene2);
                stage2.show();
            } catch (IOException err) {}
        });
        stage.show();
        Stage stage1 = (Stage) vbox.getScene().getWindow();
        stage1.close();
    }

    @FXML
    void evenement(ActionEvent event)  {
        Button button = (Button) event.getSource();
        PageEventController.indice = Integer.parseInt(button.getText());

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainPage.class.getResource("pageevent.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 451, 271);
            scene.getStylesheets().add(getClass().getResource("pageevent.css").toExternalForm());
            Stage stage = new Stage();
            stage.setTitle("MonApp");
            stage.setScene(scene);
            stage.setOnCloseRequest(e -> {
                try {
                    FXMLLoader fxmlLoader2 = new FXMLLoader(MainPage.class.getResource("page1.fxml"));
                    Scene scene2 = new Scene(fxmlLoader2.load(), 950, 600);
                    scene2.getStylesheets().add(getClass().getResource("page1.css").toExternalForm());
                    Stage stage2 = new Stage();
                    stage2.setTitle("MonApp");
                    stage2.setScene(scene2);
                    stage2.show();
                } catch (IOException err) {}
            });
            stage.show();
            Stage stage1 = (Stage) vbox.getScene().getWindow();
            stage1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
