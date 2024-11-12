use fastbook;

INSERT INTO Student (username, password, name) VALUES
('i220830', 'pass1', 'John Doe'),
('i220914', 'pass2', 'Jane Smith'),
('i221096', 'pass3', 'Alice Johnson'),
('i221122', 'pass4', 'Bob Brown'),
('i220990', 'pass5', 'Charlie Davis'),
('i220932', 'pass6', 'David Evans'),
('i220897', 'pass7', 'Eve Wilson'),
('i220956', 'pass8', 'Frank Harris'),
('i223456', 'pass9', 'Grace Lee'),
('i221234', 'pass10', 'Hannah Clark');

INSERT INTO Post (student_id, title, description, time_stamp) VALUES 
(1, 'My First Post', 'Just joined FAST NUCES. Excited for this new journey.', NOW()),
(2, 'Weekend Adventures', 'Had a great weekend exploring the city!', NOW()),
(3, 'Learning SQL', 'Just started learning SQL. Loving it so far!', NOW()),
(4, 'Travel Diaries', 'Is anyone in for a trip to the North next week?', NOW()),
(5, 'New Year Resolutions', 'What are your new year resolutions this time?', NOW()),
(6, 'Fitness Journey', 'Is lat pulldown or bent over rows better for back muscles?', NOW()),
(7, 'Git refusing to merge', 'How can I continue this rebase allowing unrelated histories with the forced flag introduced in the new release', NOW()),
(8, 'C++ OpenSSL', 'Cannot complete handshake on IOCP. Please hilp :(', NOW()),
(9, 'Json object within the BodyToMono method in WebClient', 'The Json that we know is being returned, is not in the body object that I would expect.', NOW()),
(10, '.JPG File with python', 'What is the best way to print a .JPG file in Python?', NOW());

INSERT INTO Simple_Post (post_id, picture_url, likes) VALUES
(1, 'https://example.com/pictures/first_post.jpg', 15),
(2, 'https://example.com/pictures/weekend_adventures.jpg', 32),
(3, 'https://example.com/pictures/learning_sql.jpg', 8);

INSERT INTO Activity_Post (post_id) VALUES
(4),  -- Post by Bob Brown
(5),  -- Post by Charlie Davis
(6);  -- Post by David Evans

INSERT INTO Question (post_id, votes) VALUES
(7, 15),  
(8, 10),  
(9, 18),  
(10, 25); 

INSERT INTO Answer (post_id, student_id, marked_correct, votes, text, time_stamp) VALUES
(7, 8, FALSE, 6, 'Make sure you are in the right branch.', NOW()),
(7, 1, FALSE, 3, 'Make sure you have committed the work.', NOW()),
(8, 9, TRUE, 15, 'Check OpenSSL key and also check whether TLS certificate is digitally signed.', NOW()),  
(8, 7, FALSE, 19, 'Ensure that any firewalls or security software (on either the client or server side) are not blocking the ports you are using for communication.', NOW()),
(8, 3, FALSE, 1, 'Make sure that the client and server are on the same network (or that appropriate routing is in place if they’re on different networks).', NOW()),
(9, 10, FALSE, 7, 'Use .bodyValue(request) to send a JSON payload in the body of the POST request. The WebClient will automatically convert the Java object (YourRequestType) into a JSON string if you have Jackson or Gson set up in your classpath.', NOW()),
(9, 8, TRUE, 11, 'The .retrieve().bodyToMono(YourResponseType.class) will map the incoming JSON response to the Java object (YourResponseType). You must ensure that the response is a valid JSON representation of the expected YourResponseType.', NOW()),
(10, 1, TRUE, 25, 'If you want to display or manipulate the image before printing, the Pillow library (Python Imaging Library, or PIL) can be very helpful. However, note that Pillow does not directly print images; it’s used for image processing. To actually send the image to a printer, you’ll typically need to use another library or system command.', NOW()),
(10, 2, FALSE, 12, 'On Windows, you can use the pywin32 library to interact with printers directly. This is especially useful for sending jobs to the printer.', NOW());

INSERT INTO Comment (post_id, student_id, text, likes, time_stamp) VALUES
(1, 2, 'Goodluck Man!', 12, NOW()), 
(1, 3, 'Welcome!', 10, NOW()),
(1, 4, 'You are gonna have a great time', 4, NOW()),
(2, 3, 'Looks amazing! I should try something like this next weekend.', 15, NOW()),
(2, 7, 'WOW. Amazing.', 5, NOW()),  
(3, 4, 'SQL is really interesting once you get the hang of it!', 8, NOW()),
(3, 1, 'Database is one of my favourite subjects.', 9, NOW());

INSERT INTO Reply (post_id, student_id, text, time_stamp) VALUES
(4, 6, 'Yes, I am in.', NOW()),  
(4, 9, 'Which place in North?', NOW()),
(5, 7, 'I wanna quit smoking this year!', NOW()),  
(5, 1, 'Thinking of learning cooking.', NOW()),
(6, 8, 'Bent over rows is the best for targetting back muscles.', NOW()),
(6, 2, 'Bent over is gonna do good for you.', NOW());  

INSERT INTO Friend (student_id_from, student_id_to, status) VALUES
(1, 2, TRUE),   -- John Doe and Jane Smith are friends
(2, 3, TRUE),   -- Jane Smith and Alice Johnson are friends
(3, 4, TRUE),  -- Alice Johnson and Bob Brown are friends
(4, 5, TRUE),   -- Bob Brown and Charlie Davis are friends
(5, 6, TRUE),  -- Charlie Davis and David Evans are friends
(6, 7, TRUE),   -- David Evans and Eve Wilson are friends
(7, 8, TRUE),  -- Eve Wilson and Frank Harris are friends
(8, 9, TRUE),   -- Frank Harris and Grace Lee are friends
(9, 10, TRUE),  -- Grace Lee and Hannah Clark are friends
(10, 1, TRUE); -- Hannah Clark and John Doe are friends

INSERT INTO Message (student_id_from, student_id_to, text, time_stamp) VALUES
(1, 2, 'Hey Jane, how was your weekend trip?', NOW()),   -- Message from John Doe to Jane Smith
(2, 3, 'Alice, I just finished reading that book you recommended!', NOW()),  -- Message from Jane Smith to Alice Johnson
(3, 4, 'Bob, I really liked your travel photos. They were amazing!', NOW()),  -- Message from Alice Johnson to Bob Brown
(4, 5, 'Charlie, how do you manage to stay so productive? I need tips!', NOW()),  -- Message from Bob Brown to Charlie Davis
(5, 6, 'David, I started my fitness routine this week. It has been tough!', NOW()),  -- Message from Charlie Davis to David Evans
(6, 7, 'Eve, have you tried the new coding challenge on Codeforces?', NOW()),   -- Message from David Evans to Eve Wilson
(7, 8, 'Frank, your cooking experiment looked amazing! I need the recipe.', NOW()),  -- Message from Eve Wilson to Frank Harris
(8, 9, 'Grace, I loved your review of the book! It was insightful.', NOW()),  -- Message from Frank Harris to Grace Lee
(9, 10, 'Hannah, how is your puppy doing? I saw some cute pictures!', NOW()),  -- Message from Grace Lee to Hannah Clark
(10, 1, 'John, I adopted a new puppy today! He is so cute!', NOW());  -- Message from Hannah Clark to John Doe



