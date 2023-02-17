package ru.dinerik.ECM.statemachine;

// Подготовка документа
public class PrepareState implements DocumentState {

    @Override
    public String getStatus() {
        return "Подготовка документа";
    }
}