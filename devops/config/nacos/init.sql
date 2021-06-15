DROP DATABASE IF EXISTS nacos;
CREATE DATABASE nacos DEFAULT CHARSET utf8mb4;
USE nacos;

CREATE TABLE `config_info`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `data_id`      varchar(255) NOT NULL COMMENT 'data_id',
    `group_id`     varchar(255)          DEFAULT NULL,
    `content`      longtext     NOT NULL COMMENT 'content',
    `md5`          varchar(32)           DEFAULT NULL COMMENT 'md5',
    `gmt_create`   datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `src_user`     text COMMENT 'source user',
    `src_ip`       varchar(50)           DEFAULT NULL COMMENT 'source ip',
    `app_name`     varchar(128)          DEFAULT NULL,
    `tenant_id`    varchar(128)          DEFAULT '' COMMENT '租户字段',
    `c_desc`       varchar(256)          DEFAULT NULL,
    `c_use`        varchar(64)           DEFAULT NULL,
    `effect`       varchar(64)           DEFAULT NULL,
    `type`         varchar(64)           DEFAULT NULL,
    `c_schema`     text,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_configinfo_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info';

/******************************************/
/*   数据库全名 = nacos_config   */
/*   表名称 = config_info_aggr   */
/******************************************/
CREATE TABLE `config_info_aggr`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `data_id`      varchar(255) NOT NULL COMMENT 'data_id',
    `group_id`     varchar(255) NOT NULL COMMENT 'group_id',
    `datum_id`     varchar(255) NOT NULL COMMENT 'datum_id',
    `content`      longtext     NOT NULL COMMENT '内容',
    `gmt_modified` datetime     NOT NULL COMMENT '修改时间',
    `app_name`     varchar(128) DEFAULT NULL,
    `tenant_id`    varchar(128) DEFAULT '' COMMENT '租户字段',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_configinfoaggr_datagrouptenantdatum` (`data_id`,`group_id`,`tenant_id`,`datum_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='增加租户字段';


/******************************************/
/*   数据库全名 = nacos_config   */
/*   表名称 = config_info_beta   */
/******************************************/
CREATE TABLE `config_info_beta`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `data_id`      varchar(255) NOT NULL COMMENT 'data_id',
    `group_id`     varchar(128) NOT NULL COMMENT 'group_id',
    `app_name`     varchar(128)          DEFAULT NULL COMMENT 'app_name',
    `content`      longtext     NOT NULL COMMENT 'content',
    `beta_ips`     varchar(1024)         DEFAULT NULL COMMENT 'betaIps',
    `md5`          varchar(32)           DEFAULT NULL COMMENT 'md5',
    `gmt_create`   datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `src_user`     text COMMENT 'source user',
    `src_ip`       varchar(50)           DEFAULT NULL COMMENT 'source ip',
    `tenant_id`    varchar(128)          DEFAULT '' COMMENT '租户字段',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_configinfobeta_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_beta';

/******************************************/
/*   数据库全名 = nacos_config   */
/*   表名称 = config_info_tag   */
/******************************************/
CREATE TABLE `config_info_tag`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `data_id`      varchar(255) NOT NULL COMMENT 'data_id',
    `group_id`     varchar(128) NOT NULL COMMENT 'group_id',
    `tenant_id`    varchar(128)          DEFAULT '' COMMENT 'tenant_id',
    `tag_id`       varchar(128) NOT NULL COMMENT 'tag_id',
    `app_name`     varchar(128)          DEFAULT NULL COMMENT 'app_name',
    `content`      longtext     NOT NULL COMMENT 'content',
    `md5`          varchar(32)           DEFAULT NULL COMMENT 'md5',
    `gmt_create`   datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `src_user`     text COMMENT 'source user',
    `src_ip`       varchar(50)           DEFAULT NULL COMMENT 'source ip',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_configinfotag_datagrouptenanttag` (`data_id`,`group_id`,`tenant_id`,`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_tag';

/******************************************/
/*   数据库全名 = nacos_config   */
/*   表名称 = config_tags_relation   */
/******************************************/
CREATE TABLE `config_tags_relation`
(
    `id`        bigint(20) NOT NULL COMMENT 'id',
    `tag_name`  varchar(128) NOT NULL COMMENT 'tag_name',
    `tag_type`  varchar(64)  DEFAULT NULL COMMENT 'tag_type',
    `data_id`   varchar(255) NOT NULL COMMENT 'data_id',
    `group_id`  varchar(128) NOT NULL COMMENT 'group_id',
    `tenant_id` varchar(128) DEFAULT '' COMMENT 'tenant_id',
    `nid`       bigint(20) NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (`nid`),
    UNIQUE KEY `uk_configtagrelation_configidtag` (`id`,`tag_name`,`tag_type`),
    KEY         `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_tag_relation';

/******************************************/
/*   数据库全名 = nacos_config   */
/*   表名称 = group_capacity   */
/******************************************/
CREATE TABLE `group_capacity`
(
    `id`                bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `group_id`          varchar(128) NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
    `quota`             int(10) unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
    `usage`             int(10) unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
    `max_size`          int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
    `max_aggr_count`    int(10) unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数，，0表示使用默认值',
    `max_aggr_size`     int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
    `max_history_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
    `gmt_create`        datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified`      datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='集群、各Group容量信息表';

/******************************************/
/*   数据库全名 = nacos_config   */
/*   表名称 = his_config_info   */
/******************************************/
CREATE TABLE `his_config_info`
(
    `id`           bigint(64) unsigned NOT NULL,
    `nid`          bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `data_id`      varchar(255) NOT NULL,
    `group_id`     varchar(128) NOT NULL,
    `app_name`     varchar(128)          DEFAULT NULL COMMENT 'app_name',
    `content`      longtext     NOT NULL,
    `md5`          varchar(32)           DEFAULT NULL,
    `gmt_create`   datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `gmt_modified` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `src_user`     text,
    `src_ip`       varchar(50)           DEFAULT NULL,
    `op_type`      char(10)              DEFAULT NULL,
    `tenant_id`    varchar(128)          DEFAULT '' COMMENT '租户字段',
    PRIMARY KEY (`nid`),
    KEY            `idx_gmt_create` (`gmt_create`),
    KEY            `idx_gmt_modified` (`gmt_modified`),
    KEY            `idx_did` (`data_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='多租户改造';


/******************************************/
/*   数据库全名 = nacos_config   */
/*   表名称 = tenant_capacity   */
/******************************************/
CREATE TABLE `tenant_capacity`
(
    `id`                bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `tenant_id`         varchar(128) NOT NULL DEFAULT '' COMMENT 'Tenant ID',
    `quota`             int(10) unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
    `usage`             int(10) unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
    `max_size`          int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
    `max_aggr_count`    int(10) unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数',
    `max_aggr_size`     int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
    `max_history_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
    `gmt_create`        datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified`      datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='租户容量信息表';


CREATE TABLE `tenant_info`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `kp`            varchar(128) NOT NULL COMMENT 'kp',
    `tenant_id`     varchar(128) default '' COMMENT 'tenant_id',
    `tenant_name`   varchar(128) default '' COMMENT 'tenant_name',
    `tenant_desc`   varchar(256) DEFAULT NULL COMMENT 'tenant_desc',
    `create_source` varchar(32)  DEFAULT NULL COMMENT 'create_source',
    `gmt_create`    bigint(20) NOT NULL COMMENT '创建时间',
    `gmt_modified`  bigint(20) NOT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_tenant_info_kptenantid` (`kp`,`tenant_id`),
    KEY             `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='tenant_info';

CREATE TABLE `users`
(
    `username` varchar(50)  NOT NULL PRIMARY KEY,
    `password` varchar(500) NOT NULL,
    `enabled`  boolean      NOT NULL
);

CREATE TABLE `roles`
(
    `username` varchar(50) NOT NULL,
    `role`     varchar(50) NOT NULL,
    UNIQUE INDEX `idx_user_role` (`username` ASC, `role` ASC) USING BTREE
);

CREATE TABLE `permissions`
(
    `role`     varchar(50)  NOT NULL,
    `resource` varchar(255) NOT NULL,
    `action`   varchar(8)   NOT NULL,
    UNIQUE INDEX `uk_role_permission` (`role`,`resource`,`action`) USING BTREE
);

INSERT INTO users (username, password, enabled)
VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', TRUE);

INSERT INTO roles (username, role)
VALUES ('nacos', 'ROLE_ADMIN');

-- ----------------------------
-- Records of config_info
-- ----------------------------
INSERT INTO `config_info` VALUES (1, 'gateway', 'DEFAULT_GROUP', 'server:\n  port: 10081\nspring:\n  cloud:\n    gateway:\n      routes:\n        - id: auth-server\n          uri: lb://auth-server\n          predicates:\n            - Path=/auth-server/**\n      default-filters:\n        - name: RequestRateLimiter\n          args:\n            key-resolver: \'#{@remoteAddressKeyResolver}\'\n            redis-rate-limiter.replenishRate: 1\n            redis-rate-limiter.burstCapacity: 5\n', '10a2e75d69fda475d7bdb58e46beeb85', '2021-06-11 22:04:18', '2021-06-11 22:30:53', NULL, '172.18.0.1', '', 'da4e6fb4-6fc9-45ec-9a62-9d6076742077', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (4, 'application', 'DEFAULT_GROUP', 'server:\n  servlet:\n    context-path: /${spring.application.name}\nspring:\n  redis:\n    database: 0\n    host: ${REDIS_HOST:localhost}\n    port: ${REDIS_PORT:6379}\n    password: ${REDIS_PASSWORD:123456}\n    timeout: 3000\n    jedis:\n      pool:\n        max-active: -1\n        max-wait: -1\n        max-idle: 8\n        min-idle: 3', '11796044e7be74d75e2eb5c6baec10ef', '2021-06-11 22:31:10', '2021-06-11 22:33:37', NULL, '172.18.0.1', '', 'da4e6fb4-6fc9-45ec-9a62-9d6076742077', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (6, 'gateway', 'DEFAULT_GROUP', 'spring:\n  cloud:\n    gateway:\n      routes:\n        - id: auth-server\n          uri: lb://auth-server\n          predicates:\n            - Path=/auth-server/**\n      default-filters:\n        - name: RequestRateLimiter\n          args:\n            key-resolver: \'#{@remoteAddressKeyResolver}\'\n            redis-rate-limiter.replenishRate: 1\n            redis-rate-limiter.burstCapacity: 5\n', 'e2a845594ac4488b62f360e3549d1c01', '2021-06-11 22:35:57', '2021-06-11 22:36:21', NULL, '172.18.0.1', '', '14413489-79ed-4232-b7c6-d7e9928517cc', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (7, 'application', 'DEFAULT_GROUP', 'spring:\n  redis:\n    database: 0\n    host: ${REDIS_HOST:localhost}\n    port: ${REDIS_PORT:6379}\n    password: ${REDIS_PASSWORD:123456}\n    timeout: 3000\n    jedis:\n      pool:\n        max-active: -1\n        max-wait: -1\n        max-idle: 8\n        min-idle: 3\n  datasource:\n    driver-class-name: com.mysql.jdbc.Driver\n    username: root\n    password: ${MYSQL_ROOT_PASSWORD}      ', 'c5ba8cd81a68898f75705f7350ab1c0e', '2021-06-11 22:35:57', '2021-06-12 13:09:14', NULL, '172.18.0.1', '', '14413489-79ed-4232-b7c6-d7e9928517cc', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (13, 'auth-server', 'DEFAULT_GROUP', 'server:\r\n  port: 8081\r\nspring:\r\n  datasource:\r\n    driver-class-name: com.mysql.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/auth_center?useUnicode=true&zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=utf-8&useSSL=false\r\n    username: root\r\n    password: root123', 'e568455e3692d9d6f9cbb03648aff192', '2021-06-12 05:00:27', '2021-06-12 05:00:27', NULL, '172.18.0.1', '', 'da4e6fb4-6fc9-45ec-9a62-9d6076742077', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (14, 'auth-server', 'DEFAULT_GROUP', 'spring:\n  datasource:\n    url: jdbc:mysql://mysql:3306/auth_center?useUnicode=true&zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=utf-8&useSSL=false', '1039d08077b76ea2e86648b289c1e360', '2021-06-12 12:38:09', '2021-06-12 13:09:39', NULL, '172.18.0.1', '', '14413489-79ed-4232-b7c6-d7e9928517cc', '', '', '', 'yaml', '');

-- ----------------------------
-- Records of his_config_info
-- ----------------------------
INSERT INTO `his_config_info` VALUES (0, 1, 'gateway', 'DEFAULT_GROUP', '', 'spring:\r\n  redis:\r\n    database: 0\r\n    host: ${REDIS_HOST:localhost}\r\n    port: ${REDIS_PORT:6379}\r\n    password: ${REDIS_PASSWORD:123456}\r\n    timeout: 3000\r\n    jedis:\r\n      pool:\r\n        max-active: -1\r\n        max-wait: -1\r\n        max-idle: 8\r\n        min-idle: 3\r\n  cloud:\r\n    gateway:\r\n      routes:\r\n        - id: auth-server\r\n          uri: lb://auth-server\r\n          predicates:\r\n            - Path=/auth-server/**\r\n      default-filters:\r\n        - name: RequestRateLimiter\r\n          args:\r\n            key-resolver: \'#{@remoteAddressKeyResolver}\'\r\n            redis-rate-limiter.replenishRate: 1\r\n            redis-rate-limiter.burstCapacity: 5\r\n', '925c2e9e886e985e837df5715b1fbbdd', '2021-06-12 11:04:18', '2021-06-11 22:04:18', NULL, '172.18.0.1', 'I', 'da4e6fb4-6fc9-45ec-9a62-9d6076742077');
INSERT INTO `his_config_info` VALUES (1, 2, 'gateway', 'DEFAULT_GROUP', '', 'spring:\r\n  redis:\r\n    database: 0\r\n    host: ${REDIS_HOST:localhost}\r\n    port: ${REDIS_PORT:6379}\r\n    password: ${REDIS_PASSWORD:123456}\r\n    timeout: 3000\r\n    jedis:\r\n      pool:\r\n        max-active: -1\r\n        max-wait: -1\r\n        max-idle: 8\r\n        min-idle: 3\r\n  cloud:\r\n    gateway:\r\n      routes:\r\n        - id: auth-server\r\n          uri: lb://auth-server\r\n          predicates:\r\n            - Path=/auth-server/**\r\n      default-filters:\r\n        - name: RequestRateLimiter\r\n          args:\r\n            key-resolver: \'#{@remoteAddressKeyResolver}\'\r\n            redis-rate-limiter.replenishRate: 1\r\n            redis-rate-limiter.burstCapacity: 5\r\n', '925c2e9e886e985e837df5715b1fbbdd', '2021-06-12 11:06:09', '2021-06-11 22:06:09', NULL, '172.18.0.1', 'U', 'da4e6fb4-6fc9-45ec-9a62-9d6076742077');
INSERT INTO `his_config_info` VALUES (1, 3, 'gateway', 'DEFAULT_GROUP', '', 'server:\n  port: 10081\nspring:\n  redis:\n    database: 0\n    host: ${REDIS_HOST:localhost}\n    port: ${REDIS_PORT:6379}\n    password: ${REDIS_PASSWORD:123456}\n    timeout: 3000\n    jedis:\n      pool:\n        max-active: -1\n        max-wait: -1\n        max-idle: 8\n        min-idle: 3\n  cloud:\n    gateway:\n      routes:\n        - id: auth-server\n          uri: lb://auth-server\n          predicates:\n            - Path=/auth-server/**\n      default-filters:\n        - name: RequestRateLimiter\n          args:\n            key-resolver: \'#{@remoteAddressKeyResolver}\'\n            redis-rate-limiter.replenishRate: 1\n            redis-rate-limiter.burstCapacity: 5\n', '92d094fb002f4140d09c196256a108f7', '2021-06-12 11:30:53', '2021-06-11 22:30:53', NULL, '172.18.0.1', 'U', 'da4e6fb4-6fc9-45ec-9a62-9d6076742077');
INSERT INTO `his_config_info` VALUES (0, 4, 'application', 'DEFAULT_GROUP', '', 'spring:\r\n  redis:\r\n    database: 0\r\n    host: ${REDIS_HOST:localhost}\r\n    port: ${REDIS_PORT:6379}\r\n    password: ${REDIS_PASSWORD:123456}\r\n    timeout: 3000\r\n    jedis:\r\n      pool:\r\n        max-active: -1\r\n        max-wait: -1\r\n        max-idle: 8\r\n        min-idle: 3', '10c9d0f7fca57a5b00fb6c9c243052a1', '2021-06-12 11:31:09', '2021-06-11 22:31:10', NULL, '172.18.0.1', 'I', 'da4e6fb4-6fc9-45ec-9a62-9d6076742077');
INSERT INTO `his_config_info` VALUES (4, 5, 'application', 'DEFAULT_GROUP', '', 'spring:\r\n  redis:\r\n    database: 0\r\n    host: ${REDIS_HOST:localhost}\r\n    port: ${REDIS_PORT:6379}\r\n    password: ${REDIS_PASSWORD:123456}\r\n    timeout: 3000\r\n    jedis:\r\n      pool:\r\n        max-active: -1\r\n        max-wait: -1\r\n        max-idle: 8\r\n        min-idle: 3', '10c9d0f7fca57a5b00fb6c9c243052a1', '2021-06-12 11:33:36', '2021-06-11 22:33:37', NULL, '172.18.0.1', 'U', 'da4e6fb4-6fc9-45ec-9a62-9d6076742077');
INSERT INTO `his_config_info` VALUES (0, 6, 'gateway', 'DEFAULT_GROUP', '', 'server:\n  port: 10081\nspring:\n  cloud:\n    gateway:\n      routes:\n        - id: auth-server\n          uri: lb://auth-server\n          predicates:\n            - Path=/auth-server/**\n      default-filters:\n        - name: RequestRateLimiter\n          args:\n            key-resolver: \'#{@remoteAddressKeyResolver}\'\n            redis-rate-limiter.replenishRate: 1\n            redis-rate-limiter.burstCapacity: 5\n', '10a2e75d69fda475d7bdb58e46beeb85', '2021-06-12 11:35:56', '2021-06-11 22:35:57', NULL, '172.18.0.1', 'I', '14413489-79ed-4232-b7c6-d7e9928517cc');
INSERT INTO `his_config_info` VALUES (0, 7, 'application', 'DEFAULT_GROUP', '', 'server:\n  servlet:\n    context-path: /${spring.application.name}\nspring:\n  redis:\n    database: 0\n    host: ${REDIS_HOST:localhost}\n    port: ${REDIS_PORT:6379}\n    password: ${REDIS_PASSWORD:123456}\n    timeout: 3000\n    jedis:\n      pool:\n        max-active: -1\n        max-wait: -1\n        max-idle: 8\n        min-idle: 3', '11796044e7be74d75e2eb5c6baec10ef', '2021-06-12 11:35:56', '2021-06-11 22:35:57', NULL, '172.18.0.1', 'I', '14413489-79ed-4232-b7c6-d7e9928517cc');
INSERT INTO `his_config_info` VALUES (6, 8, 'gateway', 'DEFAULT_GROUP', '', 'server:\n  port: 10081\nspring:\n  cloud:\n    gateway:\n      routes:\n        - id: auth-server\n          uri: lb://auth-server\n          predicates:\n            - Path=/auth-server/**\n      default-filters:\n        - name: RequestRateLimiter\n          args:\n            key-resolver: \'#{@remoteAddressKeyResolver}\'\n            redis-rate-limiter.replenishRate: 1\n            redis-rate-limiter.burstCapacity: 5\n', '10a2e75d69fda475d7bdb58e46beeb85', '2021-06-12 11:36:21', '2021-06-11 22:36:21', NULL, '172.18.0.1', 'U', '14413489-79ed-4232-b7c6-d7e9928517cc');
INSERT INTO `his_config_info` VALUES (7, 9, 'application', 'DEFAULT_GROUP', '', 'server:\n  servlet:\n    context-path: /${spring.application.name}\nspring:\n  redis:\n    database: 0\n    host: ${REDIS_HOST:localhost}\n    port: ${REDIS_PORT:6379}\n    password: ${REDIS_PASSWORD:123456}\n    timeout: 3000\n    jedis:\n      pool:\n        max-active: -1\n        max-wait: -1\n        max-idle: 8\n        min-idle: 3', '11796044e7be74d75e2eb5c6baec10ef', '2021-06-12 11:41:00', '2021-06-11 22:41:01', NULL, '172.18.0.1', 'U', '14413489-79ed-4232-b7c6-d7e9928517cc');
INSERT INTO `his_config_info` VALUES (0, 10, 'auth-server', 'DEFAULT_GROUP', '', 'spring:\r\n  datasource:\r\n    name: druidDataSource\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    druid:\r\n      driver-class-name: com.mysql.cj.jdbc.Driver\r\n      url: jdbc:mysql://localhost:3306/auth_center?useUnicode=true&zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=utf-8\r\n      username: root\r\n      password: root123\r\n      filters: stat,wall,log4j,config\r\n      max-active: 100\r\n      initial-size: 1\r\n      max-wait: 60000\r\n      min-idle: 1\r\n      time-between-eviction-runs-millis: 60000\r\n      min-evictable-idle-time-millis: 300000\r\n      validation-query: select \'x\'\r\n      test-while-idle: true\r\n      test-on-borrow: false\r\n      test-on-return: false\r\n      pool-prepared-statements: true\r\n      max-open-prepared-statements: 50\r\n      max-pool-prepared-statement-per-connection-size: 20', '5a64ed972e8752edf21030c3e5d08700', '2021-06-12 17:32:26', '2021-06-12 04:32:26', NULL, '172.18.0.1', 'I', 'da4e6fb4-6fc9-45ec-9a62-9d6076742077');
INSERT INTO `his_config_info` VALUES (8, 11, 'auth-server', 'DEFAULT_GROUP', '', 'spring:\r\n  datasource:\r\n    name: druidDataSource\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    druid:\r\n      driver-class-name: com.mysql.cj.jdbc.Driver\r\n      url: jdbc:mysql://localhost:3306/auth_center?useUnicode=true&zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=utf-8\r\n      username: root\r\n      password: root123\r\n      filters: stat,wall,log4j,config\r\n      max-active: 100\r\n      initial-size: 1\r\n      max-wait: 60000\r\n      min-idle: 1\r\n      time-between-eviction-runs-millis: 60000\r\n      min-evictable-idle-time-millis: 300000\r\n      validation-query: select \'x\'\r\n      test-while-idle: true\r\n      test-on-borrow: false\r\n      test-on-return: false\r\n      pool-prepared-statements: true\r\n      max-open-prepared-statements: 50\r\n      max-pool-prepared-statement-per-connection-size: 20', '5a64ed972e8752edf21030c3e5d08700', '2021-06-12 17:36:10', '2021-06-12 04:36:10', NULL, '172.18.0.1', 'U', 'da4e6fb4-6fc9-45ec-9a62-9d6076742077');
INSERT INTO `his_config_info` VALUES (8, 12, 'auth-server', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      driver-class-name: com.mysql.cj.jdbc.Driver\n      url: jdbc:mysql://localhost:3306/auth_center?useUnicode=true&zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=utf-8\n      username: root\n      password: root123\n      filters: stat,wall,log4j,config\n      max-active: 100\n      initial-size: 1\n      max-wait: 60000\n      min-idle: 1\n      time-between-eviction-runs-millis: 60000\n      min-evictable-idle-time-millis: 300000\n      validation-query: select \'x\'\n      test-while-idle: true\n      test-on-borrow: false\n      test-on-return: false\n      pool-prepared-statements: true\n      max-open-prepared-statements: 50\n      max-pool-prepared-statement-per-connection-size: 20', '8b36cf99c3e53284314e6928b65d6340', '2021-06-12 17:43:54', '2021-06-12 04:43:54', NULL, '172.18.0.1', 'U', 'da4e6fb4-6fc9-45ec-9a62-9d6076742077');
INSERT INTO `his_config_info` VALUES (8, 13, 'auth-server', 'DEFAULT_GROUP', '', 'server:\n  port: 8081\nspring:\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      driver-class-name: com.mysql.cj.jdbc.Driver\n      url: jdbc:mysql://localhost:3306/auth_center?useUnicode=true&zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=utf-8\n      username: root\n      password: root123\n      filters: stat,wall,log4j,config\n      max-active: 100\n      initial-size: 1\n      max-wait: 60000\n      min-idle: 1\n      time-between-eviction-runs-millis: 60000\n      min-evictable-idle-time-millis: 300000\n      validation-query: select \'x\'\n      test-while-idle: true\n      test-on-borrow: false\n      test-on-return: false\n      pool-prepared-statements: true\n      max-open-prepared-statements: 50\n      max-pool-prepared-statement-per-connection-size: 20', 'adb0a1d491e4de41d3a2b4e3c7abdc2c', '2021-06-12 17:49:30', '2021-06-12 04:49:30', NULL, '172.18.0.1', 'U', 'da4e6fb4-6fc9-45ec-9a62-9d6076742077');
INSERT INTO `his_config_info` VALUES (8, 14, 'auth-server', 'DEFAULT_GROUP', '', 'server:\n  port: 8081\nspring:\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://localhost:3306/auth_center?useUnicode=true&zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=utf-8\n    username: root\n    password: root123\n    druid:\n      filters: stat,wall,log4j,config\n      max-active: 100\n      initial-size: 1\n      max-wait: 60000\n      min-idle: 1\n      time-between-eviction-runs-millis: 60000\n      min-evictable-idle-time-millis: 300000\n      validation-query: select \'x\'\n      test-while-idle: true\n      test-on-borrow: false\n      test-on-return: false\n      pool-prepared-statements: true\n      max-open-prepared-statements: 50\n      max-pool-prepared-statement-per-connection-size: 20', 'e5ccb88f4d76cf706b9df9c1e95d1438', '2021-06-12 17:56:28', '2021-06-12 04:56:28', NULL, '172.18.0.1', 'U', 'da4e6fb4-6fc9-45ec-9a62-9d6076742077');
INSERT INTO `his_config_info` VALUES (8, 15, 'auth-server', 'DEFAULT_GROUP', '', 'server:\n  port: 8081\nspring:\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://localhost:3306/auth_center?useUnicode=true&zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=utf-8\n    username: root\n    password: root123', '928f79783a340c73ffa1c4d19a83f135', '2021-06-12 17:58:08', '2021-06-12 04:58:08', NULL, '172.18.0.1', 'D', 'da4e6fb4-6fc9-45ec-9a62-9d6076742077');
INSERT INTO `his_config_info` VALUES (0, 16, 'auth-server', 'DEFAULT_GROUP', '', 'server:\r\n  port: 8081\r\nspring:\r\n  datasource:\r\n    driver-class-name: com.mysql.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/auth_center?useUnicode=true&zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=utf-8&useSSL=false\r\n    username: root\r\n    password: root123', 'e568455e3692d9d6f9cbb03648aff192', '2021-06-12 18:00:27', '2021-06-12 05:00:27', NULL, '172.18.0.1', 'I', 'da4e6fb4-6fc9-45ec-9a62-9d6076742077');
INSERT INTO `his_config_info` VALUES (0, 17, 'auth-server', 'DEFAULT_GROUP', '', 'server:\r\n  port: 8081\r\nspring:\r\n  datasource:\r\n    driver-class-name: com.mysql.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/auth_center?useUnicode=true&zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=utf-8&useSSL=false\r\n    username: root\r\n    password: root123', 'e568455e3692d9d6f9cbb03648aff192', '2021-06-13 01:38:09', '2021-06-12 12:38:09', NULL, '172.18.0.1', 'I', '14413489-79ed-4232-b7c6-d7e9928517cc');
INSERT INTO `his_config_info` VALUES (14, 18, 'auth-server', 'DEFAULT_GROUP', '', 'server:\r\n  port: 8081\r\nspring:\r\n  datasource:\r\n    driver-class-name: com.mysql.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/auth_center?useUnicode=true&zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=utf-8&useSSL=false\r\n    username: root\r\n    password: root123', 'e568455e3692d9d6f9cbb03648aff192', '2021-06-13 01:39:23', '2021-06-12 12:39:23', NULL, '172.18.0.1', 'U', '14413489-79ed-4232-b7c6-d7e9928517cc');
INSERT INTO `his_config_info` VALUES (7, 19, 'application', 'DEFAULT_GROUP', '', 'spring:\n  redis:\n    database: 0\n    host: ${REDIS_HOST:localhost}\n    port: ${REDIS_PORT:6379}\n    password: ${REDIS_PASSWORD:123456}\n    timeout: 3000\n    jedis:\n      pool:\n        max-active: -1\n        max-wait: -1\n        max-idle: 8\n        min-idle: 3', 'dfbcc4a4a579ca4199e18eb37c9195e0', '2021-06-13 02:09:13', '2021-06-12 13:09:14', NULL, '172.18.0.1', 'U', '14413489-79ed-4232-b7c6-d7e9928517cc');
INSERT INTO `his_config_info` VALUES (14, 20, 'auth-server', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    driver-class-name: com.mysql.jdbc.Driver\n    url: jdbc:mysql://mysql:3306/auth_center?useUnicode=true&zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=utf-8&useSSL=false\n    username: root\n    password: ${MYSQL_ROOT_PASSWORD}', '4a5b1aef498f3ade4463c0b5e653d457', '2021-06-13 02:09:38', '2021-06-12 13:09:39', NULL, '172.18.0.1', 'U', '14413489-79ed-4232-b7c6-d7e9928517cc');

-- ----------------------------
-- Records of tenant_info
-- ----------------------------
INSERT INTO `tenant_info` VALUES (1, '1', 'da4e6fb4-6fc9-45ec-9a62-9d6076742077', 'dev', '开发', 'nacos', 1623467017920, 1623467017920);
INSERT INTO `tenant_info` VALUES (2, '1', '14413489-79ed-4232-b7c6-d7e9928517cc', 'test', '测试', 'nacos', 1623468903133, 1623468903133);
