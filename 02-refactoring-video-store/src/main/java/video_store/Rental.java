package video_store;

public class Rental {

    private Movie movie;
    private int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getPriceCode() {
        return movie.getPriceCode();
    }

    public String getTitle() {
        return movie.getTitle();
    }

    public double calculateAmount() {
        return movie.calculateAmount(daysRented);
    }

}
