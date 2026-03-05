# Psychotherapy Center Database
Ovaj projekat predstavlja relacijsku bazu podataka za upravljanje radom psihoterapijskog centra.
Baza omogućava vođenje evidencije o klijentima, psihoterapeutima, seansama, beleškama sa terapija, kandidatima na obuci, kao i finansijskim i administrativnim podacima.

Funkcionalnosti sistema

Baza omogućava:

evidenciju klijenata i njihovih osnovnih podataka

vođenje zapisa o terapijskim seansama

čuvanje beleški koje terapeut unosi nakon svake seanse

praćenje cena seansi kroz vreme

upravljanje kandidatima koji su na edukaciji za psihoterapeute

evidenciju centara za obuku i oblasti psihoterapije

čuvanje podataka o fakultetima i obrazovanju kandidata

evidentiranje situacija u kojima se podaci sa seansi moraju objaviti (supervizija, sudski postupci i slično)

Struktura baze

Baza se sastoji od više međusobno povezanih tabela, među kojima su najvažnije:

osoba – osnovni podaci o svim osobama u sistemu

klijent – klijenti koji dolaze na psihoterapiju

kandidat – osobe koje su na edukaciji za psihoterapeute

korisnik – korisnički nalozi za pristup sistemu

beleska – beleške sa psihoterapijskih seansi

cena_seanse – evidencija promene cene terapije kroz vreme

centar_za_obuku – institucije koje organizuju edukaciju

fakultet i oblast_fakulteta – obrazovne institucije i oblasti studija

oblast_psihoterapije – različiti terapijski pristupi

objavljivanje_podataka – evidencija situacija kada se podaci sa seansi dele sa trećim licima

Tehnologije

MySQL / MariaDB

phpMyAdmin

SQL

Namena projekta

Projekat je napravljen kao primer modelovanja relacione baze podataka za potrebe upravljanja psihoterapijskim centrom i može se koristiti u edukativne svrhe ili kao osnova za razvoj informacionog sistema za psihoterapijsku praksu.
