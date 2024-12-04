package com.pandev.bot.service;


import com.pandev.bot.exception.IncorrectInputException;
import org.telegram.telegrambots.meta.api.objects.InputFile;

/**
 * Сервис для работы с категориями.
 * <p>
 * Этот интерфейс определяет методы для добавления, удаления, отображения и скачивания категорий.
 * Все методы могут выбрасывать исключение IncorrectInputException при некорректных входных данных.
 */
public interface CategoryService {

    /**
     * Добавляет новую категорию в систему.
     * <p>
     * Этот метод добавляет категорию с заданным именем и родителем. Если родитель не указан, категория добавляется как корневая.
     *
     * @param categoryName имя новой категории.
     * @param categoryParentName имя родительской категории (может быть пустым, если категория корневая).
     * @return строка, сообщающая об успешности операции (например, "Категория добавлена").
     * @throws IncorrectInputException если входные данные некорректны, например, категория с таким именем уже существует.
     */
    String addCategory(String categoryName, String categoryParentName) throws IncorrectInputException;

    /**
     * Строит дерево категорий.
     * <p>
     * Метод создает строковое представление иерархии категорий, где категории отображаются в виде дерева.
     *
     * @return строка, представляющая дерево категорий.
     */
    String buildCategoryTree();

    /**
     * Удаляет категорию.
     * <p>
     * Этот метод удаляет категорию и все её дочерние категории из системы.
     *
     * @param categoryName имя категории, которую необходимо удалить.
     * @return строка, сообщающая об успешности операции (например, "Категория удалена").
     */
    String removeCategory(String categoryName);

}