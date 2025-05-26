package ui.tp3_poo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainPageController {
    public static String nomutilisateur;

    @FXML
    private AnchorPane anchorpane1;

    @FXML
    private PasswordField mot_de_passe_utilisateur;

    @FXML
    private TextField nom_utilisateur;

    @FXML
    private Label message_erreur;


    @FXML
    void connexion() {
        nomutilisateur = nom_utilisateur.getText();
        String motdepasse = mot_de_passe_utilisateur.getText();
        message_erreur.setStyle("-fx-text-fill : red");

        if ((nomutilisateur.isEmpty()) || (motdepasse.isEmpty())) {
            message_erreur.setText("Entrer votre nom ou/et votre mot de passe");
            return;
        }

        try(Connection connection = PoolConnexion.seConnecter();
            Statement stm = connection.createStatement()) {
            ResultSet resultSet = stm.executeQuery("SELECT nom, mot_de_passe FROM organisateur;");
            while (resultSet.next()) {
                if ((nomutilisateur.equals(resultSet.getString(1))) && (motdepasse.equals(resultSet.getString(2)))) {
                    resultSet.close();
                    // Lancement de la nouvelle fenetre
                    FXMLLoader fxmlLoader = new FXMLLoader(MainPage.class.getResource("page1.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 950, 600);
                    scene.getStylesheets().add(getClass().getResource("page1.css").toExternalForm());
                    Stage stage = new Stage();
                    stage.setTitle("MonApp");
                    stage.setScene(scene);
                    stage.show();
                    Stage stage1 = (Stage) anchorpane1.getScene().getWindow();
                    stage1.close();
                    return;
                }
            }

            message_erreur.setText("Utilisateur ou Mot de passe incorrect");

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Echec de connexion");
            alert.setContentText("Nous n'avons pas pu vous connecter");
            alert.showAndWait();
        }
    }
}