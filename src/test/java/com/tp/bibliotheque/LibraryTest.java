package com.tp.bibliotheque;

import org.junit.jupiter.api.*;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

// C1 RED — ces tests compilent mais échouent
class LibraryTest {

    @Test
    @DisplayName("livresDisponibles : retourne les livres non empruntés")
    void livresDisponibles_onlyAvailable() {
        Library library = new Library();
        Book b1 = new Book("ISBN-1", "Clean Code", "R. Martin");
        Book b2 = new Book("ISBN-2", "Refactoring", "M. Fowler");
        library.ajouterLivre(b1);
        library.ajouterLivre(b2);
        b1.checkout("M01");

        List<Book> dispo = library.livresDisponibles();

        // ÉCHOUE : livresDisponibles() retourne toujours une liste vide
        assertEquals(1, dispo.size());
        assertTrue(dispo.contains(b2));
    }

    @Test
    @DisplayName("rechercherParIsbn : retourne le bon livre")
    void rechercherParIsbn_found() {
        Library library = new Library();
        Book b1 = new Book("978-0-13", "Clean Code", "R. Martin");
        library.ajouterLivre(b1);

        Optional<Book> result = library.rechercherParIsbn("978-0-13");

        // ÉCHOUE : rechercherParIsbn() retourne toujours Optional.empty()
        assertTrue(result.isPresent());
    }

    @Test
    @DisplayName("nbLivresDisponibles : compte correct")
    void nbLivresDisponibles_correct() {
        Library library = new Library();
        library.ajouterLivre(new Book("I1", "T1", "A1"));
        library.ajouterLivre(new Book("I2", "T2", "A2"));

        // ÉCHOUE : nbLivresDisponibles() retourne toujours 0
        assertEquals(2, library.nbLivresDisponibles());
    }
}
