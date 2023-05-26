INSERT INTO users (email, full_name, password) VALUES
                                                   ('john.doe@example.com', 'John Doe', '$2a$12$1mprqFJh5hqZAKkLWbApy.DlYd.bjwp1oyv0Odi110FQM02OqC4xe'),
                                                   ('jane.smith@example.com', 'Jane Smith', '$2a$12$1mprqFJh5hqZAKkLWbApy.DlYd.bjwp1oyv0Odi110FQM02OqC4xe'),
                                                   ('michael.brown@example.com', 'Michael Brown', '$2a$12$1mprqFJh5hqZAKkLWbApy.DlYd.bjwp1oyv0Odi110FQM02OqC4xe'),
                                                   ('emma.johnson@example.com', 'Emma Johnson', '$2a$12$1mprqFJh5hqZAKkLWbApy.DlYd.bjwp1oyv0Odi110FQM02OqC4xe'),
                                                   ('william.davis@example.com', 'William Davis', '$2a$12$1mprqFJh5hqZAKkLWbApy.DlYd.bjwp1oyv0Odi110FQM02OqC4xe');

INSERT INTO categories (title) VALUES
                                   ('Development'),
                                   ('Business'),
                                   ('Design'),
                                   ('Marketing'),
                                   ('Programming');

INSERT INTO courses (created, description, language, name, price, author_id, category_id) VALUES
                                                                                              ('2023-05-01', 'Learn web development from scratch.', 'English', 'Web Development 101', 99.99, 1, 1),
                                                                                              ('2023-05-02', 'Master the art of entrepreneurship.', 'English', 'Entrepreneurship Masterclass', 149.99, 2, 2),
                                                                                              ('2023-05-03', 'Unlock your creativity with design principles.', 'English', 'Graphic Design Fundamentals', 79.99, 3, 3),
                                                                                              ('2023-05-04', 'Become a digital marketing expert.', 'English', 'Digital Marketing Mastery', 129.99, 4, 4),
                                                                                              ('2023-05-05', 'Achieve work-life balance and improve well-being.', 'English', 'Mindfulness and Productivity', 49.99, 5, 5);

INSERT INTO sections (name, course_id) VALUES
                                           ('Introduction', 1),
                                           ('HTML Basics', 1),
                                           ('CSS Styling', 1),
                                           ('JavaScript Fundamentals', 1),
                                           ('Project Development', 1),
                                           ('Introduction', 2),
                                           ('Market Research', 2),
                                           ('Business Planning', 2),
                                           ('Marketing Strategies', 2),
                                           ('Financial Management', 2),
                                           ('Introduction', 3),
                                           ('Typography', 3),
                                           ('Color Theory', 3),
                                           ('Layout Design', 3),
                                           ('Graphic Design Principles', 3),
                                           ('Introduction', 4),
                                           ('Digital Marketing Fundamentals', 4),
                                           ('SEO Optimization', 4),
                                           ('Social Media Marketing', 4),
                                           ('Paid Advertising', 4),
                                           ('Introduction', 5),
                                           ('Mindfulness Practices', 5),
                                           ('Stress Management', 5),
                                           ('Work-Life Balance', 5),
                                           ('Personal Development', 5);

INSERT INTO reviews (date, description, grade, title, course_id, user_id) VALUES
                                                                              ('2023-02-15', 'Great course! The content was well-structured and easy to follow. Highly recommended.', 4.5, 'Excellent Course', 1, 1),
                                                                              ('2023-03-10', 'I found this course very informative and engaging. The instructor explained complex concepts in a clear manner.', 4.0, 'Highly Informative', 1, 2),
                                                                              ('2023-04-05', 'The course exceeded my expectations. The practical exercises helped solidify my understanding of the topics.', 4.8, 'Fantastic Learning Experience', 2, 3),
                                                                              ('2023-02-28', 'This course provided valuable insights into the subject matter. The instructors expertise was evident throughout the lectures.', 4.2, 'Insightful Course', 2, 4),
                                                                              ('2023-03-20', 'I thoroughly enjoyed this course. The real-life examples and case studies made the concepts more relatable.', 4.6, 'Practical and Engaging', 3, 5);

INSERT INTO lessons (description, duration, title, section_id) VALUES
                                                                   ('Introduction to HTML', 30.5, 'Getting Started with HTML', 1),
                                                                   ('CSS Basics', 45.0, 'Fundamentals of CSS', 1),
                                                                   ('JavaScript Fundamentals', 60.0, 'Understanding JavaScript Syntax', 2),
                                                                   ('Working with Arrays', 40.0, 'Manipulating Arrays in JavaScript', 2),
                                                                   ('Database Design Principles', 55.5, 'Introduction to Relational Databases', 3),
                                                                   ('Getting Started with Python', 60.0, 'Python Basics', 4),
                                                                   ('Object-Oriented Programming', 75.5, 'Introduction to OOP', 4),
                                                                   ('Data Types and Variables', 45.0, 'Fundamentals of Data Types', 5),
                                                                   ('Control Flow and Loops', 50.0, 'Mastering Control Structures', 5);

INSERT INTO articles (text, title, lesson_id) VALUES
                                                  ('In this article, we will explore the fundamentals of web development and the technologies involved.', 'Introduction to Web Development', 1),
                                                  ('Learn the latest features and best practices of HTML5 for building modern web applications.', 'Mastering HTML5', 1),
                                                  ('Discover advanced CSS techniques and strategies to create beautiful and responsive web designs.', 'Advanced CSS Techniques', 2),
                                                  ('Dive into the world of JavaScript and learn the core concepts and programming principles.', 'JavaScript Fundamentals', 2),
                                                  ('Explore the principles of database design and optimization for efficient and scalable data management.', 'Database Design and Optimization', 3),
                                                  ('Learn the basics of graphic design and create visually appealing content for your websites and applications.', 'Introduction to Graphic Design', 3),
                                                  ('Discover the principles of user experience (UX) design and how to create intuitive and user-friendly interfaces.', 'Mastering User Experience Design', 4),
                                                  ('Explore the concepts of responsive web design and build websites that adapt to different screen sizes and devices.', 'Responsive Web Design Principles', 4),
                                                  ('Learn the fundamentals of search engine optimization (SEO) and increase the visibility of your websites in search engine results.', 'Introduction to SEO', 5),
                                                  ('Discover the latest trends and techniques in mobile app development and create engaging and user-friendly mobile applications.', 'Mobile App Development Best Practices', 5);

INSERT INTO subscriptions (date_start, course_id, user_id) VALUES
                                                               ('2023-01-15', 2, 1),
                                                               ('2023-02-20', 4, 3),
                                                               ('2023-03-10', 1, 2),
                                                               ('2023-04-05', 5, 4),
                                                               ('2023-05-02', 3, 5);