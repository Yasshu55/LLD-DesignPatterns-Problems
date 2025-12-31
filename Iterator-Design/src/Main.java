import java.util.ArrayList;
import java.util.List;

class Video{
    private String title;

    Video(String title){
        this.title = title;
    }

    public String getVideoTitle(){
        return title;
    }
}

interface Playlist{
    PlaylistIterator createIterator();
}

class YoutubePlaylist implements Playlist{
    private List<Video> videos;

    public YoutubePlaylist(){
        this.videos = new ArrayList<>();
    }

    public void add(Video video){
        videos.add(video);
    }

    @Override
    public PlaylistIterator createIterator() {
        return new YoutubePlaylistIterator(videos);
    }
}

interface PlaylistIterator{
    boolean hasNext();
    Video next();
}

class YoutubePlaylistIterator implements PlaylistIterator{
    private List<Video> videos;
    private int position;

    public YoutubePlaylistIterator(List<Video> videos) {
        this.videos = videos;
        this.position = 0;
    }

    @Override
    public boolean hasNext() {
        return position < videos.size();
    }

    @Override
    public Video next() {
        return hasNext() ? videos.get(position++) : null;
    }
}


public class Main {
    public static void main(String[] args) {

        YoutubePlaylist youtubePlaylist = new YoutubePlaylist();
        youtubePlaylist.add(new Video("LLD"));
        youtubePlaylist.add(new Video("HLD"));

        PlaylistIterator playlistIterator = youtubePlaylist.createIterator();

        while (playlistIterator.hasNext()){
            System.out.println(playlistIterator.next().getVideoTitle());
        }

    }
}