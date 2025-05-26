package classes;

//  Exception personnalisé qui sera declenché lorsqu'un event aura atteint sa capacité maximale de participants
public class CapaciteMaxAtteinteException extends RuntimeException {
    public CapaciteMaxAtteinteException(String message) {
        super(message);
    }
}
