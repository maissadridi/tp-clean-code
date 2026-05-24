package com.tp.bibliotheque;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Member {

    private static final int MAX_EMPRUNTS = 3;

    // FIX smell #1 : champs private (étaient public)
    private final String     id;
    private final String     nom;
    private final List<Book> emprunts = new ArrayList<>();

    public Member(String id, String nom) {
        // FIX smell #2 : validation des paramètres
        if (id  == null || id.isBlank())  throw new IllegalArgumentException("Id membre vide");
        if (nom == null || nom.isBlank()) throw new IllegalArgumentException("Nom membre vide");
        this.id  = id;
        this.nom = nom;
    }

    public String    getId()              { return id; }
    public String    getNom()             { return nom; }
    public int       getNbEmprunts()      { return emprunts.size(); }
    public List<Book> getEmpruntsEnCours(){ return Collections.unmodifiableList(emprunts); }

    public void emprunter(Book book) {
        if (emprunts.size() >= MAX_EMPRUNTS)
            throw new IllegalStateException(
                    nom + " a atteint le quota de " + MAX_EMPRUNTS + " emprunts");
        book.checkout(this.id);
        emprunts.add(book);
    }

    public void retourner(Book book) {
        if (!emprunts.contains(book))
            throw new IllegalArgumentException(
                    "Ce livre n'est pas dans les emprunts de " + nom);
        book.returnBook();
        emprunts.remove(book);
    }

    public boolean peutEncoreEmprunter() {
        return emprunts.size() < MAX_EMPRUNTS;
    }
}