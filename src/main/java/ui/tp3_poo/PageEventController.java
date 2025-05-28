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

import java.util.ArrayList;
import java.util.List;

public class PageEventController {
    static int indice;

    @FXML
    private TextField nom;

    @FXML
    private TextField participant;

    //  Methodes pour afficher les détails de l'event
    @FXML
    void details(ActionEvent event) {
        //  Création d'un ObjectMapper pour la conversion de type
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        // S'il s'agit d'une conférence on affiche ses infos dans une boite de dialogue
        if (Page1Controller.liste.get(indice).get(0).equals("conference")) {
            Conference conference = mapper.convertValue(Page1Controller.liste.get(indice).get(2), Conference.class);
            List<String> liste = conference.afficherDetails();
            List liste_parti = mapper.convertValue(Page1Controller.liste.get(indice).get(3), List.class);
            List<Participant> liste_participant = new ArrayList<>();
            for (int i = 0; i < liste_parti.size(); i++) {
                liste_participant.add(mapper.convertValue(liste_parti.get(i), Participant.class));
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Information de la conférence");
            alert.setContentText("Nom conference : " + liste.get(0) + "\n" +
                    "Thème conference : " + liste.get(1) + "\n" +
                    "Id conference : " + liste.get(2) + "\n" +
                    "Lieu conference : " + liste.get(3)+ "\n" +
                    "Capacité max : " + liste.get(4) + "\n" +
                    "Date de debut conference : " + liste.get(5) + "\n" +
                    "Les intervenants : " + liste.get(6) + "\n" +
                    "Les participants : " + liste_participant.stream().map(e -> e.getNom()).toList() + "\n"
            );
            alert.show();

        //  Sinon, s'il s'agit d'un concert
        } else {
            Concert concert = mapper.convertValue(Page1Controller.liste.get(indice).get(2), Concert.class);
            List<String> liste = concert.afficherDetails();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            List liste_parti = mapper.convertValue(Page1Controller.liste.get(indice).get(3), List.class);
            List<Participant> liste_participant = new ArrayList<>();
            for (int i = 0; i < liste_parti.size(); i++) {
                liste_participant.add(mapper.convertValue(liste_parti.get(i), Participant.class));
            }
            alert.setHeaderText("Information du concert");
            alert.setContentText("Nom concert : " + liste.get(0) + "\n" +
                    "Artiste concert : " + liste.get(1) + "\n" +
                    "Id concert : " + liste.get(2) + "\n" +
                    "Lieu concert : " + liste.get(3) + "\n" +
                    "Capacité max : " + liste.get(4) + "\n" +
                    "Date de debut conference : " + liste.get(5) + "\n" +
                    "Le genre musical : " + liste.get(6) + "\n" +
                    "Les participants : " + liste_participant.stream().map(e -> e.getNom()).toList() + "\n"
            );
            alert.show();
        }
    }

    //  Methode pour désinscrire un participant
    @FXML
    void annuler() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        //  Si le champ d'email est vide on ne fait rien
        if (participant.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Erreur");
            alert.setContentText("Entrer le mail du participant à retirer");
            alert.show();
            return;
        }

        //  Utilisation d'un predicat pour gérer l'affichage en cas d'échec ou de réussite
        boolean resultat = false;
        Participant participant1 = new Participant(nom.getText(), participant.getText());

        //  S'il s'agit d'un concert
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

        //  S'il s'agit d'une conférence
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

    //  Methode pour ajouter un participant
    @FXML
    void ajouter() {
        //  Création d'un ObjectMAapper pour la conversion de type après une déserialisation
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

        //  Utilisation d'un predicat pour gérer l'affichage en cas d'échec ou de réussite
        boolean resultat = false;
        Participant participant1 = new Participant(nom.getText(), participant.getText());

        //  S'il s'agit d'un concert
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

        //  Sinon une conférence
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

    //  Methode pour supprimer un event
    @FXML
    void supprimer() {
        //  Création d'un PbjectMapper pour la conversion de type après une déserialisation
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        //  S'il s'agit une conférence
        if (Page1Controller.liste.get(indice).get(0).equals("conference")) {
            Conference conference = mapper.convertValue(Page1Controller.liste.get(indice).get(2), Conference.class);
            GestionEvenements gestionEvenements = GestionEvenements.getInstance();

            //  Utilisation d'un prédicat pour gérer l'affichage en cas d'échec ou de réussite
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

        //  Concert
        } else {
            Concert concert = mapper.convertValue(Page1Controller.liste.get(indice).get(2), Concert.class);
            GestionEvenements gestionEvenements = GestionEvenements.getInstance();

            //  Utilisation d'un prédicat pour gérer l'affichage en cas d'échec ou de réussite
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
