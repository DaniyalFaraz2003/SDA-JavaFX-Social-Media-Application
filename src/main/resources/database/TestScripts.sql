UPDATE simple_post
SET picture_url = '/post_images/1.jpeg'
WHERE post_id = 1;

UPDATE simple_post
SET picture_url = '/post_images/2.jpeg'
WHERE post_id = 2;

UPDATE simple_post
SET picture_url = '/post_images/3.jpeg'
WHERE post_id = 3;

INSERT INTO Post (student_id, title, description, time_stamp) VALUES
                                                                  (3, 'Cricket Lovers', 'Is anyone up for a cricket match at 3:00 pm?', NOW()),
                                                                  (3, 'Study Session', 'Anyone up for PF studey session at 5:00 pm', NOW()),
                                                                  (3, 'Hiking Trip', 'Will be going on a hiking trip this weekend. Really excited for it', NOW()),
                                                                  (3, 'SQL injection in PHP', 'How can I prevent SQL injection in PHP?', NOW()),
                                                                  (3, 'SQL connection failed', 'My sql connection keeps failing. What can I do to prevent this?', NOW()
                                                                  );

INSERT INTO Activity_Post (post_id) VALUES
                                        (16),
                                        (17);

INSERT INTO Simple_Post (post_id, picture_url, likes) VALUES
    (18, null, 15);

INSERT INTO Question (post_id, votes) VALUES
                                          (19, 12),
                                          (20, 13);

INSERT INTO Reply (post_id, text) VALUES
                                      (16, "yes"),
                                      (16, "no"),
                                      (16, "plan it later"),
                                      (17, "change timings"),
                                      (17, "no"),
                                      (17, "great plan");

INSERT INTO Student_ActivityReply (reply_id, post_id, student_id, time_stamp) VALUES
                                                                                  (10, 16, 1, NOW()),
                                                                                  (12, 16, 2, NOW()),
                                                                                  (13, 17, 9, NOW()),
                                                                                  (14, 17, 4, NOW());

INSERT INTO Comment (post_id, student_id, text, likes, time_stamp) VALUES
                                                                       (18, 5, 'Amazing', 12, NOW()),
                                                                       (18, 3, 'That is great.', 2, NOW());

INSERT INTO Answer (post_id, student_id, marked_correct, votes, text, time_stamp) VALUES
                                                                                      (19, 8, TRUE, 6, 'The best way to prevent SQL injection is to use prepared statements with parameterized queries. This ensures that user input is treated as data and not part of the query structure.', NOW()),
                                                                                      (19, 4, FALSE, 3, 'If you must use dynamic queries (which should be avoided where possible), ensure that user input is properly escaped. However, escaping is not as secure as prepared statements.', NOW()),
                                                                                      (20, 2, TRUE, 3, 'Ensure that the MySQL server is running. If it is down, you will obviously be unable to connect.', NOW()),
                                                                                      (20, 10, FALSE, 3, 'Check that the username, password, and database name you are using to connect to MySQL are correct.', NOW());