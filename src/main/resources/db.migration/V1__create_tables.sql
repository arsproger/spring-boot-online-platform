create table articles
(
    id        bigserial not null,
    text      varchar(255),
    title     varchar(255),
    lesson_id int8,
    primary key (id)
);


create table articles_comments
(
    article_id  int8 not null,
    comments_id int8 not null
);


create table carts
(
    id bigserial not null,
    primary key (id)
);


create table carts_courses
(
    cart_id   int8 not null,
    course_id int8 not null
);


create table categories
(
    id    bigserial not null,
    title varchar(255),
    primary key (id)
);


create table comments
(
    id          bigserial not null,
    date        date,
    description varchar(255),
    title       varchar(255),
    lesson_id   int8,
    user_id     int8,
    primary key (id)
);


create table courses
(
    id          bigserial not null,
    description varchar(255),
    language    varchar(255),
    name        varchar(255),
    price       numeric(19, 2),
    author_id   int8,
    category_id int8,
    primary key (id)
);


create table lessons
(
    id          bigserial not null,
    description varchar(255),
    duration    float8,
    title       varchar(255),
    section_id  int8,
    primary key (id)
);


create table reviews
(
    id          bigserial not null,
    date        date,
    description varchar(255),
    title       varchar(255),
    course_id   int8,
    user_id     int8,
    primary key (id)
);


create table sections
(
    id        bigserial not null,
    name      varchar(255),
    course_id int8,
    primary key (id)
);


create table subscriptions
(
    id          bigserial not null,
    date_finish date,
    date_start  date,
    course_id   int8,
    user_id     int8,
    primary key (id)
);


create table users
(
    id                      bigserial not null,
    activation_token        varchar(255),
    date_of_birth           date,
    email                   varchar(255),
    name                    varchar(255),
    password                varchar(255),
    reset_token             varchar(255),
    reset_token_expire_time timestamp,
    role                    varchar(255),
    status                  varchar(255),
    surname                 varchar(255),
    cart_id                 int8,
    primary key (id)
);


alter table articles_comments
    add constraint UK_h4ki8ctrhw6tr0fec5htykuou unique (comments_id);


alter table articles
    add constraint FKq1hdhvqi49qjtqpvqvn5nuquc
        foreign key (lesson_id)
            references lessons;


alter table articles_comments
    add constraint FKd1pqh7wlw1y45u16c4w76pmq9
        foreign key (comments_id)
            references comments;


alter table articles_comments
    add constraint FKe2biwg5ju6rys8b172b1t3ey1
        foreign key (article_id)
            references articles;


alter table carts_courses
    add constraint FK4jok2joejl6vhpqyaeu82qljj
        foreign key (course_id)
            references courses;


alter table carts_courses
    add constraint FKbcpi453bp5nne2x9l48ye3i2b
        foreign key (cart_id)
            references carts;


alter table comments
    add constraint FK37jam8u2nwqw9enhv7nqn52e4
        foreign key (lesson_id)
            references lessons;


alter table comments
    add constraint FK8omq0tc18jd43bu5tjh6jvraq
        foreign key (user_id)
            references users;


alter table courses
    add constraint FKhbo41uaq9qyi5ora71hq2oyah
        foreign key (author_id)
            references users;


alter table courses
    add constraint FK72l5dj585nq7i6xxv1vj51lyn
        foreign key (category_id)
            references categories;


alter table lessons
    add constraint FKgt4502q9pklwr02uqh3qnrppi
        foreign key (section_id)
            references sections;


alter table reviews
    add constraint FKccbfc9u1qimejr5ll7yuxbtqs
        foreign key (course_id)
            references courses;


alter table reviews
    add constraint FKcgy7qjc1r99dp117y9en6lxye
        foreign key (user_id)
            references users;


alter table sections
    add constraint FK7ty9cevpq04d90ohtso1q8312
        foreign key (course_id)
            references courses;


alter table subscriptions
    add constraint FK9f5yugfr9ro4r0x5ltfr2pb0f
        foreign key (course_id)
            references courses;


alter table subscriptions
    add constraint FKhro52ohfqfbay9774bev0qinr
        foreign key (user_id)
            references users;


alter table users
    add constraint FKdv26y3bb4vdmsr89c9ppnx85w
        foreign key (cart_id)
            references carts;