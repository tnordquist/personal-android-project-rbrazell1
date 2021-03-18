---
title: DDL Code Block
description: A page holding a fenced code block version of the DDL
---

## DDL Code Block

```sqlite

CREATE TABLE IF NOT EXISTS `Bar`
(
    `bar_id`      INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `bar_name`    TEXT                              NOT NULL,
    `address`     TEXT,
    `phoneNumber` TEXT,
    `comment`     TEXT,
    `url`         TEXT,
    `star_rating` INTEGER                           NOT NULL
);

CREATE TABLE IF NOT EXISTS `Drink`
(
    `drink_id`   INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `drink_name` TEXT                              NOT NULL,
    `imgUrl`     TEXT
);

CREATE INDEX IF NOT EXISTS `index_Drink_drink_name` ON `Drink` (`drink_name`);

CREATE TABLE IF NOT EXISTS `DrinkRating`
(
    `drink_rating_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `drink_id`        INTEGER                           NOT NULL,
    `bar_id`          INTEGER                           NOT NULL,
    `star_rating`     INTEGER                           NOT NULL,
    `comment`         TEXT,
    FOREIGN KEY (`drink_id`) REFERENCES `Drink` (`drink_id`) ON UPDATE NO ACTION ON DELETE CASCADE,
    FOREIGN KEY (`bar_id`) REFERENCES `Bar` (`bar_id`) ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS `index_DrinkRating_drink_id` ON `DrinkRating` (`drink_id`);

CREATE INDEX IF NOT EXISTS `index_DrinkRating_bar_id` ON `DrinkRating` (`bar_id`);


```

[DDL Sql code](docs/sql/ddl.sql)<br>