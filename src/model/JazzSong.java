package model;
public class JazzSong extends SongBase  {
    private boolean hasImprovisation;
    private String instrumentFocus;
    private String mood;

    public JazzSong(int id, String name, String artist, String album, String genre, int duration, String releaseDate,
                    boolean hasImprovisation, String instrumentFocus, String mood) {
        super(id, name, artist, album, genre, duration, releaseDate);
        this.hasImprovisation = hasImprovisation;
        this.instrumentFocus = instrumentFocus;
        this.mood = mood;
    }

    @Override
    public void MusicPlay() {
        System.out.println("🎷 Now playing a smooth jazz track: " + getName() + " by " + getArtist());
        System.out.println("Instrument focus: " + instrumentFocus + " | Mood: " + mood);
        if (hasImprovisation) {
            System.out.println("🎵 This performance includes a live improvisation!");
        }
    }

    @Override
    public String getGenreType() {
        return "Jazz";
    }

    public boolean hasImprovisation() {
        return hasImprovisation;
    }

    public void setHasImprovisation(boolean hasImprovisation) {
        this.hasImprovisation = hasImprovisation;
    }

    public String getInstrumentFocus() {
        return instrumentFocus;
    }

    public void setInstrumentFocus(String instrumentFocus) {
        this.instrumentFocus = instrumentFocus;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

}
