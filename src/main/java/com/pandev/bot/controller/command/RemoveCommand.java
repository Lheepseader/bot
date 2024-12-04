package com.pandev.bot.controller.command;

import com.pandev.bot.exception.IncorrectInputException;
import com.pandev.bot.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.message.Message;


/**
 * Реализация команды для удаления категории.
 * <p>
 * Команда удаляет указанную категорию и возвращает результат выполнения операции.
 */
@Component
@RequiredArgsConstructor
public class RemoveCommand implements Command {

    private final CategoryService categoryService;

    /**
     * Выполняет команду удаления категории.
     *
     * @param update объект {@link Update}, содержащий сообщение от пользователя.
     * @return объект {@link SendMessage} с результатом выполнения команды.
     * @throws IncorrectInputException если входные данные некорректны или не указано название категории.
     */
    @Override
    public SendMessage execute(Update update) throws IncorrectInputException {
        Message message = update.getMessage();
        String chatId = message.getChatId().toString();
        String text = message.getText();
        String categoryName;

        String[] arguments = text.split(" ");

        switch (arguments.length) {
            case 2: {
                categoryName = arguments[1];
                break;
            }
            case 1: {
                throw new IncorrectInputException("Необходимо ввести название категории через пробел.");
            }
            default: {
                throw new IncorrectInputException("Максимум два аргумента.");
            }
        }

        String answer = categoryService.removeCategory(categoryName);
        return new SendMessage(chatId, answer);
    }
}

