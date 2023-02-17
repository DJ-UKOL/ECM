package ru.dinerik.ECM.statemachine;

// Контроль
public class ControlState implements DocumentState {

    @Override
    public String getStatus() {
        return "Документ проходит контроль";
    }
}
