package ru.dinerik.ECM.domain;

import lombok.Data;

// Подразделение
@Data
public class Division {

    private String fullName;            // Наименование подразделения
    private String contactDetails;      // Контактные данные
    private String manager;             // Руководитель
}
