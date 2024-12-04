package com.pandev.bot.controller.command;

import com.pandev.bot.exception.IncorrectInputException;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Функциональный интерфейс для выполнения команд в Telegram-боте.
 * <p>
 * Каждая команда должна реализовать метод {@link #execute(Update)},
 * который обрабатывает входящее обновление и возвращает сообщение с результатом.
 */
@FunctionalInterface
public interface Command {

    /**
     * Выполняет команду на основе переданного обновления.
     *
     * @param update объект {@link Update}, содержащий информацию о входящем событии в боте.
     * @return объект {@link SendMessage} с результатом выполнения команды.
     * @throws IncorrectInputException если входные данные некорректны для выполнения команды.
     */
    SendMessage execute(Update update) throws IncorrectInputException;
}
