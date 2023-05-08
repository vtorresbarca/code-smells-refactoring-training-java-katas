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

        int frequentRenterPoints = calculateRentalsFrequentRenterPoints();
        double totalAmount = calculateRentalsTotalAmount();

        return rentalsCustomerStatement(totalAmount, frequentRenterPoints);
    }

    private String rentalsCustomerStatement(double totalAmount, int frequentRenterPoints) {
        String result = "Rental Record for " + getName() + "\n";
        for (Rental rental: rentals) {
            double thisAmount = 0;
            thisAmount += rental.calculateAmount();
            result += "\t" + rental.getTitle() + "\t"
                    + thisAmount + "\n";

        }

        result += "You owed " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points\n";

        return result;
    }

    private double calculateRentalsTotalAmount() {
        double totalAmount = 0;
        for (Rental rental: rentals) {
            double thisAmount = 0;
            thisAmount += rental.calculateAmount();
            totalAmount += thisAmount;
        }
        return totalAmount;
    }

    private int calculateRentalsFrequentRenterPoints() {
        int frequentRenterPoints = 0;
        for (Rental rental: rentals) {
            frequentRenterPoints += rental.calculateFrequentPoints();
        }
        return frequentRenterPoints;
    }

}
