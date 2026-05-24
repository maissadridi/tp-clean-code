package com.tp.bibliotheque;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    // ── tests existants (inchangés) ───────────────────────────────────────
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

    @Test
    @DisplayName("Emprunter un livre déjà emprunté lève IllegalStateException")
    void checkoutAlreadyCheckedOut_shouldThrow() {
        Book book = new Book("978-3-16", "Clean Code", "R. Martin");
        book.checkout("membre-1");
        assertThrows(IllegalStateException.class, () -> book.checkout("membre-2"));
    }

    @Test
    @DisplayName("Retourner un livre emprunté le remet disponible")
    void returnBook_shouldMakeAvailableAgain() {
        Book book = new Book("978-3-16", "Clean Code", "R. Martin");
        book.checkout("membre-1");
        book.returnBook();
        assertTrue(book.isAvailable());
    }

    @Test
    @DisplayName("Retourner un livre non emprunté lève IllegalStateException")
    void returnBook_notCheckedOut_shouldThrow() {
        Book book = new Book("978-3-16", "Clean Code", "R. Martin");
        assertThrows(IllegalStateException.class, book::returnBook);
    }

    // ── REFACTOR : @ParameterizedTest — évite la duplication ─────────────
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "   "})
    @DisplayName("ISBN invalide (null/vide/blank) → IllegalArgumentException")
    void invalidIsbn_shouldThrow(String isbn) {
        assertThrows(IllegalArgumentException.class,
                () -> new Book(isbn, "Titre", "Auteur"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Titre invalide → IllegalArgumentException")
    void invalidTitre_shouldThrow(String titre) {
        assertThrows(IllegalArgumentException.class,
                () -> new Book("978-3-16", titre, "Auteur"));
    }
}