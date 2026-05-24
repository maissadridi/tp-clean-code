package com.tp.bibliotheque;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Library {

    // FIX smell #3 : champs private (étaient public)
    private final List<Book>   catalogue = new ArrayList<>();
    private final List<Member> membres   = new ArrayList<>();

    public void ajouterLivre(Book book) {
        if (book != null) catalogue.add(book);
    }

    public void inscrireMembre(Member membre) {
        if (membre != null) membres.add(membre);
    }

    public Optional<Book> rechercherParIsbn(String isbn) {
        return catalogue.stream()
                .filter(b -> b.getIsbn().equals(isbn))
                .findFirst();
    }

    public Optional<Member> rechercherMembre(String id) {
        return membres.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst();
    }

    public List<Book> livresDisponibles() {
        return catalogue.stream()
                .filter(Book::isAvailable)
                .toList();
    }

    public int nbLivresDisponibles() {
        return (int) catalogue.stream().filter(Book::isAvailable).count();
    }

    public int nbLivresEmpruntes() {
        return (int) catalogue.stream().filter(b -> !b.isAvailable()).count();
    }

    public List<Book>   getCatalogue() { return catalogue; }
    public List<Member> getMembres()   { return membres; }
}