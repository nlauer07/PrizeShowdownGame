/*
 * Written by Nick Lauer
 */
class Prize {
    // Name of the prize
    private String name;
    
    // Price of the prize
    private double price;

    // Constructor for Prize object with a specific name and price
    public Prize(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Returns the name of the prize
    public String getName() {
        return name;
    }

    // Returns the price of the prize
    public double getPrice() {
        return price;
    }
}