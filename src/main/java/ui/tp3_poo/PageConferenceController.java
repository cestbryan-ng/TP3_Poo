package ui.tp3_poo;

import classes.Concert;
import classes.Conference;
import classes.GestionEvenements;
import classes.Participant;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PageConferenceController {
    @FXML
    private TextField capacite;

    @FXML
    private TextField date;

    @FXML
    private TextField intervenant;

    @FXML
    private TextField lieu;

    @FXML
    private TextField nom;

    @FXML
    private TextField theme;

    //  Methode pour ajouter une conference dans le json (sérialisation)
    @FXML
    void ajouter() {
        if (intervenant.getText().equals("") || capacite.getText().equals("") || date.getText().equals("") || theme.getText().equals("") || lieu.getText().equals("") || nom.getText().equals(""))  {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Remplissez tous les champs");
            alert.setContentText("Tes champs manquent");
            alert.show();
            return;
        }

        // Création d'un singleton pour gérer le conférence et du conference en question
        GestionEvenements gestionEvenements = GestionEvenements.getInstance();
        Conference conference = new Conference();

        try {
            List<Participant> liste = new ArrayList<>();
            String[] intervants = intervenant.getText().split(",");
            for (int i = 0; i < intervants.length; i++) {
                Participant participant = new Participant();
                participant.setNom(intervants[0]);
                liste.add(participant);
            }
            conference.setCapaciteMax(Integer.parseInt(capacite.getText()));
            conference.setIntervenants(liste);
            conference.setDate(LocalDateTime.parse(date.getText()));
            conference.setTheme(theme.getText());
            conference.setLieu(lieu.getText());
            conference.setNom(nom.getText());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Echec");
            alert.setContentText("Nous n'avons pas pu créer.");
            alert.show();
            return;
        }

        //  Utilisation d'un predicat qui confirme l'ajout de la conference (sérialisation)
        boolean resultat = false;

        try {
            resultat = gestionEvenements.ajouterEvenement(conference, MainPageController.nomutilisateur, Page1Controller.liste, "liste_event");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Echec");
            alert.setContentText("L'event existe déjà.");
            alert.show();
            return;
        }

        if (resultat) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Ajouté avec succès");
            alert.setContentText("Fermer l'interface pour accéder à la nouvelle modification");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Echec");
            alert.setContentText("Nous n'avons pas pu créer.");
            alert.show();
        }
    }
}
