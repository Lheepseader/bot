package com.pandev.bot.config;

import com.pandev.bot.repository.CategoryRepository;
import com.pandev.bot.service.CategoryService;
import com.pandev.bot.service.CategoryServiceI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Конфигурация Spring для настройки компонентов бота.
 */
@Configuration
@PropertySource("classpath:application.properties")
public class BotConfig {

    /**
     * Создает бин для {@link CategoryService}.
     *
     * @param categoryRepository репозиторий для работы с категориями.
     * @return экземпляр {@link CategoryService}, реализованный через {@link CategoryServiceI}.
     */
    @Bean
    public CategoryService categoryService(CategoryRepository categoryRepository) {
        return new CategoryServiceI(categoryRepository);
    }
}
