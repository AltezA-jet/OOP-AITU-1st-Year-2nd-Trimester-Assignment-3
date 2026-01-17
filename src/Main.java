public class Main {
    public static void main(String[] args) {
        Playlist metalPlaylist = new Playlist(1, "Metal Classics");
        MetalSong masterOfPuppets = new MetalSong(
            1,"Master of Puppets","Metallica","Master of Puppets","Metal",515,"1986-03-03",true,220,true
        );
        metalPlaylist.addSong(masterOfPuppets);
        metalPlaylist.showPlaylist();
        metalPlaylist.play();
    }
}