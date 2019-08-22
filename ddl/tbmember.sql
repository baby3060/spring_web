CREATE TABLE `tbmember` (
  `email` varchar(100) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `login_type` varchar(1) DEFAULT NULL,
  `allow_mail` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Member 용 테이블'