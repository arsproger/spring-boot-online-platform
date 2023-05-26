-- ���������� ������ � ������� carts
INSERT INTO carts DEFAULT VALUES;
INSERT INTO carts DEFAULT VALUES;
INSERT INTO carts DEFAULT VALUES;
INSERT INTO carts DEFAULT VALUES;
INSERT INTO carts DEFAULT VALUES;
INSERT INTO carts DEFAULT VALUES;

-- ���������� ������ � ������� users
INSERT INTO users (id, date_of_birth, email, full_name, password, provider, reset_token, reset_token_expire_time, role, status, stripe_account_id, cart_id)
VALUES
    (1, '1990-01-01', 'admin@mail.ru', 'Admin', '$2a$12$.nwSTW70key.0.VxnmpjJ.NquOYmgGH7vbxyMEH.kyv815vdYdcii', 'LOCAL', 'reset1', null, 'ROLE_ADMIN', 'ACTIVE', 'stripe1', 1),
    (2, '1990-01-01', 'user1@mail.ru', 'User 1', '$2a$12$s/jbU496o6DzdqAWWOla1uc5PKPhP8bbv6cFz1MfTVOiK/RxAEXsO', 'LOCAL', 'reset1', null, 'ROLE_STUDENT', 'ACTIVE', 'stripe1', 2),
    (3, '1990-01-02', 'user2@mail.ru', 'User 2', '$2a$12$s/jbU496o6DzdqAWWOla1uc5PKPhP8bbv6cFz1MfTVOiK/RxAEXsO', 'LOCAL', 'reset2', null, 'ROLE_STUDENT', 'ACTIVE', 'stripe2', 3),
    (4, '1990-01-03', 'user3@mail.ru', 'User 3', '$2a$12$s/jbU496o6DzdqAWWOla1uc5PKPhP8bbv6cFz1MfTVOiK/RxAEXsO', 'LOCAL', 'reset3', null, 'ROLE_STUDENT', 'ACTIVE', 'stripe3', 4),
    (5, '1990-01-04', 'user4@mail.ru', 'User 4', '$2a$12$s/jbU496o6DzdqAWWOla1uc5PKPhP8bbv6cFz1MfTVOiK/RxAEXsO', 'LOCAL', 'reset4', null, 'ROLE_STUDENT', 'ACTIVE', 'stripe4', 5),
    (6, '1990-01-05', 'user5@mail.ru', 'User 5', '$2a$12$s/jbU496o6DzdqAWWOla1uc5PKPhP8bbv6cFz1MfTVOiK/RxAEXsO', 'LOCAL', 'reset5', null, 'ROLE_STUDENT', 'ACTIVE', 'stripe5', 6);

-- ���������� ������ � ������� categories
INSERT INTO categories (title)
VALUES
    ('Category 1'),
    ('Category 2'),
    ('Category 3'),
    ('Category 4'),
    ('Category 5');

-- ���������� ������ � ������� courses
INSERT INTO courses (id, created, description, language, name, price, author_id, category_id)
VALUES
    (1, CURRENT_DATE, 'Course 1 description', 'English', 'Course 1', 100.00, 1, 1),
    (2, CURRENT_DATE, 'Course 2 description', 'Spanish', 'Course 2', 200.00, 2, 2),
    (3, CURRENT_DATE, 'Course 3 description', 'French', 'Course 3', 300.00, 3, 3),
    (4, CURRENT_DATE, 'Course 4 description', 'German', 'Course 4', 400.00, 4, 4),
    (5, CURRENT_DATE, 'Course 5 description', 'Italian', 'Course 5', 500.00, 5, 5);

-- ���������� ������ � ������� sections
INSERT INTO sections (name, course_id)
VALUES
    ('Section 1', 1),
    ('Section 2', 1),
    ('Section 3', 2),
    ('Section 4', 2),
    ('Section 5', 3);

-- ���������� ������ � ������� lessons
INSERT INTO lessons (description, duration, title, section_id)
VALUES
    ('Lesson 1 description', 60.5, 'Lesson 1', 1),
    ('Lesson 2 description', 45.25, 'Lesson 2', 1),
    ('Lesson 3 description', 30.75, 'Lesson 3', 2),
    ('Lesson 4 description', 75.0, 'Lesson 4', 2),
    ('Lesson 5 description', 90.0, 'Lesson 5', 3);

-- ���������� ������ � ������� comments
INSERT INTO comments (date, description, title, lesson_id, user_id)
VALUES
    (CURRENT_DATE, 'Comment 1 description', 'Comment 1 title', 1, 1),
    (CURRENT_DATE, 'Comment 2 description', 'Comment 2 title', 2, 2),
    (CURRENT_DATE, 'Comment 3 description', 'Comment 3 title', 3, 3),
    (CURRENT_DATE, 'Comment 4 description', 'Comment 4 title', 4, 4),
    (CURRENT_DATE, 'Comment 5 description', 'Comment 5 title', 5, 5);

-- ���������� ������ � ������� articles
INSERT INTO articles (text, title, lesson_id)
VALUES
    ('Article 1 text', 'Article 1 title', 1),
    ('Article 2 text', 'Article 2 title', 2),
    ('Article 3 text', 'Article 3 title', 3),
    ('Article 4 text', 'Article 4 title', 4),
    ('Article 5 text', 'Article 5 title', 5);

-- ���������� ������ � ������� articles_comments
INSERT INTO articles_comments (article_id, comments_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 3),
    (2, 4),
    (3, 5);

-- ���������� ������ � ������� carts_courses
INSERT INTO carts_courses (cart_id, course_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5);

-- ���������� ������ � ������� reviews
INSERT INTO reviews (date, description, grade, title, course_id, user_id)
VALUES
    (CURRENT_DATE, 'Review 1 description', 4.5, 'Review 1 title', 1, 1),
    (CURRENT_DATE, 'Review 2 description', 3.2, 'Review 2 title', 2, 2),
    (CURRENT_DATE, 'Review 3 description', 2.8, 'Review 3 title', 3, 3),
    (CURRENT_DATE, 'Review 4 description', 4.0, 'Review 4 title', 4, 4),
    (CURRENT_DATE, 'Review 5 description', 5.0, 'Review 5 title', 5, 5);

-- ���������� ������ � ������� s3
INSERT INTO s3 (create_date, size, url, course_id, lesson_id, user_id)
VALUES
    (CURRENT_DATE, 10.5, 'http://example.com/s3/1', 1, 1, 1),
    (CURRENT_DATE, 15.75, 'http://example.com/s3/2', 2, 2, 2),
    (CURRENT_DATE, 20.25, 'http://example.com/s3/3', 3, 3, 3),
    (CURRENT_DATE, 25.0, 'http://example.com/s3/4', 4, 4, 4),
    (CURRENT_DATE, 30.0, 'http://example.com/s3/5', 5, 5, 5);

-- ���������� ������ � ������� subscriptions
INSERT INTO subscriptions (creation_date, course_id, user_id)
VALUES
    (CURRENT_DATE, 1, 1),
    (CURRENT_DATE, 2, 2),
    (CURRENT_DATE, 3, 3),
    (CURRENT_DATE, 4, 4),
    (CURRENT_DATE, 5, 5);
