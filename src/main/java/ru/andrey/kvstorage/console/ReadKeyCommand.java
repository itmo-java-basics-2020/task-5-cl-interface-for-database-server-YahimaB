package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class ReadKeyCommand implements DatabaseCommand {

    private final String objectKey;
    private final String tableName;
    private final String databaseName;
    private final ExecutionEnvironment environment;

    public ReadKeyCommand(ExecutionEnvironment environment, String databaseName, String tableName, String objectKey) {
        this.objectKey = objectKey;
        this.tableName = tableName;
        this.databaseName = databaseName;
        this.environment = environment;
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        Optional<Database> database = environment.getDatabase(databaseName);

        if (database.isPresent()) {
            String value = database.get().read(tableName, objectKey);
            return DatabaseCommandResult.success(String.format("The value for key is %s", value));
        } else {
            return DatabaseCommandResult.error("There is no database with this name");
        }
    }
}
