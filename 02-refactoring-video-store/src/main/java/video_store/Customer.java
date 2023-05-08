package video_store;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String name;
    private List<Rental> rentals;

    public Customer(String name) {
        this.name = name;
        rentals  = new ArrayList<>();
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String result = "Rental Record for " + getName() + "\n";

/*
        for (Rental rental: rentals) {
            double thisAmount = rental.calculateAmount();

            frequentRenterPoints += rental.calculateFrequentPoints();

            result += "\t" + rental.getTitle() + "\t"
                    + thisAmount + "\n";
            totalAmount += thisAmount;
        }
*/

        for (Rental rental: rentals) {
            frequentRenterPoints += rental.calculateFrequentPoints();
        }

        for (Rental rental: rentals) {
            double thisAmount = 0;
            thisAmount += rental.calculateAmount();
            result += "\t" + rental.getTitle() + "\t"
                    + thisAmount + "\n";
            totalAmount += thisAmount;
        }

        result += "You owed " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points\n";

        return result;
    }

}
