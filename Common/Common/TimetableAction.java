package Common;

public enum TimetableAction {
    CHECK_PRICE("Check Price"),
    BOOK_TICKET("Book ticket");

    private final String columnName;

    TimetableAction(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
