package video_store;

public class Movie {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;

    private String title;
    private int priceCode;

    public Movie(String title, int priceCode) {
        this.title = title;
        this.priceCode = priceCode;
    }

    public int getPriceCode() {
        return priceCode;
    }

    public String getTitle() {
        return title;
    }

    public double calculateAmount(int daysRented) {
        double amount = 0;
        switch (priceCode) {
            case REGULAR:
                amount = 2;
                if (daysRented > 2) {
                    amount += (daysRented -2) * 1.5;
                }
                break;
            case NEW_RELEASE:
                amount = daysRented * 3;
                break;
            case CHILDRENS:
                amount = 1.5;
                if (daysRented > 3) {
                    amount += (daysRented - 3) * 1.5;
                }
                break;
        }
        return amount;
    }

    public int calculateFrequentPoints(int daysRented) {
        int renterPoints = 0;
        if (getPriceCode() == NEW_RELEASE
                && daysRented > 1) {
            renterPoints = 2;
        }else {
            renterPoints = 1;
        }
        return renterPoints;
    }
}
