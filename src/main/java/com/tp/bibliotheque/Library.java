package com.tp.bibliotheque;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Library {

    // champs toujours publics — sera corrigé en C8
    public List<Book>   catalogue = new ArrayList<>();
    public List<Member> membres   = new ArrayList<>();

    public void ajouterLivre(Book book) {
        if (book != null) catalogue.add(book);
    }

    public void inscrireMembre(Member membre) {
        if (membre != null) membres.add(membre);
    }

    public Optional<Book> rechercherParIsbn(String isbn) {
        return catalogue.stream()
                .filter(b -> b.getIsbn().equals(isbn))
                .findFirst(); // FIX : vraie recherche
    }

    public List<Book> livresDisponibles() {
        return catalogue.stream()
                .filter(Book::isAvailable)
                .toList(); // FIX : filtre les disponibles
    }

    public int nbLivresDisponibles() {
        return (int) catalogue.stream()
                .filter(Book::isAvailable).count(); // FIX
    }

    public int nbLivresEmpruntes() {
        return (int) catalogue.stream()
                .filter(b -> !b.isAvailable()).count(); // FIX
    }

    public List<Book>   getCatalogue() { return catalogue; }
    public List<Member> getMembres()   { return membres; }
}