package com.tp.bibliotheque;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    // ── RED 1 ─────────────────────────────────────────────────────────────
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

    // ── RED 2 ─────────────────────────────────────────────────────────────
    @Test
    @DisplayName("Un livre emprunté n'est plus disponible")
    void checkedOutBook_shouldNotBeAvailable() {
        // ARRANGE
        Book book = new Book("978-3-16", "Clean Code", "R. Martin");
        // ACT
        book.checkout("membre-1");
        // ASSERT
        assertFalse(book.isAvailable()); // ÉCHOUE : checkout() ne fait rien
    }
}