package sn.l2gl.suunu.daara.exception;

public class DaaraException extends RuntimeException{
    //RuntimeException c'est juste une classe exception fourni pas java
    public DaaraException (String message){
        super(message); //sert à envoyer le message à la classe mère
    }
}
