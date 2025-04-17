import java.sql.*;
import java.util.Scanner;

public class SongService {
    public static void addSong(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter song title: ");
        String title = scanner.nextLine();
        System.out.print("Enter album ID: ");
        int albumId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter duration (HH:MM:SS): ");
        String duration = scanner.nextLine();

        String sql = "INSERT INTO Songs (title, album_id, duration) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, title);
            stmt.setInt(2, albumId);
            stmt.setTime(3, Time.valueOf(duration));
            stmt.executeUpdate();
            System.out.println("Song added successfully.");
        }
    }

    public static void playSong(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter song ID to play: ");
        int songId = scanner.nextInt();
        scanner.nextLine();

        String sql = "UPDATE Songs SET play_count = play_count + 1 WHERE song_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, songId);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Song played successfully.");
            } else {
                System.out.println("Song not found.");
            }
        }
    }

    public static void viewTopSongs(Connection conn) throws SQLException {
        String sql = "SELECT title, play_count FROM Songs ORDER BY play_count DESC LIMIT 10";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\nTop Played Songs:");
            while (rs.next()) {
                System.out.println(rs.getString("title") + " - Plays: " + rs.getInt("play_count"));
            }
        }
    }

    public static void searchSongs(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter keyword to search songs: ");
        String keyword = scanner.nextLine();

        String sql = "SELECT title FROM Songs WHERE title LIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            System.out.println("\nSearch Results:");
            while (rs.next()) {
                System.out.println("- " + rs.getString("title"));
            }
        }
    }
}
