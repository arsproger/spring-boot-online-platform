INSERT INTO carts DEFAULT
VALUES;
INSERT INTO carts DEFAULT
VALUES;
INSERT INTO carts DEFAULT
VALUES;
INSERT INTO carts DEFAULT
VALUES;
INSERT INTO carts DEFAULT
VALUES;
INSERT INTO carts DEFAULT
VALUES;

INSERT INTO users (date_of_birth, email, full_name, password, image_url, provider, role, status, cart_id)
VALUES ('1985-12-02', 'admin@mail.ru', 'Admin', '$2a$12$l6lPWCv3wejHwIQ4ooZeLuHJn8H6EcZAYRxm8XSxgyDTzIyAvFy9a', 'default-course-image.jpg',
        'LOCAL', 'ROLE_STUDENT', 'ACTIVE', 2),
       ('1990-05-15', 'user1@mail.ru', 'John Doe', '$2a$12$eYmYCplG.4GUqIR.hkoPpOWA1arpFak9uwjoqkojo5bY51PmfaCZe', 'default-course-image.jpg',
        'LOCAL', 'ROLE_STUDENT', 'ACTIVE', 1),
       ('1985-12-02', 'user2@mail.ru', 'Jane Smith', '$2a$12$eYmYCplG.4GUqIR.hkoPpOWA1arpFak9uwjoqkojo5bY51PmfaCZe', 'default-course-image.jpg',
        'LOCAL', 'ROLE_STUDENT', 'ACTIVE', 2),
       ('1978-09-20', 'user3@mail.ru', 'Mike Johnson', '$2a$12$eYmYCplG.4GUqIR.hkoPpOWA1arpFak9uwjoqkojo5bY51PmfaCZe', 'default-course-image.jpg',
        'LOCAL', 'ROLE_STUDENT', 'ACTIVE', 3),
       ('1992-06-10', 'user4@mail.ru', 'Emily Williams', '$2a$12$eYmYCplG.4GUqIR.hkoPpOWA1arpFak9uwjoqkojo5bY51PmfaCZe', 'default-course-image.jpg',
        'LOCAL', 'ROLE_STUDENT', 'ACTIVE', 4),
       ('1987-03-25', 'user5@mail.ru', 'David Brown', '$2a$12$eYmYCplG.4GUqIR.hkoPpOWA1arpFak9uwjoqkojo5bY51PmfaCZe', 'default-course-image.jpg',
        'LOCAL', 'ROLE_STUDENT', 'ACTIVE', 5),
       ('1995-11-08', 'user6@mail.ru', 'Sarah Jones', '$2a$12$eYmYCplG.4GUqIR.hkoPpOWA1arpFak9uwjoqkojo5bY51PmfaCZe', 'default-course-image.jpg',
        'LOCAL', 'ROLE_STUDENT', 'ACTIVE', 6);

INSERT INTO categories (title)
VALUES ('Web Development'),
       ('Design'),
       ('Programming'),
       ('Marketing'),
       ('Music'),
       ('Business');

INSERT INTO courses (created, description, language, name, price, author_id, category_id)
VALUES ('2023-05-25', 'Learn web development using the latest technologies', 'English', 'Web Development Bootcamp',
        99.99, 1, 1),
       ('2023-05-26', 'A comprehensive guide to mastering Photoshop', 'English', 'Photoshop Masterclass', 49.99, 2, 2),
       ('2023-05-27', 'Beginner-friendly course to learn Python programming', 'English', 'Python for Beginners', 79.99,
        3, 3),
       ('2023-05-28', 'Master the art of digital marketing and grow your business', 'English',
        'Digital Marketing Mastery', 149.99, 4, 4),
       ('2023-05-29', 'Learn to play the guitar like a pro', 'English', 'Guitar Mastery: From Beginner to Advanced',
        69.99, 5, 5),
       ('2023-05-30', 'Discover the secrets of successful entrepreneurship', 'English', 'Entrepreneurship 101', 129.99,
        6, 6);

INSERT INTO sections (name, course_id)
VALUES ('Introduction to Programming', 1),
       ('Data Types and Variables', 1),
       ('Web Development Basics', 2),
       ('HTML and CSS Fundamentals', 2),
       ('Introduction to Mathematics', 3),
       ('Algebraic Expressions', 3),
       ('Introduction to Machine Learning', 4),
       ('Supervised Learning Algorithms', 4),
       ('Financial Accounting Principles', 5),
       ('Budgeting and Financial Planning', 5),
       ('Introduction to Graphic Design', 6),
       ('Color Theory and Typography', 6);

INSERT INTO lessons (description, duration, title, section_id)
VALUES ('Introduction to Programming Concepts', 30.5, 'Variables and Data Types', 1),
       ('Conditional Statements and Loops', 45.2, 'Functions and Methods', 1),
       ('HTML Basics and Structure', 22.8, 'CSS Styling and Selectors', 3),
       ('Responsive Web Design', 35.9, 'Introduction to JavaScript', 3),
       ('Linear Equations and Inequalities', 40.3, 'Polynomials and Factoring', 5),
       ('Exponents and Radicals', 28.6, 'Systems of Equations', 5),
       ('Introduction to Regression Analysis', 55.7, 'Decision Trees and Random Forests', 7),
       ('Logistic Regression', 48.9, 'Support Vector Machines', 7),
       ('Basic Accounting Principles', 30.1, 'Financial Statements Analysis', 9),
       ('Cost Accounting', 42.5, 'Managerial Accounting', 9),
       ('Introduction to Design Principles', 25.4, 'Layout and Composition', 11),
       ('Typography and Font Selection', 36.2, 'Color Theory and Color Schemes', 11),
       ('Marketing Fundamentals', 35.7, 'Market Research and Analysis', 2),
       ('Digital Marketing Strategies', 42.9, 'Search Engine Optimization (SEO)', 2),
       ('HTML5 and CSS3', 28.3, 'JavaScript Fundamentals', 4),
       ('DOM Manipulation and Events', 39.8, 'jQuery Essentials', 4),
       ('Quadratic Equations and Functions', 33.6, 'Exponential and Logarithmic Functions', 6),
       ('Rational Expressions and Equations', 47.2, 'Radical Expressions and Equations', 6),
       ('Introduction to Classification Models', 52.1, 'Naive Bayes Classifier', 8),
       ('Neural Networks and Deep Learning', 43.5, 'Reinforcement Learning', 8),
       ('Financial Planning and Analysis', 38.2, 'Financial Risk Management', 10),
       ('Auditing and Assurance', 41.7, 'Taxation Principles', 10),
       ('Color Psychology and Branding', 31.9, 'Layout and Grid Systems', 12),
       ('Logo Design and Brand Identity', 37.6, 'Digital Illustration Techniques', 12);

INSERT INTO articles (text, title, lesson_id)
VALUES ('In this article, we will explore the basics of programming and its importance in today''s digital world. We will discuss key concepts such as variables, data types, and control structures. By the end of this lesson, you will have a solid understanding of the fundamental building blocks of programming.',
        'Introduction to Programming Concepts', 1),
       ('Now that we''ve covered the basics, let''s dive deeper into the concept of variables. Variables are used to store and manipulate data in a program. We will learn about different data types, variable declaration, and assignment. Understanding variables is crucial for writing efficient and flexible code.',
        'Understanding Variables', 1);
INSERT INTO articles (text, title, lesson_id)
VALUES ('Conditional statements allow us to control the flow of our program based on certain conditions. In this article, we will explore if statements, switch statements, and the concept of branching. We will also discuss loops, which are used to repeat a set of instructions. By the end of this lesson, you will be able to make your programs more dynamic and responsive.',
        'Introduction to Conditional Statements', 2),
       ('Loops are essential in programming as they enable us to perform repetitive tasks efficiently. In this article, we will cover different types of loops, including the for loop, while loop, and do-while loop. We will also discuss loop control statements such as break and continue. Mastering loops will greatly enhance your ability to automate processes and iterate over data.',
        'Looping Structures in Programming', 2);
INSERT INTO articles (text, title, lesson_id)
VALUES ('HTML is the backbone of the web. In this article, we will introduce you to the basics of HTML and its structure. You will learn about HTML tags, elements, attributes, and how to create the structure of a web page. Understanding HTML is essential for building web applications and creating content for the internet.',
        'HTML Basics and Structure', 3),
       ('Now that we have a solid understanding of HTML, let''s delve into more advanced topics. In this article, we will explore HTML forms, tables, and semantic tags. These elements are crucial for creating interactive web pages and organizing content effectively. By the end of this lesson, you will have a solid foundation in HTML and be ready to take your web development skills to the next level.',
        'Advanced HTML Concepts', 3);
INSERT INTO articles (text, title, lesson_id)
VALUES ('Object-oriented programming (OOP) is a powerful programming paradigm used in many modern programming languages. In this article, we will introduce you to the key concepts of OOP, such as classes, objects, inheritance, and encapsulation. Understanding OOP will enable you to write modular and reusable code.',
        'Introduction to Object-Oriented Programming', 4),
       ('Now that we have a grasp of the basics, let''s dive deeper into the concept of inheritance. Inheritance allows us to create classes that inherit properties and behaviors from other classes. We will explore single inheritance, multiple inheritance, and method overriding. By the end of this lesson, you will have a solid understanding of inheritance and its benefits in OOP.',
        'Understanding Inheritance in OOP', 4);
INSERT INTO articles (text, title, lesson_id)
VALUES ('Databases are essential for storing and managing large amounts of data. In this article, we will discuss the fundamentals of database design and the SQL language. You will learn how to create tables, define relationships between them, and perform basic CRUD (Create, Read, Update, Delete) operations. Understanding databases and SQL will open up a wide range of opportunities in software development.',
        'Introduction to Database Design', 5),
       ('Now that we have a solid foundation in SQL, let''s explore more advanced topics. In this article, we will dive deeper into SQL queries, covering topics such as joins, subqueries, and aggregate functions. These advanced SQL techniques are crucial for working with complex databases and extracting meaningful insights from data.',
        'Advanced SQL Techniques', 5);
INSERT INTO articles (text, title, lesson_id)
VALUES ('Responsive web design is an essential skill for modern web developers. In this article, we will introduce you to the concepts of responsive design and media queries. You will learn how to create websites that adapt to different screen sizes and devices, providing an optimal user experience. By the end of this lesson, you will be able to build responsive and mobile-friendly websites.',
        'Introduction to Responsive Web Design', 6),
       ('Now that we have covered the basics, let''s explore more advanced techniques in responsive web design. In this article, we will discuss CSS frameworks, such as Bootstrap, and their role in building responsive websites. We will also cover techniques for optimizing web performance and handling browser compatibility issues. Mastering responsive web design will make your websites stand out in today''s digital landscape.',
        'Advanced Responsive Web Design Techniques', 6);
INSERT INTO articles (text, title, lesson_id)
VALUES ('Data structures are fundamental building blocks in computer science. In this article, we will introduce you to various data structures, such as arrays, linked lists, stacks, queues, and trees. Understanding data structures will empower you to write efficient algorithms and solve complex problems. Get ready to dive into the world of data structures!',
        'Introduction to Data Structures', 7),
       ('Now that we have a solid understanding of basic data structures, let''s explore more advanced ones. In this article, we will cover topics such as hash tables, graphs, and heaps. These advanced data structures play a crucial role in solving complex computational problems and optimizing algorithms. By the end of this lesson, you will have a strong foundation in data structures.',
        'Advanced Data Structures', 7);
INSERT INTO articles (text, title, lesson_id)
VALUES ('Machine learning is revolutionizing various industries, from healthcare to finance. In this article, we will introduce you to the basics of machine learning, including supervised learning, unsupervised learning, and reinforcement learning. You will learn how to train models, make predictions, and evaluate their performance. Join us on this exciting journey into the world of machine learning!',
        'Introduction to Machine Learning', 8),
       ('Now that we understand the fundamentals, let''s explore more advanced concepts in machine learning. In this article, we will cover topics such as neural networks, deep learning, and natural language processing. These advanced techniques have the power to solve complex problems and make intelligent predictions. By the end of this lesson, you will have a deeper understanding of machine learning.',
        'Advanced Machine Learning Techniques', 8);
INSERT INTO articles (text, title, lesson_id)
VALUES ('Mobile app development is in high demand, with millions of apps available on app stores. In this article, we will introduce you to the world of mobile app development. We will cover different platforms, such as iOS and Android, and discuss the tools and frameworks used in mobile app development. By the end of this lesson, you will have a clear understanding of the mobile app development landscape.',
        'Introduction to Mobile App Development', 9),
       ('Now that we have a grasp of the basics, let''s dive deeper into mobile app development. In this article, we will cover topics such as user interface design, app navigation, and data storage. These are crucial aspects of building successful mobile apps. Get ready to take your mobile app development skills to the next level!',
        'Advanced Mobile App Development', 9);

INSERT INTO comments (date, description, title, lesson_id, user_id)
VALUES ('2023-05-01', 'Great lesson!', 'Amazing Course', 1, 1),
       ('2023-05-02', 'Very informative!', 'Excellent Explanation', 1, 2),
       ('2023-05-03', 'I learned a lot from this lesson.', 'Highly Recommended', 2, 3),
       ('2023-05-04', 'The content was well-structured.', 'Thumbs up!', 2, 1),
       ('2023-05-05', 'Clear and concise explanations.', 'Great Instructor', 3, 4),
       ('2023-05-06', 'I enjoyed every minute of this lesson.', 'Awesome Content', 3, 2),
       ('2023-05-07', 'The examples provided were very helpful.', 'Fantastic Course', 4, 3),
       ('2023-05-08', 'I would definitely recommend this lesson.', 'Well Worth It', 4, 4),
       ('2023-05-09', 'The concepts were explained in an easy-to-understand manner.', 'Impressive Teaching', 5, 1),
       ('2023-05-10', 'I found this lesson to be extremely useful.', 'Great Learning Experience', 5, 3);

INSERT INTO reviews (date, description, grade, title, course_id, user_id)
VALUES ('2023-05-01', 'Great course, highly recommended!', 4.5, 'Excellent Course', 1, 1),
       ('2023-05-02', 'The content was well-explained and engaging.', 4.2, 'Informative and Interesting', 1, 2),
       ('2023-05-03', 'I learned a lot from this course.', 4.8, 'Highly Informative', 2, 3),
       ('2023-05-04', 'The instructor was knowledgeable and helpful.', 4.7, 'Great Instructor', 2, 1),
       ('2023-05-05', 'This course exceeded my expectations.', 4.9, 'Fantastic Learning Experience', 3, 4),
       ('2023-05-06', 'I would recommend this course to anyone.', 4.5, 'Highly Recommended', 3, 2),
       ('2023-05-07', 'The course material was well-organized and easy to follow.', 4.6, 'Well-Structured Content', 4,
        3),
       ('2023-05-08', 'I really enjoyed the practical exercises.', 4.3, 'Great Hands-On Experience', 4, 4),
       ('2023-05-09', 'The concepts were explained clearly and concisely.', 4.7, 'Clear and Informative', 5, 1),
       ('2023-05-10', 'I found the course to be extremely valuable.', 4.9, 'Highly Valuable Content', 5, 3);


INSERT INTO subscriptions (creation_date, course_id, user_id)
VALUES ('2023-05-01', 1, 1),
       ('2023-05-02', 2, 2),
       ('2023-05-03', 3, 3),
       ('2023-05-04', 4, 4),
       ('2023-05-05', 5, 5);

INSERT INTO carts_courses (cart_id, course_id)
VALUES (1, 1),
       (1, 2),
       (2, 3),
       (2, 4),
       (3, 5),
       (3, 6);