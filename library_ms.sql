-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 03, 2023 at 02:06 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `library_ms`
--

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE `books` (
  `book_id` int(10) NOT NULL,
  `book_name` varchar(20) NOT NULL,
  `book_author` varchar(20) NOT NULL,
  `book_type` varchar(10) NOT NULL,
  `book_quantity` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`book_id`, `book_name`, `book_author`, `book_type`, `book_quantity`) VALUES
(1, 'Madol Duwa', 'Martin Wickramasingh', 'Novel', 4),
(2, 'Gamperaliya', 'Gunadasa Amarasekara', 'Novel', 2),
(3, 'Yuganthaya', 'Martin Wickramasingh', 'Novel', 4),
(4, 'Mina', 'Ediriweera Sarachcha', 'Play', 1),
(5, 'Sinhabahu', 'Ediriweera Sarachcha', 'Play', 3),
(6, 'Kavsilumina', ' Ven. Ratmalane Dhar', 'Poem', 2),
(7, 'Janakiharana', 'Ven. Kumaradasa Ther', 'Poem', 3),
(8, 'Kasyapa Yuddhaya', 'Ven. Piyarathana The', 'Poem', 3),
(9, 'Amavatura', 'Ven. Buddhaghosa The', 'Buddhist T', 4),
(10, 'Visuddhimagga', 'Ven. Buddhaghosa The', 'Buddhist T', 2),
(11, 'Abhidhamma Sangaha', 'Ven. Acariya Dhammas', 'Buddhist T', 1),
(12, 'Mahawanshaya', 'Ven. Mahanama Thero', 'History', 2),
(13, 'Rajawaliya', 'Ven. Diyasena Thero', 'History', 3),
(14, 'Chulawanshaya', 'Ven. Dhammakitti The', 'History', 2),
(15, 'Kumarihami', 'Munidasa Cumaratunga', 'Short Stor', 3),
(16, 'Kalu Hamu', 'Munidasa Cumaratunga', 'Short Stor', 4),
(17, 'Mawathage Kaluwaran', 'Munidasa Cumaratunga', 'Short Stor', 1),
(18, 'Aluth Sahitya Sangar', ' Martin Wickramasing', 'Literary C', 1),
(19, 'Bawa Yugaya', 'Gunadasa Amarasekara', 'Literary C', 2),
(20, 'Sinhala Sahityaye Ka', 'Ediriweera Sarachcha', 'Literary C', 3),
(21, 'Sinhala Sahityaye Al', 'K.N.O. Dharmadasa', 'Literary C', 4);

-- --------------------------------------------------------

--
-- Table structure for table `issue_books`
--

CREATE TABLE `issue_books` (
  `issue_id` int(11) NOT NULL,
  `book_id` int(10) NOT NULL,
  `reader_id` int(10) NOT NULL,
  `issue_date` date NOT NULL,
  `returned_date` date NOT NULL,
  `status` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `issue_books`
--

INSERT INTO `issue_books` (`issue_id`, `book_id`, `reader_id`, `issue_date`, `returned_date`, `status`) VALUES
(1, 2, 2, '2023-12-04', '2023-12-25', 'Pennding'),
(2, 1, 1, '2023-12-10', '2023-12-24', 'Pennding'),
(3, 1, 1, '2023-12-17', '2023-12-31', 'Pennding'),
(4, 4, 4, '2023-12-10', '2023-12-31', 'Pennding'),
(5, 2, 2, '2023-12-10', '2023-12-31', 'Pennding');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `uName` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`uName`, `password`) VALUES
('user', '123'),
('asd', 'asd');

-- --------------------------------------------------------

--
-- Table structure for table `readers`
--

CREATE TABLE `readers` (
  `reader_id` int(10) NOT NULL,
  `reader_name` varchar(20) NOT NULL,
  `reader_age` int(2) NOT NULL,
  `reader_city` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `readers`
--

INSERT INTO `readers` (`reader_id`, `reader_name`, `reader_age`, `reader_city`) VALUES
(1, 'Kamala Silva', 35, 'Colombo'),
(2, 'Nuwan Perera', 28, 'Gampaha'),
(3, 'Dilhani Fernando', 42, 'Kandy'),
(4, 'Pradeep Kumara', 50, 'Jaffna'),
(5, 'Sunethra Bandara', 22, 'Galle'),
(6, 'Madushi Rathnayake', 32, 'Matara'),
(7, 'Chathurika Jayawarde', 26, 'Ratnapura'),
(8, 'Dinuka Priyadarshana', 45, 'Anuradhapu'),
(9, 'Prasanna Wijeratne', 38, 'Polonnaruw'),
(10, 'Chaminda Jayalal', 24, 'Kurunegala'),
(11, 'Upuli Jayasinghe', 36, 'Badulla'),
(12, 'Shashikala Jayasunda', 40, 'Monaragala'),
(13, 'Nimal Fernando', 52, 'Hambantota'),
(14, 'Saman Kumara', 48, 'Ampara'),
(15, 'Thilakasiri Jayantha', 30, 'Batticaloa'),
(16, 'Udaya Kumara', 25, 'Trincomale'),
(17, 'Priyanka Perera', 43, 'Mullaitivu'),
(18, 'Janaka Silva', 39, 'Kilinochch');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`book_id`);

--
-- Indexes for table `issue_books`
--
ALTER TABLE `issue_books`
  ADD PRIMARY KEY (`issue_id`);

--
-- Indexes for table `readers`
--
ALTER TABLE `readers`
  ADD PRIMARY KEY (`reader_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `books`
--
ALTER TABLE `books`
  MODIFY `book_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `issue_books`
--
ALTER TABLE `issue_books`
  MODIFY `issue_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `readers`
--
ALTER TABLE `readers`
  MODIFY `reader_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
