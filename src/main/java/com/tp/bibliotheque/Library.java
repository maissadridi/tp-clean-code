package com.tp.bibliotheque;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Library {

    // BUG 1 : champs publics
    public List<Book>   catalogue = new ArrayList<>();
    public List<Member> membres   = new ArrayList<>();

    public void ajouterLivre(Book book) {
        catalogue.add(book); // BUG 2 : pas de vérification null
    }

    public void inscrireMembre(Member membre) {
        membres.add(membre); // BUG 3 : pas de vérification null
    }

    public Optional<Book> rechercherParIsbn(String isbn) {
        // BUG 4 : retourne toujours vide
        return Optional.empty();
    }

    public List<Book> livresDisponibles() {
        // BUG 5 : retourne toujours vide
        return new ArrayList<>();
    }

    public int nbLivresDisponibles() {
        // BUG 6 : retourne toujours 0
        return 0;
    }

    public int nbLivresEmpruntes() {
        // BUG 7 : retourne toujours 0
        return 0;
    }

    public List<Book>   getCatalogue() { return catalogue; }
    public List<Member> getMembres()   { return membres; }
}
