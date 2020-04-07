package ru.andrey.kvstorage.console;

import java.util.Optional;

public interface DatabaseCommandResult {

    Optional<String> getResult();

    DatabaseCommandStatus getStatus();

    boolean isSuccess();

    String getErrorMessage();

    enum DatabaseCommandStatus {
        SUCCESS, FAILED
    }

    static DatabaseCommandResult success(String result) {
        return new BaseCommandResult(DatabaseCommandStatus.SUCCESS, result,null);
    }

    static DatabaseCommandResult error(String message) {
        return new BaseCommandResult( DatabaseCommandStatus.FAILED, null, message);
    }

    class BaseCommandResult implements DatabaseCommandResult {

        private final String result;
        private final String message;
        private final DatabaseCommandStatus status;

        private BaseCommandResult(DatabaseCommandStatus status, String result, String message) {
            this.status = status;
            this.result = result;
            this.message = message;
        }

        @Override
        public Optional<String> getResult() {
            return Optional.ofNullable(result);
        }

        @Override
        public DatabaseCommandStatus getStatus() {
            return status;
        }

        @Override
        public boolean isSuccess() {
            return status.equals(DatabaseCommandStatus.SUCCESS);
        }

        @Override
        public String getErrorMessage() {
            return message;
        }
    }
}