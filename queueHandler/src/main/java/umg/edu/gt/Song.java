package umg.edu.gt;

public class Song {
	 private String title;
	    private String artist;
	    private int duration;
	    private int priority; // 1 = Alta, 2 = Normal

	    public Song(String title, String artist, int duration, int priority) {
	        this.title = title;
	        this.artist = artist;
	        this.duration = duration;
	        this.priority = priority;
	    }

	    public String getTitle() {
	        return title;
	    }

	    public String getArtist() {
	        return artist;
	    }

	    public int getDuration() {
	        return duration;
	    }

	    public int getPriority() {
	        return priority;
	    }

}
