package com.epam.web.controller.command;

import java.util.Objects;

public class CommandResult {
    private final String command;
    private final boolean isRedirect;

    public CommandResult(String command, boolean isRedirect) {
        this.command = command;
        this.isRedirect = isRedirect;
    }

    public static CommandResult forward(String page) {
        return new CommandResult(page, false);
    }

    public static CommandResult redirect(String page) {
        return new CommandResult(page, true);
    }

    public String getCommand() {
        return command;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommandResult that = (CommandResult) o;

        if (isRedirect != that.isRedirect) return false;
        return Objects.equals(command, that.command);
    }

    @Override
    public int hashCode() {
        int result = command != null ? command.hashCode() : 0;
        result = 31 * result + (isRedirect ? 1 : 0);
        return result;
    }

    public boolean isRedirect() {
        return isRedirect;
    }
}