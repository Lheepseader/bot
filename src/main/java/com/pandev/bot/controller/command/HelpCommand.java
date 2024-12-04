package com.pandev.bot.controller.command;

import com.pandev.bot.controller.Constants;
import com.pandev.bot.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Реализация команды помощи для Telegram-бота.
 * <p>
 * Команда возвращает сообщение с перечнем доступных команд и их описанием.
 */
@Component
@RequiredArgsConstructor
public class HelpCommand implements Command {

    private final CategoryService categoryService;

    /**
     * Выполняет команду помощи, отправляя пользователю список доступных команд.
     *
     * @param update объект {@link Update}, содержащий информацию о входящем событии.
     * @return объект {@link SendMessage} с текстом списка доступных команд.
     */
    @Override
    public SendMessage execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        return new SendMessage(chatId, Constants.HELP_COMMAND);
    }
}

