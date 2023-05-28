create table articles
(
    id        bigserial not null,
    text      varchar(255),
    title     varchar(155),
    lesson_id bigint    not null,
    primary key (id)
)
;

create table carts
(
    id bigserial not null,
    primary key (id)
)
;

create table carts_courses
(
    cart_id   bigint not null,
    course_id bigint not null
)
;

create table categories
(
    id    bigserial not null,
    title varchar(155),
    primary key (id)
)
;

create table comments
(
    id          bigserial not null,
    date        date      not null,
    description varchar(255),
    title       varchar(155),
    lesson_id   bigint    not null,
    user_id     bigint    not null,
    primary key (id)
)
;

create table courses
(
    id          bigserial      not null,
    created     date           not null,
    description varchar(255),
    language    varchar(155),
    name        varchar(155),
    price       numeric(38, 2) not null,
    image_url   varchar(255),
    author_id   bigint         not null,
    category_id bigint,
    primary key (id)
)
;

create table lessons
(
    id          bigserial not null,
    description varchar(255),
    duration    float(53),
    title       varchar(155),
    video_url   varchar(255),
    section_id  bigint    not null,
    primary key (id)
)
;

create table reviews
(
    id          bigserial not null,
    date        date,
    description varchar(255),
    grade       float(53) not null,
    title       varchar(155),
    course_id   bigint    not null,
    user_id     bigint    not null,
    primary key (id)
)
;

create table s3
(
    id          bigserial    not null,
    create_date date         not null,
    size        float(53)    not null,
    url         varchar(255) not null,
    course_id   bigint       not null,
    lesson_id   bigint       not null,
    user_id     bigint,
    primary key (id)
)
;

create table sections
(
    id        bigserial not null,
    name      varchar(155),
    course_id bigint    not null,
    primary key (id)
)
;

create table subscriptions
(
    id            bigserial not null,
    creation_date date      not null,
    course_id     bigint    not null,
    user_id       bigint    not null,
    primary key (id)
)
;

create table users
(
    id                      bigserial not null,
    date_of_birth           date,
    email                   varchar(255),
    full_name               varchar(255),
    image_url               varchar(255),
    password                varchar(255),
    provider                varchar(255),
    reset_token             varchar(255),
    reset_token_expire_time timestamp(6),
    role                    varchar(255),
    status                  varchar(255),
    stripe_account_id       varchar(255),
    cart_id                 bigint,
    primary key (id)
)
;

alter table if exists articles
    add constraint FKq1hdhvqi49qjtqpvqvn5nuquc
        foreign key (lesson_id)
            references lessons
;

alter table if exists carts_courses
    add constraint FK4jok2joejl6vhpqyaeu82qljj
        foreign key (course_id)
            references courses
;

alter table if exists carts_courses
    add constraint FKbcpi453bp5nne2x9l48ye3i2b
        foreign key (cart_id)
            references carts
;

alter table if exists comments
    add constraint FK37jam8u2nwqw9enhv7nqn52e4
        foreign key (lesson_id)
            references lessons
;

alter table if exists comments
    add constraint FK8omq0tc18jd43bu5tjh6jvraq
        foreign key (user_id)
            references users
;

alter table if exists courses
    add constraint FKhbo41uaq9qyi5ora71hq2oyah
        foreign key (author_id)
            references users
;

alter table if exists courses
    add constraint FK72l5dj585nq7i6xxv1vj51lyn
        foreign key (category_id)
            references categories
;

alter table if exists lessons
    add constraint FKgt4502q9pklwr02uqh3qnrppi
        foreign key (section_id)
            references sections
;

alter table if exists reviews
    add constraint FKccbfc9u1qimejr5ll7yuxbtqs
        foreign key (course_id)
            references courses
;

alter table if exists reviews
    add constraint FKcgy7qjc1r99dp117y9en6lxye
        foreign key (user_id)
            references users
;

alter table if exists s3
    add constraint FK1y9a4fq6xl96fy0wqg822f36t
        foreign key (course_id)
            references courses
;

alter table if exists s3
    add constraint FKi3mage2pcaeqosyr9xd8a9i4w
        foreign key (lesson_id)
            references lessons
;

alter table if exists s3
    add constraint FKatv22s7tt8ga7fknl8cpy8i57
        foreign key (user_id)
            references users
;

alter table if exists sections
    add constraint FK7ty9cevpq04d90ohtso1q8312
        foreign key (course_id)
            references courses
;

alter table if exists subscriptions
    add constraint FK9f5yugfr9ro4r0x5ltfr2pb0f
        foreign key (course_id)
            references courses
;

alter table if exists subscriptions
    add constraint FKhro52ohfqfbay9774bev0qinr
        foreign key (user_id)
            references users
;

alter table if exists users
    add constraint FKdv26y3bb4vdmsr89c9ppnx85w
        foreign key (cart_id)
            references carts