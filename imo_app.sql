-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Sep 20, 2018 at 10:41 AM
-- Server version: 5.7.21
-- PHP Version: 7.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `imo_app`
--

-- --------------------------------------------------------

--
-- Table structure for table `casas`
--

DROP TABLE IF EXISTS `casas`;
CREATE TABLE IF NOT EXISTS `casas` (
  `idc` int(11) NOT NULL AUTO_INCREMENT,
  `ref_imo` varchar(255) NOT NULL,
  `quartos` int(11) NOT NULL,
  `ano` int(11) NOT NULL,
  `preco` int(11) NOT NULL,
  PRIMARY KEY (`idc`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `casas`
--

INSERT INTO `casas` (`idc`, `ref_imo`, `quartos`, `ano`, `preco`) VALUES
(3, 'T4MIRANDELA', 20, 2017, 100000);

-- --------------------------------------------------------

--
-- Table structure for table `marcavisita`
--

DROP TABLE IF EXISTS `marcavisita`;
CREATE TABLE IF NOT EXISTS `marcavisita` (
  `idmv` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `ref_imo` varchar(255) NOT NULL,
  `data` varchar(255) NOT NULL,
  `hora` varchar(255) NOT NULL,
  PRIMARY KEY (`idmv`)
) ENGINE=MyISAM AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `marcavisita`
--

INSERT INTO `marcavisita` (`idmv`, `uid`, `ref_imo`, `data`, `hora`) VALUES
(4, 1, 'T1AROUCA', '24/9/2021', '13'),
(3, 1, 'T2BRAGA', '18/9/2018', '9'),
(5, 1, 'T2BRAGA', '20/3/2026', '13'),
(6, 1, 'T10Macedo', '18/9/2024', '9'),
(7, 1, 'T10Macedo', '18/9/2031', '9'),
(8, 1, 'T10Macedo', '18/9/2024', '9'),
(9, 1, 'T10Macedo', '18/9/2012', '9'),
(10, 1, 'T10Macedo', '18/9/2024', '9'),
(11, 1, 'T10Macedo', '24/9/2022', '9'),
(12, 1, 'T10Macedo', '18/9/2024', '9'),
(13, 1, 'T10Macedo', '18/9/2025', '9'),
(14, 1, 'T10Macedo', '18/9/2024', '9'),
(15, 1, 'T10Macedo', '24/9/2018', '9'),
(16, 1, 'T10Macedo', '18/9/2009', '9'),
(17, 1, 'T10Macedo', '18/2/2019', '9'),
(18, 1, 'T10Macedo', '18/9/2024', '9'),
(19, 1, 'T10Macedo', '18/9/2023', '9'),
(20, 1, 'T10Macedo', '24/9/2024', '9'),
(21, 1, 'T4MIRANDELA', '19/9/2018', '14'),
(22, 1, 'T4MIRANDELAWHATEVER', '19/9/2018', '17'),
(23, 0, 'T4MIRANDELAWHATEVER', 'Selecionar Data', '15'),
(24, 0, 'T4MIRANDELA', '30/9/2018', '9'),
(25, 0, 'T4MIRANDELA', '22/10/2018', '12'),
(26, 0, 'T4MIRANDELA', '25/1/2023', '14'),
(27, 0, 'T4MIRANDELA', '20/9/2023', '13');

-- --------------------------------------------------------

--
-- Table structure for table `utilizadores`
--

DROP TABLE IF EXISTS `utilizadores`;
CREATE TABLE IF NOT EXISTS `utilizadores` (
  `idu` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `pass` varchar(255) NOT NULL,
  `telefone` int(9) NOT NULL,
  PRIMARY KEY (`idu`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `utilizadores`
--

INSERT INTO `utilizadores` (`idu`, `nome`, `email`, `pass`, `telefone`) VALUES
(1, 'Guilherme', 'Gui@gui.pt', '123456', 123456789),
(2, 'qwert', 'qwert@qwe.xom', '123456', 123456789),
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
