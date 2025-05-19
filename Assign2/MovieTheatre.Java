import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Movie {
    private static final int ROWS = 10;
    private static final int COLS = 5;
    private static char[][] seats = new char[ROWS][COLS];

    public static void main(String[] args) {
        intializedBooking();

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("1: Display seating");
            System.out.println("2: Book seats");
            System.out.println("3: Cancel booking");
            System.out.println("4: Exit");
            System.out.println("Provide Choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

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

    private static void intializedBooking() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                seats[i][j] = '.';
            }
        }
    }

    private static void displaySeating() {
        System.out.println("Seating: ");
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.println(seats[i[j] + " "]);
            }
            System.out.println();
        }
    }
    private static bookSeats(Scanner scanner) {
        System.out.println("Enter number of seats you would like to book: ");
        int numSeats = scanner.nextInt();
        scanner.nextLine();
        List<String> availSeats = findAvailSeats(numSeats);

        if (availSeats.isEmpty()) {
            System.out.println("No available seats");
        } else {
            System.out.println("Available seats: ");
            for (String seat : availSeats) {
                System.out.print(seat + " ");
            }
            System.out.println("Please enter seats you want to book: ");
            String selectedSeat = scanner.nextLine();

            String[] selectedSeatArray = selectedSeat.split(" ");

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
                System.out.println("SUCCESSFULLY BOOKED");
            } else {
                System.out.println("ERROR: INVALID SEATING");
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
                        for (int k = 0; k < numSeats; k++) {
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
        System.out.print("ENTER SEAT YOU WANT TO CANCEL: ");
        String seatCancel = scanner.nextLine();

        int row = seatCancel.charAt(0) - 'A';
        int col = Integer.parseInt(seatCancel.substring(1)) - 1;

        if (row >= 0 && row < ROWS && col >= 0 col < COLS && seats[row][col] == 'R') {
            seats[row][col] = '.';
            System.out.println("Seat cancelled");
        } else {
            System.out.println("Invalid seat");
        }
    }
}
