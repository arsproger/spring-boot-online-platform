# Онлайн Платформа 
# Платформа позволяет пользователям зарегистрироваться, выбрать курсы, просматривать видеоуроки. 
# Также платформа предоставляет возможность добавлять и управлять курсами, производить оплату через платежную систему Stripe, загружать видео для уроков. 
# Для хранения файлов использовали AWS, а при получении файлов использовали кэширование для оптимизации запросов.

## Стек технологий: Java, Spring Framework, PostgreSQL, JUnit5, Mockito, Flyway, OpenAPI, OAuth2, Stripe, Amazon S3.

# База данных

В качестве базы данных для этого проекта использовался PostgreSQL. Для создания связей между таблицами был использован
Hibernate ORM и JdbcTemplate.

# Сборщик

В качестве сборщика использовался Maven.

# Авторизация

Для авторизации была использована Spring Security и OAuth2 через Google и GitHub.

# Функционал

### Проект реализует следующий функционал:

* Принимает HTTP запросы и отвечает на них соответствующими статусами.

* Сохраняет все объекты в базе данных.

### Реализует следующие запросы:

* GET /city — получение всех городов из базы
* POST /auth/register - регистрация для пользователя
* POST /auth/login - вход для пользователя
* GET /cart - получение корзины текущего пользователя
* GET /cart/sum - получение итоговой суммы корзины текущего пользователя
* GET /category/title/{title} - получение категорий по заголовку
* GET /comment/lesson/{lessonId} - получение комментариев по id урока
* GET /course/author/{id} - получение всех курсов по id автора
* GET /course/filter/price - фильтрация курса по цене и категории
* GET /course/language - получение всех курсов по определенному языку и категории
* GET /course/category - получение всех курсов по определенной категории
* GET /course/name/{name} - получение курсов по имени
* GET /course/duration/{courseId} - получение длительности курса по его id
* GET /course/count - получение количества всех курсов
* GET /lesson/section/{sectionId} - получение уроков по id раздела
* POST /password/reset - Запрос на восстановление пароля
* POST /password/reset/{resetToken} - проверка и установление нового пароля
* POST /stripe/pay - оплата для курса
* GET /review/author/{authorId} - получение всех отзывов определенного автора
* GET /review/course/{courseId} - получение отзывов определенного курса
* GET /review/course/avg-grade/{courseId} - получение средней оценки курса по его id
* GET /review/count - получение количества всех отзывов
* POST /s3/upload/video - загрузка видео для урока на сервер
* POST /s3/upload/course/image - загрузка фото для курса на сервер
* POST /s3/upload/user/image - загрузка фото для пользователя на сервер
* GET /s3/download/{filename:.+} - получение файла из сервера по его имени
* DELETE /s3/{filename} - удаление видео из сервера по его имени
* GET /s3/list - получение всех загруженных файлов с сервера
* GET /section/course/{id} - получение всех разделов определенного курса
* GET /subscription/user - получение всех подписок текущего пользователя
* GET /user/current - получение текущего авторизованного пользователя
* GET /user/course/{courseId} - получение всех пользователей курса
* GET /user/count - получение количества всех пользователей
* GET /user/count/today - получение количества всех пользователей зарегистрированных сегодня

# Объекты

## Проект включает следующие сущности:

### Article - Сущность статьи
### Cart - Сущность для корзины пользователя
### Category - Сущность категории
### Comment - Сущность комментария
### Course - Сущность курса
### Lesson - Сущность урока
### Review - Сущность отзыва
### Section - Сущность раздела
### Subscription - Сущность подписки
### User - Сущность пользователя

# Подготовительные действия

## Для успешной работы проекта необходимо иметь следующие инструменты:

* JDK 8 или выше

* Spring Boot 3.0.0 или выше

* База данных PostgreSQL

* Сборщик проекта Maven

# Для установки необходимых инструментов можно воспользоваться соответствующими инструкциями, доступными на официальных сайтах.

# Запуск проекта

*  Клонируйте репозиторий с проектом на свой компьютер.

*  Убедитесь, что на вашем компьютере установлены Java (версия 8 и выше) и Maven.

*  Откройте терминал и перейдите в директорию с проектом.

*  Выполните команду mvn clean install, Maven загрузит все зависимости и соберет ваш проект.

*  Запустите проект, выполнив команду mvn spring-boot:run, Spring Boot запустит ваше приложение, и вы увидите вывод в консоли.

*  После запуска проект будет доступен по адресу http://localhost:8080/.

*  Для доступа к защищенным ресурсам необходимо авторизоваться.

# Дополнительные комментарии:

Для работы с базой данных в проекте используется Spring Data JPA.
Если вы захотите использовать другую реляционную
СУБД, необходимо будет изменить настройки в файле application.properties.

Postman коллекции:
...
