import java.sql.*;
import java.util.Scanner;

public class PlaylistService {
    public static void createPlaylist(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter user ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter playlist name: ");
        String name = scanner.nextLine();

        String sql = "INSERT INTO Playlists (user_id, name) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, name);
            stmt.executeUpdate();
            System.out.println("Playlist created successfully.");
        }
    }

    public static void addSongToPlaylist(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter playlist ID: ");
        int playlistId = scanner.nextInt();
        System.out.print("Enter song ID: ");
        int songId = scanner.nextInt();
        scanner.nextLine();

        String sql = "INSERT INTO PlaylistSongs (playlist_id, song_id) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, playlistId);
            stmt.setInt(2, songId);
            stmt.executeUpdate();
            System.out.println("Song added to playlist successfully.");
        }
    }

    public static void viewPlaylistSongs(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter playlist ID: ");
        int playlistId = scanner.nextInt();
        scanner.nextLine();

        String sql = "SELECT s.title FROM Songs s JOIN PlaylistSongs ps ON s.song_id = ps.song_id WHERE ps.playlist_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, playlistId);
            ResultSet rs = stmt.executeQuery();
            System.out.println("\nSongs in Playlist:");
            while (rs.next()) {
                System.out.println("- " + rs.getString("title"));
            }
        }
    }
}
