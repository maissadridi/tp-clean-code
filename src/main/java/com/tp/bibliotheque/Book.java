package com.tp.bibliotheque;

public class Book {

    public String isbn;
    public String titre;
    public String auteur;
    private boolean disponible = true;
    public String membreEmprunteurId;

    public Book(String isbn, String titre, String auteur) {
        // REFACTOR : validation extraite dans méthode privée
        validateParam(isbn,  "ISBN");
        validateParam(titre, "Titre");
        this.isbn   = isbn;
        this.titre  = titre;
        this.auteur = auteur;
    }

    // méthode privée extraite — SRP
    private void validateParam(String valeur, String nom) {
        if (valeur == null || valeur.isBlank())
            throw new IllegalArgumentException(nom + " ne peut pas être vide");
    }

    public boolean isAvailable()           { return disponible; }
    public String  getIsbn()               { return isbn; }
    public String  getTitre()              { return titre; }
    public String  getAuteur()             { return auteur; }
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