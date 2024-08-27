import java.util.Scanner;

class Room {
    String roomNumber;
    String category;
    double price;
    boolean available;
    String guestName;
    String guestId;
    String guestAddress;
    String checkInDate;
    String checkOutDate;

    public Room(String roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.available = true;
        this.guestName = "";
        this.guestId = "";
        this.guestAddress = "";
        this.checkInDate = "";
        this.checkOutDate = "";
    }
}

class User {
    String username;
    String password;
    String email;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}

public class HotelReservationSystem {
    private Room[] rooms;
    private Scanner scanner;
    private User[] users;
    private boolean loggedIn;
    private int userCount;

    public HotelReservationSystem() {
        rooms = new Room[10];
        scanner = new Scanner(System.in);
        users = new User[10];
        loggedIn = false;
        userCount = 0;
        initializeRooms();
    }

    private void initializeRooms() {
        rooms[0] = new Room("101", "Single", 500);
        rooms[1] = new Room("102", "Double", 800);
        rooms[2] = new Room("103", "Deluxe", 1200);
        rooms[3] = new Room("104", "Super Deluxe", 1500);
    }

    public void run() {
        while (true) {
            if (!loggedIn) {
                System.out.println("1. Signup");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Enter choice: ");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        signup();
                        break;
                    case 2:
                        login();
                        break;
                    case 3:
                        System.out.println("Exiting");
                        return;
                    default:
                        System.out.println("Invalid choice");
                }
            } else {
                int choice;
                boolean continueLoop = true;
                do {
                    System.out.println("Hotel Reservation System");
                    System.out.println("1. Search for available rooms");
                    System.out.println("2. Make a reservation");
                    System.out.println("3. Process payment");
                    System.out.println("4. View booking details");
                    System.out.println("5. Check-in");
                    System.out.println("6. Check-out");
                    System.out.println("7. Exit");
                    System.out.print("Choose an option: ");
                    choice = scanner.nextInt();

                    switch (choice) {
                        case 1:
                            searchRooms();
                            break;
                        case 2:
                            makeReservation();
                            break;
                        case 3:
                            System.out.print("Enter amount: ");
                            double amount = scanner.nextDouble();
                            processPayment(amount);
                            break;
                        case 4:
                            viewBookings();
                            break;
                        case 5:
                            checkInDate();
                            break;
                        case 6:
                            checkOutDate();
                            break;
                        case 7:
                            System.out.println("Exiting...");
                            continueLoop = false;
                            break;
                        default:
                            System.out.println("Invalid choice");
                    }
                    if (continueLoop && choice != 7) {
                        System.out.print("Do you want to continue? (yes/no): ");
                        String response = scanner.next();
                        continueLoop = response.equalsIgnoreCase("yes");
                    }
                } while (continueLoop);
               
                return;
            }
        }
    }

    private void signup() {
        if (userCount < users.length) {
            System.out.println("Enter username:");
            String username = scanner.next();
            System.out.println("Enter password:");
            String password = scanner.next();
            System.out.println("Enter email:");
            String email = scanner.next();
            users[userCount++] = new User(username, password, email);
            System.out.println("Signup successful!");
        } else {
            System.out.println("User limit reached. Cannot signup more users.");
        }
    }

    private void login() {
        System.out.println("Enter username:");
        String username = scanner.next();
        System.out.println("Enter password:");
        String password = scanner.next();
        for (User user : users) {
            if (user != null && user.username.equals(username) && user.password.equals(password)) {
                loggedIn = true;
                System.out.println("Login successful!");
                return;
            }
        }
        System.out.println("Invalid username or password!");
    }

    private void searchRooms() {
        System.out.println("Enter room category:");
        String category = scanner.next();
        boolean roomFound = false;
        for (Room room : rooms) {
            if (room != null && room.category.equalsIgnoreCase(category) && room.available) {
                System.out.println("Room number: " + room.roomNumber + " Price: " + room.price);
                roomFound = true;
            }
        }
        if (!roomFound) {
            System.out.println("No rooms available in this category.");
        }
    }
    

    private void makeReservation() {
        System.out.println("Enter room number: ");
        String roomNumber = scanner.next();
        for (Room room : rooms) {
            if (room != null && room.roomNumber.equals(roomNumber) && room.available) {
                room.available = false;
                System.out.println("Reservation made successfully");
                System.out.println("Room Number: " + room.roomNumber + " Price: " + room.price);
                System.out.println("Do you want to enter guest name? (yes/no)");
                String choice = scanner.next();
                if (choice.equalsIgnoreCase("yes")) {
                    System.out.println("Enter guest name: ");
                    room.guestName = scanner.next();
                }
                processPayment(room.price);
                return;
            }
        }
        System.out.println("Room not available");
    }
    

    private boolean paymentSuccessful = false;
    public void processPayment(double amount) {
        System.out.println("Payment processing:");
        System.out.println("Amount: rs" + amount);
        System.out.println("Select payment method:");
        System.out.println("1. Cash");
        System.out.println("2. Online Payment (Credit/Debit Card)");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.println("Payment method: Cash");
            paymentSuccessful = true;
            System.out.println("Payment Successful!");
        } else if (choice == 2) {
            System.out.println("Enter card number:");
            String cardNumber = scanner.next();
            System.out.println("Enter expiry date (MM/YY):");
            String expiryDate = scanner.next();
            System.out.println("Enter CVV:");
            String cvv = scanner.next();
            // Simulate online payment processing
           if (validateCardDetails(cardNumber, expiryDate, cvv, amount)) {
            System.out.println("Payment Successful!");
        } else {
            System.out.println("Payment failed! Invalid card details.");
            return;
        }
    }
}
    private Room getOccupiedRoom() {
        for (Room room : rooms) {
            if (room != null && !room.available) {
                return room;
            }
        }
        return null;
    }
    
    private void viewBookings() {
        boolean hasBookings = false;
        for (Room room : rooms) {
            if (room != null && !room.available && !room.guestName.isEmpty()) {
                System.out.println("Room Number: " + room.roomNumber);
                System.out.println("Category: " + room.category);
                System.out.println("Price: " + room.price);
                System.out.println("Guest Name: " + room.guestName);
                System.out.println("------------------------");
                hasBookings = true;
            }
        }
        if (!hasBookings) {
            System.out.println("No bookings available.");
        }
    }
    
    
    
    private void checkInDate() {
        System.out.println("Enter room number: ");
        String roomNumber = scanner.next();
        System.out.println("Searching for room number: " + roomNumber);
        for (Room room : rooms) {
            System.out.println("Checking room: " + room.roomNumber + " (Available: " + room.available + ")");
            
            if (room.roomNumber.equals(roomNumber) && !room.available) {
                System.out.println("Enter guest name: ");
                room.guestName = scanner.next();
                System.out.println("Enter check-in date (DD/MM/YYYY): ");
                room.checkInDate = scanner.next();
                System.out.println("Check-in successful!");
                return;
            }
        }
        System.out.println("Room not found or not reserved");
    }
    
    private void checkOutDate() {
        System.out.println("Enter room number: ");
        String roomNumber = scanner.next();
        for (Room room : rooms) {
            if (room.roomNumber.equals(roomNumber) && !room.available) {
                System.out.println("Enter check-out date (DD/MM/YYYY): ");
                room.checkOutDate = scanner.next();
                room.available = true;
                room.guestName = "";
                room.checkInDate = "";
                room.checkOutDate = "";
                System.out.println("Check-out successful!");
                return;
            }
        }
        System.out.println("Room not found or not checked-in");
    }
    
    public static boolean validateCardDetails(String cardNumber, String expiryDate, String cvv, double amount) {
      // Simple validation logic (for demonstration purposes only)
if (cardNumber.length() >= 13 && cardNumber.length() <= 16 && expiryDate.matches("(0[1-9]|1[0-2])/\\d{2}") && cvv.length() == 3 && amount > 0) {
    return true;
} else {
    return false;
}
}
public static void main(String[] args) {
    HotelReservationSystem system=new HotelReservationSystem();
    system.run();
    
}
}  




