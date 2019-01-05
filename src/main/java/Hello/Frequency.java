package Hello;

public enum Frequency {

    MONTH(0), // Monthly will not divide to a whole weekly value.
    WEEK(1),
    TWO_WEEK(2),
    FOUR_WEEK(4),
    QUARTER(13),
    YEAR(52);

    private final int NumberOfWeeks;

    private Frequency(int numberOfWeeks) {
        this.NumberOfWeeks = numberOfWeeks;
    }

    public int getNumberOfWeeks() {
        return NumberOfWeeks;
    }
}
