package command;

public enum CommandType {
    LOGIN, SIGNUP, LOGOUT;
    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    CommandType() {

    }

    public Command getCommand() {
        return command;
    }
}
