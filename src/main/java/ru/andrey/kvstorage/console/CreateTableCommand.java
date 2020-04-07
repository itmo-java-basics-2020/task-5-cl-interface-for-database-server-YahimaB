package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class CreateTableCommand implements DatabaseCommand {

    private final String tableName;
    private final String databaseName;
    private final ExecutionEnvironment environment;

    public CreateTableCommand(ExecutionEnvironment environment, String databaseName, String tableName) {
        this.tableName = tableName;
        this.databaseName = databaseName;
        this.environment = environment;
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        Optional<Database> database = environment.getDatabase(databaseName);

        if (database.isPresent()) {
            database.get().createTableIfNotExists(tableName);
            return DatabaseCommandResult.success(String.format("Table %s was successfully created", tableName));
        } else {
            return DatabaseCommandResult.error("There is no database with this name");
        }
    }
}
