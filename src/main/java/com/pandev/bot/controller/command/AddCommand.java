package com.pandev.bot.controller.command;

import com.pandev.bot.exception.IncorrectInputException;
import com.pandev.bot.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.message.Message;

/**
 * Реализация команды для добавления новой категории.
 * <p>
 * Обрабатывает входящие сообщения от пользователя и вызывает метод сервиса
 * для добавления категории. Поддерживает добавление как основной, так и дочерней категории.
 */
@Component
@RequiredArgsConstructor
public class AddCommand implements Command {

    private final CategoryService categoryService;

    /**
     * Выполняет команду добавления категории.
     *
     * @param update объект {@link Update}, содержащий сообщение от пользователя.
     * @return объект {@link SendMessage} с результатом выполнения команды.
     * @throws IncorrectInputException если количество аргументов некорректно.
     */
    @Override
    public SendMessage execute(Update update) throws IncorrectInputException {
        Message message = update.getMessage();
        String chatId = message.getChatId().toString();
        String text = message.getText();
        String categoryParentName = "";
        String categoryName;

        String[] arguments = text.split(" ");

        switch (arguments.length) {
            case 3: {
                categoryParentName = arguments[1];
                categoryName = arguments[2];
                break;
            }
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

        String answer = categoryService.addCategory(categoryName, categoryParentName);
        return new SendMessage(chatId, answer);
    }
}
