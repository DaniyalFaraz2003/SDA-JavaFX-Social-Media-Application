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

INSERT into Student (username, password, name, email, phone_number) VALUES
                                                                        ('i221188', 'pass11', 'Ibrahim Ahmed', 'i221188@nu.edu.pk', '03377888999'),
                                                                        ('i221988', 'pass11', 'Abdullah Ahmed', 'i221988@nu.edu.pk', '03377777999');

INSERT INTO Activity_Post (post_id) VALUES
                                        (11),
                                        (12);

INSERT INTO Simple_Post (post_id, picture_url, likes) VALUES
    (13, null, 15);

INSERT INTO Question (post_id, votes) VALUES
                                          (14, 12),
                                          (15, 13);


INSERT INTO Reply (post_id, text) VALUES
                                      (11, "yes"),
                                      (11, "no"),
                                      (11, "plan it later"),
                                      (12, "change timings"),
                                      (12, "no"),
                                      (12, "great plan");


INSERT INTO Student_ActivityReply (reply_id, post_id, student_id, time_stamp) VALUES
                                                                                  (10, 11, 1, NOW()),
                                                                                  (12, 11, 2, NOW()),
                                                                                  (13, 12, 9, NOW()),
                                                                                  (14, 12, 4, NOW());

INSERT INTO Comment (post_id, student_id, text, likes, time_stamp) VALUES
                                                                       (13, 5, 'Amazing', 12, NOW()),
                                                                       (13, 3, 'That is great.', 2, NOW());

INSERT INTO Answer (post_id, student_id, marked_correct, votes, text, time_stamp) VALUES
                                                                                      (14, 8, TRUE, 6, 'The best way to prevent SQL injection is to use prepared statements with parameterized queries. This ensures that user input is treated as data and not part of the query structure.', NOW()),
                                                                                      (14, 4, FALSE, 3, 'If you must use dynamic queries (which should be avoided where possible), ensure that user input is properly escaped. However, escaping is not as secure as prepared statements.', NOW()),
                                                                                      (15, 2, TRUE, 3, 'Ensure that the MySQL server is running. If it is down, you will obviously be unable to connect.', NOW()),
                                                                                      (15, 10, FALSE, 3, 'Check that the username, password, and database name you are using to connect to MySQL are correct.', NOW());

INSERT INTO Friend (student_id_from, student_id_to, status, time_stamp) VALUES
                                                                            (11, 12, false, NOW()),
                                                                            (11, 1, false, NOW()),
                                                                            (11, 3, false, NOW()),
                                                                            (12, 4, false, NOW()),
                                                                            (4, 11, false, NOW()),
                                                                            (12, 3, false, NOW());