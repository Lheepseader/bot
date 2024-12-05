# Telegram Category Bot

Этот проект представляет собой Telegram-бота для управления категориями. Для успешного запуска необходимо настроить параметры конфигурации.

## Требования

- Java 17+
- PostgreSQL
- Maven

## Настройка проекта

Перед запуском проекта необходимо изменить следующие параметры конфигурации в файле `application.properties`:

```properties
# Конфигурация Telegram-бота
telegram.bot.username=bot           # Укажите имя пользователя вашего бота
telegram.bot.token=token            # Укажите токен вашего бота, полученный от BotFather

# Конфигурация базы данных PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/telegram_bot_db  # Укажите URL вашей базы данных
spring.datasource.username=username                                     # Укажите имя пользователя для подключения к БД
spring.datasource.password=password                                     # Укажите пароль для подключения к БД
