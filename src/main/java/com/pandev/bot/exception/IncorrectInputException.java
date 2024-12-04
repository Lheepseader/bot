package com.pandev.bot.exception;

/**
 * Исключение, которое выбрасывается при некорректном вводе данных.
 * <p>
 * Это исключение используется для обработки случаев, когда пользователь вводит данные в неверном формате
 * или нарушает правила ввода, установленные в приложении.
 */
public class IncorrectInputException extends RuntimeException {

    /**
     * Конструктор, который инициализирует исключение с сообщением.
     *
     * @param message сообщение, которое будет связано с исключением.
     */
    public IncorrectInputException(String message) {
        super(message);
    }
}
