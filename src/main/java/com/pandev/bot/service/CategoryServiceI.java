package com.pandev.bot.service;

import com.pandev.bot.entity.Category;
import com.pandev.bot.exception.IncorrectInputException;
import com.pandev.bot.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы с категориями.
 * Реализует методы для добавления, удаления, получения дерева категорий и скачивания данных о категориях в формате Excel.
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceI implements CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     * Добавляет новую категорию в систему.
     * Если указано имя родительской категории, то добавленная категория будет дочерней для указанной родительской категории.
     *
     * @param categoryName       имя категории
     * @param categoryParentName имя родительской категории (может быть пустым)
     * @return строка с результатом выполнения операции
     * @throws IncorrectInputException если имя категории пустое или категория с таким именем уже существует,
     *                                 если родительская категория не найдена
     */
    @Override
    public String addCategory(String categoryName, String categoryParentName) throws IncorrectInputException {
        if (categoryName.isBlank()) {
            throw new IncorrectInputException("Название категории должно содержать хотя бы один символ.");
        } else {
            if (categoryRepository.existsByName(categoryName)) {
                throw new IncorrectInputException("Категория \"" + categoryName + "\" уже существует.");
            }
            Category category = new Category();
            category.setName(categoryName);
            if (!categoryParentName.isBlank()) {
                Category parent = categoryRepository.findByName(categoryParentName)
                        .orElseThrow(() -> new IncorrectInputException("Категории \"" + categoryParentName + "\" не существует"));
                category.setParent(parent);
            }
            categoryRepository.save(category);
        }
        return "Категория \"" + categoryName + "\" успешно добавлена.";
    }

    /**
     * Строит дерево категорий.
     *
     * @return строка, представляющая дерево категорий, или сообщение о том, что дерево пусто
     */
    @Override
    public String buildCategoryTree() {
        List<Category> rootCategories = categoryRepository.findByParentIsNull();

        if (rootCategories.isEmpty()) {
            return "Дерево категорий пусто.";
        }

        StringBuilder treeBuilder = new StringBuilder();
        for (Category root : rootCategories) {
            buildTreeRecursive(root, treeBuilder, 0);
        }

        return treeBuilder.toString();
    }

    /**
     * Удаляет категорию по имени, включая все её дочерние категории.
     *
     * @param categoryName имя категории
     * @return строка с результатом выполнения операции
     * @throws IncorrectInputException если категория с указанным именем не найдена
     */
    @Override
    public String removeCategory(String categoryName) {
        Optional<Category> optionalCategory = categoryRepository.findByName(categoryName);
        if (optionalCategory.isEmpty()) {
            throw new IncorrectInputException("Категория \"" + categoryName + "\" не существует.");
        }

        Category category = optionalCategory.get();

        categoryRepository.delete(category);
        return "Категория \"" + categoryName + "\" и её дочерние элементы удалены.";
    }

    /**
     * Рекурсивно строит строковое представление дерева категорий.
     *
     * @param category    текущая категория
     * @param treeBuilder строка, в которую добавляется информация о категориях
     * @param depth       уровень вложенности текущей категории
     */
    private void buildTreeRecursive(Category category, StringBuilder treeBuilder, int depth) {
        treeBuilder.append("  ".repeat(depth))
                .append("🔹 ")
                .append(category.getName())
                .append("\n");

        List<Category> children = categoryRepository.findByParent(category);
        for (Category child : children) {
            buildTreeRecursive(child, treeBuilder, depth + 1);
        }
    }
}

