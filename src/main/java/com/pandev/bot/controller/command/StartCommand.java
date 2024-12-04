package com.pandev.bot.controller.command;

import com.pandev.bot.controller.Constants;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Реализация команды для запуска Telegram-бота.
 * <p>
 * Команда отправляет приветственное сообщение при первом запуске бота пользователем.
 */
@Component
public class StartCommand implements Command {

    /**
     * Выполняет команду запуска, отправляя приветственное сообщение пользователю.
     *
     * @param update объект {@link Update}, содержащий информацию о входящем событии.
     * @return объект {@link SendMessage} с приветственным сообщением.
     */
    @Override
    public SendMessage execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        return new SendMessage(chatId, Constants.START_COMMAND);
    }
}
