package ui.tp3_poo;

import classes.Concert;
import classes.GestionEvenements;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PageConcertController {

    @FXML
    private TextField artiste;

    @FXML
    private TextField capacite;

    @FXML
    private TextField date;

    @FXML
    private TextField genre;

    @FXML
    private TextField lieu;

    @FXML
    private TextField nom;

    @FXML
    void ajouter() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        if (artiste.getText().equals("") || capacite.getText().equals("") || date.getText().equals("") || genre.getText().equals("") || lieu.getText().equals("") || nom.getText().equals(""))  {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Remplissez tous les champs");
            alert.setContentText("Tes champs manquent");
            alert.show();
            return;
        }

        GestionEvenements gestionEvenements = GestionEvenements.getInstance();
        Concert concert = new Concert();

        try {
            concert.setCapaciteMax(Integer.parseInt(capacite.getText()));
            concert.setArtiste(artiste.getText());
            concert.setDate(LocalDateTime.parse(date.getText()));
            concert.setGenreMusical(genre.getText());
            concert.setLieu(lieu.getText());
            concert.setNom(nom.getText());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Echec");
            alert.setContentText("Nous n'avons pas pu créer.");
            alert.show();
            return;
        }

        List<Object> liste = new ArrayList<>();
        try {
            liste = gestionEvenements.ajouterEvenement(concert, MainPageController.nomutilisateur, Page1Controller.liste);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Echec");
            alert.setContentText("L'event existe déjà.");
            alert.show();
            return;
        }
        Page1Controller.liste.add(liste);

        try {
            mapper.writeValue(new File("liste_event.json"), Page1Controller.liste);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Ajouté avec succès");
            alert.setContentText("Fermer l'interface pour accéder à la nouvelle modification");
            alert.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
