-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Feb 14, 2022 alle 10:18
-- Versione del server: 10.4.22-MariaDB
-- Versione PHP: 8.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mybooksmanager`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `autori`
--

CREATE TABLE `autori` (
  `idautore` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `cognome` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `autori`
--

INSERT INTO `autori` (`idautore`, `nome`, `cognome`) VALUES (1, 'Joanne ', 'Rowling');
INSERT INTO `autori` (`idautore`, `nome`, `cognome`) VALUES (2, 'Umberto', 'Eco');
INSERT INTO `autori` (`idautore`, `nome`, `cognome`) VALUES (3, ' Ray ', 'Bradbury');
INSERT INTO `autori` (`idautore`, `nome`, `cognome`) VALUES (4, ' Stephen ', 'King');




-- --------------------------------------------------------

--
-- Struttura della tabella `autorilibri`
--

CREATE TABLE `autorilibri` (
  `idautorelibro` int(11) NOT NULL,
  `idautore` int(11) NOT NULL,
  `idlibro` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `generi`
--

CREATE TABLE `generi` (
  `idgenere` int(11) NOT NULL,
  `nomegenere` varchar(255) NOT NULL,
  `descrizione` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `generi`
--

INSERT INTO `generi` (`idgenere`, `nomegenere`, `descrizione`) VALUES
(1, 'Fantasy', ''),
(2, 'Giallo storico', ''),
(3, 'Fantascienza', '');

-- --------------------------------------------------------

--
-- Struttura della tabella `libri`
--

CREATE TABLE `libri` (
  `idlibro` int(11) NOT NULL,
  `titolo` varchar(255) NOT NULL,
  `descrizione` text NOT NULL,
  `giudizio` tinyint(4) NOT NULL,
  `prezzo` float NOT NULL,
  `anno` smallint(6) NOT NULL,
  `dataInserimento` date NOT NULL,
  `copertina` varchar(200) NOT NULL,
  `data` date NOT NULL,
  `idgenere` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `libri`
--

INSERT INTO `libri` (`idlibro`, `titolo`, `descrizione`, `giudizio`, `prezzo`, `anno`, `dataInserimento`, `copertina`, `data`, `idgenere`) VALUES
(2, 'Harry Potter e la pietra filosofale ', 'Harry Potter e la pietra filosofale (titolo originale in inglese: Harry Potter and the Philosopher\'s Stone) è il primo romanzo della saga high fantasy Harry Potter, scritta da J. K. Rowling e ambientata principalmente nell\'immaginario Mondo magico durante gli anni novanta del XX secolo.', 2, 9.99, 1997, '2020-04-10', '', '2020-04-10', 1),
(4, 'Il nome della rosa', 'L\'opera, ambientata sul finire dell\'anno 1327, si presenta con un classico espediente letterario, quello del manoscritto ritrovato, opera, in questo caso, di un monaco di nome Adso da Melk, che, divenuto ormai anziano, decide di mettere su carta i fatti notevoli vissuti da novizio, molti decenni addietro, in compagnia del proprio maestro Guglielmo da Baskerville. La vicenda si svolge all\'interno di un monastero benedettino del Piemonte, ed è suddivisa in sette giornate, scandite dai ritmi della vita monastica.', 5, 18, 1980, '2020-05-10', '', '2020-05-10', 2),
(5, 'Harry Potter e la camera dei segreti', 'Harry Potter e la camera dei segreti (titolo originale in inglese: Harry Potter and the Chamber of Secrets) è il secondo romanzo della saga high fantasy Harry Potter, scritta da J. K. Rowling e ambientata principalmente nell\'immaginario Mondo magico durante gli anni novanta del XX secolo.', 3, 9, 1998, '2020-05-10', '', '2020-05-10', 1),
(6, 'Fahrenheit 451', 'Fahrenheit 451 (edito in Italia anche con il titolo Gli anni della fenice) è un romanzo di fantascienza del 1953, scritto da Ray Bradbury.\r\n\r\nAmbientato in un imprecisato futuro posteriore al 1960, vi si descrive una società distopica in cui leggere o possedere libri è considerato un reato, per contrastare il quale è stato istituito un apposito corpo di vigili del fuoco impegnato a bruciare ogni tipo di volume.', 5, 7.99, 1953, '2020-05-08', '', '2020-05-10', 3);

-- --------------------------------------------------------

--
-- Struttura della tabella `utenti`
--

CREATE TABLE `utenti` (
  `iduser` int(11) NOT NULL,
  `user` varchar(50) NOT NULL,
  `password` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `utenti`
--

INSERT INTO `utenti` (`iduser`, `user`, `password`) VALUES
(1, 'ale', '81dc9bdb52d04dc20036dbd8313ed055');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `autori`
--
ALTER TABLE `autori`
  ADD PRIMARY KEY (`idautore`);

--
-- Indici per le tabelle `autorilibri`
--
ALTER TABLE `autorilibri`
  ADD PRIMARY KEY (`idautorelibro`),
  ADD KEY `idautore` (`idautore`),
  ADD KEY `idlibro` (`idlibro`);

--
-- Indici per le tabelle `generi`
--
ALTER TABLE `generi`
  ADD PRIMARY KEY (`idgenere`);

--
-- Indici per le tabelle `libri`
--
ALTER TABLE `libri`
  ADD PRIMARY KEY (`idlibro`),
  ADD KEY `titolo` (`titolo`(191)),
  ADD KEY `genereID` (`idgenere`);

--
-- Indici per le tabelle `utenti`
--
ALTER TABLE `utenti`
  ADD PRIMARY KEY (`iduser`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `autori`
--
ALTER TABLE `autori`
  MODIFY `idautore` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT per la tabella `autorilibri`
--
ALTER TABLE `autorilibri`
  MODIFY `idautorelibro` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `generi`
--
ALTER TABLE `generi`
  MODIFY `idgenere` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT per la tabella `libri`
--
ALTER TABLE `libri`
  MODIFY `idlibro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT per la tabella `utenti`
--
ALTER TABLE `utenti`
  MODIFY `iduser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `libri`
--
ALTER TABLE `libri`
  ADD CONSTRAINT `libri_ibfk_1` FOREIGN KEY (`idgenere`) REFERENCES `generi` (`idgenere`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
