```
CREATE TABLE `DBprj`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(45) NOT NULL,
  `nickname` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `sign_up_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `email` VARCHAR(45) NULL,
  `phone_number` CHAR(11) NULL,
  `user_level` INT NULL,
  `user_state` INT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_name_UNIQUE` (`user_name` ASC) VISIBLE);
```

```
CREATE TABLE `DBprj`.`image` (
  `image_id` INT NOT NULL AUTO_INCREMENT,
  `image_name` VARCHAR(45),
  `image_upload_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `image_uploader_id` INT NULL,
  `image_url` VARCHAR(512) NULL,
  PRIMARY KEY (`image_id`),
  INDEX `image_uploader_id_user_id_idx` (`image_uploader_id` ASC) VISIBLE,
  CONSTRAINT `image_uploader_id_user_id`
    FOREIGN KEY (`image_uploader_id`)
    REFERENCES `DBprj`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
```

```
CREATE TABLE `DBprj`.`message` (
  `message_id` INT NOT NULL AUTO_INCREMENT,
  `message_content` TEXT NULL,
  `message_send_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `message_sender_id` INT NULL,
  `message_receiver_id` INT NULL,
  PRIMARY KEY (`message_id`),
  INDEX `message_sender_id_user_id_idx` (`message_sender_id` ASC) VISIBLE,
  INDEX `message_receiver_id_user_id_idx` (`message_receiver_id` ASC) VISIBLE,
  CONSTRAINT `message_sender_id_user_id`
    FOREIGN KEY (`message_sender_id`)
    REFERENCES `DBprj`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `message_receiver_id_user_id`
    FOREIGN KEY (`message_receiver_id`)
    REFERENCES `DBprj`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

```

```
CREATE TABLE `DBprj`.`column` (
  `column_id` INT NOT NULL AUTO_INCREMENT,
  `column_upload_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `column_name` VARCHAR(45) NULL,
  `column_read_times` INT NULL,
  `column_content` TEXT NULL,
  `column_uploader_id` INT NULL,
  `column_image_id` INT NULL,
  PRIMARY KEY (`column_id`),
  INDEX `column_uploader_id_user_id_idx` (`column_uploader_id` ASC) VISIBLE,
  INDEX `column_image_id_image_id_idx` (`column_image_id` ASC) VISIBLE,
  CONSTRAINT `column_uploader_id_user_id`
    FOREIGN KEY (`column_uploader_id`)
    REFERENCES `DBprj`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `column_image_id_image_id`
    FOREIGN KEY (`column_image_id`)
    REFERENCES `DBprj`.`image` (`image_id`)
    ON DELETE SET NULL
    ON UPDATE SET NULL);
```

```
CREATE TABLE `DBprj`.`admin` (
  `admin_id` INT NOT NULL,
  `admin_name` VARCHAR(45) NULL,
  `admin_permission` INT NULL,
  PRIMARY KEY (`admin_id`),
  CONSTRAINT `admin_id_user_id`
    FOREIGN KEY (`admin_id`)
    REFERENCES `DBprj`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
```

```
CREATE TABLE `DBprj`.`tag` (
  `tag_id` INT NOT NULL AUTO_INCREMENT,
  `tag_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`tag_id`));
```

```
CREATE TABLE `DBprj`.`bangumi` (
  `bangumi_id` INT NOT NULL AUTO_INCREMENT,
  `bangumi_name` VARCHAR(100) NULL,
  `bangumi_upload_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `bangumi_play_times` INT NULL,
  `bangumi_content_url` VARCHAR(512) NULL,
  `bangumi_uploader_id` INT NULL,
  `bangumi_description` TEXT NULL,
  `bangumi_category` VARCHAR(45) NULL,
  PRIMARY KEY (`bangumi_id`),
  INDEX `bangumi_uploader_id_admin_id_idx` (`bangumi_uploader_id` ASC) VISIBLE,
  CONSTRAINT `bangumi_uploader_id_admin_id`
    FOREIGN KEY (`bangumi_uploader_id`)
    REFERENCES `DBprj`.`admin` (`admin_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
```

```
CREATE TABLE `DBprj`.`favorite` (
  `favorite_id` INT NOT NULL AUTO_INCREMENT,
  `favorite_name` VARCHAR(45) NULL,
  `favorite_permission` INT NULL,
  `favorite_owner_id` INT NULL,
  PRIMARY KEY (`favorite_id`),
  INDEX `favorite_owner_id_user_id_idx` (`favorite_owner_id` ASC) VISIBLE,
  CONSTRAINT `favorite_owner_id_user_id`
    FOREIGN KEY (`favorite_owner_id`)
    REFERENCES `DBprj`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

```

```
CREATE TABLE `DBprj`.`video` (
  `video_id` INT NOT NULL AUTO_INCREMENT,
  `video_upload_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `video_duration` TIME NULL,
  `video_name` VARCHAR(45) NULL,
  `video_play_times` INT NULL,
  `video_content_url` VARCHAR(512) NULL,
  `video_image_id` INT NULL,
  `video_uploader_id` INT NULL,
  `video_description` TEXT NULL,
  PRIMARY KEY (`video_id`),
  INDEX `video_image_id_image_id_idx` (`video_image_id` ASC) VISIBLE,
  INDEX `video_uploader_id_user_id_idx` (`video_uploader_id` ASC) VISIBLE,
  CONSTRAINT `video_image_id_image_id`
    FOREIGN KEY (`video_image_id`)
    REFERENCES `DBprj`.`image` (`image_id`)
    ON DELETE SET NULL
    ON UPDATE SET NULL,
  CONSTRAINT `video_uploader_id_user_id`
    FOREIGN KEY (`video_uploader_id`)
    REFERENCES `DBprj`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
```

```
CREATE TABLE `DBprj`.`dynamic` (
  `dynamic_id` INT NOT NULL AUTO_INCREMENT,
  `dynamic_upload_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `dynamic_read_times` INT NULL,
  `dynamic_permission` INT NULL,
  `dynamic_content` TEXT NULL,
  `dynamic_uploader_id` INT NULL,
  PRIMARY KEY (`dynamic_id`),
  INDEX `dynamic_uploader_id_user_id_idx` (`dynamic_uploader_id` ASC) VISIBLE,
  CONSTRAINT `dynamic_uploader_id_user_id`
    FOREIGN KEY (`dynamic_uploader_id`)
    REFERENCES `DBprj`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
```

```
CREATE TABLE `DBprj`.`comment` (
  `comment_id` INT NOT NULL AUTO_INCREMENT,
  `comment_send_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `comment_content` TEXT NULL,
  `commented_type` INT NULL,
  `comment_sender_id` INT NULL,
  `commented_bangumi_id` INT NULL,
  `commented_video_id` INT NULL,
  `commented_dynamic_id` INT NULL,
  `commented_column_id` INT NULL,
  PRIMARY KEY (`comment_id`),
  INDEX `comment_sender_id_user_id_idx` (`comment_sender_id` ASC) VISIBLE,
  INDEX `commented_bangumi_id_bangumi_id_idx` (`commented_bangumi_id` ASC) VISIBLE,
  INDEX `commented_video_id_video_id_idx` (`commented_video_id` ASC) VISIBLE,
  INDEX `commented_dynamic_id_dynamic_id_idx` (`commented_dynamic_id` ASC) VISIBLE,
  INDEX `commented_column_id_column_id_idx` (`commented_column_id` ASC) VISIBLE,
  CONSTRAINT `comment_sender_id_user_id`
    FOREIGN KEY (`comment_sender_id`)
    REFERENCES `DBprj`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `commented_bangumi_id_bangumi_id`
    FOREIGN KEY (`commented_bangumi_id`)
    REFERENCES `DBprj`.`bangumi` (`bangumi_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `commented_video_id_video_id`
    FOREIGN KEY (`commented_video_id`)
    REFERENCES `DBprj`.`video` (`video_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `commented_dynamic_id_dynamic_id`
    FOREIGN KEY (`commented_dynamic_id`)
    REFERENCES `DBprj`.`dynamic` (`dynamic_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `commented_column_id_column_id`
    FOREIGN KEY (`commented_column_id`)
    REFERENCES `DBprj`.`column` (`column_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

```

---



```
CREATE TABLE `DBprj`.`favorite_content` (
  `fc_id` INT NOT NULL AUTO_INCREMENT,
  `fc_collected_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `fc_owner_id` INT NULL,
  `fc_type` INT NULL,
  `fc_column_id` INT NULL,
  `fc_bangumi_id` INT NULL,
  `fc_video_id` INT NULL,
  PRIMARY KEY (`fc_id`),
  INDEX `fc_owner_id_favorite_id_idx` (`fc_owner_id` ASC) VISIBLE,
  INDEX `fc_column_id_column_id_idx` (`fc_column_id` ASC) VISIBLE,
  INDEX `fc_bangumi_id_bangumi_id_idx` (`fc_bangumi_id` ASC) VISIBLE,
  INDEX `fc_video_id_video_id_idx` (`fc_video_id` ASC) VISIBLE,
  CONSTRAINT `fc_owner_id_favorite_id`
    FOREIGN KEY (`fc_owner_id`)
    REFERENCES `DBprj`.`favorite` (`favorite_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fc_column_id_column_id`
    FOREIGN KEY (`fc_column_id`)
    REFERENCES `DBprj`.`column` (`column_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fc_bangumi_id_bangumi_id`
    FOREIGN KEY (`fc_bangumi_id`)
    REFERENCES `DBprj`.`bangumi` (`bangumi_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fc_video_id_video_id`
    FOREIGN KEY (`fc_video_id`)
    REFERENCES `DBprj`.`video` (`video_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

```

```
CREATE TABLE `DBprj`.`like` (
  `like_id` INT NOT NULL AUTO_INCREMENT,
  `liker_id` INT NULL,
  `liked_type` INT NULL,
  `liking_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `liked_column_id` INT NULL,
  `liked_bangumi_id` INT NULL,
  `liked_video_id` INT NULL,
  `liked_dynamic_id` INT NULL,
  `liked_comment_id` INT NULL,
  PRIMARY KEY (`like_id`),
  INDEX `liker_id_user_id_idx` (`liker_id` ASC) VISIBLE,
  INDEX `liked_column_id_column_id_idx` (`liked_column_id` ASC) VISIBLE,
  INDEX `liked_bangumi_id_bangumi_id_idx` (`liked_bangumi_id` ASC) VISIBLE,
  INDEX `liked_video_id_video_id_idx` (`liked_video_id` ASC) VISIBLE,
  INDEX `liked_dynamic_id_dynamic_id_idx` (`liked_dynamic_id` ASC) VISIBLE,
  INDEX `liked_comment_id_comment_id_idx` (`liked_comment_id` ASC) VISIBLE,
  CONSTRAINT `liker_id_user_id`
    FOREIGN KEY (`liker_id`)
    REFERENCES `DBprj`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `liked_column_id_column_id`
    FOREIGN KEY (`liked_column_id`)
    REFERENCES `DBprj`.`column` (`column_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `liked_bangumi_id_bangumi_id`
    FOREIGN KEY (`liked_bangumi_id`)
    REFERENCES `DBprj`.`bangumi` (`bangumi_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `liked_video_id_video_id`
    FOREIGN KEY (`liked_video_id`)
    REFERENCES `DBprj`.`video` (`video_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `liked_dynamic_id_dynamic_id`
    FOREIGN KEY (`liked_dynamic_id`)
    REFERENCES `DBprj`.`dynamic` (`dynamic_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `liked_comment_id_comment_id`
    FOREIGN KEY (`liked_comment_id`)
    REFERENCES `DBprj`.`comment` (`comment_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

```

```
CREATE TABLE `DBprj`.`tag_relation` (
  `tag_relation_id` INT NOT NULL AUTO_INCREMENT,
  `tag_id` INT NULL,
  `tagged_type` INT NULL,
  `tagged_column_id` INT NULL,
  `tagged_bangumi_id` INT NULL,
  `tagged_video_id` INT NULL,
  `tagged_dynamic_id` INT NULL,
  PRIMARY KEY (`tag_relation_id`),
  INDEX `tag_id_tag_id_idx` (`tag_id` ASC) VISIBLE,
  INDEX `tagged_column_id_column_id_idx` (`tagged_column_id` ASC) VISIBLE,
  INDEX `tagged_bangumi_id_bangumi_id_idx` (`tagged_bangumi_id` ASC) VISIBLE,
  INDEX `tagged_video_id_video_id_idx` (`tagged_video_id` ASC) VISIBLE,
  INDEX `tagged_dynamic_id_dynamic_id_idx` (`tagged_dynamic_id` ASC) VISIBLE,
  CONSTRAINT `tag_id_tag_id`
    FOREIGN KEY (`tag_id`)
    REFERENCES `DBprj`.`tag` (`tag_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `tagged_column_id_column_id`
    FOREIGN KEY (`tagged_column_id`)
    REFERENCES `DBprj`.`column` (`column_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `tagged_bangumi_id_bangumi_id`
    FOREIGN KEY (`tagged_bangumi_id`)
    REFERENCES `DBprj`.`bangumi` (`bangumi_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `tagged_video_id_video_id`
    FOREIGN KEY (`tagged_video_id`)
    REFERENCES `DBprj`.`video` (`video_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `tagged_dynamic_id_dynamic_id`
    FOREIGN KEY (`tagged_dynamic_id`)
    REFERENCES `DBprj`.`dynamic` (`dynamic_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

```

```
CREATE TABLE `DBprj`.`history` (
  `history_id` INT NOT NULL AUTO_INCREMENT,
  `watch_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `watcher_id` INT NULL,
  `watched_type` INT NULL,
  `watched_column_id` INT NULL,
  `watched_bangumi_id` INT NULL,
  `watched_video_id` INT NULL,
  PRIMARY KEY (`history_id`),
  INDEX `watcher_id_user_id_idx` (`watcher_id` ASC) VISIBLE,
  INDEX `watched_column_id_column_id_idx` (`watched_column_id` ASC) VISIBLE,
  INDEX `watched_bangumi_id_bangumi_id_idx` (`watched_bangumi_id` ASC) VISIBLE,
  INDEX `watched_video_id_video_id_idx` (`watched_video_id` ASC) VISIBLE,
  CONSTRAINT `watcher_id_user_id`
    FOREIGN KEY (`watcher_id`)
    REFERENCES `DBprj`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `watched_column_id_column_id`
    FOREIGN KEY (`watched_column_id`)
    REFERENCES `DBprj`.`column` (`column_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `watched_bangumi_id_bangumi_id`
    FOREIGN KEY (`watched_bangumi_id`)
    REFERENCES `DBprj`.`bangumi` (`bangumi_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `watched_video_id_video_id`
    FOREIGN KEY (`watched_video_id`)
    REFERENCES `DBprj`.`video` (`video_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

```

```
CREATE TABLE `DBprj`.`followship` (
  `followship_id` INT NOT NULL AUTO_INCREMENT,
  `following_id` INT NULL,
  `follower_id` INT NULL,
  `follow_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`followship_id`),
  INDEX `following_id_user_id_idx` (`following_id` ASC) VISIBLE,
  INDEX `follower_id_user_id_idx` (`follower_id` ASC) VISIBLE,
  CONSTRAINT `following_id_user_id`
    FOREIGN KEY (`following_id`)
    REFERENCES `DBprj`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `follower_id_user_id`
    FOREIGN KEY (`follower_id`)
    REFERENCES `DBprj`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

```

