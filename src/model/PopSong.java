package model;

public class PopSong extends SongBase{
    private boolean danceable;
    private int chartPosition;
    private String producer;

    public PopSong(int id, String name, String artist, String album, String genre, int duration, String releaseDate,
                   boolean danceable, int chartPosition, String producer) {
        super(id, name, artist, album, genre, duration, releaseDate);
        this.danceable = danceable;
        this.chartPosition = chartPosition;
        this.producer = producer;
    }

    @Override
    public void MusicPlay() {
        System.out.println(" Playing a catchy POP song: " + getName() + " by " + getArtist());
        System.out.println("Produced by: " + producer);
        if (danceable) {
            System.out.println("💃 This song is totally danceable!");
        }
    }

    @Override
    public String getGenreType() {
        return "Pop";
    }

    public boolean isDanceable() {
        return danceable;
    }

    public void setDanceable(boolean danceable) {
        this.danceable = danceable;
    }

    public int getChartPosition() {
        return chartPosition;
    }

    public void setChartPosition(int chartPosition) {
        if (chartPosition < 1) {
            throw new IllegalArgumentException("Chart position must be greater than 0.");
        }
        this.chartPosition = chartPosition;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        if (producer == null || producer.trim().isEmpty()) {
            throw new IllegalArgumentException("Producer name cannot be empty.");
        }
        this.producer = producer;
    }
}
