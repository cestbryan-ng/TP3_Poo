package classes;

//  Exception personnalisé qui sera declenché lorsqu'un event sera ajouté dans la liste des events et que cet event y figure déjà
public class EvenementDejaExistantException extends RuntimeException {
    public EvenementDejaExistantException(String message) {
        super(message);
    }
}
