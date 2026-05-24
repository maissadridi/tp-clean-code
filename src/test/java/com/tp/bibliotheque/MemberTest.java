package com.tp.bibliotheque;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

// C1 RED — ces tests compilent mais échouent
class MemberTest {

    @Test
    @DisplayName("emprunter : le livre passe indisponible")
    void emprunter_bookBecomesUnavailable() {
        Member membre = new Member("M01", "Alice");
        Book   book   = new Book("ISBN-1", "Clean Code", "R. Martin");

        membre.emprunter(book);

        assertFalse(book.isAvailable()); // ÉCHOUE : emprunter() n'appelle pas checkout()
    }

    @Test
    @DisplayName("emprunter : quota 3 atteint → IllegalStateException")
    void emprunter_quotaAtteint_shouldThrow() {
        Member membre = new Member("M01", "Alice");
        Book b1 = new Book("I1", "T1", "A1");
        Book b2 = new Book("I2", "T2", "A2");
        Book b3 = new Book("I3", "T3", "A3");
        Book b4 = new Book("I4", "T4", "A4");

        membre.emprunter(b1);
        membre.emprunter(b2);
        membre.emprunter(b3);

        // ÉCHOUE : emprunter() n'a pas de vérification quota
        assertThrows(IllegalStateException.class, () -> membre.emprunter(b4));
    }

    @Test
    @DisplayName("retourner : le livre redevient disponible")
    void retourner_bookBecomesAvailable() {
        Member membre = new Member("M01", "Alice");
        Book   book   = new Book("ISBN-1", "Clean Code", "R. Martin");

        membre.emprunter(book);
        membre.retourner(book);

        assertTrue(book.isAvailable()); // ÉCHOUE : retourner() n'appelle pas returnBook()
    }
}
