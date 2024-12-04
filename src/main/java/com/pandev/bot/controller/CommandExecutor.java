package com.pandev.bot.controller;

import com.pandev.bot.controller.command.*;
import com.pandev.bot.exception.IncorrectInputException;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс для выполнения команд в Telegram-боте.
 * <p>
 * Этот класс управляет доступными командами и их исполнением, используя маппинг команд
 * и соответствующих обработчиков команд.
 */
@Component
public class CommandExecutor {

    private final Map<String, Command> commands = new HashMap<>();

    /**
     * Конструктор для инициализации команд и их привязки к соответствующим обработчикам.
     *
     * @param startCommand команда для запуска.
     * @param helpCommand команда помощи.
     * @param addCommand команда для добавления элемента.
     * @param viewTreeCommand команда для просмотра дерева категорий.
     * @param removeCommand команда для удаления элемента.
     */
    public CommandExecutor(StartCommand startCommand,
                           HelpCommand helpCommand,
                           AddCommand addCommand,
                           ViewTreeCommand viewTreeCommand,
                           RemoveCommand removeCommand) {
        commands.put("/start", startCommand);
        commands.put("/help", helpCommand);
        commands.put("/addElement", addCommand);
        commands.put("/viewTree", viewTreeCommand);
        commands.put("/removeElement", removeCommand);
    }

    /**
     * Выполняет команду на основе аргументов и входящего обновления.
     *
     * @param arguments строка аргументов, содержащая команду и ее параметры.
     * @param update объект {@link Update}, содержащий информацию о входящем событии.
     * @return объект {@link SendMessage} с результатом выполнения команды.
     */
    public SendMessage executeCommand(String arguments, Update update) {
        String commandText = arguments.split(" ")[0];

        Command command = commands.getOrDefault(commandText, new UnknownCommand());
        SendMessage sendMessage;
        try {
            sendMessage = command.execute(update);
        } catch (IncorrectInputException e) {
            sendMessage = new SendMessage(update.getMessage().getChatId().toString(), e.getMessage());
            return sendMessage;
        }

        return sendMessage;
    }
}

