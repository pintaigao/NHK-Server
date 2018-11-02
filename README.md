# NHK EASY NEWS Backend Server
### Introduction

This is a backend server that return the news list which scrawl from NHK Easy News website. Implement with Spring Rest Framework and deploy on AWS.

<div align=center>
    <img src = "/resources/nhk-logo.jpg" height = "150px" /> <img src = "/resources/spring-logo.png"  height = "150px"/>
</div>

### Api

* `/api/news` : return a list of the news_list
* `/api/daily_update_news` : feed database with the latest news
* `/api/news/:id` : return a specific news with id = `:id`
* **Don't Recommand to Use !!! **`/api/update_all_news` : feed database with all news if database being truncated


### MySql Database 

1. Create a `news_list` table:

   ```mysql
   CREATE TABLE news_list (
     `news_id` char(25) PRIMARY KEY NOT NULL,
     `title` TEXT NOT NULL,
     `title_with_ruby` TEXT,
     `news_img_url` TEXT,
     `news_url` TEXT,
     `pulic_date` TEXT
   )
   ```


### File Structure

```asp
packages/
├── server
│ 	└── NewsListServer.java
├── Entity
│	└── NewsList.java
├── Main
	└── Main.java
	
```

> Note: 
>
> 1. On the NewsListDAOImpl, on `geNewsList()` 's HQL `from NewsList`, NewsList is the classname that in the Entity instead of Table Name in MYSQL !!!
>
> 2. Deploying onto AWS, if the error goes like "Incorrect string value:\xF0\x90\x8D..." that means the collation of columns of table is not utf-8, ( it probably is 'latin' ), using `SHOW FULL FIELDS FROM news_list ` to check that, and here is my solution:
>
>    ```
>    ALTER table `news_list` DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_unicode_ci; 
>    
>    ALTER TABLE `news_list` MODIFY news_id char(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci;
>    
>    ALTER TABLE `news_list` MODIFY title text CHARACTER SET utf8 COLLATE utf8_unicode_ci;
>    
>    ALTER TABLE `news_list` MODIFY title_with_ruby text CHARACTER SET utf8 COLLATE utf8_unicode_ci;
>    
>    ALTER TABLE `news_list` MODIFY news_image_url text CHARACTER SET utf8 COLLATE utf8_unicode_ci;
>    
>    ALTER TABLE `news_list` MODIFY news_url text CHARACTER SET utf8 COLLATE utf8_unicode_ci;
>    
>    ALTER TABLE `news_list` MODIFY public_date text CHARACTER SET utf8 COLLATE utf8_unicode_ci;
>    ```
>
>    Need to think about that when creating the table !!!
>
> 3. When injecting data into databse, seeing all the data become question mark, reference: https://blog.csdn.net/qq_36381640/article/details/79185358,https://blog.csdn.net/violet_echo_0908/article/details/51498829,
and ALTER TABLE Table CONVERT TO CHARACTER SET utf8 COLLATE utf8_unicode_ci;

