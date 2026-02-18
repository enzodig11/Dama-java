# 🇮🇹 Dama Italiana -- Implementazione in Java

## Descrizione del Progetto

Questo progetto rappresenta un'implementazione completa del gioco della "Dama Italiana", sviluppata in Java.

L'applicazione implementa le regole ufficiali del gioco, inclusi: 
- Movimenti validi 
- Catture obbligatorie
- Promozione a dama
- Determinazione della vittoria

Il progetto segue principi di: 
- Programmazione Orientata agli Oggetti (OOP)
- Clean Code
- Testing automatico
- Integrazione Continua (CI/CD)
- Analisi statica del codice

------------------------------------------------------------------------

## Tecnologie Utilizzate

-   Java\
-   Gradle\
-   JUnit\
-   GitHub Actions\
-   JaCoCo\
-   Checkstyle & SpotBugs

------------------------------------------------------------------------

## Struttura della Repository

    Dama-java/
    │
    ├── src/
    │   ├── main/java/
    │   └── test/java/
    │
    ├── build.gradle
    ├── settings.gradle
    ├── gradlew
    ├── gradlew.bat
    ├── .github/workflows/
    └── README.md

------------------------------------------------------------------------

## omandi Base di Sistema

### Clonare il repository

    git clone https://github.com/enzodig11/Dama-java.git
    cd Dama-java

### Compilare il progetto

    ./gradlew build

### Eseguire i test

    ./gradlew test

### Avviare l'applicazione

    ./gradlew run

### Pulire la build

    ./gradlew clean

------------------------------------------------------------------------

## Comandi di Gioco (CLI)

Una volta avviata l'applicazione, è possibile utilizzare i seguenti comandi:

    gioca        → Avvia una nuova partita
    esci         → Termina l’applicazione
    abbandona    → Abbandona la partita corrente
    numeri       → Mostra numerazione della scacchiera
    tempo        → Visualizza il tempo di gioco
    damiera      → Mostra la scacchiera corrente
    prese        → Mostra le catture disponibili

### Notazione delle Mosse

Le mosse devono essere inserite in "notazione algebrica":

-   Spostamento semplice:\
    Esempio → `1-5`

-   Spostamento con presa semplice:\
    Esempio → `18x11`

-   Spostamento con presa multipla:\
    Esempio → `22x15x6`

Dopo aver digitato un comando o una mossa, il sistema aggiorna automaticamente lo stato della partita.

------------------------------------------------------------------------

## Qualità del Codice

Il progetto integra:

-   Integrazione Continua con GitHub Actions\
-   Analisi della copertura del codice con JaCoCo\
-   Analisi statica con Checkstyle e SpotBugs\
-   Test automatici con JUnit

------------------------------------------------------------------------

## Autore

Vincenzo Digioia\
Junior Software Engineer\
GitHub: https://github.com/enzodig11
