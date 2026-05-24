# Bibliothèque TDD

[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=maissadridi_tp-clean-code&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=maissadridi_tp-clean-code)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=maissadridi_tp-clean-code&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=maissadridi_tp-clean-code)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=maissadridi_tp-clean-code&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=maissadridi_tp-clean-code)
[![Duplications](https://sonarcloud.io/api/project_badges/measure?project=maissadridi_tp-clean-code&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=maissadridi_tp-clean-code)

> Projet Java réalisé dans le cadre du cours **Qualité et Tests Logiciels** — ESIEE-IT 2025/2026  
> Démonstration du cycle **TDD Red / Green / Refactor** appliqué commit par commit.

---

## Description

Système de gestion de prêts pour une bibliothèque. Un membre peut emprunter jusqu'à 3 livres simultanément. Chaque fonctionnalité a été développée en TDD strict : le test est écrit avant le code, et chaque commit correspond à une étape précise du cycle.

---

## Structure

```
src/
├── main/java/com/tp/bibliotheque/
│   ├── Book.java       — livre avec checkout() / returnBook()
│   ├── Member.java     — membre avec quota 3 emprunts max
│   └── Library.java    — façade catalogue + membres + statistiques
└── test/java/com/tp/bibliotheque/
    ├── BookTest.java    — 13 tests
    ├── MemberTest.java  — 11 tests
    └── LibraryTest.java —  9 tests
```

---

## 🔴🟢🔵 Historique git TDD

| Commit | Type | Description |
|--------|------|-------------|
| C0 | `chore` | Init projet — squelettes vides, code cassé |
| C1 | `test` 🔴 | `Book.isAvailable()` et `checkout()` — tests écrits, **échouent** |
| C2 | `feat` 🟢 | `Book.isAvailable()` — `disponible=true`, tests **passent** |
| C3 | `test` 🔴 | Cas limites `checkout()` double appel et `returnBook()` — **échouent** |
| C4 | `feat` 🟢 | Guards `IllegalStateException` dans `checkout()` et `returnBook()` |
| C5 | `test` 🔴 | `Member.emprunter()` quota 3 et `retourner()` — tests écrits, **échouent** |
| C6 | `feat` 🟢 | `Member.emprunter()` quota=3 et `retourner()` — tests **passent** |
| C7 | `refactor` 🔵 | `@ParameterizedTest` ISBN invalides, extraction `validateParam()`, `Library` complète |
| C8 | `fix` 🟠 | Encapsulation `public` → `private`, validation `Member`, SRP `Library` |

---

## Code smells corrigés

### Smell #1 — Champs publics (violation d'encapsulation)
```java
// AVANT
public String isbn;
public boolean disponible;

// APRÈS
private final String isbn;
private boolean disponible = true;
```

### Smell #2 — Méthodes vides sans implémentation
`checkout()`, `returnBook()` et `emprunter()` étaient des squelettes vides. Implémentées via TDD avec guards `IllegalStateException`.

### Smell #3 — Absence de validation des paramètres
```java
// AVANT : aucune validation
public Book(String isbn, String titre, String auteur) { ... }

// APRÈS : méthode privée extraite + @ParameterizedTest
private void validateParam(String valeur, String nom) {
    if (valeur == null || valeur.isBlank())
        throw new IllegalArgumentException(nom + " ne peut pas être vide");
}
```

---

## Lancer les tests

```bash
mvn test
```

## Analyse SonarCloud

```bash
mvn clean verify sonar:sonar -Dsonar.token=TON_TOKEN
```

---

## Résultats SonarCloud

| Axe | Rating | Issues |
|-----|--------|--------|
| Security | **A** | 0 |
| Reliability | **A** | 0 |
| Maintainability | **A** | 0 |
| Duplications | **0.0%** | — |

---

## Stack

- Java 17
- JUnit Jupiter 5.10.2
- Mockito 5.11.0
- JaCoCo 0.8.12
- SonarCloud
- Maven 3.8+
