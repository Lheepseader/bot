package com.pandev.bot.controller.command;

import com.pandev.bot.exception.IncorrectInputException;
import com.pandev.bot.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Реализация команды для отображения дерева категорий.
 * <p>
 * Команда формирует и возвращает дерево категорий в виде строки.
 */
@Component
@RequiredArgsConstructor
public class ViewTreeCommand implements Command {

    private final CategoryService categoryService;

    /**
     * Выполняет команду отображения дерева категорий.
     *
     * @param update объект {@link Update}, содержащий информацию о входящем сообщении.
     * @return объект {@link SendMessage} с деревом категорий.
     * @throws IncorrectInputException если входные данные некорректны.
     */
    @Override
    public SendMessage execute(Update update) throws IncorrectInputException {
        String tree = categoryService.buildCategoryTree();
        return new SendMessage(update.getMessage().getChatId().toString(), tree);
    }
}
