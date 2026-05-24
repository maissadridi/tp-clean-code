package com.tp.bibliotheque;

public class Book {

    public String isbn;
    public String titre;
    public String auteur;
    private boolean disponible = true;
    public String membreEmprunteurId;

    public Book(String isbn, String titre, String auteur) {
        this.isbn   = isbn;
        this.titre  = titre;
        this.auteur = auteur;
    }

    public boolean isAvailable() {
        return disponible;
    }

    public void checkout(String membreId) {
        if (!disponible)
            throw new IllegalStateException("Le livre '" + titre + "' est déjà emprunté");
        this.disponible         = false;
        this.membreEmprunteurId = membreId;
    }

    public void returnBook() {
        if (disponible)
            throw new IllegalStateException("Le livre '" + titre + "' n'est pas emprunté");
        this.disponible         = true;   // FIX : remet disponible
        this.membreEmprunteurId = null;   // FIX : libère le membre
    }

    public String getMembreEmprunteurId() {
        return membreEmprunteurId;
    }
}