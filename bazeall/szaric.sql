-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 22, 2025 at 11:19 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `novipocetakfinal`
--

-- --------------------------------------------------------

--
-- Table structure for table `beleska`
--

CREATE TABLE `beleska` (
  `ID_beleske` int(11) NOT NULL,
  `ID_seanse` int(11) NOT NULL,
  `sadrzaj` text NOT NULL,
  `datum_unosa` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `beleska`
--

INSERT INTO `beleska` (`ID_beleske`, `ID_seanse`, `sadrzaj`, `datum_unosa`) VALUES
(1, 1, 'Klijent pokazuje znakove anksioznosti. Dogovorili smo tehnike opuštanja.', '2024-01-10'),
(2, 2, 'Klijent je primenjivao tehnike opuštanja. Prijavljuje poboljšanje u spavanju.', '2024-01-17'),
(3, 3, 'Klijent pokazuje znakove depresije. Potrebna je detaljnija procena.', '2024-01-15'),
(4, 4, 'Nakon testa, potvrđena je blaga depresija. Dogovorene su kognitivne tehnike.', '2024-01-22'),
(5, 5, 'Klijent ima probleme u komunikaciji sa partnerom. Dogovoreni su zadaci za sledeću nedelju.', '2024-01-20'),
(6, 6, 'Klijent je primenio tehnike komunikacije. Primećuje poboljšanje.', '2024-01-27'),
(7, 7, 'Klijent je pod velikim stresom na poslu. Dogovorene su tehnike upravljanja stresom.', '2024-01-25'),
(8, 8, 'Klijent je smanjio radne sate i prijavljuje manje stresa.', '2024-02-01'),
(9, 9, 'Klijent je u procesu tugovanja. Potrebna je podrška.', '2024-02-03'),
(10, 10, 'Klijent polako prihvata gubitak. Dogovorene su dalje strategije suočavanja.', '2024-02-10');

-- --------------------------------------------------------

--
-- Table structure for table `cena_seanse`
--

CREATE TABLE `cena_seanse` (
  `ID_cene` int(11) NOT NULL,
  `iznos_po_satu` decimal(10,2) NOT NULL,
  `datum_promene` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cena_seanse`
--

INSERT INTO `cena_seanse` (`ID_cene`, `iznos_po_satu`, `datum_promene`) VALUES
(1, 3000.00, '2023-01-01'),
(2, 3500.00, '2023-04-01'),
(3, 4000.00, '2023-07-01'),
(4, 4500.00, '2023-10-01'),
(5, 5000.00, '2024-01-01'),
(6, 5500.00, '2024-02-15'),
(7, 6000.00, '2024-03-20'),
(8, 6500.00, '2024-04-10'),
(9, 7000.00, '2024-05-01'),
(10, 7500.00, '2024-06-01');

-- --------------------------------------------------------

--
-- Table structure for table `centar_za_obuku`
--

CREATE TABLE `centar_za_obuku` (
  `ID_centra` int(11) NOT NULL,
  `naziv` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `telefon` varchar(50) DEFAULT NULL,
  `ulica` varchar(255) DEFAULT NULL,
  `broj` varchar(20) DEFAULT NULL,
  `opstina` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `centar_za_obuku`
--

INSERT INTO `centar_za_obuku` (`ID_centra`, `naziv`, `email`, `telefon`, `ulica`, `broj`, `opstina`) VALUES
(1, 'Centar za psihoterapiju i edukaciju', 'edukacija@cpe.rs', '011/123-4567', 'Knez Mihailova', '22', 'Stari Grad'),
(2, 'Srpsko udruženje za transakcionu analizu', 'info@suta.org', '011/234-5678', 'Bulevar kralja Aleksandra', '125', 'Zvezdara'),
(3, 'Centar za KBT edukaciju', 'kbt@edu.rs', '011/345-6789', 'Požeška', '31', 'Čukarica'),
(4, 'Geštalt studio', 'kontakt@gestalt.rs', '011/456-7890', 'Makedonska', '15', 'Stari Grad'),
(5, 'Institut za porodičnu terapiju', 'institut@porodicna.rs', '011/567-8901', 'Cara Dušana', '54', 'Dorćol'),
(6, 'Nacionalna asocijacija psihoterapeuta', 'nap@psihoterapeut.rs', '011/678-9012', 'Beogradska', '78', 'Vračar'),
(7, 'Centar za psihodinamsku edukaciju', 'info@psihodinamika.rs', '011/789-0123', 'Maksima Gorkog', '17', 'Vračar'),
(8, 'Edukativni centar za mindfulness', 'mindfulness@edu.rs', '011/890-1234', 'Južni bulevar', '45', 'Vračar'),
(9, 'Društvo sistemskih porodičnih terapeuta', 'dspt@sistemska.rs', '011/901-2345', 'Tadeuša Košćuška', '82', 'Dorćol'),
(10, 'Institut za ACT terapiju', 'act@institut.rs', '011/012-3456', 'Resavska', '29', 'Savski venac');

-- --------------------------------------------------------

--
-- Table structure for table `fakultet`
--

CREATE TABLE `fakultet` (
  `ID_fakulteta` int(11) NOT NULL,
  `naziv` varchar(255) NOT NULL,
  `ID_univerziteta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `fakultet`
--

INSERT INTO `fakultet` (`ID_fakulteta`, `naziv`, `ID_univerziteta`) VALUES
(1, 'Filozofski fakultet', 1),
(2, 'Fakultet za specijalnu edukaciju i rehabilitaciju', 1),
(3, 'Medicinski fakultet', 1),
(4, 'Prirodno-matematički fakultet', 2),
(5, 'Fakultet tehničkih nauka', 2),
(6, 'Filozofski fakultet', 3),
(7, 'Medicinski fakultet', 3),
(8, 'Prirodno-matematički fakultet', 4),
(9, 'Fakultet informacionih tehnologija', 5),
(10, 'Fakultet za poslovne studije', 6),
(11, 'Fakultet za kulturu i medije', 7),
(12, 'Fakultet za evropske pravno-političke studije', 8),
(13, 'Fakultet zaštite životne sredine', 9),
(14, 'Pravni fakultet', 10);

-- --------------------------------------------------------

--
-- Table structure for table `fakultet_oblast`
--

CREATE TABLE `fakultet_oblast` (
  `ID_veze` int(11) NOT NULL,
  `ID_fakulteta` int(11) NOT NULL,
  `ID_oblasti_fakulteta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `fakultet_oblast`
--

INSERT INTO `fakultet_oblast` (`ID_veze`, `ID_fakulteta`, `ID_oblasti_fakulteta`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 1, 3),
(4, 2, 4),
(5, 3, 5),
(6, 3, 6),
(7, 4, 7),
(8, 4, 8),
(9, 5, 9),
(10, 6, 1),
(11, 6, 2),
(12, 7, 5),
(13, 8, 7),
(14, 9, 9),
(15, 10, 3),
(16, 11, 3),
(17, 12, 10),
(18, 13, 7),
(19, 14, 10);

-- --------------------------------------------------------

--
-- Table structure for table `kandidat`
--

CREATE TABLE `kandidat` (
  `ID_kandidata` int(11) NOT NULL,
  `ID_osobe` int(11) NOT NULL,
  `ID_fakulteta` int(11) NOT NULL,
  `stepen_studija` varchar(20) NOT NULL,
  `ID_centra` int(11) NOT NULL,
  `status_obuke` varchar(20) NOT NULL
) ;

--
-- Dumping data for table `kandidat`
--

INSERT INTO `kandidat` (`ID_kandidata`, `ID_osobe`, `ID_fakulteta`, `stepen_studija`, `ID_centra`, `status_obuke`) VALUES
(1, 11, 1, 'master', 1, 'u toku'),
(2, 12, 1, 'doktorske', 2, 'u toku'),
(3, 13, 2, 'master', 3, 'završena'),
(4, 14, 3, 'doktorske', 4, 'u toku'),
(5, 15, 6, 'master', 5, 'napuštena'),
(6, 16, 6, 'master', 6, 'u toku'),
(7, 17, 7, 'doktorske', 7, 'završena'),
(8, 18, 1, 'master', 8, 'u toku'),
(9, 19, 6, 'master', 9, 'završena'),
(10, 20, 3, 'doktorske', 10, 'u toku');

-- --------------------------------------------------------

--
-- Table structure for table `klijent`
--

CREATE TABLE `klijent` (
  `ID_klijenta` int(11) NOT NULL,
  `ID_osobe` int(11) NOT NULL,
  `ranije_isao_psihoterapeutu` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `klijent`
--

INSERT INTO `klijent` (`ID_klijenta`, `ID_osobe`, `ranije_isao_psihoterapeutu`) VALUES
(1, 11, 0),
(2, 12, 1),
(3, 13, 0),
(4, 14, 1),
(5, 15, 0),
(6, 16, 1),
(7, 17, 0),
(8, 18, 1),
(9, 19, 0),
(10, 20, 1);

-- --------------------------------------------------------

--
-- Table structure for table `korisnik`
--

CREATE TABLE `korisnik` (
  `ID_korisnika` int(11) NOT NULL,
  `ID_psihoterapeuta` int(11) NOT NULL,
  `korisnicko_ime` varchar(100) NOT NULL,
  `lozinka` varchar(255) NOT NULL,
  `ime` varchar(100) NOT NULL,
  `prezime` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `korisnik`
--

INSERT INTO `korisnik` (`ID_korisnika`, `ID_psihoterapeuta`, `korisnicko_ime`, `lozinka`, `ime`, `prezime`) VALUES
(1, 1, 'marko_admin', 'Lozinka123', 'Marko', 'Marković'),
(2, 2, 'ana_psih', 'Anapsih123', 'Ana', 'Anić'),
(3, 3, 'jovan_terapeut', 'Jovan2024', 'Jovan', 'Jovanović'),
(4, 4, 'milica_psy', 'Milica123', 'Milica', 'Milić'),
(5, 5, 'stefan_savetnik', 'Stefan123', 'Stefan', 'Stefanović'),
(6, 6, 'jelena_psiholog', 'Jelena456', 'Jelena', 'Jelenić'),
(7, 7, 'nikola_terapeut', 'Nikola789', 'Nikola', 'Nikolić'),
(8, 8, 'jovana_psy', 'Jovana123', 'Jovana', 'Jovanović'),
(9, 9, 'petar_savetnik', 'Petar2024', 'Petar', 'Petrović'),
(10, 10, 'maja_psiholog', 'Maja12345', 'Maja', 'Majić'),
(11, 18, 'Alen', 'alen123', 'Jovan', 'Dimi'),
(12, 19, 'ivanivan', 'jsnmus2409', 'Ivan', 'Lutrija');

-- --------------------------------------------------------

--
-- Table structure for table `kurs_valute`
--

CREATE TABLE `kurs_valute` (
  `ID_kursa` int(11) NOT NULL,
  `ID_valute` int(11) NOT NULL,
  `kurs_prema_dinaru` decimal(10,6) NOT NULL,
  `datum_promene` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kurs_valute`
--

INSERT INTO `kurs_valute` (`ID_kursa`, `ID_valute`, `kurs_prema_dinaru`, `datum_promene`) VALUES
(1, 1, 1.000000, '2024-01-01'),
(2, 2, 117.500000, '2024-01-01'),
(3, 3, 106.250000, '2024-01-01'),
(4, 4, 135.780000, '2024-01-01'),
(5, 5, 122.350000, '2024-01-01'),
(6, 2, 117.580000, '2024-02-01'),
(7, 3, 107.120000, '2024-02-01'),
(8, 4, 136.450000, '2024-02-01'),
(9, 2, 117.620000, '2024-03-01'),
(10, 3, 107.580000, '2024-03-01');

-- --------------------------------------------------------

--
-- Table structure for table `objavljivanje_podataka`
--

CREATE TABLE `objavljivanje_podataka` (
  `ID_objavljivanja` int(11) NOT NULL,
  `ID_seanse` int(11) NOT NULL,
  `datum_objavljivanja` date NOT NULL,
  `razlog` varchar(50) NOT NULL,
  `primalac_podataka` varchar(255) NOT NULL,
  `detalji` text DEFAULT NULL
) ;

--
-- Dumping data for table `objavljivanje_podataka`
--

INSERT INTO `objavljivanje_podataka` (`ID_objavljivanja`, `ID_seanse`, `datum_objavljivanja`, `razlog`, `primalac_podataka`, `detalji`) VALUES
(1, 1, '2024-01-15', 'supervizija', 'Dr Marko Marković', 'Supervizija kandidata na obuci'),
(2, 3, '2024-01-20', 'supervizija', 'Dr Ana Anić', 'Supervizija kandidata na obuci'),
(3, 5, '2024-01-25', 'supervizija', 'Dr Petar Petrović', 'Supervizija kandidata na obuci'),
(4, 7, '2024-01-30', 'supervizija', 'Dr Jelena Jelenić', 'Supervizija kandidata na obuci'),
(5, 2, '2024-02-15', 'priznanje_krivicnog_dela', 'Policijska uprava Beograd', 'Klijent priznao krađu'),
(6, 4, '2024-02-20', 'saslusanje_pred_sudom', 'Osnovni sud u Beogradu', 'Sudski nalog za dostavljanje podataka'),
(7, 6, '2024-02-25', 'supervizija', 'Dr Ivana Ivanović', 'Supervizija kandidata na obuci'),
(8, 8, '2024-03-01', 'supervizija', 'Dr Maja Majić', 'Supervizija kandidata na obuci'),
(9, 9, '2024-03-05', 'supervizija', 'Dr Dušan Dušanović', 'Supervizija kandidata na obuci'),
(10, 10, '2024-03-10', 'supervizija', 'Dr Tamara Tamarić', 'Supervizija kandidata na obuci');

-- --------------------------------------------------------

--
-- Table structure for table `oblast_fakulteta`
--

CREATE TABLE `oblast_fakulteta` (
  `ID_oblasti_fakulteta` int(11) NOT NULL,
  `naziv_oblasti` varchar(255) NOT NULL,
  `opis` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `oblast_fakulteta`
--

INSERT INTO `oblast_fakulteta` (`ID_oblasti_fakulteta`, `naziv_oblasti`, `opis`) VALUES
(1, 'Psihologija', 'Izučavanje ponašanja i mentalnih procesa'),
(2, 'Pedagogija', 'Izučavanje obrazovanja i vaspitanja'),
(3, 'Sociologija', 'Izučavanje društva i društvenih pojava'),
(4, 'Specijalna edukacija', 'Izučavanje obrazovanja osoba sa smetnjama u razvoju'),
(5, 'Psihijatrija', 'Grana medicine koja se bavi mentalnim poremećajima'),
(6, 'Neurologija', 'Grana medicine koja se bavi nervnim sistemom'),
(7, 'Biologija', 'Nauka o živim bićima'),
(8, 'Matematika', 'Nauka o brojevima i prostornim odnosima'),
(9, 'Informacioni sistemi', 'Oblasti koja se bavi obradom i skladištenjem podataka'),
(10, 'Pravo', 'Oblast koja se bavi pravnim normama i njihovom primenom');

-- --------------------------------------------------------

--
-- Table structure for table `oblast_psihoterapije`
--

CREATE TABLE `oblast_psihoterapije` (
  `ID_oblasti_psihoterapije` int(11) NOT NULL,
  `naziv_oblasti` varchar(255) NOT NULL,
  `opis` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `oblast_psihoterapije`
--

INSERT INTO `oblast_psihoterapije` (`ID_oblasti_psihoterapije`, `naziv_oblasti`, `opis`) VALUES
(1, 'Kognitivno-bihevioralna terapija', 'Pristup koji se fokusira na promene u mišljenju i ponašanju'),
(2, 'Terapija prihvatanja i posvećenosti', 'Pristup koji se fokusira na psihološku fleksibilnost'),
(3, 'Humanistička terapija', 'Pristup koji naglašava važnost ljudskog dostojanstva i potencijala'),
(4, 'Geštalt terapija', 'Pristup koji se fokusira na sadašnje iskustvo i odnose'),
(5, 'Porodična terapija', 'Pristup koji radi sa celom porodicom kao sistemom'),
(6, 'Sistemska terapija', 'Pristup koji posmatra probleme u kontekstu širih sistema'),
(7, 'Psihodinamska terapija', 'Pristup koji se fokusira na nesvesne procese i iskustva iz detinjstva'),
(8, 'Mindfulness terapija', 'Pristup koji se fokusira na svesnost trenutka i prihvatanje'),
(9, 'Egzistencijalna terapija', 'Pristup koji se fokusira na suočavanje sa egzistencijalnim pitanjima'),
(10, 'Narativna terapija', 'Pristup koji posmatra život kao priču koja se može preoblikovati');

-- --------------------------------------------------------

--
-- Table structure for table `oblast_usmerenja`
--

CREATE TABLE `oblast_usmerenja` (
  `ID_oblasti` int(11) NOT NULL,
  `naziv_oblasti` varchar(255) NOT NULL,
  `opis` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `oblast_usmerenja`
--

INSERT INTO `oblast_usmerenja` (`ID_oblasti`, `naziv_oblasti`, `opis`) VALUES
(1, 'Društvene nauke', 'Oblast koja proučava ljudsko društvo i odnose među ljudima'),
(2, 'Prirodne nauke', 'Oblast koja proučava prirodne fenomene i zakonitosti'),
(3, 'Humanističke nauke', 'Oblast koja proučava ljudsku kulturu i istoriju'),
(4, 'Medicinske nauke', 'Oblast koja proučava zdravlje i bolesti'),
(5, 'Tehničke nauke', 'Oblast koja proučava primenu naučnih saznanja u praksi'),
(6, 'Informacione tehnologije', 'Oblast koja proučava obradu i prenos informacija'),
(7, 'Umetnost', 'Oblast koja proučava kreativno izražavanje'),
(8, 'Pravne nauke', 'Oblast koja proučava pravni sistem i zakonodavstvo'),
(9, 'Ekonomske nauke', 'Oblast koja proučava ekonomske sisteme i procese'),
(10, 'Filološke nauke', 'Oblast koja proučava jezik i književnost');

-- --------------------------------------------------------

--
-- Table structure for table `osoba`
--

CREATE TABLE `osoba` (
  `ID_osobe` int(11) NOT NULL,
  `ime` varchar(100) NOT NULL,
  `prezime` varchar(100) NOT NULL,
  `JMBG` varchar(13) NOT NULL,
  `datum_rodjenja` date NOT NULL,
  `prebivaliste` varchar(255) DEFAULT NULL,
  `telefon` varchar(50) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `pol` char(1) NOT NULL
) ;

--
-- Dumping data for table `osoba`
--

INSERT INTO `osoba` (`ID_osobe`, `ime`, `prezime`, `JMBG`, `datum_rodjenja`, `prebivaliste`, `telefon`, `email`, `pol`) VALUES
(1, 'Marko', 'Marković', '1234567890123', '1980-05-15', 'Beograd', '064/123-4567', 'marko.markovic@email.com', 'M'),
(2, 'Ana', 'Anić', '2345678901234', '1982-07-22', 'Novi Sad', '064/234-5678', 'ana.anic@email.com', 'Z'),
(3, 'Petar', 'Petrović', '3456789012345', '1975-03-10', 'Niš', '064/345-6789', 'petar.petrovic@email.com', 'M'),
(4, 'Jelena', 'Jelenić', '4567890123456', '1990-12-05', 'Beograd', '064/456-7890', 'jelena.jelenic@email.com', 'Z'),
(5, 'Milan', 'Milanović', '5678901234567', '1988-09-18', 'Kragujevac', '064/567-8901', 'milan.milanovic@email.com', 'M'),
(6, 'Ivana', 'Ivanović', '6789012345678', '1985-11-30', 'Beograd', '064/678-9012', 'ivana.ivanovic@email.com', 'Z'),
(7, 'Nikola', 'Nikolić', '7890123456789', '1978-02-25', 'Subotica', '064/789-0123', 'nikola.nikolic@email.com', 'M'),
(8, 'Maja', 'Majić', '8901234567890', '1992-08-14', 'Beograd', '064/890-1234', 'maja.majic@email.com', 'Z'),
(9, 'Dušan', 'Dušanović', '9012345678901', '1983-06-27', 'Novi Sad', '064/901-2345', 'dusan.dusanovic@email.com', 'M'),
(10, 'Tamara', 'Tamarić', '0123456789012', '1995-04-09', 'Beograd', '064/012-3456', 'tamara.tamaric@email.com', 'Z'),
(11, 'Stefan', 'Stefanović', '9876543210987', '1986-01-12', 'Niš', '064/987-6543', 'stefan.stefanovic@email.com', 'M'),
(12, 'Jovana', 'Jovanović', '8765432109876', '1991-10-23', 'Beograd', '064/876-5432', 'jovana.jovanovic@email.com', 'Z'),
(13, 'Miloš', 'Milošević', '7654321098765', '1984-07-18', 'Kragujevac', '064/765-4321', 'milos.milosevic@email.com', 'M'),
(14, 'Sandra', 'Sandrić', '6543210987654', '1989-03-25', 'Beograd', '064/654-3210', 'sandra.sandric@email.com', 'Z'),
(15, 'Branko', 'Branković', '5432109876543', '1977-12-08', 'Novi Sad', '064/543-2109', 'branko.brankovic@email.com', 'M'),
(16, 'Tijana', 'Tijanić', '4321098765432', '1993-05-16', 'Beograd', '064/432-1098', 'tijana.tijanic@email.com', 'Z'),
(17, 'Dragan', 'Draganović', '3210987654321', '1981-09-29', 'Niš', '064/321-0987', 'dragan.draganovic@email.com', 'M'),
(18, 'Katarina', 'Katić', '2109876543210', '1994-11-02', 'Beograd', '064/210-9876', 'katarina.katic@email.com', 'Z'),
(19, 'Goran', 'Goranović', '1098765432109', '1979-04-15', 'Kragujevac', '064/109-8765', 'goran.goranovic@email.com', 'M'),
(20, 'Nataša', 'Natašić', '1122334455667', '1990-08-27', 'Beograd', '064/112-2334', 'natasa.natasic@email.com', 'Z'),
(30, 'Jovan', 'Dimi', '2409003790056', '2025-05-01', 'asdasd', '6669994', 'milan@gmail.com', 'M'),
(31, 'Ivan', 'Lutrija', '2828282828888', '2025-05-08', 'Arilje', '00005', 'ivan@gmail.com', 'M');

-- --------------------------------------------------------

--
-- Table structure for table `placanje`
--

CREATE TABLE `placanje` (
  `ID_placanja` int(11) NOT NULL,
  `ID_klijenta` int(11) NOT NULL,
  `ID_seanse` int(11) DEFAULT NULL,
  `ID_rezultata` int(11) DEFAULT NULL,
  `svrha` varchar(10) NOT NULL,
  `ID_valute` int(11) NOT NULL,
  `iznos` decimal(10,2) NOT NULL,
  `nacin_placanja` varchar(20) NOT NULL,
  `broj_rate` int(11) NOT NULL,
  `procenat_prve_rate` decimal(5,2) DEFAULT NULL,
  `rok_druge_rate` date DEFAULT NULL,
  `provizija` decimal(5,2) DEFAULT NULL,
  `datum_placanja` date NOT NULL
) ;

--
-- Dumping data for table `placanje`
--

INSERT INTO `placanje` (`ID_placanja`, `ID_klijenta`, `ID_seanse`, `ID_rezultata`, `svrha`, `ID_valute`, `iznos`, `nacin_placanja`, `broj_rate`, `procenat_prve_rate`, `rok_druge_rate`, `provizija`, `datum_placanja`) VALUES
(1, 1, 1, NULL, 'seansa', 1, 0.00, 'gotovina', 1, NULL, NULL, NULL, '2024-01-10'),
(2, 1, 2, NULL, 'seansa', 1, 5000.00, 'kartica', 1, NULL, NULL, NULL, '2024-01-17'),
(3, 2, 3, NULL, 'seansa', 1, 0.00, 'gotovina', 1, NULL, NULL, NULL, '2024-01-15'),
(4, 2, 4, NULL, 'seansa', 1, 5000.00, 'gotovina', 2, 40.00, '2024-02-15', NULL, '2024-01-22'),
(5, 2, NULL, 1, 'test', 1, 2500.00, 'kartica', 1, NULL, NULL, NULL, '2024-01-15'),
(6, 3, 5, NULL, 'seansa', 1, 0.00, 'gotovina', 1, NULL, NULL, NULL, '2024-01-20'),
(7, 3, 6, NULL, 'seansa', 2, 42.55, 'kartica', 1, NULL, NULL, NULL, '2024-01-27'),
(8, 4, 7, NULL, 'seansa', 1, 0.00, 'gotovina', 1, NULL, NULL, NULL, '2024-01-25'),
(9, 4, 8, NULL, 'seansa', 3, 46.57, 'kartica', 1, NULL, NULL, 5.00, '2024-02-01'),
(10, 4, NULL, 6, 'test', 1, 7000.00, 'gotovina', 1, NULL, NULL, NULL, '2024-02-01');

-- --------------------------------------------------------

--
-- Table structure for table `prijava`
--

CREATE TABLE `prijava` (
  `ID_prijave` int(11) NOT NULL,
  `ID_klijenta` int(11) NOT NULL,
  `ID_psihoterapeuta` int(11) NOT NULL,
  `datum_prijave` date NOT NULL,
  `problem_opis` text DEFAULT NULL,
  `komunikacioni_kanal` varchar(20) NOT NULL
) ;

--
-- Dumping data for table `prijava`
--

INSERT INTO `prijava` (`ID_prijave`, `ID_klijenta`, `ID_psihoterapeuta`, `datum_prijave`, `problem_opis`, `komunikacioni_kanal`) VALUES
(1, 1, 1, '2024-01-05', 'Anksioznost i problemi sa spavanjem', 'telefon'),
(2, 2, 2, '2024-01-12', 'Depresivno raspoloženje', 'email'),
(3, 3, 3, '2024-01-18', 'Problemi u partnerskim odnosima', 'telefon'),
(4, 4, 4, '2024-01-23', 'Stres na poslu', 'email'),
(5, 5, 5, '2024-02-01', 'Tugovanje nakon gubitka', 'telefon'),
(6, 6, 6, '2024-02-08', 'Problemi sa samopouzdanjem', 'email'),
(7, 7, 7, '2024-02-15', 'Panični napadi', 'telefon'),
(8, 8, 8, '2024-02-22', 'Problemi u porodičnim odnosima', 'email'),
(9, 9, 9, '2024-03-01', 'Socijalna anksioznost', 'telefon'),
(10, 10, 10, '2024-03-08', 'Problemi sa besom', 'email');

-- --------------------------------------------------------

--
-- Table structure for table `psiholoski_test`
--

CREATE TABLE `psiholoski_test` (
  `ID_testa` int(11) NOT NULL,
  `naziv` varchar(255) NOT NULL,
  `oblast` varchar(100) NOT NULL,
  `cena` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `psiholoski_test`
--

INSERT INTO `psiholoski_test` (`ID_testa`, `naziv`, `oblast`, `cena`) VALUES
(1, 'MMPI-2', 'Klinička procena ličnosti', 8000.00),
(2, 'Beck-ov inventar depresije', 'Procena depresije', 2500.00),
(3, 'GAD-7', 'Procena anksioznosti', 2000.00),
(4, 'STAXI-2', 'Procena besa', 3000.00),
(5, 'NEO PI-R', 'Procena ličnosti', 7000.00),
(6, 'WISC-V', 'Procena inteligencije', 10000.00),
(7, 'Rorschach test', 'Projektivna tehnika', 8500.00),
(8, 'TAT', 'Projektivna tehnika', 7500.00),
(9, 'SCL-90-R', 'Procena psihopatologije', 5000.00),
(10, '16PF', 'Procena ličnosti', 6000.00);

-- --------------------------------------------------------

--
-- Table structure for table `psihoterapeut`
--

CREATE TABLE `psihoterapeut` (
  `ID_psihoterapeuta` int(11) NOT NULL,
  `ID_osobe` int(11) NOT NULL,
  `je_psiholog` tinyint(1) NOT NULL,
  `ID_sertifikata` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `psihoterapeut`
--

INSERT INTO `psihoterapeut` (`ID_psihoterapeuta`, `ID_osobe`, `je_psiholog`, `ID_sertifikata`) VALUES
(1, 1, 1, 1),
(2, 2, 1, 2),
(3, 3, 0, 3),
(4, 4, 1, 4),
(5, 5, 0, 5),
(6, 6, 1, 6),
(7, 7, 0, 7),
(8, 8, 1, 8),
(9, 9, 0, 9),
(10, 10, 1, 10),
(11, 13, 1, 11),
(12, 17, 0, 12),
(13, 19, 1, 13),
(18, 30, 1, 18),
(19, 31, 1, 19);

-- --------------------------------------------------------

--
-- Table structure for table `seansa`
--

CREATE TABLE `seansa` (
  `ID_seanse` int(11) NOT NULL,
  `ID_prijave` int(11) NOT NULL,
  `datum` date NOT NULL,
  `vreme_pocetka` time NOT NULL,
  `trajanje_minuta` int(11) NOT NULL,
  `je_prva_seansa` tinyint(1) NOT NULL,
  `ID_cene` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `seansa`
--

INSERT INTO `seansa` (`ID_seanse`, `ID_prijave`, `datum`, `vreme_pocetka`, `trajanje_minuta`, `je_prva_seansa`, `ID_cene`) VALUES
(1, 1, '2024-01-10', '10:00:00', 60, 1, 5),
(2, 1, '2024-01-17', '10:00:00', 60, 0, 5),
(3, 2, '2024-01-15', '12:00:00', 90, 1, 5),
(4, 2, '2024-01-22', '12:00:00', 60, 0, 5),
(5, 3, '2024-01-20', '14:00:00', 60, 1, 5),
(6, 3, '2024-01-27', '14:00:00', 60, 0, 5),
(7, 4, '2024-01-25', '16:00:00', 60, 1, 5),
(8, 4, '2024-02-01', '16:00:00', 60, 0, 5),
(9, 5, '2024-02-03', '10:00:00', 60, 1, 5),
(10, 5, '2024-02-10', '10:00:00', 60, 0, 6);

-- --------------------------------------------------------

--
-- Table structure for table `sertifikat`
--

CREATE TABLE `sertifikat` (
  `ID_sertifikata` int(11) NOT NULL,
  `ID_osobe` int(11) NOT NULL,
  `ID_oblasti_psihoterapije` int(11) NOT NULL,
  `datum_sertifikacije` date NOT NULL,
  `ID_centra` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sertifikat`
--

INSERT INTO `sertifikat` (`ID_sertifikata`, `ID_osobe`, `ID_oblasti_psihoterapije`, `datum_sertifikacije`, `ID_centra`) VALUES
(1, 1, 1, '2015-06-20', 1),
(2, 2, 2, '2016-09-15', 2),
(3, 3, 3, '2014-03-25', 3),
(4, 4, 4, '2017-11-10', 4),
(5, 5, 5, '2018-07-05', 5),
(6, 6, 6, '2016-12-18', 6),
(7, 7, 7, '2013-05-22', 7),
(8, 8, 8, '2019-08-30', 8),
(9, 9, 9, '2017-02-14', 9),
(10, 10, 10, '2015-10-27', 10),
(11, 13, 1, '2020-04-12', 3),
(12, 17, 5, '2021-08-09', 5),
(13, 19, 9, '2019-11-23', 9),
(18, 30, 1, '2025-05-21', 1),
(19, 31, 1, '2025-05-21', 1);

-- --------------------------------------------------------

--
-- Table structure for table `supervizija`
--

CREATE TABLE `supervizija` (
  `ID_supervizije` int(11) NOT NULL,
  `ID_kandidata` int(11) NOT NULL,
  `ID_psihoterapeuta` int(11) NOT NULL,
  `datum_pocetka` date NOT NULL,
  `datum_zavrsetka` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `supervizija`
--

INSERT INTO `supervizija` (`ID_supervizije`, `ID_kandidata`, `ID_psihoterapeuta`, `datum_pocetka`, `datum_zavrsetka`) VALUES
(1, 1, 1, '2023-01-10', NULL),
(2, 2, 2, '2022-06-15', NULL),
(3, 3, 3, '2021-09-20', '2023-09-20'),
(4, 4, 4, '2023-03-05', NULL),
(5, 5, 5, '2022-11-12', '2023-05-20'),
(6, 6, 6, '2023-02-18', NULL),
(7, 7, 7, '2020-08-25', '2022-08-25'),
(8, 8, 8, '2023-04-30', NULL),
(9, 9, 9, '2021-12-07', '2023-12-07'),
(10, 10, 10, '2023-01-22', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `test_rezultat`
--

CREATE TABLE `test_rezultat` (
  `ID_rezultata` int(11) NOT NULL,
  `ID_testa` int(11) NOT NULL,
  `ID_seanse` int(11) NOT NULL,
  `ID_klijenta` int(11) NOT NULL,
  `ID_psihoterapeuta` int(11) NOT NULL,
  `rezultat` text NOT NULL,
  `datum_testiranja` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `test_rezultat`
--

INSERT INTO `test_rezultat` (`ID_rezultata`, `ID_testa`, `ID_seanse`, `ID_klijenta`, `ID_psihoterapeuta`, `rezultat`, `datum_testiranja`) VALUES
(1, 2, 3, 2, 2, 'Skor 16 - blaga depresija', '2024-01-15'),
(2, 3, 7, 7, 7, 'Skor 12 - umerena anksioznost', '2024-02-15'),
(3, 1, 4, 2, 2, 'Povišenje na skalama depresije i anksioznosti', '2024-01-22'),
(4, 9, 5, 3, 3, 'Povišenje na skali interpersonalne osetljivosti', '2024-01-20'),
(5, 4, 10, 10, 10, 'Skor 24 - povišen nivo besa', '2024-03-15'),
(6, 5, 8, 4, 4, 'Visok neuroticizam, niska ekstraverzija', '2024-02-01'),
(7, 7, 6, 6, 6, 'Indikacije nisko samopouzdanje', '2024-01-27'),
(8, 8, 9, 5, 5, 'Teškoće u prihvatanju gubitka', '2024-02-03'),
(9, 3, 1, 1, 1, 'Skor 18 - visoka anksioznost', '2024-01-10'),
(10, 10, 2, 1, 1, 'Visoka savesnost, niska otvorenost', '2024-01-17');

-- --------------------------------------------------------

--
-- Table structure for table `univerzitet`
--

CREATE TABLE `univerzitet` (
  `ID_univerziteta` int(11) NOT NULL,
  `naziv` varchar(255) NOT NULL,
  `uze_usmerenje` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `univerzitet`
--

INSERT INTO `univerzitet` (`ID_univerziteta`, `naziv`, `uze_usmerenje`) VALUES
(1, 'Univerzitet u Beogradu', 'Društvene nauke'),
(2, 'Univerzitet u Novom Sadu', 'Prirodne nauke'),
(3, 'Univerzitet u Nišu', 'Humanističke nauke'),
(4, 'Univerzitet u Kragujevcu', NULL),
(5, 'Univerzitet Metropolitan', 'Primenjene nauke'),
(6, 'Univerzitet Singidunum', 'Informacione tehnologije'),
(7, 'Univerzitet Megatrend', NULL),
(8, 'Evropski univerzitet', 'Međunarodne studije'),
(9, 'Univerzitet Educons', 'Ekološke nauke'),
(10, 'Univerzitet Union', 'Pravne nauke');

-- --------------------------------------------------------

--
-- Table structure for table `univerzitet_oblast`
--

CREATE TABLE `univerzitet_oblast` (
  `ID_veze` int(11) NOT NULL,
  `ID_univerziteta` int(11) NOT NULL,
  `ID_oblasti` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `univerzitet_oblast`
--

INSERT INTO `univerzitet_oblast` (`ID_veze`, `ID_univerziteta`, `ID_oblasti`) VALUES
(1, 1, 1),
(2, 1, 3),
(3, 2, 2),
(4, 3, 3),
(5, 4, 4),
(6, 5, 5),
(7, 5, 6),
(8, 6, 6),
(9, 7, 9),
(10, 8, 1),
(11, 8, 10),
(12, 9, 2),
(13, 10, 8);

-- --------------------------------------------------------

--
-- Table structure for table `valuta`
--

CREATE TABLE `valuta` (
  `ID_valute` int(11) NOT NULL,
  `skraceni_naziv` varchar(3) NOT NULL,
  `puni_naziv` varchar(100) NOT NULL,
  `je_li_domaca` tinyint(1) NOT NULL,
  `je_li_euro` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `valuta`
--

INSERT INTO `valuta` (`ID_valute`, `skraceni_naziv`, `puni_naziv`, `je_li_domaca`, `je_li_euro`) VALUES
(1, 'RSD', 'Srpski dinar', 1, 0),
(2, 'EUR', 'Euro', 0, 1),
(3, 'USD', 'Američki dolar', 0, 0),
(4, 'GBP', 'Britanska funta', 0, 0),
(5, 'CHF', 'Švajcarski franak', 0, 0),
(6, 'RUB', 'Ruska rublja', 0, 0),
(7, 'HRK', 'Hrvatska kuna', 0, 0),
(8, 'BAM', 'Konvertibilna marka', 0, 0),
(9, 'HUF', 'Mađarska forinta', 0, 0),
(10, 'CZK', 'Češka kruna', 0, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `beleska`
--
ALTER TABLE `beleska`
  ADD PRIMARY KEY (`ID_beleske`),
  ADD KEY `idx_beleska_datum_unosa` (`datum_unosa`),
  ADD KEY `fk_beleska_seansa` (`ID_seanse`);

--
-- Indexes for table `cena_seanse`
--
ALTER TABLE `cena_seanse`
  ADD PRIMARY KEY (`ID_cene`),
  ADD KEY `idx_cena_seanse_datum` (`datum_promene`);

--
-- Indexes for table `centar_za_obuku`
--
ALTER TABLE `centar_za_obuku`
  ADD PRIMARY KEY (`ID_centra`),
  ADD UNIQUE KEY `uk_centar_email` (`email`),
  ADD KEY `idx_centar_telefon` (`telefon`);

--
-- Indexes for table `fakultet`
--
ALTER TABLE `fakultet`
  ADD PRIMARY KEY (`ID_fakulteta`),
  ADD KEY `idx_fakultet_naziv` (`naziv`),
  ADD KEY `fk_fakultet_univerzitet` (`ID_univerziteta`);

--
-- Indexes for table `fakultet_oblast`
--
ALTER TABLE `fakultet_oblast`
  ADD PRIMARY KEY (`ID_veze`),
  ADD UNIQUE KEY `uk_fakultet_oblast` (`ID_fakulteta`,`ID_oblasti_fakulteta`),
  ADD KEY `fk_fakultet_oblast_oblast` (`ID_oblasti_fakulteta`);

--
-- Indexes for table `kandidat`
--
ALTER TABLE `kandidat`
  ADD PRIMARY KEY (`ID_kandidata`),
  ADD UNIQUE KEY `uk_kandidat_osoba` (`ID_osobe`),
  ADD KEY `idx_kandidat_status_obuke` (`status_obuke`),
  ADD KEY `fk_kandidat_centar` (`ID_centra`),
  ADD KEY `fk_kandidat_fakultet` (`ID_fakulteta`);

--
-- Indexes for table `klijent`
--
ALTER TABLE `klijent`
  ADD PRIMARY KEY (`ID_klijenta`),
  ADD UNIQUE KEY `uk_klijent_osoba` (`ID_osobe`),
  ADD KEY `idx_klijent_ranije_isao` (`ranije_isao_psihoterapeutu`);

--
-- Indexes for table `korisnik`
--
ALTER TABLE `korisnik`
  ADD PRIMARY KEY (`ID_korisnika`),
  ADD UNIQUE KEY `uk_korisnik_korisnicko_ime` (`korisnicko_ime`),
  ADD UNIQUE KEY `uk_korisnik_psihoterapeut` (`ID_psihoterapeuta`),
  ADD KEY `idx_korisnik_korisnicko_ime` (`korisnicko_ime`),
  ADD KEY `idx_korisnik_ime_prezime` (`ime`,`prezime`);

--
-- Indexes for table `kurs_valute`
--
ALTER TABLE `kurs_valute`
  ADD PRIMARY KEY (`ID_kursa`),
  ADD KEY `idx_kurs_valute_datum` (`datum_promene`),
  ADD KEY `fk_kurs_valuta` (`ID_valute`);

--
-- Indexes for table `objavljivanje_podataka`
--
ALTER TABLE `objavljivanje_podataka`
  ADD PRIMARY KEY (`ID_objavljivanja`),
  ADD KEY `idx_objavljivanje_razlog` (`razlog`),
  ADD KEY `idx_objavljivanje_datum` (`datum_objavljivanja`),
  ADD KEY `fk_objavljivanje_seansa` (`ID_seanse`);

--
-- Indexes for table `oblast_fakulteta`
--
ALTER TABLE `oblast_fakulteta`
  ADD PRIMARY KEY (`ID_oblasti_fakulteta`),
  ADD UNIQUE KEY `uk_oblast_fakulteta_naziv` (`naziv_oblasti`);

--
-- Indexes for table `oblast_psihoterapije`
--
ALTER TABLE `oblast_psihoterapije`
  ADD PRIMARY KEY (`ID_oblasti_psihoterapije`),
  ADD UNIQUE KEY `uk_oblast_psihoterapije_naziv` (`naziv_oblasti`);

--
-- Indexes for table `oblast_usmerenja`
--
ALTER TABLE `oblast_usmerenja`
  ADD PRIMARY KEY (`ID_oblasti`),
  ADD UNIQUE KEY `uk_oblast_usmerenja_naziv` (`naziv_oblasti`);

--
-- Indexes for table `osoba`
--
ALTER TABLE `osoba`
  ADD PRIMARY KEY (`ID_osobe`),
  ADD UNIQUE KEY `uk_osoba_jmbg` (`JMBG`),
  ADD UNIQUE KEY `uk_osoba_email` (`email`),
  ADD KEY `idx_osoba_ime_prezime` (`ime`,`prezime`);

--
-- Indexes for table `placanje`
--
ALTER TABLE `placanje`
  ADD PRIMARY KEY (`ID_placanja`),
  ADD KEY `idx_placanje_datum` (`datum_placanja`),
  ADD KEY `idx_placanje_svrha` (`svrha`),
  ADD KEY `idx_placanje_nacin` (`nacin_placanja`),
  ADD KEY `fk_placanje_klijent` (`ID_klijenta`),
  ADD KEY `fk_placanje_rezultat` (`ID_rezultata`),
  ADD KEY `fk_placanje_seansa` (`ID_seanse`),
  ADD KEY `fk_placanje_valuta` (`ID_valute`);

--
-- Indexes for table `prijava`
--
ALTER TABLE `prijava`
  ADD PRIMARY KEY (`ID_prijave`),
  ADD KEY `idx_prijava_datum` (`datum_prijave`),
  ADD KEY `idx_prijava_komunikacioni_kanal` (`komunikacioni_kanal`),
  ADD KEY `fk_prijava_klijent` (`ID_klijenta`),
  ADD KEY `fk_prijava_psihoterapeut` (`ID_psihoterapeuta`);

--
-- Indexes for table `psiholoski_test`
--
ALTER TABLE `psiholoski_test`
  ADD PRIMARY KEY (`ID_testa`),
  ADD UNIQUE KEY `uk_psiholoski_test_naziv` (`naziv`),
  ADD KEY `idx_psiholoski_test_oblast` (`oblast`);

--
-- Indexes for table `psihoterapeut`
--
ALTER TABLE `psihoterapeut`
  ADD PRIMARY KEY (`ID_psihoterapeuta`),
  ADD UNIQUE KEY `uk_psihoterapeut_osoba` (`ID_osobe`),
  ADD UNIQUE KEY `uk_psihoterapeut_sertifikat` (`ID_sertifikata`),
  ADD KEY `idx_psihoterapeut_psiholog` (`je_psiholog`);

--
-- Indexes for table `seansa`
--
ALTER TABLE `seansa`
  ADD PRIMARY KEY (`ID_seanse`),
  ADD KEY `idx_seansa_datum` (`datum`),
  ADD KEY `idx_seansa_je_prva` (`je_prva_seansa`),
  ADD KEY `fk_seansa_cena` (`ID_cene`),
  ADD KEY `fk_seansa_prijava` (`ID_prijave`);

--
-- Indexes for table `sertifikat`
--
ALTER TABLE `sertifikat`
  ADD PRIMARY KEY (`ID_sertifikata`),
  ADD KEY `idx_sertifikat_datum` (`datum_sertifikacije`),
  ADD KEY `fk_sertifikat_centar` (`ID_centra`),
  ADD KEY `fk_sertifikat_oblast` (`ID_oblasti_psihoterapije`),
  ADD KEY `fk_sertifikat_osoba` (`ID_osobe`);

--
-- Indexes for table `supervizija`
--
ALTER TABLE `supervizija`
  ADD PRIMARY KEY (`ID_supervizije`),
  ADD KEY `idx_supervizija_datum_pocetka` (`datum_pocetka`),
  ADD KEY `idx_supervizija_datum_zavrsetka` (`datum_zavrsetka`),
  ADD KEY `fk_supervizija_kandidat` (`ID_kandidata`),
  ADD KEY `fk_supervizija_psihoterapeut` (`ID_psihoterapeuta`);

--
-- Indexes for table `test_rezultat`
--
ALTER TABLE `test_rezultat`
  ADD PRIMARY KEY (`ID_rezultata`),
  ADD KEY `idx_test_rezultat_datum` (`datum_testiranja`),
  ADD KEY `fk_test_rezultat_klijent` (`ID_klijenta`),
  ADD KEY `fk_test_rezultat_psihoterapeut` (`ID_psihoterapeuta`),
  ADD KEY `fk_test_rezultat_seansa` (`ID_seanse`),
  ADD KEY `fk_test_rezultat_test` (`ID_testa`);

--
-- Indexes for table `univerzitet`
--
ALTER TABLE `univerzitet`
  ADD PRIMARY KEY (`ID_univerziteta`),
  ADD KEY `idx_univerzitet_naziv` (`naziv`);

--
-- Indexes for table `univerzitet_oblast`
--
ALTER TABLE `univerzitet_oblast`
  ADD PRIMARY KEY (`ID_veze`),
  ADD UNIQUE KEY `uk_univerzitet_oblast` (`ID_univerziteta`,`ID_oblasti`),
  ADD KEY `fk_univerzitet_oblast_oblast` (`ID_oblasti`);

--
-- Indexes for table `valuta`
--
ALTER TABLE `valuta`
  ADD PRIMARY KEY (`ID_valute`),
  ADD UNIQUE KEY `uk_valuta_skraceni_naziv` (`skraceni_naziv`),
  ADD KEY `idx_valuta_je_domaca` (`je_li_domaca`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `beleska`
--
ALTER TABLE `beleska`
  MODIFY `ID_beleske` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `cena_seanse`
--
ALTER TABLE `cena_seanse`
  MODIFY `ID_cene` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `centar_za_obuku`
--
ALTER TABLE `centar_za_obuku`
  MODIFY `ID_centra` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `fakultet`
--
ALTER TABLE `fakultet`
  MODIFY `ID_fakulteta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `fakultet_oblast`
--
ALTER TABLE `fakultet_oblast`
  MODIFY `ID_veze` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `kandidat`
--
ALTER TABLE `kandidat`
  MODIFY `ID_kandidata` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `klijent`
--
ALTER TABLE `klijent`
  MODIFY `ID_klijenta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `korisnik`
--
ALTER TABLE `korisnik`
  MODIFY `ID_korisnika` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `kurs_valute`
--
ALTER TABLE `kurs_valute`
  MODIFY `ID_kursa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `objavljivanje_podataka`
--
ALTER TABLE `objavljivanje_podataka`
  MODIFY `ID_objavljivanja` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `oblast_fakulteta`
--
ALTER TABLE `oblast_fakulteta`
  MODIFY `ID_oblasti_fakulteta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `oblast_psihoterapije`
--
ALTER TABLE `oblast_psihoterapije`
  MODIFY `ID_oblasti_psihoterapije` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `oblast_usmerenja`
--
ALTER TABLE `oblast_usmerenja`
  MODIFY `ID_oblasti` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `osoba`
--
ALTER TABLE `osoba`
  MODIFY `ID_osobe` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `placanje`
--
ALTER TABLE `placanje`
  MODIFY `ID_placanja` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `prijava`
--
ALTER TABLE `prijava`
  MODIFY `ID_prijave` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `psiholoski_test`
--
ALTER TABLE `psiholoski_test`
  MODIFY `ID_testa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `psihoterapeut`
--
ALTER TABLE `psihoterapeut`
  MODIFY `ID_psihoterapeuta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `seansa`
--
ALTER TABLE `seansa`
  MODIFY `ID_seanse` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `sertifikat`
--
ALTER TABLE `sertifikat`
  MODIFY `ID_sertifikata` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `supervizija`
--
ALTER TABLE `supervizija`
  MODIFY `ID_supervizije` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `test_rezultat`
--
ALTER TABLE `test_rezultat`
  MODIFY `ID_rezultata` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `univerzitet`
--
ALTER TABLE `univerzitet`
  MODIFY `ID_univerziteta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `univerzitet_oblast`
--
ALTER TABLE `univerzitet_oblast`
  MODIFY `ID_veze` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `valuta`
--
ALTER TABLE `valuta`
  MODIFY `ID_valute` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `beleska`
--
ALTER TABLE `beleska`
  ADD CONSTRAINT `fk_beleska_seansa` FOREIGN KEY (`ID_seanse`) REFERENCES `seansa` (`ID_seanse`);

--
-- Constraints for table `fakultet`
--
ALTER TABLE `fakultet`
  ADD CONSTRAINT `fk_fakultet_univerzitet` FOREIGN KEY (`ID_univerziteta`) REFERENCES `univerzitet` (`ID_univerziteta`);

--
-- Constraints for table `fakultet_oblast`
--
ALTER TABLE `fakultet_oblast`
  ADD CONSTRAINT `fk_fakultet_oblast_fakultet` FOREIGN KEY (`ID_fakulteta`) REFERENCES `fakultet` (`ID_fakulteta`),
  ADD CONSTRAINT `fk_fakultet_oblast_oblast` FOREIGN KEY (`ID_oblasti_fakulteta`) REFERENCES `oblast_fakulteta` (`ID_oblasti_fakulteta`);

--
-- Constraints for table `kandidat`
--
ALTER TABLE `kandidat`
  ADD CONSTRAINT `fk_kandidat_centar` FOREIGN KEY (`ID_centra`) REFERENCES `centar_za_obuku` (`ID_centra`),
  ADD CONSTRAINT `fk_kandidat_fakultet` FOREIGN KEY (`ID_fakulteta`) REFERENCES `fakultet` (`ID_fakulteta`),
  ADD CONSTRAINT `fk_kandidat_osoba` FOREIGN KEY (`ID_osobe`) REFERENCES `osoba` (`ID_osobe`);

--
-- Constraints for table `klijent`
--
ALTER TABLE `klijent`
  ADD CONSTRAINT `fk_klijent_osoba` FOREIGN KEY (`ID_osobe`) REFERENCES `osoba` (`ID_osobe`);

--
-- Constraints for table `korisnik`
--
ALTER TABLE `korisnik`
  ADD CONSTRAINT `fk_korisnik_psihoterapeut` FOREIGN KEY (`ID_psihoterapeuta`) REFERENCES `psihoterapeut` (`ID_psihoterapeuta`);

--
-- Constraints for table `kurs_valute`
--
ALTER TABLE `kurs_valute`
  ADD CONSTRAINT `fk_kurs_valuta` FOREIGN KEY (`ID_valute`) REFERENCES `valuta` (`ID_valute`);

--
-- Constraints for table `objavljivanje_podataka`
--
ALTER TABLE `objavljivanje_podataka`
  ADD CONSTRAINT `fk_objavljivanje_seansa` FOREIGN KEY (`ID_seanse`) REFERENCES `seansa` (`ID_seanse`);

--
-- Constraints for table `placanje`
--
ALTER TABLE `placanje`
  ADD CONSTRAINT `fk_placanje_klijent` FOREIGN KEY (`ID_klijenta`) REFERENCES `klijent` (`ID_klijenta`),
  ADD CONSTRAINT `fk_placanje_rezultat` FOREIGN KEY (`ID_rezultata`) REFERENCES `test_rezultat` (`ID_rezultata`),
  ADD CONSTRAINT `fk_placanje_seansa` FOREIGN KEY (`ID_seanse`) REFERENCES `seansa` (`ID_seanse`),
  ADD CONSTRAINT `fk_placanje_valuta` FOREIGN KEY (`ID_valute`) REFERENCES `valuta` (`ID_valute`);

--
-- Constraints for table `prijava`
--
ALTER TABLE `prijava`
  ADD CONSTRAINT `fk_prijava_klijent` FOREIGN KEY (`ID_klijenta`) REFERENCES `klijent` (`ID_klijenta`),
  ADD CONSTRAINT `fk_prijava_psihoterapeut` FOREIGN KEY (`ID_psihoterapeuta`) REFERENCES `psihoterapeut` (`ID_psihoterapeuta`);

--
-- Constraints for table `psihoterapeut`
--
ALTER TABLE `psihoterapeut`
  ADD CONSTRAINT `fk_psihoterapeut_osoba` FOREIGN KEY (`ID_osobe`) REFERENCES `osoba` (`ID_osobe`),
  ADD CONSTRAINT `fk_psihoterapeut_sertifikat` FOREIGN KEY (`ID_sertifikata`) REFERENCES `sertifikat` (`ID_sertifikata`);

--
-- Constraints for table `seansa`
--
ALTER TABLE `seansa`
  ADD CONSTRAINT `fk_seansa_cena` FOREIGN KEY (`ID_cene`) REFERENCES `cena_seanse` (`ID_cene`),
  ADD CONSTRAINT `fk_seansa_prijava` FOREIGN KEY (`ID_prijave`) REFERENCES `prijava` (`ID_prijave`);

--
-- Constraints for table `sertifikat`
--
ALTER TABLE `sertifikat`
  ADD CONSTRAINT `fk_sertifikat_centar` FOREIGN KEY (`ID_centra`) REFERENCES `centar_za_obuku` (`ID_centra`),
  ADD CONSTRAINT `fk_sertifikat_oblast` FOREIGN KEY (`ID_oblasti_psihoterapije`) REFERENCES `oblast_psihoterapije` (`ID_oblasti_psihoterapije`),
  ADD CONSTRAINT `fk_sertifikat_osoba` FOREIGN KEY (`ID_osobe`) REFERENCES `osoba` (`ID_osobe`);

--
-- Constraints for table `supervizija`
--
ALTER TABLE `supervizija`
  ADD CONSTRAINT `fk_supervizija_kandidat` FOREIGN KEY (`ID_kandidata`) REFERENCES `kandidat` (`ID_kandidata`),
  ADD CONSTRAINT `fk_supervizija_psihoterapeut` FOREIGN KEY (`ID_psihoterapeuta`) REFERENCES `psihoterapeut` (`ID_psihoterapeuta`);

--
-- Constraints for table `test_rezultat`
--
ALTER TABLE `test_rezultat`
  ADD CONSTRAINT `fk_test_rezultat_klijent` FOREIGN KEY (`ID_klijenta`) REFERENCES `klijent` (`ID_klijenta`),
  ADD CONSTRAINT `fk_test_rezultat_psihoterapeut` FOREIGN KEY (`ID_psihoterapeuta`) REFERENCES `psihoterapeut` (`ID_psihoterapeuta`),
  ADD CONSTRAINT `fk_test_rezultat_seansa` FOREIGN KEY (`ID_seanse`) REFERENCES `seansa` (`ID_seanse`),
  ADD CONSTRAINT `fk_test_rezultat_test` FOREIGN KEY (`ID_testa`) REFERENCES `psiholoski_test` (`ID_testa`);

--
-- Constraints for table `univerzitet_oblast`
--
ALTER TABLE `univerzitet_oblast`
  ADD CONSTRAINT `fk_univerzitet_oblast_oblast` FOREIGN KEY (`ID_oblasti`) REFERENCES `oblast_usmerenja` (`ID_oblasti`),
  ADD CONSTRAINT `fk_univerzitet_oblast_univerzitet` FOREIGN KEY (`ID_univerziteta`) REFERENCES `univerzitet` (`ID_univerziteta`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
