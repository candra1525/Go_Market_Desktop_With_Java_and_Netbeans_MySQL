-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 09, 2022 at 05:48 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_gomarket`
--

-- --------------------------------------------------------

--
-- Stand-in structure for view `rtrans`
-- (See below for the actual view)
--
CREATE TABLE `rtrans` (
`id_transaksi` int(11)
,`kode_transaksi` varchar(8)
,`tgl_trans` date
,`kode_pelanggan` varchar(8)
,`nama_pelanggan` varchar(25)
,`tempat_lahir` varchar(25)
,`tanggal_lahir` date
,`jenis_kelamin` varchar(1)
,`alamat` varchar(50)
,`no_hp` varchar(12)
,`email` varchar(25)
,`kode_barang` varchar(8)
,`nama_barang` varchar(30)
,`satuan` varchar(20)
,`jenis_barang` varchar(20)
,`stok_barang` int(15)
,`harga_beli` int(20)
,`harga_jual` int(20)
,`nama_supp` varchar(30)
,`qty` int(5)
,`total` int(30)
,`uangbayar` int(30)
);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_barang`
--

CREATE TABLE `tbl_barang` (
  `kode_barang` varchar(8) NOT NULL,
  `nama_barang` varchar(30) NOT NULL,
  `satuan` varchar(20) NOT NULL,
  `jenis_barang` varchar(20) NOT NULL,
  `stok_barang` int(15) NOT NULL,
  `harga_beli` int(20) NOT NULL,
  `harga_jual` int(20) NOT NULL,
  `nama_supp` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_barang`
--

INSERT INTO `tbl_barang` (`kode_barang`, `nama_barang`, `satuan`, `jenis_barang`, `stok_barang`, `harga_beli`, `harga_jual`, `nama_supp`) VALUES
('BRG001', 'Mie Goreng', 'Buah', 'Makanan', 50, 2500, 3000, 'andy'),
('BRG002', 'Jus Lemon', 'Kaleng', 'Minuman', 50, 5000, 6000, 'ana'),
('BRG003', 'Kaos Anak', 'Pasang', 'Pakaian', 50, 50000, 60000, 'linda'),
('BRG004', 'Gelas', 'Buah', 'Peralatan', 50, 5000, 6000, 'Jono');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_data`
--

CREATE TABLE `tbl_data` (
  `username` varchar(30) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `tempat_lahir` varchar(30) NOT NULL,
  `tanggal_lahir` date NOT NULL,
  `jenis_kelamin` varchar(1) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `no_hp` varchar(15) NOT NULL,
  `email` varchar(30) NOT NULL,
  `level` varchar(30) NOT NULL,
  `pword` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_data`
--

INSERT INTO `tbl_data` (`username`, `nama`, `tempat_lahir`, `tanggal_lahir`, `jenis_kelamin`, `alamat`, `no_hp`, `email`, `level`, `pword`) VALUES
('anna', 'Anna Sari', 'Bengkulu', '2002-06-10', 'P', 'Jalan Kenanga', '084557848656', 'annasari@gmail.com', 'Admin', 'anna'),
('budi123', 'Budi Budiman', 'Aceh', '2001-06-05', 'L', 'Jalan Halim', '021545788963', 'budi@outlook.com', 'Admin', 'budi'),
('devcandra', 'Candra', 'Palembang', '2003-07-15', 'L', 'Jalan Wahid Hasyim', '0895603231365', 'candra_2007@mhs.mdp.ac.id', 'Developer', '2125250012'),
('devrafael', 'Rafael Lois Widyakusuma', 'Palembang', '2003-08-20', 'P', 'Jalan Sudirman', '085832604677', 'rafaelois17@mhs.mdp.ac.id', 'Developer', '2125250092'),
('devrafli', 'MGS. Muhammad Rafli', 'Palembang', '2003-06-20', 'L', 'Jalan Kemayoran', '0895605812487', 'rafli.llg2017@mhs.mdp.ac.id', 'Developer', '2125250034'),
('devrikky', 'Rikky', 'Palembang', '2003-05-20', 'L', 'Jalan Merdeka', '0895344584661', 'rikky25@mhs.mdp.ac.id', 'Developer', '2125250067'),
('kelvin', 'KelvinW.C', 'LubukLinggau', '2022-06-09', 'L', 'Jl.Rajawali', '08975678580', 'KelvinWilliamCandra@gmail.com', 'Developer', 'KelvinWC'),
('mirana', 'Mirana Sakura', 'Riau', '2003-08-05', 'P', 'Jalan Nanas', '0215457889', 'rana123@gmail.com', 'Kasir', 'rana'),
('rizky', 'Rizky ', 'Semarang', '2002-06-02', 'L', 'Jalan Iskandar', '024578984656', 'ki@gmail.com', 'Kasir', 'rizky');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_pelanggan`
--

CREATE TABLE `tbl_pelanggan` (
  `kode_pelanggan` varchar(8) NOT NULL,
  `nama_pelanggan` varchar(25) NOT NULL,
  `tempat_lahir` varchar(25) NOT NULL,
  `tanggal_lahir` date NOT NULL,
  `jenis_kelamin` varchar(1) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `no_hp` varchar(12) NOT NULL,
  `email` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_pelanggan`
--

INSERT INTO `tbl_pelanggan` (`kode_pelanggan`, `nama_pelanggan`, `tempat_lahir`, `tanggal_lahir`, `jenis_kelamin`, `alamat`, `no_hp`, `email`) VALUES
('P001', 'Dhika', 'Malang', '2000-06-09', 'L', 'Jalan Melon', '081245562323', 'dhika@email.com'),
('P002', 'Maya', 'Bekasi', '2001-06-05', 'P', 'Jalan Kusuma', '087845561232', 'maya123@outlook.com'),
('P003', 'Sukri', 'Jakarta', '2000-06-09', 'L', 'Jalan Merdeka II', '087487543356', 'skri@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_transaksi`
--

CREATE TABLE `tbl_transaksi` (
  `id_transaksi` int(11) NOT NULL,
  `kode_transaksi` varchar(8) NOT NULL,
  `tgl_trans` date NOT NULL,
  `kode_pelanggan` varchar(8) NOT NULL,
  `kode_barang` varchar(8) NOT NULL,
  `qty` int(5) NOT NULL,
  `total` int(30) NOT NULL,
  `uangbayar` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_transaksi`
--

INSERT INTO `tbl_transaksi` (`id_transaksi`, `kode_transaksi`, `tgl_trans`, `kode_pelanggan`, `kode_barang`, `qty`, `total`, `uangbayar`) VALUES
(1, 'T001', '2022-06-04', 'P001', 'BRG001', 1, 1000000, 1200000),
(10, 'T002', '2022-06-03', 'P003', 'BRG002', 5, 30000, 50000);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_ulasan`
--

CREATE TABLE `tbl_ulasan` (
  `id_ulasan` int(11) NOT NULL,
  `nilai` int(11) NOT NULL,
  `nama` varchar(25) NOT NULL,
  `masukkan` varchar(800) NOT NULL,
  `ket` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_ulasan`
--

INSERT INTO `tbl_ulasan` (`id_ulasan`, `nilai`, `nama`, `masukkan`, `ket`) VALUES
(3, 4, 'Candra', 'Belum Tersedia Report', 'Done'),
(4, 5, 'Andi', 'Aplikasi yang baguss hehehe', 'Done');

-- --------------------------------------------------------

--
-- Structure for view `rtrans`
--
DROP TABLE IF EXISTS `rtrans`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `rtrans`  AS SELECT `tbl_transaksi`.`id_transaksi` AS `id_transaksi`, `tbl_transaksi`.`kode_transaksi` AS `kode_transaksi`, `tbl_transaksi`.`tgl_trans` AS `tgl_trans`, `tbl_transaksi`.`kode_pelanggan` AS `kode_pelanggan`, `tbl_pelanggan`.`nama_pelanggan` AS `nama_pelanggan`, `tbl_pelanggan`.`tempat_lahir` AS `tempat_lahir`, `tbl_pelanggan`.`tanggal_lahir` AS `tanggal_lahir`, `tbl_pelanggan`.`jenis_kelamin` AS `jenis_kelamin`, `tbl_pelanggan`.`alamat` AS `alamat`, `tbl_pelanggan`.`no_hp` AS `no_hp`, `tbl_pelanggan`.`email` AS `email`, `tbl_transaksi`.`kode_barang` AS `kode_barang`, `tbl_barang`.`nama_barang` AS `nama_barang`, `tbl_barang`.`satuan` AS `satuan`, `tbl_barang`.`jenis_barang` AS `jenis_barang`, `tbl_barang`.`stok_barang` AS `stok_barang`, `tbl_barang`.`harga_beli` AS `harga_beli`, `tbl_barang`.`harga_jual` AS `harga_jual`, `tbl_barang`.`nama_supp` AS `nama_supp`, `tbl_transaksi`.`qty` AS `qty`, `tbl_transaksi`.`total` AS `total`, `tbl_transaksi`.`uangbayar` AS `uangbayar` FROM ((`tbl_transaksi` join `tbl_pelanggan`) join `tbl_barang`) WHERE `tbl_transaksi`.`kode_pelanggan` = `tbl_pelanggan`.`kode_pelanggan` AND `tbl_transaksi`.`kode_barang` = `tbl_barang`.`kode_barang``kode_barang`  ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_barang`
--
ALTER TABLE `tbl_barang`
  ADD PRIMARY KEY (`kode_barang`);

--
-- Indexes for table `tbl_data`
--
ALTER TABLE `tbl_data`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `tbl_pelanggan`
--
ALTER TABLE `tbl_pelanggan`
  ADD PRIMARY KEY (`kode_pelanggan`);

--
-- Indexes for table `tbl_transaksi`
--
ALTER TABLE `tbl_transaksi`
  ADD PRIMARY KEY (`id_transaksi`);

--
-- Indexes for table `tbl_ulasan`
--
ALTER TABLE `tbl_ulasan`
  ADD PRIMARY KEY (`id_ulasan`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_transaksi`
--
ALTER TABLE `tbl_transaksi`
  MODIFY `id_transaksi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `tbl_ulasan`
--
ALTER TABLE `tbl_ulasan`
  MODIFY `id_ulasan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
