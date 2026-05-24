package com.tp.bibliotheque;

public class Book {

    // BUG 1 : champs publics — violation d'encapsulation
    public String isbn;
    public String titre;
    public String auteur;
    public boolean disponible;
    public String membreEmprunteurId;

    public Book(String isbn, String titre, String auteur) {
        // BUG 2 : aucune validation des paramètres
        this.isbn    = isbn;
        this.titre   = titre;
        this.auteur  = auteur;
        // BUG 3 : disponible n'est pas initialisé à true
    }

    public boolean isAvailable() {
        // BUG 4 : retourne toujours false
        return false;
    }

    public void checkout(String membreId) {
        // BUG 5 : ne fait rien
    }

    public void returnBook() {
        // BUG 6 : ne fait rien
    }

    public String getMembreEmprunteurId() {
        return membreEmprunteurId;
    }
}
