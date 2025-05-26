package ui.tp3_poo;

import classes.Concert;
import classes.GestionEvenements;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

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

        boolean resultat = false;

        try {
            resultat = gestionEvenements.ajouterEvenement(concert, MainPageController.nomutilisateur, Page1Controller.liste, "liste_event");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Echec");
            alert.setContentText("Nous n'avons pas pu créer.");
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
