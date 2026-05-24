package com.tp.bibliotheque;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Member {

    private static final int MAX_EMPRUNTS = 3;

    // champs toujours publics — sera corrigé en C8
    public String id;
    public String nom;
    public List<Book> emprunts = new ArrayList<>();

    public Member(String id, String nom) {
        this.id  = id;
        this.nom = nom;
    }

    public String getId()         { return id; }
    public String getNom()        { return nom; }
    public int    getNbEmprunts() { return emprunts.size(); }

    public List<Book> getEmpruntsEnCours() {
        return Collections.unmodifiableList(emprunts); // FIX : liste non mutable
    }

    public void emprunter(Book book) {
        if (emprunts.size() >= MAX_EMPRUNTS)
            throw new IllegalStateException(
                    nom + " a atteint le quota de " + MAX_EMPRUNTS + " emprunts");
        book.checkout(this.id); // FIX : appelle checkout()
        emprunts.add(book);
    }

    public void retourner(Book book) {
        if (!emprunts.contains(book))
            throw new IllegalArgumentException("Ce livre n'est pas dans les emprunts de " + nom);
        book.returnBook(); // FIX : appelle returnBook()
        emprunts.remove(book);
    }

    public boolean peutEncoreEmprunter() {
        return emprunts.size() < MAX_EMPRUNTS; // FIX : vérifie le quota
    }
}