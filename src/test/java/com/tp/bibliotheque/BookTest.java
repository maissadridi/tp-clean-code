package com.tp.bibliotheque;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    // ── GREEN ──────────────────────────────────────────────
    @Test
    @DisplayName("Un livre nouvellement créé est disponible")
    void newBook_shouldBeAvailable() {
        Book book = new Book("978-3-16", "Clean Code", "R. Martin");
        assertTrue(book.isAvailable());
    }

    @Test
    @DisplayName("Un livre emprunté n'est plus disponible")
    void checkedOutBook_shouldNotBeAvailable() {
        Book book = new Book("978-3-16", "Clean Code", "R. Martin");
        book.checkout("membre-1");
        assertFalse(book.isAvailable());
    }

    // ── RED 3 ─────────────────────────────────────────────────────────────
    @Test
    @DisplayName("Emprunter un livre déjà emprunté lève IllegalStateException")
    void checkoutAlreadyCheckedOut_shouldThrow() {
        // ARRANGE
        Book book = new Book("978-3-16", "Clean Code", "R. Martin");
        book.checkout("membre-1");
        // ACT + ASSERT
        assertThrows(IllegalStateException.class,
                () -> book.checkout("membre-2")); // ÉCHOUE : aucune exception levée
    }

    // ── RED 4 ─────────────────────────────────────────────────────────────
    @Test
    @DisplayName("Retourner un livre emprunté le remet disponible")
    void returnBook_shouldMakeAvailableAgain() {
        // ARRANGE
        Book book = new Book("978-3-16", "Clean Code", "R. Martin");
        book.checkout("membre-1");
        // ACT
        book.returnBook();
        // ASSERT
        assertTrue(book.isAvailable()); // ÉCHOUE : returnBook() ne fait rien
    }

    // ── RED 5 ─────────────────────────────────────────────────────────────
    @Test
    @DisplayName("Retourner un livre non emprunté lève IllegalStateException")
    void returnBook_notCheckedOut_shouldThrow() {
        Book book = new Book("978-3-16", "Clean Code", "R. Martin");
        assertThrows(IllegalStateException.class,
                book::returnBook); // ÉCHOUE : returnBook() ne fait rien
    }
}