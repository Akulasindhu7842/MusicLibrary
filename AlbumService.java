import java.sql.*;
import java.util.Scanner;

	public class AlbumService {
	    public static void addAlbum(Connection conn, Scanner scanner) throws SQLException {
	        System.out.print("Enter album title: ");
	        String title = scanner.nextLine();
	        System.out.print("Enter artist_id: ");
	        int artistId = scanner.nextInt();
	        System.out.print("Enter release year: ");
	        int year = scanner.nextInt();
	        scanner.nextLine();

	        String sql = "INSERT INTO Albums (title, artist_id, release_year) VALUES (?, ?, ?)";
	        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setString(1, title);
	            stmt.setInt(2, artistId);
	            stmt.setInt(3, year);
	            stmt.executeUpdate();
	            System.out.println("Album added successfully.");
	        }
	    }
	}

