# NHK EASY NEWS Backend Server
### Introduction

This is a backend server that return the news list which scrawl from NHK Easy News website. Implement with Spring Rest Framework

<div align=center>
    <img src = "/resources/nhk-logo.jpg" height = "150px" /> <img src = "/resources/spring-logo.png"  height = "150px"/>
</div>

### Usage



### Api

* `/api/news` : return a list of the news_list
* `/api/news/:id` : return a news in detail


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

> Note: On the NewsListDAOImpl, on `geNewsList()` 's HQL `from NewsList`, NewsList is the classname that in the Entity instead of Table Name in MYSQL !!!
