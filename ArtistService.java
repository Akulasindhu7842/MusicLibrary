import java.sql.*;
import java.util.Scanner;

	public class ArtistService {
	    public static void addArtist(Connection conn, Scanner scanner) throws SQLException {
	        System.out.print("Enter artist name: ");
	        String name = scanner.nextLine();
	        System.out.print("Enter genre: ");
	        String genre = scanner.nextLine();

	        String sql = "INSERT INTO Artists (name, genre) VALUES (?, ?)";
	        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setString(1, name);
	            stmt.setString(2, genre);
	            stmt.executeUpdate();
	            System.out.println("Artist added successfully.");
	        }
	    }

	    public static void viewAlbumsByArtist(Connection conn, Scanner scanner) throws SQLException {
	        System.out.print("Enter artist ID: ");
	        int artistId = scanner.nextInt();
	        scanner.nextLine();

	        String sql = "SELECT title, release_year FROM Albums WHERE artist_id = ?";
	        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, artistId);
	            ResultSet rs = stmt.executeQuery();
	            System.out.println("\nAlbums by Artist:");
	            while (rs.next()) {
	                System.out.println(rs.getString("title") + " (" + rs.getInt("release_year") + ")");
	            }
	        }
	    }
	}
