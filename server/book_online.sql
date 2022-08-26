-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th8 26, 2022 lúc 11:43 AM
-- Phiên bản máy phục vụ: 10.4.22-MariaDB
-- Phiên bản PHP: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `book_online`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `books`
--

CREATE TABLE `books` (
  `pid` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `author` varchar(50) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `price` decimal(10,0) NOT NULL,
  `description` text COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `linkAvatar` text COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `books`
--

INSERT INTO `books` (`pid`, `name`, `author`, `price`, `description`, `linkAvatar`, `created_at`, `updated_at`) VALUES
(1, 'DR. STONE - TẬP 20', 'Riichiro Inagaki, Boichi', '22500', 'Senku và Dr. Xeno, hai nhà khoa học mạnh nhất thế giới đá đã hợp tác để xác định nguồn phát sinh của sự kiện hóa đá nhân loại!', 'https://product.hstatic.net/200000343865/product/20_8f4f3e7a587045409179dd3816936ac9_large.jpg', '2022-07-27 01:47:48', '2022-07-27 01:53:39'),
(2, 'CONAN - TẬP 29', 'Gosho Aoyama', '1000', 'Nghe Hattori rủ, Conan tới Osaka... Lí do chính là vì cầu thủ bóng đã yêu thích của cậu - Ray Curtis sẽ có mặt ở đó!! Tuy nhiên, án mạng diễn ra ở khách sạn nơi Ray ở?! Lại một vụ án có kết thúc buồn...', 'https://product.hstatic.net/200000343865/product/29_752726e6bd184b24bfb2723e90934ce4_large.jpg', '2022-07-27 01:49:54', '2022-08-14 02:47:21'),
(3, 'ONG BÉO VÀ ONG GẦY', 'Uông Triều', '10000', 'Tôi viết cuốn sách này để tặng con gái tôi và những ai yêu thiên nhiên, yêu những sinh vật nhỏ bé và cuộc sống tự do của cỏ cây, bầu trời...” - Uông Triều', 'https://product.hstatic.net/200000343865/product/ong-beo-ong-gay_68bcbb3b9cbf486ea9935d5461c8ffdf_large.jpg', '2022-07-27 01:55:07', '2022-08-15 16:51:09');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `password_reset_request`
--

CREATE TABLE `password_reset_request` (
  `sno` int(11) NOT NULL,
  `email` varchar(50) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `encrypted_temp_password` varchar(256) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `salt` varchar(10) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `created_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `sno` int(11) NOT NULL,
  `unique_id` varchar(23) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `name` varchar(50) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `email` varchar(50) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `encrypted_password` varchar(256) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `salt` varchar(10) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `created_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`sno`, `unique_id`, `name`, `email`, `encrypted_password`, `salt`, `created_at`) VALUES
(1, '62ec7839918fd4.63763119', 'Tấn Sang', 'sang@gmail.com', '$2y$10$ENAWgSIgw74XU25EK/SIQu6u4gHYcdt9NBD99bRUd22IUPO84oBtG', '543f2a4e74', '2022-08-05 08:54:01'),
(2, '62ec7c5be34927.57157507', 'Nam', 'nam@gmail.com', '$2y$10$DuFmf6abv28h6LdRIEn4cuMGeq88CYzcNbDjohWTXknH0Vk73MB4C', 'af1d91fd80', '2022-08-05 09:11:40');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`pid`);

--
-- Chỉ mục cho bảng `password_reset_request`
--
ALTER TABLE `password_reset_request`
  ADD PRIMARY KEY (`sno`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`sno`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `books`
--
ALTER TABLE `books`
  MODIFY `pid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `password_reset_request`
--
ALTER TABLE `password_reset_request`
  MODIFY `sno` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
  MODIFY `sno` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
