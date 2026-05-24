package com.tp.bibliotheque;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

// C1 RED — ces 2 tests compilent mais échouent
class BookTest {

    @Test
    @DisplayName("Un livre nouvellement créé est disponible")
    void newBook_shouldBeAvailable() {
        // ARRANGE
        Book book = new Book("978-3-16", "Clean Code", "R. Martin");
        // ACT
        boolean result = book.isAvailable();
        // ASSERT
        assertTrue(result); // ÉCHOUE : isAvailable() retourne false
    }

    @Test
    @DisplayName("Un livre emprunté n'est plus disponible")
    void checkedOutBook_shouldNotBeAvailable() {
        Book book = new Book("978-3-16", "Clean Code", "R. Martin");
        book.checkout("membre-1");
        assertFalse(book.isAvailable()); // ÉCHOUE : checkout() ne fait rien
    }
}
