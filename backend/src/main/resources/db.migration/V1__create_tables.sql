create table articles (
                          id  bigserial not null,
                          text varchar(255) not null,
                          title varchar(155) not null,
                          lesson_id int8 not null,
                          primary key (id));

create table carts (
    id  bigserial not null,
    primary key (id)
                   );

create table carts_courses
(
    cart_id int8 not null,
    course_id int8 not null
);

create table categories
(
    id  bigserial not null,
    title varchar(155) not null, primary key (id)
);

create table comments
(
    id  bigserial not null,
    date date not null,
    description varchar(255) not null,
    title varchar(155) not null,
    lesson_id int8 not null,
    user_id int8 not null,
    primary key (id)
);
create table courses (
    id  bigserial not null,
    description varchar(255) not null,
    language varchar(155) not null,
    name varchar(155) not null,
    price numeric(19, 2) not null,
    author_id int8 not null,
    category_id int8 not null,
    primary key (id)
                     );
create table lessons (
    id  bigserial not null,
    description varchar(255) not null,
    duration float8 not null,
    title varchar(155) not null,
    section_id int8 not null,
    primary key (id)
                     );


create table reviews (
    id  bigserial not null,
    date date not null,
    description varchar(255) not null,
    title varchar(155) not null,
    course_id int8 not null,
    user_id int8 not null,
    primary key (id)
                     );
create table sections (
    id  bigserial not null,
    name varchar(155) not null,
    course_id int8 not null,
    primary key (id)
                      );
create table subscriptions (
    id  bigserial not null,
    creation_date date not null,
    course_id int8 not null,
    user_id int8 not null,
    primary key (id)
                           );
create table users (
    id  bigserial not null,
    activation_token varchar(255),
    date_of_birth date,
    email varchar(255),
    name varchar(155) not null,
    password varchar(255) not null,
    reset_token varchar(255),
    reset_token_expire_time timestamp,
    role varchar(255), status varchar(255),
    stripe_account_id varchar(255),
    surname varchar(155) not null,
    cart_id int8,
    primary key (id)
                   );

alter table categories
    add constraint UK_tkwloef0k6ccv94cipgnmvma8
        unique (title);

alter table courses
    add constraint UK_5o6x4fpafbywj4v2g0owhh11r
        unique (name);

alter table articles
    add constraint FKq1hdhvqi49qjtqpvqvn5nuquc
        foreign key (lesson_id) references lessons;

alter table carts_courses
    add constraint FK4jok2joejl6vhpqyaeu82qljj
        foreign key (course_id) references courses;

alter table carts_courses
    add constraint FKbcpi453bp5nne2x9l48ye3i2b
        foreign key (cart_id) references carts;

alter table comments
    add constraint FK37jam8u2nwqw9enhv7nqn52e4
        foreign key (lesson_id) references lessons;

alter table comments
    add constraint FK8omq0tc18jd43bu5tjh6jvraq
        foreign key (user_id) references users;

alter table courses
    add constraint FKhbo41uaq9qyi5ora71hq2oyah
        foreign key (author_id) references users;

alter table courses
    add constraint FK72l5dj585nq7i6xxv1vj51lyn
        foreign key (category_id) references categories;

alter table lessons
    add constraint FKgt4502q9pklwr02uqh3qnrppi
        foreign key (section_id) references sections;

alter table reviews
    add constraint FKccbfc9u1qimejr5ll7yuxbtqs
        foreign key (course_id) references courses;

alter table reviews
    add constraint FKcgy7qjc1r99dp117y9en6lxye
        foreign key (user_id) references users;

alter table sections
    add constraint FK7ty9cevpq04d90ohtso1q8312
        foreign key (course_id) references courses;

alter table subscriptions
    add constraint FK9f5yugfr9ro4r0x5ltfr2pb0f
        foreign key (course_id) references courses;

alter table users
    add constraint FKdv26y3bb4vdmsr89c9ppnx85w
        foreign key (cart_id) references carts;

alter table subscriptions
    add constraint FKhro52ohfqfbay9774bev0qinr
        foreign key (user_id) references users;
