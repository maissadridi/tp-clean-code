package com.tp.bibliotheque;

import org.junit.jupiter.api.*;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    private Library library;
    private Book    book1;
    private Book    book2;

    @BeforeEach
    void setUp() {
        library = new Library();
        book1   = new Book("978-0-13", "Clean Code",  "R. Martin");
        book2   = new Book("978-0-20", "Refactoring", "M. Fowler");
    }

    @Test
    @DisplayName("ajouterLivre : le livre apparaît dans le catalogue")
    void ajouterLivre_shouldAddToCatalogue() {
        library.ajouterLivre(book1);
        assertEquals(1, library.getCatalogue().size());
    }

    @Test
    @DisplayName("livresDisponibles : retourne uniquement les livres non empruntés")
    void livresDisponibles_onlyAvailable() {
        library.ajouterLivre(book1);
        library.ajouterLivre(book2);
        book1.checkout("M01");

        List<Book> dispo = library.livresDisponibles();

        assertEquals(1, dispo.size());
        assertTrue(dispo.contains(book2));
    }

    @Test
    @DisplayName("rechercherParIsbn : retourne le bon livre")
    void rechercherParIsbn_found() {
        library.ajouterLivre(book1);

        Optional<Book> result = library.rechercherParIsbn("978-0-13");

        assertTrue(result.isPresent());
        assertEquals("Clean Code", result.get().getTitre());
    }

    @Test
    @DisplayName("rechercherParIsbn : ISBN inconnu → Optional vide")
    void rechercherParIsbn_notFound() {
        library.ajouterLivre(book1);

        Optional<Book> result = library.rechercherParIsbn("INCONNU");

        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("nbLivresDisponibles : compte correct après emprunt")
    void nbLivresDisponibles_afterCheckout() {
        library.ajouterLivre(book1);
        library.ajouterLivre(book2);
        book1.checkout("M01");

        assertEquals(1, library.nbLivresDisponibles());
        assertEquals(1, library.nbLivresEmpruntes());
    }
}