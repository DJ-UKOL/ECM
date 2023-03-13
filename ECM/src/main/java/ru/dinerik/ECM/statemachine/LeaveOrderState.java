package ru.dinerik.ECM.statemachine;

// StateMachine для установки состояний для документа поручение
public enum LeaveOrderState {
    PREPARE {
        final String status = "Подготовка документа";
        @Override
        public LeaveOrderState nextState(Boolean isCheck) {
            if(isCheck)
                return PERFORM;
            return PREPARE;
        }

        @Override
        public String getStatus() {
            return status;
        }
    },

    PERFORM {
        final String status = "Документ в работе";
        @Override
        public LeaveOrderState nextState(Boolean isCheck) {
            if(isCheck)
                return CONTROL;
            return PERFORM;
        }

        @Override
        public String getStatus() {
            return status;
        }
    },
    CONTROL {
        final String status = "Документ проходит контроль";
        @Override
        public LeaveOrderState nextState(Boolean isCheck) {
            if(isCheck)
                return ACCEPT;
            return REFORM;
        }

        @Override
        public String getStatus() {
            return status;
        }
    },
    REFORM {
        final String status = "Документ на доработке";
        @Override
        public LeaveOrderState nextState(Boolean isCheck) {
            return PERFORM;
        }

        @Override
        public String getStatus() {
            return status;
        }
    },
    ACCEPT {
        final String status = "Документ принят";
        @Override
        public LeaveOrderState nextState(Boolean isCheck) {
            return this;
        }

        @Override
        public String getStatus() {
            return status;
        }

    };



    public abstract LeaveOrderState nextState(Boolean isCheck);
    public abstract String getStatus();
}