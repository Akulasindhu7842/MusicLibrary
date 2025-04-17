import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection(); Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n==== Music Library Menu ====");
                System.out.println("1. Add Artist");
                System.out.println("2. Add Album");
                System.out.println("3. Add Song");
                System.out.println("4. Play Song");
                System.out.println("5. View Top Songs");
                System.out.println("6. Search Songs");
                System.out.println("7. View Albums by Artist");
                System.out.println("8. Register User");
                System.out.println("9. Create Playlist");
                System.out.println("10. Add Song to Playlist");
                System.out.println("11. View Playlist Songs");
                System.out.println("12. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> ArtistService.addArtist(conn, scanner);
                    case 2 -> AlbumService.addAlbum(conn, scanner);
                    case 3 -> SongService.addSong(conn, scanner);
                    case 4 -> SongService.playSong(conn, scanner);
                    case 5 -> SongService.viewTopSongs(conn);
                    case 6 -> SongService.searchSongs(conn, scanner);
                    case 7 -> ArtistService.viewAlbumsByArtist(conn, scanner);
                    case 8 -> UserService.registerUser(conn, scanner);
                    case 9 -> PlaylistService.createPlaylist(conn, scanner);
                    case 10 -> PlaylistService.addSongToPlaylist(conn, scanner);
                    case 11 -> PlaylistService.viewPlaylistSongs(conn, scanner);
                    case 12 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid option. Try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
