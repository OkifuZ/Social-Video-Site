```
ALTER TABLE `DBprj`.`bangumi` 
ADD COLUMN `bangumi_image_id` INT NULL AFTER `bangumi_category`,
ADD INDEX `bangumi_image_id_image_id_idx` (`bangumi_image_id` ASC) VISIBLE;
;
ALTER TABLE `DBprj`.`bangumi` 
ADD CONSTRAINT `bangumi_image_id_image_id`
  FOREIGN KEY (`bangumi_image_id`)
  REFERENCES `DBprj`.`image` (`image_id`)
  ON DELETE SET NULL
  ON UPDATE SET NULL;
  

```

```
ALTER TABLE `DBprj`.`dynamic` 
ADD COLUMN `dynamic_image_id` INT NULL AFTER `dynamic_uploader_id`,
ADD INDEX `dynamic_image_id_image_id_idx` (`dynamic_image_id` ASC) VISIBLE;
;
ALTER TABLE `DBprj`.`dynamic` 
ADD CONSTRAINT `dynamic_image_id_image_id`
  FOREIGN KEY (`dynamic_image_id`)
  REFERENCES `DBprj`.`image` (`image_id`)
  ON DELETE SET NULL
  ON UPDATE SET NULL;
```

```
ALTER TABLE `DBprj`.`bangumi` 
ADD COLUMN `bangumi_like_numbers` INT NULL AFTER `bangumi_image_id`;
```

```
ALTER TABLE `DBprj`.`column` 
ADD COLUMN `column_like_numbers` INT NULL AFTER `column_image_id`;
```

```
ALTER TABLE `DBprj`.`comment` 
ADD COLUMN `comment_like_numbers` INT NULL AFTER `commented_column_id`;
```

```
ALTER TABLE `DBprj`.`dynamic` 
ADD COLUMN `dynamic_like_numbers` INT NULL AFTER `dynamic_image_id`;
```

```
ALTER TABLE `DBprj`.`video` 
ADD COLUMN `video_like_numbers` INT NULL AFTER `video_description`;
```

