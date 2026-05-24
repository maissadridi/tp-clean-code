package com.tp.bibliotheque;

public class Book {

    public String isbn;
    public String titre;
    public String auteur;
    private boolean disponible = true; // FIX : initialisé à true
    public String membreEmprunteurId;

    public Book(String isbn, String titre, String auteur) {
        this.isbn   = isbn;
        this.titre  = titre;
        this.auteur = auteur;
    }

    public boolean isAvailable() {
        return disponible; // FIX : retourne le vrai état
    }

    public void checkout(String membreId) {
        this.disponible         = false;        // FIX : marque indisponible
        this.membreEmprunteurId = membreId;     // FIX : mémorise le membre
    }

    public void returnBook() {
        // TODO : sera implémenté au prochain cycle
    }

    public String getMembreEmprunteurId() {
        return membreEmprunteurId;
    }
}