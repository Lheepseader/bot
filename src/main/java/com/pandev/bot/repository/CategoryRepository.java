package com.pandev.bot.repository;

import com.pandev.bot.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для работы с категориями в базе данных.
 * <p>
 * Этот интерфейс расширяет JpaRepository, что позволяет выполнять стандартные операции с сущностью Category
 * (например, сохранение, удаление, поиск и т.д.). Также определяет дополнительные методы для работы с категориями.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * Находит категорию по имени.
     * <p>
     * Метод использует имя категории для поиска и возвращает объект типа Optional.
     * Если категория с таким именем не найдена, возвращается пустой Optional.
     *
     * @param name имя категории.
     * @return Optional<Category> с категорией или пустой Optional, если категория не найдена.
     */
    Optional<Category> findByName(String name);

    /**
     * Проверяет, существует ли категория с указанным именем.
     * <p>
     * Метод возвращает true, если категория с таким именем существует в базе данных, иначе false.
     *
     * @param name имя категории.
     * @return true, если категория с таким именем существует, иначе false.
     */
    boolean existsByName(String name);

    /**
     * Находит все корневые категории (категории без родителя).
     * <p>
     * Метод возвращает список всех категорий, у которых родительская категория равна null.
     *
     * @return список категорий, у которых нет родителя.
     */
    List<Category> findByParentIsNull();

    /**
     * Находит все дочерние категории для указанной родительской категории.
     * <p>
     * Метод возвращает список всех категорий, которые имеют указанную категорию как родителя.
     *
     * @param parent родительская категория.
     * @return список дочерних категорий для указанной родительской категории.
     */
    List<Category> findByParent(Category parent);
}
