DROP DATABASE IF EXISTS nacos;
CREATE DATABASE nacos DEFAULT CHARSET utf8mb4;
USE nacos;

DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `data_id`      varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
    `group_id`     varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
    `content`      longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
    `md5`          varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
    `gmt_create`   datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'åˆ›å»ºæ—¶é—´',
    `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'ä¿®æ”¹æ—¶é—´',
    `src_user`     text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
    `src_ip`       varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
    `app_name`     varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
    `tenant_id`    varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'ç§Ÿæˆ·å­—æ®µ',
    `c_desc`       varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
    `c_use`        varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
    `effect`       varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
    `type`         varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
    `c_schema`     text CHARACTER SET utf8 COLLATE utf8_bin NULL,
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uk_configinfo_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info' ROW_FORMAT = Dynamic;

INSERT INTO `config_info`
VALUES (1, 'gateway', 'DEFAULT_GROUP',
        'server:\n  port: 10081\nspring:\n  cloud:\n    gateway:\n      routes:\n        - id: auth-server\n          uri: lb://auth-server\n          predicates:\n            - Path=/auth-server/**\n      default-filters:\n        - name: RequestRateLimiter\n          args:\n            key-resolver: \'#{@remoteAddressKeyResolver}\'\n            redis-rate-limiter.replenishRate: 1\n            redis-rate-limiter.burstCapacity: 5\n',
        '10a2e75d69fda475d7bdb58e46beeb85', '2021-06-11 22:04:18', '2021-06-11 22:30:53', NULL, '172.18.0.1', '',
        'da4e6fb4-6fc9-45ec-9a62-9d6076742077', '', '', '', 'yaml', '');
INSERT INTO `config_info`
VALUES (4, 'application', 'DEFAULT_GROUP',
        'server:\n  servlet:\n    context-path: /${spring.application.name}\nspring:\n  redis:\n    database: 0\n    host: ${REDIS_HOST:localhost}\n    port: ${REDIS_PORT:6379}\n    password: ${REDIS_PASSWORD:123456}\n    timeout: 3000\n    jedis:\n      pool:\n        max-active: -1\n        max-wait: -1\n        max-idle: 8\n        min-idle: 3',
        '11796044e7be74d75e2eb5c6baec10ef', '2021-06-11 22:31:10', '2021-06-15 02:03:48', NULL, '172.18.0.1', '',
        'da4e6fb4-6fc9-45ec-9a62-9d6076742077', '', '', '', 'yaml', '');
INSERT INTO `config_info`
VALUES (6, 'gateway', 'DEFAULT_GROUP',
        'spring:\n  cloud:\n    gateway:\n      routes:\n        - id: auth-server\n          uri: lb://auth-server\n          predicates:\n            - Path=/auth-server/**\n      default-filters:\n        - name: RequestRateLimiter\n          args:\n            key-resolver: \'#{@remoteAddressKeyResolver}\'\n            redis-rate-limiter.replenishRate: 1\n            redis-rate-limiter.burstCapacity: 5\n',
        'e2a845594ac4488b62f360e3549d1c01', '2021-06-11 22:35:57', '2021-06-11 22:36:21', NULL, '172.18.0.1', '',
        '14413489-79ed-4232-b7c6-d7e9928517cc', '', '', '', 'yaml', '');
INSERT INTO `config_info`
VALUES (7, 'application', 'DEFAULT_GROUP',
        'spring:\n  redis:\n    database: 0\n    host: ${REDIS_HOST:localhost}\n    port: ${REDIS_PORT:6379}\n    password: ${REDIS_PASSWORD:123456}\n    timeout: 3000\n    jedis:\n      pool:\n        max-active: -1\n        max-wait: -1\n        max-idle: 8\n        min-idle: 3\n',
        'da5a6e1c007eed2dad7554a774ea4c37', '2021-06-11 22:35:57', '2021-06-15 02:05:14', NULL, '172.18.0.1', '',
        '14413489-79ed-4232-b7c6-d7e9928517cc', '', '', '', 'yaml', '');
INSERT INTO `config_info`
VALUES (13, 'auth-server', 'DEFAULT_GROUP',
        'server:\n  port: 8081\nspring:\n  datasource:\n    driver-class-name: com.mysql.jdbc.Driver\n    url: jdbc:mysql://localhost:3306/auth_center?useUnicode=true&zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=utf-8&useSSL=false\n    username: root\n    password: root123',
        '9fa599cbdd66e2f0bed3f8b04e59c98c', '2021-06-12 05:00:27', '2021-06-15 02:03:58', NULL, '172.18.0.1', '',
        'da4e6fb4-6fc9-45ec-9a62-9d6076742077', '', '', '', 'yaml', '');
INSERT INTO `config_info`
VALUES (14, 'auth-server', 'DEFAULT_GROUP',
        'spring:\n  datasource:\n    driver-class-name: com.mysql.jdbc.Driver\n    username: root\n    password: ${MYSQL_ROOT_PASSWORD}      \n    url: jdbc:mysql://mysql:3306/auth_center?useUnicode=true&zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=utf-8&useSSL=false',
        'd5855a9a77f89b2f77b882285378dc9c', '2021-06-12 12:38:09', '2021-06-15 02:06:25', NULL, '172.18.0.1', '',
        '14413489-79ed-4232-b7c6-d7e9928517cc', '', '', '', 'yaml', '');

DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `data_id`      varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
    `group_id`     varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
    `datum_id`     varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
    `content`      longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'å†…å®¹',
    `gmt_modified` datetime(0) NOT NULL COMMENT 'ä¿®æ”¹æ—¶é—´',
    `app_name`     varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
    `tenant_id`    varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'ç§Ÿæˆ·å­—æ®µ',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uk_configinfoaggr_datagrouptenantdatum`(`data_id`, `group_id`, `tenant_id`, `datum_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'å¢žåŠ ç§Ÿæˆ·å­—æ®µ' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `data_id`      varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
    `group_id`     varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
    `app_name`     varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
    `content`      longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
    `beta_ips`     varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'betaIps',
    `md5`          varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
    `gmt_create`   datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'åˆ›å»ºæ—¶é—´',
    `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'ä¿®æ”¹æ—¶é—´',
    `src_user`     text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
    `src_ip`       varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
    `tenant_id`    varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'ç§Ÿæˆ·å­—æ®µ',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uk_configinfobeta_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_beta' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `data_id`      varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
    `group_id`     varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
    `tenant_id`    varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
    `tag_id`       varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
    `app_name`     varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
    `content`      longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
    `md5`          varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
    `gmt_create`   datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'åˆ›å»ºæ—¶é—´',
    `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'ä¿®æ”¹æ—¶é—´',
    `src_user`     text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
    `src_ip`       varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uk_configinfotag_datagrouptenanttag`(`data_id`, `group_id`, `tenant_id`, `tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_tag' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation`
(
    `id`        bigint(20) NOT NULL COMMENT 'id',
    `tag_name`  varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
    `tag_type`  varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tag_type',
    `data_id`   varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
    `group_id`  varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
    `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
    `nid`       bigint(20) NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (`nid`) USING BTREE,
    UNIQUE INDEX `uk_configtagrelation_configidtag`(`id`, `tag_name`, `tag_type`) USING BTREE,
    INDEX       `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_tag_relation' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity`
(
    `id`                bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ä¸»é”®ID',
    `group_id`          varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group IDï¼Œç©ºå­—ç¬¦è¡¨ç¤ºæ•´ä¸ªé›†ç¾¤',
    `quota`             int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'é…é¢ï¼Œ0è¡¨ç¤ºä½¿ç”¨é»˜è®¤å€¼',
    `usage`             int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'ä½¿ç”¨é‡',
    `max_size`          int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'å•ä¸ªé…ç½®å¤§å°ä¸Šé™ï¼Œå•ä½ä¸ºå­—èŠ‚ï¼Œ0è¡¨ç¤ºä½¿ç”¨é»˜è®¤å€¼',
    `max_aggr_count`    int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'èšåˆå­é…ç½®æœ€å¤§ä¸ªæ•°ï¼Œï¼Œ0è¡¨ç¤ºä½¿ç”¨é»˜è®¤å€¼',
    `max_aggr_size`     int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'å•ä¸ªèšåˆæ•°æ®çš„å­é…ç½®å¤§å°ä¸Šé™ï¼Œå•ä½ä¸ºå­—èŠ‚ï¼Œ0è¡¨ç¤ºä½¿ç”¨é»˜è®¤å€¼',
    `max_history_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'æœ€å¤§å˜æ›´åŽ†å²æ•°é‡',
    `gmt_create`        datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'åˆ›å»ºæ—¶é—´',
    `gmt_modified`      datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'ä¿®æ”¹æ—¶é—´',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uk_group_id`(`group_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'é›†ç¾¤ã€å„Groupå®¹é‡ä¿¡æ¯è¡¨' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info`
(
    `id`           bigint(64) UNSIGNED NOT NULL,
    `nid`          bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `data_id`      varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    `group_id`     varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    `app_name`     varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
    `content`      longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    `md5`          varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
    `gmt_create`   datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `src_user`     text CHARACTER SET utf8 COLLATE utf8_bin NULL,
    `src_ip`       varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
    `op_type`      char(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
    `tenant_id`    varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'ç§Ÿæˆ·å­—æ®µ',
    PRIMARY KEY (`nid`) USING BTREE,
    INDEX          `idx_gmt_create`(`gmt_create`) USING BTREE,
    INDEX          `idx_gmt_modified`(`gmt_modified`) USING BTREE,
    INDEX          `idx_did`(`data_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'å¤šç§Ÿæˆ·æ”¹é€ ' ROW_FORMAT = Dynamic;

INSERT INTO `his_config_info`
VALUES (0, 1, 'gateway', 'DEFAULT_GROUP', '',
        'spring:\r\n  redis:\r\n    database: 0\r\n    host: ${REDIS_HOST:localhost}\r\n    port: ${REDIS_PORT:6379}\r\n    password: ${REDIS_PASSWORD:123456}\r\n    timeout: 3000\r\n    jedis:\r\n      pool:\r\n        max-active: -1\r\n        max-wait: -1\r\n        max-idle: 8\r\n        min-idle: 3\r\n  cloud:\r\n    gateway:\r\n      routes:\r\n        - id: auth-server\r\n          uri: lb://auth-server\r\n          predicates:\r\n            - Path=/auth-server/**\r\n      default-filters:\r\n        - name: RequestRateLimiter\r\n          args:\r\n            key-resolver: \'#{@remoteAddressKeyResolver}\'\r\n            redis-rate-limiter.replenishRate: 1\r\n            redis-rate-limiter.burstCapacity: 5\r\n',
        '925c2e9e886e985e837df5715b1fbbdd', '2021-06-12 11:04:18', '2021-06-11 22:04:18', NULL, '172.18.0.1', 'I',
        'da4e6fb4-6fc9-45ec-9a62-9d6076742077');
INSERT INTO `his_config_info`
VALUES (1, 2, 'gateway', 'DEFAULT_GROUP', '',
        'spring:\r\n  redis:\r\n    database: 0\r\n    host: ${REDIS_HOST:localhost}\r\n    port: ${REDIS_PORT:6379}\r\n    password: ${REDIS_PASSWORD:123456}\r\n    timeout: 3000\r\n    jedis:\r\n      pool:\r\n        max-active: -1\r\n        max-wait: -1\r\n        max-idle: 8\r\n        min-idle: 3\r\n  cloud:\r\n    gateway:\r\n      routes:\r\n        - id: auth-server\r\n          uri: lb://auth-server\r\n          predicates:\r\n            - Path=/auth-server/**\r\n      default-filters:\r\n        - name: RequestRateLimiter\r\n          args:\r\n            key-resolver: \'#{@remoteAddressKeyResolver}\'\r\n            redis-rate-limiter.replenishRate: 1\r\n            redis-rate-limiter.burstCapacity: 5\r\n',
        '925c2e9e886e985e837df5715b1fbbdd', '2021-06-12 11:06:09', '2021-06-11 22:06:09', NULL, '172.18.0.1', 'U',
        'da4e6fb4-6fc9-45ec-9a62-9d6076742077');
INSERT INTO `his_config_info`
VALUES (1, 3, 'gateway', 'DEFAULT_GROUP', '',
        'server:\n  port: 10081\nspring:\n  redis:\n    database: 0\n    host: ${REDIS_HOST:localhost}\n    port: ${REDIS_PORT:6379}\n    password: ${REDIS_PASSWORD:123456}\n    timeout: 3000\n    jedis:\n      pool:\n        max-active: -1\n        max-wait: -1\n        max-idle: 8\n        min-idle: 3\n  cloud:\n    gateway:\n      routes:\n        - id: auth-server\n          uri: lb://auth-server\n          predicates:\n            - Path=/auth-server/**\n      default-filters:\n        - name: RequestRateLimiter\n          args:\n            key-resolver: \'#{@remoteAddressKeyResolver}\'\n            redis-rate-limiter.replenishRate: 1\n            redis-rate-limiter.burstCapacity: 5\n',
        '92d094fb002f4140d09c196256a108f7', '2021-06-12 11:30:53', '2021-06-11 22:30:53', NULL, '172.18.0.1', 'U',
        'da4e6fb4-6fc9-45ec-9a62-9d6076742077');
INSERT INTO `his_config_info`
VALUES (0, 4, 'application', 'DEFAULT_GROUP', '',
        'spring:\r\n  redis:\r\n    database: 0\r\n    host: ${REDIS_HOST:localhost}\r\n    port: ${REDIS_PORT:6379}\r\n    password: ${REDIS_PASSWORD:123456}\r\n    timeout: 3000\r\n    jedis:\r\n      pool:\r\n        max-active: -1\r\n        max-wait: -1\r\n        max-idle: 8\r\n        min-idle: 3',
        '10c9d0f7fca57a5b00fb6c9c243052a1', '2021-06-12 11:31:09', '2021-06-11 22:31:10', NULL, '172.18.0.1', 'I',
        'da4e6fb4-6fc9-45ec-9a62-9d6076742077');
INSERT INTO `his_config_info`
VALUES (4, 5, 'application', 'DEFAULT_GROUP', '',
        'spring:\r\n  redis:\r\n    database: 0\r\n    host: ${REDIS_HOST:localhost}\r\n    port: ${REDIS_PORT:6379}\r\n    password: ${REDIS_PASSWORD:123456}\r\n    timeout: 3000\r\n    jedis:\r\n      pool:\r\n        max-active: -1\r\n        max-wait: -1\r\n        max-idle: 8\r\n        min-idle: 3',
        '10c9d0f7fca57a5b00fb6c9c243052a1', '2021-06-12 11:33:36', '2021-06-11 22:33:37', NULL, '172.18.0.1', 'U',
        'da4e6fb4-6fc9-45ec-9a62-9d6076742077');
INSERT INTO `his_config_info`
VALUES (0, 6, 'gateway', 'DEFAULT_GROUP', '',
        'server:\n  port: 10081\nspring:\n  cloud:\n    gateway:\n      routes:\n        - id: auth-server\n          uri: lb://auth-server\n          predicates:\n            - Path=/auth-server/**\n      default-filters:\n        - name: RequestRateLimiter\n          args:\n            key-resolver: \'#{@remoteAddressKeyResolver}\'\n            redis-rate-limiter.replenishRate: 1\n            redis-rate-limiter.burstCapacity: 5\n',
        '10a2e75d69fda475d7bdb58e46beeb85', '2021-06-12 11:35:56', '2021-06-11 22:35:57', NULL, '172.18.0.1', 'I',
        '14413489-79ed-4232-b7c6-d7e9928517cc');
INSERT INTO `his_config_info`
VALUES (0, 7, 'application', 'DEFAULT_GROUP', '',
        'server:\n  servlet:\n    context-path: /${spring.application.name}\nspring:\n  redis:\n    database: 0\n    host: ${REDIS_HOST:localhost}\n    port: ${REDIS_PORT:6379}\n    password: ${REDIS_PASSWORD:123456}\n    timeout: 3000\n    jedis:\n      pool:\n        max-active: -1\n        max-wait: -1\n        max-idle: 8\n        min-idle: 3',
        '11796044e7be74d75e2eb5c6baec10ef', '2021-06-12 11:35:56', '2021-06-11 22:35:57', NULL, '172.18.0.1', 'I',
        '14413489-79ed-4232-b7c6-d7e9928517cc');
INSERT INTO `his_config_info`
VALUES (6, 8, 'gateway', 'DEFAULT_GROUP', '',
        'server:\n  port: 10081\nspring:\n  cloud:\n    gateway:\n      routes:\n        - id: auth-server\n          uri: lb://auth-server\n          predicates:\n            - Path=/auth-server/**\n      default-filters:\n        - name: RequestRateLimiter\n          args:\n            key-resolver: \'#{@remoteAddressKeyResolver}\'\n            redis-rate-limiter.replenishRate: 1\n            redis-rate-limiter.burstCapacity: 5\n',
        '10a2e75d69fda475d7bdb58e46beeb85', '2021-06-12 11:36:21', '2021-06-11 22:36:21', NULL, '172.18.0.1', 'U',
        '14413489-79ed-4232-b7c6-d7e9928517cc');
INSERT INTO `his_config_info`
VALUES (7, 9, 'application', 'DEFAULT_GROUP', '',
        'server:\n  servlet:\n    context-path: /${spring.application.name}\nspring:\n  redis:\n    database: 0\n    host: ${REDIS_HOST:localhost}\n    port: ${REDIS_PORT:6379}\n    password: ${REDIS_PASSWORD:123456}\n    timeout: 3000\n    jedis:\n      pool:\n        max-active: -1\n        max-wait: -1\n        max-idle: 8\n        min-idle: 3',
        '11796044e7be74d75e2eb5c6baec10ef', '2021-06-12 11:41:00', '2021-06-11 22:41:01', NULL, '172.18.0.1', 'U',
        '14413489-79ed-4232-b7c6-d7e9928517cc');
INSERT INTO `his_config_info`
VALUES (0, 10, 'auth-server', 'DEFAULT_GROUP', '',
        'spring:\r\n  datasource:\r\n    name: druidDataSource\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    druid:\r\n      driver-class-name: com.mysql.cj.jdbc.Driver\r\n      url: jdbc:mysql://localhost:3306/auth_center?useUnicode=true&zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=utf-8\r\n      username: root\r\n      password: root123\r\n      filters: stat,wall,log4j,config\r\n      max-active: 100\r\n      initial-size: 1\r\n      max-wait: 60000\r\n      min-idle: 1\r\n      time-between-eviction-runs-millis: 60000\r\n      min-evictable-idle-time-millis: 300000\r\n      validation-query: select \'x\'\r\n      test-while-idle: true\r\n      test-on-borrow: false\r\n      test-on-return: false\r\n      pool-prepared-statements: true\r\n      max-open-prepared-statements: 50\r\n      max-pool-prepared-statement-per-connection-size: 20',
        '5a64ed972e8752edf21030c3e5d08700', '2021-06-12 17:32:26', '2021-06-12 04:32:26', NULL, '172.18.0.1', 'I',
        'da4e6fb4-6fc9-45ec-9a62-9d6076742077');
INSERT INTO `his_config_info`
VALUES (8, 11, 'auth-server', 'DEFAULT_GROUP', '',
        'spring:\r\n  datasource:\r\n    name: druidDataSource\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    druid:\r\n      driver-class-name: com.mysql.cj.jdbc.Driver\r\n      url: jdbc:mysql://localhost:3306/auth_center?useUnicode=true&zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=utf-8\r\n      username: root\r\n      password: root123\r\n      filters: stat,wall,log4j,config\r\n      max-active: 100\r\n      initial-size: 1\r\n      max-wait: 60000\r\n      min-idle: 1\r\n      time-between-eviction-runs-millis: 60000\r\n      min-evictable-idle-time-millis: 300000\r\n      validation-query: select \'x\'\r\n      test-while-idle: true\r\n      test-on-borrow: false\r\n      test-on-return: false\r\n      pool-prepared-statements: true\r\n      max-open-prepared-statements: 50\r\n      max-pool-prepared-statement-per-connection-size: 20',
        '5a64ed972e8752edf21030c3e5d08700', '2021-06-12 17:36:10', '2021-06-12 04:36:10', NULL, '172.18.0.1', 'U',
        'da4e6fb4-6fc9-45ec-9a62-9d6076742077');
INSERT INTO `his_config_info`
VALUES (8, 12, 'auth-server', 'DEFAULT_GROUP', '',
        'spring:\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      driver-class-name: com.mysql.cj.jdbc.Driver\n      url: jdbc:mysql://localhost:3306/auth_center?useUnicode=true&zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=utf-8\n      username: root\n      password: root123\n      filters: stat,wall,log4j,config\n      max-active: 100\n      initial-size: 1\n      max-wait: 60000\n      min-idle: 1\n      time-between-eviction-runs-millis: 60000\n      min-evictable-idle-time-millis: 300000\n      validation-query: select \'x\'\n      test-while-idle: true\n      test-on-borrow: false\n      test-on-return: false\n      pool-prepared-statements: true\n      max-open-prepared-statements: 50\n      max-pool-prepared-statement-per-connection-size: 20',
        '8b36cf99c3e53284314e6928b65d6340', '2021-06-12 17:43:54', '2021-06-12 04:43:54', NULL, '172.18.0.1', 'U',
        'da4e6fb4-6fc9-45ec-9a62-9d6076742077');
INSERT INTO `his_config_info`
VALUES (8, 13, 'auth-server', 'DEFAULT_GROUP', '',
        'server:\n  port: 8081\nspring:\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      driver-class-name: com.mysql.cj.jdbc.Driver\n      url: jdbc:mysql://localhost:3306/auth_center?useUnicode=true&zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=utf-8\n      username: root\n      password: root123\n      filters: stat,wall,log4j,config\n      max-active: 100\n      initial-size: 1\n      max-wait: 60000\n      min-idle: 1\n      time-between-eviction-runs-millis: 60000\n      min-evictable-idle-time-millis: 300000\n      validation-query: select \'x\'\n      test-while-idle: true\n      test-on-borrow: false\n      test-on-return: false\n      pool-prepared-statements: true\n      max-open-prepared-statements: 50\n      max-pool-prepared-statement-per-connection-size: 20',
        'adb0a1d491e4de41d3a2b4e3c7abdc2c', '2021-06-12 17:49:30', '2021-06-12 04:49:30', NULL, '172.18.0.1', 'U',
        'da4e6fb4-6fc9-45ec-9a62-9d6076742077');
INSERT INTO `his_config_info`
VALUES (8, 14, 'auth-server', 'DEFAULT_GROUP', '',
        'server:\n  port: 8081\nspring:\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://localhost:3306/auth_center?useUnicode=true&zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=utf-8\n    username: root\n    password: root123\n    druid:\n      filters: stat,wall,log4j,config\n      max-active: 100\n      initial-size: 1\n      max-wait: 60000\n      min-idle: 1\n      time-between-eviction-runs-millis: 60000\n      min-evictable-idle-time-millis: 300000\n      validation-query: select \'x\'\n      test-while-idle: true\n      test-on-borrow: false\n      test-on-return: false\n      pool-prepared-statements: true\n      max-open-prepared-statements: 50\n      max-pool-prepared-statement-per-connection-size: 20',
        'e5ccb88f4d76cf706b9df9c1e95d1438', '2021-06-12 17:56:28', '2021-06-12 04:56:28', NULL, '172.18.0.1', 'U',
        'da4e6fb4-6fc9-45ec-9a62-9d6076742077');
INSERT INTO `his_config_info`
VALUES (8, 15, 'auth-server', 'DEFAULT_GROUP', '',
        'server:\n  port: 8081\nspring:\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://localhost:3306/auth_center?useUnicode=true&zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=utf-8\n    username: root\n    password: root123',
        '928f79783a340c73ffa1c4d19a83f135', '2021-06-12 17:58:08', '2021-06-12 04:58:08', NULL, '172.18.0.1', 'D',
        'da4e6fb4-6fc9-45ec-9a62-9d6076742077');
INSERT INTO `his_config_info`
VALUES (0, 16, 'auth-server', 'DEFAULT_GROUP', '',
        'server:\r\n  port: 8081\r\nspring:\r\n  datasource:\r\n    driver-class-name: com.mysql.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/auth_center?useUnicode=true&zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=utf-8&useSSL=false\r\n    username: root\r\n    password: root123',
        'e568455e3692d9d6f9cbb03648aff192', '2021-06-12 18:00:27', '2021-06-12 05:00:27', NULL, '172.18.0.1', 'I',
        'da4e6fb4-6fc9-45ec-9a62-9d6076742077');
INSERT INTO `his_config_info`
VALUES (0, 17, 'auth-server', 'DEFAULT_GROUP', '',
        'server:\r\n  port: 8081\r\nspring:\r\n  datasource:\r\n    driver-class-name: com.mysql.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/auth_center?useUnicode=true&zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=utf-8&useSSL=false\r\n    username: root\r\n    password: root123',
        'e568455e3692d9d6f9cbb03648aff192', '2021-06-13 01:38:09', '2021-06-12 12:38:09', NULL, '172.18.0.1', 'I',
        '14413489-79ed-4232-b7c6-d7e9928517cc');
INSERT INTO `his_config_info`
VALUES (14, 18, 'auth-server', 'DEFAULT_GROUP', '',
        'server:\r\n  port: 8081\r\nspring:\r\n  datasource:\r\n    driver-class-name: com.mysql.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/auth_center?useUnicode=true&zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=utf-8&useSSL=false\r\n    username: root\r\n    password: root123',
        'e568455e3692d9d6f9cbb03648aff192', '2021-06-13 01:39:23', '2021-06-12 12:39:23', NULL, '172.18.0.1', 'U',
        '14413489-79ed-4232-b7c6-d7e9928517cc');
INSERT INTO `his_config_info`
VALUES (7, 19, 'application', 'DEFAULT_GROUP', '',
        'spring:\n  redis:\n    database: 0\n    host: ${REDIS_HOST:localhost}\n    port: ${REDIS_PORT:6379}\n    password: ${REDIS_PASSWORD:123456}\n    timeout: 3000\n    jedis:\n      pool:\n        max-active: -1\n        max-wait: -1\n        max-idle: 8\n        min-idle: 3',
        'dfbcc4a4a579ca4199e18eb37c9195e0', '2021-06-13 02:09:13', '2021-06-12 13:09:14', NULL, '172.18.0.1', 'U',
        '14413489-79ed-4232-b7c6-d7e9928517cc');
INSERT INTO `his_config_info`
VALUES (14, 20, 'auth-server', 'DEFAULT_GROUP', '',
        'spring:\n  datasource:\n    driver-class-name: com.mysql.jdbc.Driver\n    url: jdbc:mysql://mysql:3306/auth_center?useUnicode=true&zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=utf-8&useSSL=false\n    username: root\n    password: ${MYSQL_ROOT_PASSWORD}',
        '4a5b1aef498f3ade4463c0b5e653d457', '2021-06-13 02:09:38', '2021-06-12 13:09:39', NULL, '172.18.0.1', 'U',
        '14413489-79ed-4232-b7c6-d7e9928517cc');
INSERT INTO `his_config_info`
VALUES (13, 21, 'auth-server', 'DEFAULT_GROUP', '',
        'server:\r\n  port: 8081\r\nspring:\r\n  datasource:\r\n    driver-class-name: com.mysql.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/auth_center?useUnicode=true&zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=utf-8&useSSL=false\r\n    username: root\r\n    password: root123',
        'e568455e3692d9d6f9cbb03648aff192', '2021-06-15 14:56:42', '2021-06-15 01:56:42', NULL, '172.18.0.1', 'U',
        'da4e6fb4-6fc9-45ec-9a62-9d6076742077');
INSERT INTO `his_config_info`
VALUES (4, 22, 'application', 'DEFAULT_GROUP', '',
        'server:\n  servlet:\n    context-path: /${spring.application.name}\nspring:\n  redis:\n    database: 0\n    host: ${REDIS_HOST:localhost}\n    port: ${REDIS_PORT:6379}\n    password: ${REDIS_PASSWORD:123456}\n    timeout: 3000\n    jedis:\n      pool:\n        max-active: -1\n        max-wait: -1\n        max-idle: 8\n        min-idle: 3',
        '11796044e7be74d75e2eb5c6baec10ef', '2021-06-15 14:57:11', '2021-06-15 01:57:12', NULL, '172.18.0.1', 'U',
        'da4e6fb4-6fc9-45ec-9a62-9d6076742077');
INSERT INTO `his_config_info`
VALUES (13, 23, 'auth-server', 'DEFAULT_GROUP', '',
        'server:\n  port: 8081\nspring:\n  datasource:\n    url: jdbc:mysql://localhost:3306/auth_center?useUnicode=true&zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=utf-8&useSSL=false\n    password: root123',
        '8bc3d64e88fc1544ef33d920e06e24d7', '2021-06-15 14:57:53', '2021-06-15 01:57:54', NULL, '172.18.0.1', 'U',
        'da4e6fb4-6fc9-45ec-9a62-9d6076742077');
INSERT INTO `his_config_info`
VALUES (4, 24, 'application', 'DEFAULT_GROUP', '',
        'server:\n  servlet:\n    context-path: /${spring.application.name}\nspring:\n  redis:\n    database: 0\n    host: ${REDIS_HOST:localhost}\n    port: ${REDIS_PORT:6379}\n    password: ${REDIS_PASSWORD:123456}\n    timeout: 3000\n    jedis:\n      pool:\n        max-active: -1\n        max-wait: -1\n        max-idle: 8\n        min-idle: 3\nspring:\n  datasource:\n    driver-class-name: com.mysql.jdbc.Driver\n    username: root\n    password: root',
        '1d29414731a41a14c09cefbe2e03e1af', '2021-06-15 14:58:50', '2021-06-15 01:58:51', NULL, '172.18.0.1', 'U',
        'da4e6fb4-6fc9-45ec-9a62-9d6076742077');
INSERT INTO `his_config_info`
VALUES (13, 25, 'auth-server', 'DEFAULT_GROUP', '',
        'server:\n  port: 8081\nspring:\n  datasource:\n    url: jdbc:mysql://localhost:3306/auth_center?useUnicode=true&zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=utf-8&useSSL=false',
        '10b8c0c492516f136139ff14d47f121a', '2021-06-15 14:59:03', '2021-06-15 01:59:03', NULL, '172.18.0.1', 'U',
        'da4e6fb4-6fc9-45ec-9a62-9d6076742077');
INSERT INTO `his_config_info`
VALUES (4, 26, 'application', 'DEFAULT_GROUP', '',
        'server:\n  servlet:\n    context-path: /${spring.application.name}\nspring:\n  redis:\n    database: 0\n    host: ${REDIS_HOST:localhost}\n    port: ${REDIS_PORT:6379}\n    password: ${REDIS_PASSWORD:123456}\n    timeout: 3000\n    jedis:\n      pool:\n        max-active: -1\n        max-wait: -1\n        max-idle: 8\n        min-idle: 3\nspring:\n  datasource:\n    username: root\n    password: root',
        '77bb866d328fab6106a2ca4965006397', '2021-06-15 15:00:45', '2021-06-15 02:00:46', NULL, '172.18.0.1', 'U',
        'da4e6fb4-6fc9-45ec-9a62-9d6076742077');
INSERT INTO `his_config_info`
VALUES (13, 27, 'auth-server', 'DEFAULT_GROUP', '',
        'server:\n  port: 8081\nspring:\n  datasource:\n    driver-class-name: com.mysql.jdbc.Driver\n    url: jdbc:mysql://localhost:3306/auth_center?useUnicode=true&zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=utf-8&useSSL=false',
        '11a85f5bf5aa174f894516487ae6a839', '2021-06-15 15:00:59', '2021-06-15 02:00:59', NULL, '172.18.0.1', 'U',
        'da4e6fb4-6fc9-45ec-9a62-9d6076742077');
INSERT INTO `his_config_info`
VALUES (13, 28, 'auth-server', 'DEFAULT_GROUP', '',
        'server:\n  port: 8081\nspring:\n  datasource:\n    driver-class-name: com.mysql.jdbc.Driver\n    url: jdbc:mysql://localhost:3306/auth_center?useUnicode=true&zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=utf-8&useSSL=false\n    username: root\n    password: root',
        'c582659858e1460e3909214df8760dc0', '2021-06-15 15:02:10', '2021-06-15 02:02:10', NULL, '172.18.0.1', 'U',
        'da4e6fb4-6fc9-45ec-9a62-9d6076742077');
INSERT INTO `his_config_info`
VALUES (4, 29, 'application', 'DEFAULT_GROUP', '',
        'server:\n  servlet:\n    context-path: /${spring.application.name}\nspring:\n  redis:\n    database: 0\n    host: ${REDIS_HOST:localhost}\n    port: ${REDIS_PORT:6379}\n    password: ${REDIS_PASSWORD:123456}\n    timeout: 3000\n    jedis:\n      pool:\n        max-active: -1\n        max-wait: -1\n        max-idle: 8\n        min-idle: 3\n',
        '56568d48268ea5cc81c0587cfbf08819', '2021-06-15 15:02:33', '2021-06-15 02:02:34', NULL, '172.18.0.1', 'U',
        'da4e6fb4-6fc9-45ec-9a62-9d6076742077');
INSERT INTO `his_config_info`
VALUES (4, 30, 'application', 'DEFAULT_GROUP', '',
        'server:\n  servlet:\n    context-path: /${spring.application.name}\nspring:\n  redis:\n    database: 0\n    host: ${REDIS_HOST:localhost}\n    port: ${REDIS_PORT:6379}\n    password: ${REDIS_PASSWORD:123456}\n    timeout: 3000\n    jedis:\n      pool:\n        max-active: -1\n        max-wait: -1\n        max-idle: 8\n        min-idle: 3\nspring:\n  datasource:\n    username: root\n    password: root123',
        '77fde2d52a6c55eba89522102383c186', '2021-06-15 15:03:47', '2021-06-15 02:03:48', NULL, '172.18.0.1', 'U',
        'da4e6fb4-6fc9-45ec-9a62-9d6076742077');
INSERT INTO `his_config_info`
VALUES (13, 31, 'auth-server', 'DEFAULT_GROUP', '',
        'server:\n  port: 8081\nspring:\n  datasource:\n    driver-class-name: com.mysql.jdbc.Driver\n    url: jdbc:mysql://localhost:3306/auth_center?useUnicode=true&zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=utf-8&useSSL=false',
        '11a85f5bf5aa174f894516487ae6a839', '2021-06-15 15:03:57', '2021-06-15 02:03:58', NULL, '172.18.0.1', 'U',
        'da4e6fb4-6fc9-45ec-9a62-9d6076742077');
INSERT INTO `his_config_info`
VALUES (7, 32, 'application', 'DEFAULT_GROUP', '',
        'spring:\n  redis:\n    database: 0\n    host: ${REDIS_HOST:localhost}\n    port: ${REDIS_PORT:6379}\n    password: ${REDIS_PASSWORD:123456}\n    timeout: 3000\n    jedis:\n      pool:\n        max-active: -1\n        max-wait: -1\n        max-idle: 8\n        min-idle: 3\n  datasource:\n    driver-class-name: com.mysql.jdbc.Driver\n    username: root\n    password: ${MYSQL_ROOT_PASSWORD}      ',
        'c5ba8cd81a68898f75705f7350ab1c0e', '2021-06-15 15:05:14', '2021-06-15 02:05:14', NULL, '172.18.0.1', 'U',
        '14413489-79ed-4232-b7c6-d7e9928517cc');
INSERT INTO `his_config_info`
VALUES (14, 33, 'auth-server', 'DEFAULT_GROUP', '',
        'spring:\n  datasource:\n    url: jdbc:mysql://mysql:3306/auth_center?useUnicode=true&zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=utf-8&useSSL=false',
        '1039d08077b76ea2e86648b289c1e360', '2021-06-15 15:05:29', '2021-06-15 02:05:29', NULL, '172.18.0.1', 'U',
        '14413489-79ed-4232-b7c6-d7e9928517cc');
INSERT INTO `his_config_info`
VALUES (14, 34, 'auth-server', 'DEFAULT_GROUP', '',
        'spring:\n  datasource:\n  datasource:\n    driver-class-name: com.mysql.jdbc.Driver\n    username: root\n    password: ${MYSQL_ROOT_PASSWORD}      \n    url: jdbc:mysql://mysql:3306/auth_center?useUnicode=true&zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=utf-8&useSSL=false',
        '999056ea1541ca737ac1e363ff16b6ae', '2021-06-15 15:05:35', '2021-06-15 02:05:36', NULL, '172.18.0.1', 'U',
        '14413489-79ed-4232-b7c6-d7e9928517cc');
INSERT INTO `his_config_info`
VALUES (14, 35, 'auth-server', 'DEFAULT_GROUP', '',
        'spring:\n  datasource:\n  datasource:\n    driver-class-name: com.mysql.jdbc.Driver\n    username: root\n    password: ${MYSQL_ROOT_PASSWORD}      \n    url: jdbc:mysql://mysql:3306/auth_center?useUnicode=true&zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=utf-8&useSSL=false',
        '999056ea1541ca737ac1e363ff16b6ae', '2021-06-15 15:06:25', '2021-06-15 02:06:25', NULL, '172.18.0.1', 'U',
        '14413489-79ed-4232-b7c6-d7e9928517cc');

DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions`
(
    `role`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL,
    `resource` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `action`   varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci   NOT NULL,
    UNIQUE INDEX `uk_role_permission`(`role`, `resource`, `action`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`
(
    `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `role`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    UNIQUE INDEX `idx_user_role`(`username`, `role`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

INSERT INTO `roles`
VALUES ('nacos', 'ROLE_ADMIN');

DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity`
(
    `id`                bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ä¸»é”®ID',
    `tenant_id`         varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
    `quota`             int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'é…é¢ï¼Œ0è¡¨ç¤ºä½¿ç”¨é»˜è®¤å€¼',
    `usage`             int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'ä½¿ç”¨é‡',
    `max_size`          int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'å•ä¸ªé…ç½®å¤§å°ä¸Šé™ï¼Œå•ä½ä¸ºå­—èŠ‚ï¼Œ0è¡¨ç¤ºä½¿ç”¨é»˜è®¤å€¼',
    `max_aggr_count`    int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'èšåˆå­é…ç½®æœ€å¤§ä¸ªæ•°',
    `max_aggr_size`     int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'å•ä¸ªèšåˆæ•°æ®çš„å­é…ç½®å¤§å°ä¸Šé™ï¼Œå•ä½ä¸ºå­—èŠ‚ï¼Œ0è¡¨ç¤ºä½¿ç”¨é»˜è®¤å€¼',
    `max_history_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'æœ€å¤§å˜æ›´åŽ†å²æ•°é‡',
    `gmt_create`        datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'åˆ›å»ºæ—¶é—´',
    `gmt_modified`      datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'ä¿®æ”¹æ—¶é—´',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uk_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'ç§Ÿæˆ·å®¹é‡ä¿¡æ¯è¡¨' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `kp`            varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'kp',
    `tenant_id`     varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
    `tenant_name`   varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_name',
    `tenant_desc`   varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tenant_desc',
    `create_source` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'create_source',
    `gmt_create`    bigint(20) NOT NULL COMMENT 'åˆ›å»ºæ—¶é—´',
    `gmt_modified`  bigint(20) NOT NULL COMMENT 'ä¿®æ”¹æ—¶é—´',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uk_tenant_info_kptenantid`(`kp`, `tenant_id`) USING BTREE,
    INDEX           `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'tenant_info' ROW_FORMAT = Dynamic;

INSERT INTO `tenant_info`
VALUES (1, '1', 'da4e6fb4-6fc9-45ec-9a62-9d6076742077', 'dev', '开发', 'nacos', 1623467017920, 1623467017920);
INSERT INTO `tenant_info`
VALUES (2, '1', '14413489-79ed-4232-b7c6-d7e9928517cc', 'test', '测试', 'nacos', 1623468903133, 1623468903133);

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`
(
    `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL,
    `password` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `enabled`  tinyint(1) NOT NULL,
    PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

INSERT INTO `users`
VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', 1);

