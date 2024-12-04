package com.pandev.bot.controller.command;

import com.pandev.bot.controller.Constants;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Реализация команды для обработки неизвестных или неподдерживаемых команд.
 * <p>
 * Команда возвращает сообщение об ошибке, если пользователь ввел команду, которую бот не распознает.
 */
@Component
public class UnknownCommand implements Command {

    /**
     * Выполняет команду обработки неизвестной команды, отправляя сообщение с уведомлением об ошибке.
     *
     * @param update объект {@link Update}, содержащий информацию о входящем событии.
     * @return объект {@link SendMessage} с уведомлением об ошибке и инструкцией для пользователя.
     */
    @Override
    public SendMessage execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        return new SendMessage(chatId, Constants.UNKNOWN_COMMAND);
    }
}
