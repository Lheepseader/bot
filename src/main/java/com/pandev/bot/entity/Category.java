package com.pandev.bot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Представляет категорию в дереве категорий.
 * <p>
 * Этот класс используется для хранения данных о категориях, включая их идентификатор, имя, родительскую категорию и дочерние категории.
 * Сущность хранится в базе данных с использованием JPA.
 */
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    /**
     * Уникальный идентификатор категории.
     * <p>
     * Генерируется автоматически с использованием последовательности.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    /**
     * Название категории.
     * <p>
     * Название категории уникально и не может быть пустым.
     */
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * Родительская категория.
     * <p>
     * Ссылка на родительскую категорию в дереве.
     * Если категория является корневой, то родитель будет равен null.
     */
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    /**
     * Дочерние категории.
     * <p>
     * Это множество категорий, которые являются детьми данной категории.
     * При удалении родительской категории дочерние категории также будут удалены.
     */
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<Category> children = new HashSet<>();
}
