package ru.dinerik.ECM.statemachine;

// Доработка
public class ReformState implements DocumentState {
    @Override
    public String getStatus() {
        return "Документ на доработке";
    }
}