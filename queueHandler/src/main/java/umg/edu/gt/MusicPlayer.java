package umg.edu.gt;

import java.util.Scanner; 
import umg.edu.gt.queue.LinkedQueue;

public class MusicPlayer {

	private static boolean skipRequested = false;

    public static void main(String[] args) {

        LinkedQueue<Song> highPriority = new LinkedQueue<>();
        LinkedQueue<Song> normalPriority = new LinkedQueue<>();
        LinkedQueue<Song> history = new LinkedQueue<>();

        addSong(highPriority, normalPriority, new Song("Sarà perché ti amo", "Ricchi e Poveri", 8, 1));
        addSong(highPriority, normalPriority, new Song("Girls Just Want To Have Fun", "Cyndi Lauper", 12, 2));
        addSong(highPriority, normalPriority, new Song("The Winner Takes It All", "ABBA", 15, 1));
        addSong(highPriority, normalPriority, new Song("Un Millon de Primaveras", "Vicente Fernandez", 10, 2));
        addSong(highPriority, normalPriority, new Song("Photograph", "Ed Sheeran", 11, 2));

        System.out.println("[LOG] Starting playlist...");
        System.out.println("[INFO] Press 's' + ENTER to skip current song.\n");

        // AGREGADO: Hilo que escucha el teclado
        Thread inputListener = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("s")) { //La letra "S" es la que hara que se pare la canción
                    skip();
                }
            }
        });

        inputListener.setDaemon(true); // Se cierra cuando termina el programa
        inputListener.start();

        while (!highPriority.isEmpty() || !normalPriority.isEmpty()) {

            Song current;

            if (!highPriority.isEmpty()) {
                current = highPriority.dequeue();
            } else {
                current = normalPriority.dequeue();
            }

            playSong(current, history);
        }

        System.out.println("[LOG] Playlist finished.");

        System.out.println("\n[LOG] Songs played (History):");

        while (!history.isEmpty()) {
            Song played = history.dequeue();
            System.out.println("- " + played.getTitle() + " - " + played.getArtist());
        }
    }

    private static void addSong(LinkedQueue<Song> high,
                                LinkedQueue<Song> normal,
                                Song song) {

        if (song.getPriority() == 1) {
            high.enqueue(song);
        } else {
            normal.enqueue(song);
        }
    }

    private static void playSong(Song song, LinkedQueue<Song> history) {

        skipRequested = false;

        System.out.println("[LOG] Now playing: "
                + song.getTitle()
                + " - " + song.getArtist()
                + " (" + song.getDuration() + "s)");

        for (int i = 1; i <= song.getDuration(); i++) {

            if (skipRequested) {
                System.out.println("[LOG] Song skipped: " + song.getTitle() + "\n");
                return;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("[LOG] Playing: "
                    + song.getTitle()
                    + " | " + i + "s / "
                    + song.getDuration() + "s");
        }

        System.out.println("[LOG] Finished: " + song.getTitle() + "\n");

        history.enqueue(song);
    }

    // Método skip se activa desde teclado
    public static void skip() {
        skipRequested = true;
    }
   
}
