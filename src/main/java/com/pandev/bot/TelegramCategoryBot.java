package com.pandev.bot;

import com.pandev.bot.controller.CommandExecutor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

/**
 * Бот для работы с Telegram, который обрабатывает команды и сообщения от пользователей.
 * Использует {@link CommandExecutor} для выполнения команд и отправки ответов.
 */
@Component
@Getter
public class TelegramCategoryBot implements SpringLongPollingBot, LongPollingSingleThreadUpdateConsumer {

    private final CommandExecutor commandExecutor;
    private final String botName;
    private final String botToken;
    private final TelegramClient telegramClient;

    /**
     * Конструктор для создания бота с параметрами.
     *
     * @param commandExecutor объект, отвечающий за выполнение команд
     * @param botName имя бота, полученное из конфигурации
     * @param botToken токен бота, полученный из конфигурации
     */
    public TelegramCategoryBot(CommandExecutor commandExecutor, @Value("${telegram.bot.username}") String botName,
                               @Value("${telegram.bot.token}") String botToken) {
        this.commandExecutor = commandExecutor;
        this.botName = botName;
        this.botToken = botToken;
        this.telegramClient = new OkHttpTelegramClient(botToken);
    }

    /**
     * Метод для обработки входящих обновлений (сообщений) от пользователей.
     * Если сообщение содержит текст, оно передается в {@link CommandExecutor} для обработки,
     * после чего результат отправляется обратно пользователю через {@link TelegramClient}.
     *
     * @param update обновление, содержащее сообщение от пользователя
     */
    @Override
    public void consume(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            SendMessage sendMessage = commandExecutor.executeCommand(messageText, update);
            try {
                telegramClient.execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Возвращает объект, который будет использоваться для получения обновлений от Telegram API.
     *
     * @return объект {@link LongPollingUpdateConsumer}, который обрабатывает обновления
     */
    @Override
    public LongPollingUpdateConsumer getUpdatesConsumer() {
        return this;
    }
}

