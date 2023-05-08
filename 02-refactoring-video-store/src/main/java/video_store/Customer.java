package video_store;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {

    private String name;
    private Vector rentals = new Vector();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        rentals.addElement(rental);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Enumeration rentals = this.rentals.elements();
        String result = "Rental Record for " + getName() + "\n";

        while (rentals.hasMoreElements()) {
            double thisAmount = 0;
            Rental rental = (Rental) rentals.nextElement();

            thisAmount += rental.calculateAmount();
            frequentRenterPoints += rental.calculateFrequentPoints();

            result += "\t" + rental.getTitle() + "\t"
                + thisAmount + "\n";
            totalAmount += thisAmount;
        }

        result += "You owed " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points\n";

        return result;
    }

}
