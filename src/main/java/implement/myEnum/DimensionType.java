package implement.myEnum;

/**
 * Two types of Time-Dimension, each has the Field from and to
 */
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
