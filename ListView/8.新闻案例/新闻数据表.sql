-- phpMyAdmin SQL Dump
-- http://www.phpmyadmin.net
--
-- 生成日期: 2015 年 08 月 08 日 23:15

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 数据库: `ahaUomrdcFSIMZUucMBS`
--

-- --------------------------------------------------------


--
-- 表的结构 `news`
--

CREATE TABLE IF NOT EXISTS `news` (
  `id` int(11) NOT NULL,
  `title` varchar(300) NOT NULL,
  `des` varchar(300) NOT NULL,
  `icon_url` varchar(200) NOT NULL,
  `news_url` varchar(200) NOT NULL,
  `type` int(20) NOT NULL,
  `comment` int(20) NOT NULL,
  `time` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `id_2` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `news`
--

INSERT INTO `news` (`id`, `title`, `des`, `icon_url`, `news_url`, `type`, `comment`, `time`) VALUES
(0, '刘强东与奶茶妹妹领证结婚 有图为证', '凤凰科技讯 8月8日消息，今天有网友爆料，京东创始人兼CEO刘强东已与奶茶妹妹章泽天在朝阳区民政局领证结婚。', 'http://d.ifengimg.com/mw604/y3.ifengimg.com/ifengimcp/pic/20150808/ce1b80056cfc584fafbf_size20_w450_h800.jpg', 'http://i.ifeng.com/news/sharenews.f?aid=100435430', 2, 3000, ''),
(1, '苹果或于9月9日发布iPhone6s 火狐爆严重漏洞', '苹果iPhone6s发布的具体时间越传越近了，但至今还没有官方的准信儿。今天还是让我们关注一下火狐漏洞吧。火狐浏览器开发商Mozilla提醒用户立即升级到最新版本，最好还要修改密码和登录信息。', 'http://img.jiemian.com/101/original/20150808/143899303035536900_a580x330.jpg', 'http://m.jiemian.com/article/347958.html', 3, 1200, ''),
(2, '升还是不升：Win7、Win10全面对比评测', '7月29日，历经9个月数百万人内测完善之后，微软终于发布Win10正式版系统。但是可能对于部分用户而言，Win7仍然是绝对的经典、游戏玩家的不二之选，为何非要升级到Win10系统呢？Windows10性能和功能相比Windows7，有提升吗？下面IT之家就为大家带来Win7与Win10功能与性能的正面PK，相信还在犹豫不决的用户看完本文心里就会有了答案。', 'http://p2.pstatp.com/large/6850/6105376239', 'http://toutiao.com/a5229867988/', 1, 5000, '');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
