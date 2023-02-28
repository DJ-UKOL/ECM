package ru.dinerik.ECM.service.impl.util;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// Класс получения списка, всех объектов из БД
@AllArgsConstructor
public class Sorting<K> {

    private JpaRepository<K, Long> repository;

    public List<K> sortList(Optional<Integer> page,
                                 Optional<Integer> objectPerPage,
                                 Optional<String> sortBy) {

        return page.isPresent() && objectPerPage.isPresent() ?
                sortBy.map(s -> repository
                                .findAll(PageRequest.of(page.get(), objectPerPage.get(), Sort.by(s)))
                                .getContent())
                        .orElseGet(() -> repository
                                .findAll(PageRequest.of(page.get(), objectPerPage.get()))
                                .getContent()) :
                sortBy.map(s -> repository
                                .findAll(Sort.by(s)))
                        .orElseGet(repository::findAll);
    }
}