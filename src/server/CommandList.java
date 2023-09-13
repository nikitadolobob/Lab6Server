package server;

public enum CommandList {
    HELP() {
        @Override
        public String printPrescription(){
            return "Gives you this priceless information. Requires no argument";
        }
    }
    ,
    INFO(){
        @Override
        public String printPrescription(){
            return "Gives data about the collection. Requires no argument";
        }
    },
    SHOW() {
        @Override
        public String printPrescription(){
            return "Gives data on the elements of the collection. Requires no argument";
        }
    },
    ADD() {
        @Override
        public String printPrescription(){
            return "Adds new element to the collection. Requires no argument. Data required for forming an element will be asked after typing the command";
        }
    },
    UPDATE() {
        @Override
        public String printPrescription(){
            return "Updates element of the collection with the given ID. Requires int argument - element ID. Updation detailes will be asked later";
        }
    },
    REMOVE_BY_ID() {
        @Override
        public String printPrescription(){
            return "Deletes element with given ID. Requires int arguement - element ID";
        }
    },
    CLEAR() {
        @Override
        public String printPrescription(){
            return "Deletes all elements of the collection. Requires no argument";
        }
    },
    EXECUTE_SCRIPT() {
        @Override
        public String printPrescription(){
            return "Executes you script. Requires String name of the file";
        }
    },
    REMOVE_LAST() {
        @Override
        public String printPrescription(){
            return "Deletes last element of the collection. Requires no argument";
        }
    },
    ADD_IF_MIN() {
        @Override
        public String printPrescription(){
            return "Adds a given element if it is less then all the elements already placed in collection. Requires no argument. Required data gets asked later";
        }
    },
    REORDER() {
        @Override
        public String printPrescription(){
            return "Reverses the order of the elements in the collection. Requires no argument";
        }
    },
    COUNT_GREATER_THAN_GENRE() {
        @Override
        public String printPrescription(){
            return "Gives amount of elements with genres greater than given. Requires a genre argument";
        }
    },
    FILTER_GREATER_THAN_GENRE() {
        @Override
        public String printPrescription(){
            return "Gives elements with genres greater than given. Requires a genre argument";
        }
    },
    COUNT_LESS_THAN_GENRE() {
        @Override
        public String printPrescription(){
            return "Gives amount of elements with genres lesser than given. Requires a genre argument";
        }
    };

    public String printPrescription(){return null;}

}
