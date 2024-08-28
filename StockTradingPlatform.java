import java.util.Scanner;

class Stock {
    String symbol;
    double price; // Current buying price
    int quantity;

    public Stock(String symbol, double price, int quantity) {
        this.symbol = symbol;
        this.price = price;
        this.quantity = quantity;
    }

    public void updatePrice(double newPrice) {
        this.price = newPrice;
    }

    public double getTotalValue() {
        return this.price * this.quantity;
    }
}

class Portfolio {
    Stock[] stocks;
    double balance;

    public Portfolio() {
        this.stocks = new Stock[10];
        this.balance = 50000.0;
    }

    public void buyStock(String symbol, double price, int quantity) {
        for (int i = 0; i < stocks.length; i++) {
            if (stocks[i] == null) {
                stocks[i] = new Stock(symbol, price, quantity);
                balance -= price * quantity;
                System.out.println("Stock bought successfully!");
                return;
            } else if (stocks[i].symbol.equals(symbol)) {
                stocks[i].quantity += quantity;
                balance -= price * quantity;
                System.out.println("Stock quantity updated!");
                return;
            }
        }
        System.out.println("Portfolio is full!");
    }

    public void sellStock(String symbol, int quantity, double sellPrice) {
        for (int i = 0; i < stocks.length; i++) {
            if (stocks[i] != null && stocks[i].symbol.equals(symbol)) {
                if (stocks[i].quantity >= quantity) {
                    balance += sellPrice * quantity; // Use selling price for calculation
                    stocks[i].quantity -= quantity;
                    if (stocks[i].quantity == 0) {
                        stocks[i] = null;
                    }
                    System.out.println("Stock sold successfully at price " + sellPrice + " per unit!");
                    return;
                } else {
                    System.out.println("Not enough stock quantity to sell!");
                    return;
                }
            }
        }
        System.out.println("Stock not found in portfolio!");
    }

    public void viewStockDetails(String symbol) {
        for (Stock stock : stocks) {
            if (stock != null && stock.symbol.equals(symbol)) {
                System.out.println("Stock: " + stock.symbol);
                System.out.println("Price: " + stock.price);
                System.out.println("Quantity: " + stock.quantity);
                System.out.println("Total Value: " + stock.getTotalValue());
                return;
            }
        }
        System.out.println("Stock not found in portfolio!");
    }

    public void updateStockPrice(String symbol, double newPrice) {
        for (Stock stock : stocks) {
            if (stock != null && stock.symbol.equals(symbol)) {
                stock.updatePrice(newPrice);
                System.out.println("Stock price updated successfully!");
                return;
            }
        }
        System.out.println("Stock not found in portfolio!");
    }

    public void displayPortfolio() {
        System.out.println("Portfolio:");
        double totalValue = 0.0;
        for (Stock stock : stocks) {
            if (stock != null) {
                double stockValue = stock.getTotalValue();
                totalValue += stockValue;
                System.out.println(stock.symbol + ": " + stock.price + " (Quantity: " + stock.quantity + ", Value: " + stockValue + ")");
            }
        }
        System.out.println("Total Portfolio Value: " + totalValue);
        System.out.println("Balance: " + balance);
    }
}

public class StockTradingPlatform {
    public static void main(String[] args) {
        Portfolio portfolio = new Portfolio();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Buy Stock");
            System.out.println("2. Sell Stock");
            System.out.println("3. View Stock Details");
            System.out.println("4. Update Stock Price");
            System.out.println("5. Display Portfolio");
            System.out.println("6. Exit");
            System.out.println("Enter choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter stock symbol:");
                    String symbol = scanner.next();
                    System.out.println("Enter stock price:");
                    double price = scanner.nextDouble();
                    System.out.println("Enter stock quantity:");
                    int quantity = scanner.nextInt();
                    portfolio.buyStock(symbol, price, quantity);
                    break;
                case 2:
                    System.out.println("Enter stock symbol:");
                    symbol = scanner.next();
                    System.out.println("Enter quantity to sell:");
                    quantity = scanner.nextInt();
                    System.out.println("Enter selling price:");
                    double sellPrice = scanner.nextDouble();
                    portfolio.sellStock(symbol, quantity, sellPrice);
                    break;
                case 3:
                    System.out.println("Enter stock symbol:");
                    symbol = scanner.next();
                    portfolio.viewStockDetails(symbol);
                    break;
                case 4:
                    System.out.println("Enter stock symbol:");
                    symbol = scanner.next();
                    System.out.println("Enter new stock price:");
                    price = scanner.nextDouble();
                    portfolio.updateStockPrice(symbol, price);
                    break;
                case 5:
                    portfolio.displayPortfolio();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
