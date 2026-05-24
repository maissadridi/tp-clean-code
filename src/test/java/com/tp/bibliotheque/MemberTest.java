package com.tp.bibliotheque;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    private Member membre;
    private Book   book1;
    private Book   book2;
    private Book   book3;
    private Book   book4;

    @BeforeEach
    void setUp() {
        membre = new Member("M01", "Alice");
        book1  = new Book("ISBN-1", "Clean Code",   "R. Martin");
        book2  = new Book("ISBN-2", "Refactoring",  "M. Fowler");
        book3  = new Book("ISBN-3", "Design Patterns", "GoF");
        book4  = new Book("ISBN-4", "The Pragmatic Programmer", "Hunt");
    }

    // ── RED 1 ─────────────────────────────────────────────────────────────
    @Test
    @DisplayName("emprunter : le livre passe indisponible")
    void emprunter_bookBecomesUnavailable() {
        // ARRANGE + ACT
        membre.emprunter(book1);
        // ASSERT
        assertFalse(book1.isAvailable()); // ÉCHOUE : emprunter() n'appelle pas checkout()
        assertEquals(1, membre.getNbEmprunts());
    }

    // ── RED 2 ─────────────────────────────────────────────────────────────
    @Test
    @DisplayName("emprunter : quota 3 atteint → IllegalStateException")
    void emprunter_quotaAtteint_shouldThrow() {
        membre.emprunter(book1);
        membre.emprunter(book2);
        membre.emprunter(book3);

        // ÉCHOUE : emprunter() n'a pas de vérification quota
        assertThrows(IllegalStateException.class, () -> membre.emprunter(book4));
    }

    // ── RED 3 ─────────────────────────────────────────────────────────────
    @Test
    @DisplayName("retourner : le livre redevient disponible")
    void retourner_bookBecomesAvailable() {
        membre.emprunter(book1);

        membre.retourner(book1);

        assertTrue(book1.isAvailable()); // ÉCHOUE : retourner() n'appelle pas returnBook()
        assertEquals(0, membre.getNbEmprunts());
    }

    // ── RED 4 ─────────────────────────────────────────────────────────────
    @Test
    @DisplayName("peutEncoreEmprunter : false quand quota atteint")
    void peutEncoreEmprunter_false_whenFull() {
        membre.emprunter(book1);
        membre.emprunter(book2);
        membre.emprunter(book3);

        // ÉCHOUE : peutEncoreEmprunter() retourne toujours true
        assertFalse(membre.peutEncoreEmprunter());
    }
}