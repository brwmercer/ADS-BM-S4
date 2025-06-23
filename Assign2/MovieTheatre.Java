import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Movie {
    private static final int ROWS = 10;
    private static final int COLS = 5;
    private static char[][] seats = new char[ROWS][COLS];

    public static void main(String[] args) {
        initializeBooking();

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n1: Display seating");
            System.out.println("2: Book seats");
            System.out.println("3: Cancel booking");
            System.out.println("4: Exit");
            System.out.print("Provide Choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    displaySeating();
                    break;
                case 2:
                    bookSeats(scanner);
                    break;
                case 3:
                    cancelBooking(scanner);
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Error - please select a valid option");
            }
        }
        scanner.close();
    }

    private static void initializeBooking() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                seats[i][j] = '.';
            }
        }
    }

    private static void displaySeating() {
        System.out.println("\nSeating:");
        for (int i = 0; i < ROWS; i++) {
            System.out.print((char) ('A' + i) + ": ");
            for (int j = 0; j < COLS; j++) {
                System.out.print(seats[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void bookSeats(Scanner scanner) {
        System.out.print("Enter number of seats you would like to book: ");
        int numSeats = scanner.nextInt();
        scanner.nextLine();

        List<String> availSeats = findAvailSeats(numSeats);

        if (availSeats.isEmpty()) {
            System.out.println("No available seats found for " + numSeats + " consecutive seats.");
        } else {
            System.out.println("Suggested available seats: " + String.join(" ", availSeats));
            System.out.print("Enter the seats you want to book (space-separated): ");
            String selectedSeatLine = scanner.nextLine();
            String[] selectedSeatArray = selectedSeatLine.trim().split("\\s+");

            boolean validBooking = true;
            for (String seat : selectedSeatArray) {
                if (!availSeats.contains(seat)) {
                    validBooking = false;
                    break;
                }
            }

            if (validBooking) {
                for (String seat : selectedSeatArray) {
                    reserveSeat(seat);
                }
                System.out.println("Seats successfully booked!");
            } else {
                System.out.println("Invalid seat selection. Booking failed.");
            }
        }
    }

    private static List<String> findAvailSeats(int numSeats) {
        List<String> availSeats = new ArrayList<>();
        for (int i = 0; i < ROWS; i++) {
            int emptySeats = 0;
            for (int j = 0; j < COLS; j++) {
                if (seats[i][j] == '.') {
                    emptySeats++;
                    if (emptySeats == numSeats) {
                        for (int k = numSeats - 1; k >= 0; k--) {
                            availSeats.add((char) ('A' + i) + Integer.toString(j - k + 1));
                        }
                        return availSeats;
                    }
                } else {
                    emptySeats = 0;
                }
            }
        }
        return availSeats;
    }

    private static void reserveSeat(String seat) {
        int row = seat.charAt(0) - 'A';
        int col = Integer.parseInt(seat.substring(1)) - 1;
        seats[row][col] = 'R';
    }

    private static void cancelBooking(Scanner scanner) {
        System.out.print("Enter the seat to cancel (e.g., B3): ");
        String seatCancel = scanner.nextLine().toUpperCase();

        int row = seatCancel.charAt(0) - 'A';
        int col = Integer.parseInt(seatCancel.substring(1)) - 1;

        if (row >= 0 && row < ROWS && col >= 0 && col < COLS && seats[row][col] == 'R') {
            seats[row][col] = '.';
            System.out.println("Seat " + seatCancel + " has been cancelled.");
        } else {
            System.out.println("Invalid seat or seat not booked.");
        }
    }
}
