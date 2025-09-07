import java.sql.*;
import java.util.Scanner;

public class LibraryApp {

    private static void displayBooks(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM books");
        while (rs.next()) {
            Book book = new Book(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getBoolean("isAvailable")
            );
            System.out.println(book);
        }
    }

    private static void borrowBook(Connection conn, int id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
            "UPDATE books SET isAvailable=false WHERE id=? AND isAvailable=true"
        );
        ps.setInt(1, id);
        int rows = ps.executeUpdate();
        if (rows > 0) System.out.println("✅ Book borrowed successfully!");
        else System.out.println("❌ Book not available!");
    }

    private static void returnBook(Connection conn, int id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
            "UPDATE books SET isAvailable=true WHERE id=? AND isAvailable=false"
        );
        ps.setInt(1, id);
        int rows = ps.executeUpdate();
        if (rows > 0) System.out.println("✅ Book returned successfully!");
        else System.out.println("❌ Invalid return attempt!");
    }

    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection();
             Scanner sc = new Scanner(System.in)) {

            while (true) {
                System.out.println("\n--- Library Menu ---");
                System.out.println("1. Display Books");
                System.out.println("2. Borrow Book");
                System.out.println("3. Return Book");
                System.out.println("4. Exit");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1 -> displayBooks(conn);
                    case 2 -> {
                        System.out.print("Enter book ID: ");
                        borrowBook(conn, sc.nextInt());
                    }
                    case 3 -> {
                        System.out.print("Enter book ID: ");
                        returnBook(conn, sc.nextInt());
                    }
                    case 4 -> {
                        System.out.println("Goodbye!");
                        return;
                    }
                    default -> System.out.println("❌ Invalid choice!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
