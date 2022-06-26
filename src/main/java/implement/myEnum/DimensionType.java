package implement.myEnum;

public enum DimensionType {
    VALID_TIME,
    TRANSACTION_TIME;

    public enum Field {
        /**
         * The start of the interval.
         */
        FROM,
        /**
         * The end of the interval.
         */
        TO
    }
}
