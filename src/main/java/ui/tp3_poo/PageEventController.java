package ui.tp3_poo;

import classes.Concert;
import classes.Conference;
import classes.GestionEvenements;
import classes.Participant;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PageEventController {
    static int indice;

    @FXML
    private TextField nom;

    @FXML
    private TextField participant;

    @FXML
    void details(ActionEvent event) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        if (Page1Controller.liste.get(indice).get(0).equals("conference")) {
            Conference conference = mapper.convertValue(Page1Controller.liste.get(indice).get(2), Conference.class);
            List<String> liste = conference.afficherDetails();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Information de la conférence");
            alert.setContentText("Nom conference : " + liste.get(0) + "\n" +
                    "Thème conference : " + liste.get(1) + "\n" +
                    "Id conference : " + liste.get(2) + "\n" +
                    "Lieu conference : " + liste.get(3)+ "\n" +
                    "Capacité max : " + liste.get(4) + "\n" +
                    "Date de debut conference : " + liste.get(5) + "\n" +
                    "Les intervenants : " + liste.get(6) + "\n"
            );
            alert.show();
        } else {
            Concert concert = mapper.convertValue(Page1Controller.liste.get(indice).get(2), Concert.class);
            List<String> liste = concert.afficherDetails();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Information du concert");
            alert.setContentText("Nom concert : " + liste.get(0) + "\n" +
                    "Artiste concert : " + liste.get(1) + "\n" +
                    "Id concert : " + liste.get(2) + "\n" +
                    "Lieu concert : " + liste.get(3) + "\n" +
                    "Capacité max : " + liste.get(4) + "\n" +
                    "Date de debut conference : " + liste.get(5) + "\n" +
                    "Le genre musical : " + liste.get(6) + "\n"
            );
            alert.show();
        }
    }

    @FXML
    void annuler() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        if (participant.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Erreur");
            alert.setContentText("Entrer le mail du participant à retirer");
            alert.show();
            return;
        }

        boolean resultat = false;
        Participant participant1 = new Participant(nom.getText(), participant.getText());

        if (Page1Controller.liste.get(indice).get(0).equals("concert")) {
            Concert concert = mapper.convertValue(Page1Controller.liste.get(indice).get(2), Concert.class);
            try {
                resultat = concert.annuler(Page1Controller.liste, indice, participant1, "liste_event");
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Not Found Participant");
                alert.setContentText("Le participant n'existe pas");
                alert.show();
                return;
            }

            if (resultat) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Retiré avec succès");
                alert.setContentText("Participant " + nom.getText() + " d'email " + participant.getText() + " retiré.");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Error");
                alert.setContentText("Nous n'avons pas pu rétirer");
                alert.show();
            }


        } else {
            Conference conference = mapper.convertValue(Page1Controller.liste.get(indice).get(2), Conference.class);
            try {
                resultat = conference.annuler(Page1Controller.liste, indice, participant1, "liste_event");
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Not Found Participant");
                alert.setContentText("Le participant n'existe pas");
                alert.show();
                return;
            }

            if (resultat) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Retiré avec succès");
                alert.setContentText("Participant " + nom.getText() + " d'email " + participant.getText() + " retiré.");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Error");
                alert.setContentText("Nous n'avons pas pu rétirer");
                alert.show();
            }
        }


    }

    @FXML
    void ajouter() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        if (participant.getText().equals("") || nom.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Erreur");
            alert.setContentText("Entrer un mail et un nom au participant à ajouter");
            alert.show();
            return;
        }

        boolean resultat = false;
        Participant participant1 = new Participant(nom.getText(), participant.getText());

        if (Page1Controller.liste.get(indice).get(0).equals("concert")) {
            Concert concert = mapper.convertValue(Page1Controller.liste.get(indice).get(2), Concert.class);
            try {
                resultat = concert.ajouterParticipant(Page1Controller.liste, indice, participant1, "liste_event");
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Evenement plein");
                alert.setContentText("Nous ne pouvons plus ajouter");
                alert.show();
                return;
            }

            if (resultat) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Ajouté avec succès");
                alert.setContentText("Participant " + nom.getText() + " ajouté.");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Erreur");
                alert.setContentText("Nous n'avons pas pu ajouter");
                alert.show();
            }


        } else {
            Conference conference = mapper.convertValue(Page1Controller.liste.get(indice).get(2), Conference.class);
            try {
                resultat = conference.ajouterParticipant(Page1Controller.liste, indice, participant1, "liste_event");
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Evenement plein");
                alert.setContentText("Nous ne pouvons plus ajouter");
                alert.show();
                return;
            }

            if (resultat) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Ajouté avec succès");
                alert.setContentText("Participant " + nom.getText() + " ajouté.");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Erreur");
                alert.setContentText("Nous n'avons pas pu ajouter");
                alert.show();
            }
        }
    }

    @FXML
    void supprimer() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        if (Page1Controller.liste.get(indice).get(0).equals("conference")) {
            Conference conference = mapper.convertValue(Page1Controller.liste.get(indice).get(2), Conference.class);
            GestionEvenements gestionEvenements = GestionEvenements.getInstance();

            boolean result = false;

            result = gestionEvenements.supprimerEvenement(Page1Controller.liste, conference, "liste_event");

            if (result) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Retiré avec succès");
                alert.setContentText("L'event a été retiré.");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Erreur");
                alert.setContentText("L'event n'a pas pu être retiré.");
                alert.show();
            }

        } else {
            Concert concert = mapper.convertValue(Page1Controller.liste.get(indice).get(2), Concert.class);
            GestionEvenements gestionEvenements = GestionEvenements.getInstance();

            boolean result = false;

            result = gestionEvenements.supprimerEvenement(Page1Controller.liste, concert, "liste_event");

            if (result) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Retiré avec succès");
                alert.setContentText("L'event a été retiré.");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Erreur");
                alert.setContentText("L'event n'a pas pu être retiré.");
                alert.show();
            }
        }
    }
}
