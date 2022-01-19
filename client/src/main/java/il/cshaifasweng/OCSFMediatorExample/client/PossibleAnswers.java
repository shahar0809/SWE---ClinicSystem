package il.cshaifasweng.OCSFMediatorExample.client;

enum PossibleAnswers {
    YES("Yes"),
    NO("No");

    private final String type;

    PossibleAnswers(final String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
