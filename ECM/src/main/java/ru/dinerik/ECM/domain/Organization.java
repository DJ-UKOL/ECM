package ru.dinerik.ECM.domain;

import lombok.Data;

// Организация
@Data
public class Organization {

    private String fullName;            // Наименование организации
    private String postalAddress;       // Физический адрес
    private String legalAddress;        // Юридический адрес
    private String director;            // Руководитель
}