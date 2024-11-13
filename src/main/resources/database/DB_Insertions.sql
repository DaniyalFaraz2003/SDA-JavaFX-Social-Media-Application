use fastbook;

INSERT INTO Student (username, password, name, email, phone_number) VALUES
    ('i220830', 'pass1', 'Amna Siddiqui', 'i220830@nu.edu.pk', '03377888990'),
    ('i220914', 'pass2', 'Hammad Ali', 'i220914@nu.edu.pk', '03277858090'),
    ('i221096', 'pass3', 'Daniyal Faraz', 'i221096@nu.edu.pk', '03355888990'),
    ('i221122', 'pass4', 'Abeer Noor', 'i221122@nu.edu.pk', '03377111990'),
    ('i220990', 'pass5', 'Ahmed Mustafa', 'i220990@nu.edu.pk', '03277888900'),
    ('i220932', 'pass6', 'Muhammad Ali', 'i220932@nu.edu.pk', '03376878990'),
    ('i220897', 'pass7', 'Ali Mustafa', 'i220897@nu.edu.pk', '03277888990'),
    ('i220956', 'pass8', 'Abdullah Kamran', 'i220956@nu.edu.pk', '03377222990'),
    ('i223456', 'pass9', 'Sheharyar Ahmed', 'i223456@nu.edu.pk', '03322888990'),
    ('i221234', 'pass10', 'Muneeb Ahmad', 'i221234@nu.edu.pk', '03377888950');

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
    (4),
    (5),
    (6);

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
    (1, 2, TRUE),
    (2, 3, TRUE),
    (3, 4, TRUE),
    (4, 5, TRUE),
    (5, 6, TRUE),
    (6, 7, TRUE),
    (7, 8, TRUE),
    (8, 9, TRUE),
    (9, 10, TRUE),
    (10, 1, TRUE);

INSERT INTO Message (student_id_from, student_id_to, text, time_stamp) VALUES
    (1, 2, 'Hey Hammad, how was your weekend trip?', NOW()),
    (2, 3, 'Daniyal, I just finished reading that book you recommended!', NOW()),
    (3, 4, 'Abeer, I really liked your travel photos. They were amazing!', NOW()),
    (4, 5, 'Ahmed, how do you manage to stay so productive? I need tips!', NOW()),
    (5, 6, 'Ali, I started my fitness routine this week. It has been tough!', NOW()),
    (6, 7, 'Ali Mustafa, have you tried the new coding challenge on Codeforces?', NOW()),
    (7, 8, 'Abdullah, your cooking experiment looked amazing! I need the recipe.', NOW()),
    (8, 9, 'Sheharyar, I loved your review of the book! It was insightful.', NOW()),
    (9, 10, 'Muneeb, how is your puppy doing? I saw some cute pictures!', NOW()),
    (10, 1, 'Amna, I adopted a new puppy today! He is so cute!', NOW());



