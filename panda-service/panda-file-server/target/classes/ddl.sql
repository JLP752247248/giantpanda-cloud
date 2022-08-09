CREATE TABLE `tb_file_info` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(32) NOT NULL,
  `file_type` tinyint(1) NOT NULL DEFAULT '0',
  `file_uuid` varchar(36) NOT NULL,
  `directory` varchar(36) NOT NULL DEFAULT '',
  `md5` varchar(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_file_uuid` (`file_uuid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;