package ru.dinerik.ECM.statemachine;

// StateMachine для установки состояний для документа поручение
public enum LeaveOrderState {
    PREPARE {
        @Override
        public LeaveOrderState nextState(Boolean isCheck) {
            if(isCheck)
                return PERFORM;
            return PREPARE;
        }

        @Override
        public String getStatus() {
            return "Подготовка документа";
        }
    },

    PERFORM {
        @Override
        public LeaveOrderState nextState(Boolean isCheck) {
            if(isCheck)
                return CONTROL;
            return PERFORM;
        }

        @Override
        public String getStatus() {
            return "Документ в работе";
        }
    },
    CONTROL {
        @Override
        public LeaveOrderState nextState(Boolean isCheck) {
            if(isCheck)
                return ACCEPT;
            return REFORM;
        }

        @Override
        public String getStatus() {
            return "Документ проходит контроль";
        }
    },
    REFORM {
        @Override
        public LeaveOrderState nextState(Boolean isCheck) {
            return PERFORM;
        }

        @Override
        public String getStatus() {
            return "Документ на доработке";
        }
    },
    ACCEPT {
        @Override
        public LeaveOrderState nextState(Boolean isCheck) {
            return this;
        }

        @Override
        public String getStatus() {
            return "Документ принят";
        }
    };

    public abstract LeaveOrderState nextState(Boolean isCheck);
    public abstract String getStatus();
}