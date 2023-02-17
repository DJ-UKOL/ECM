package ru.dinerik.ECM.statemachine;

// Исполнение
public class PerformState implements DocumentState {

    @Override
    public String getStatus() {
        return "Документ в работе";
    }
}