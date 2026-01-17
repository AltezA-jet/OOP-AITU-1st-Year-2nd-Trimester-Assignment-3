public class MetalSong extends SongBase{
    private boolean hasGuitarSolo;
    private int bpm;
    private boolean isHeavy;

    public MetalSong(int id, String name, String artist, String album, String genre, int duration, String releaseDate,
                     boolean hasGuitarSolo, int bpm, boolean isHeavy) {
        super(id, name, artist, album, genre, duration, releaseDate);
        this.hasGuitarSolo = hasGuitarSolo;
        this.bpm = bpm;
        this.isHeavy = isHeavy;
    }
    @Override
    public void musicPlay() {
        System.out.println("🔥 Playing a powerful Metal track: " + getName() + " by " + getArtist());
        if (hasGuitarSolo) {
            System.out.println(" Epic guitar solo incoming!");
        }
    }
    @Override
    public String getGenreType() {
        return "Metal";
    }
    public boolean hasGuitarSolo() {
        return hasGuitarSolo;
    }
    public boolean hasGuitarSolo() {
        return hasGuitarSolo;
    }

    public void setHasGuitarSolo(boolean hasGuitarSolo) {
        this.hasGuitarSolo = hasGuitarSolo;
    }

    public int getBpm() {
        return bpm;
    }

    public void setBpm(int bpm) {
        if (bpm <= 0) {
            throw new IllegalArgumentException("BPM must be greater than 0.");
        }
        this.bpm = bpm;
    }

    public boolean isHeavy() {
        return isHeavy;
    }

    public void setHeavy(boolean heavy) {
        isHeavy = heavy;
    }
    
}
