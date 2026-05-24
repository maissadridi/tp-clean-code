package com.tp.bibliotheque;

public class Book {

    // FIX smell #1 : champs private (étaient public)
    private final String isbn;
    private final String titre;
    private final String auteur;
    private boolean disponible = true;
    private String membreEmprunteurId;

    public Book(String isbn, String titre, String auteur) {
        validateParam(isbn,   "ISBN");
        validateParam(titre,  "Titre");
        validateParam(auteur, "Auteur");
        this.isbn   = isbn;
        this.titre  = titre;
        this.auteur = auteur;
    }

    private void validateParam(String valeur, String nom) {
        if (valeur == null || valeur.isBlank())
            throw new IllegalArgumentException(nom + " ne peut pas être vide");
    }

    // getters
    public String  getIsbn()               { return isbn; }
    public String  getTitre()              { return titre; }
    public String  getAuteur()             { return auteur; }
    public boolean isAvailable()           { return disponible; }
    public String  getMembreEmprunteurId() { return membreEmprunteurId; }

    public void checkout(String membreId) {
        if (!disponible)
            throw new IllegalStateException("Le livre '" + titre + "' est déjà emprunté");
        this.disponible         = false;
        this.membreEmprunteurId = membreId;
    }

    public void returnBook() {
        if (disponible)
            throw new IllegalStateException("Le livre '" + titre + "' n'est pas emprunté");
        this.disponible         = true;
        this.membreEmprunteurId = null;
    }
}