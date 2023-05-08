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

            // determines the amount for rental line
            double amount = 0;
            switch (rental.getPriceCode()) {
                case Movie.REGULAR:
                    amount = 2;
                    if (rental.getDaysRented() > 2) {
                        amount += (rental.getDaysRented() -2) * 1.5;
                    }
                    thisAmount = amount;
                    break;
                case Movie.NEW_RELEASE:
                    amount = rental.getDaysRented() * 3;
                    thisAmount = amount;
                    break;
                case Movie.CHILDRENS:
                    amount = 1.5;
                    if (rental.getDaysRented() > 3)
                        amount += (rental.getDaysRented() - 3) * 1.5;
                    thisAmount = amount;
                    break;
            }

            frequentRenterPoints++;

            if (rental.getPriceCode() == Movie.NEW_RELEASE
                    && rental.getDaysRented() > 1)
                frequentRenterPoints++;


            result += "\t" + rental.getTitle() + "\t"
                + thisAmount + "\n";
            totalAmount += thisAmount;

        }

        result += "You owed " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points\n";

        return result;
    }
}
