DROP DATABASE IF EXISTS auth_center;
CREATE DATABASE auth_center DEFAULT CHARSET utf8mb4;
USE auth_center;

DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`
(
    `client_id`               varchar(255) NOT NULL,
    `resource_ids`            varchar(255)  DEFAULT NULL,
    `client_secret`           varchar(255)  DEFAULT NULL,
    `scope`                   varchar(255)  DEFAULT NULL,
    `authorized_grant_types`  varchar(255)  DEFAULT NULL,
    `web_server_redirect_uri` varchar(255)  DEFAULT NULL,
    `authorities`             varchar(255)  DEFAULT NULL,
    `access_token_validity`   int(11) DEFAULT NULL,
    `refresh_token_validity`  int(11) DEFAULT NULL,
    `additional_information`  varchar(4096) DEFAULT NULL,
    `autoapprove`             varchar(255)  DEFAULT NULL,
    PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `oauth_client_details`
VALUES ('test-client-id', 'test-resource-id', '$2a$10$Pfw6D2FF36we6y07Me0Z.e9XaxI9kfFSTis8n9snsHK4papxIGMq6', 'all',
        'password,refresh_token', null, null, '86400', '86400', null, null);
