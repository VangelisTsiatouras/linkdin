USE web_dev_db

LOAD DATA LOCAL INFILE 'db_backup/user.csv' 
INTO TABLE user 
FIELDS TERMINATED BY ';' 
ENCLOSED BY '"' 
LINES TERMINATED BY "\n" 
IGNORE 1 LINES;

LOAD DATA LOCAL INFILE 'db_backup/post.csv' 
INTO TABLE post 
FIELDS TERMINATED BY ';' 
ENCLOSED BY '"' 
LINES TERMINATED BY "\n" 
IGNORE 1 LINES;

LOAD DATA LOCAL INFILE 'db_backup/post_comment.csv' 
INTO TABLE post_comment 
FIELDS TERMINATED BY ';' 
ENCLOSED BY '"'
LINES TERMINATED BY "\n"
IGNORE 1 LINES;

LOAD DATA LOCAL INFILE 'db_backup/post_interest.csv' 
INTO TABLE post_interest 
FIELDS TERMINATED BY ';' 
ENCLOSED BY '"'
LINES TERMINATED BY "\n"
IGNORE 1 LINES;

LOAD DATA LOCAL INFILE 'db_backup/chat.csv' 
INTO TABLE chat 
FIELDS TERMINATED BY ';' 
ENCLOSED BY '"'
LINES TERMINATED BY "\n"
IGNORE 1 LINES;

LOAD DATA LOCAL INFILE 'db_backup/chat_history.csv' 
INTO TABLE chat_history 
FIELDS TERMINATED BY ';' 
ENCLOSED BY '"'
LINES TERMINATED BY "\n"
IGNORE 1 LINES;

LOAD DATA LOCAL INFILE 'db_backup/user_network.csv' 
INTO TABLE user_network 
FIELDS TERMINATED BY ';' 
ENCLOSED BY '"'
LINES TERMINATED BY "\n"
IGNORE 1 LINES;

LOAD DATA LOCAL INFILE 'db_backup/notifications.csv' 
INTO TABLE notifications 
FIELDS TERMINATED BY ';' 
ENCLOSED BY '"'
LINES TERMINATED BY "\n"
IGNORE 1 LINES;
