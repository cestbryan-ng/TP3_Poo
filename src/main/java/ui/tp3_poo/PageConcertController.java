package ui.tp3_poo;

import classes.Concert;
import classes.GestionEvenements;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import java.time.LocalDateTime;

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

    //  Methode pour ajouter un concert dans le json (sérialisation)
    @FXML
    void ajouter() {
        //  On ne fait rien si les champs sont vides
        if (artiste.getText().equals("") || capacite.getText().equals("") || date.getText().equals("") || genre.getText().equals("") || lieu.getText().equals("") || nom.getText().equals(""))  {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Remplissez tous les champs");
            alert.setContentText("Tes champs manquent");
            alert.show();
            return;
        }

        // Création d'un singleton pour gérer le concert et du concert en question
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

        //  Utilisation d'un predicat qui confirme l'ajout du concert (sérialisation)
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
