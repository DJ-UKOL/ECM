package ru.dinerik.ECM.statemachine;

// Прием
public class AcceptState implements DocumentState {

    @Override
    public String getStatus() {
        return "Документ принят";
    }
}