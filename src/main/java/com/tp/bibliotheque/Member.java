package com.tp.bibliotheque;

import java.util.ArrayList;
import java.util.List;

public class Member {

    // BUG 1 : champs publics
    public String id;
    public String nom;
    public List<Book> emprunts = new ArrayList<>();

    public Member(String id, String nom) {
        // BUG 2 : pas de validation
        this.id  = id;
        this.nom = nom;
    }

    public String getId()          { return id; }
    public String getNom()         { return nom; }
    public int    getNbEmprunts()  { return emprunts.size(); }

    public List<Book> getEmpruntsEnCours() {
        return emprunts; // BUG 3 : liste mutable exposée directement
    }

    public void emprunter(Book book) {
        // BUG 4 : aucune vérification du quota
        // BUG 5 : n'appelle pas book.checkout()
        emprunts.add(book);
    }

    public void retourner(Book book) {
        // BUG 6 : n'appelle pas book.returnBook()
        emprunts.remove(book);
    }

    public boolean peutEncoreEmprunter() {
        // BUG 7 : retourne toujours true
        return true;
    }
}
