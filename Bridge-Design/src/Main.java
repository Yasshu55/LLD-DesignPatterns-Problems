interface VideoQuality{
    void load(String title);
}

class SDQuality implements VideoQuality{
    @Override
    public void load(String title) {
        System.out.println("Viewing video - " + title + " In SD Quality!");
    }
}

class HDQuality implements VideoQuality{
    @Override
    public void load(String title) {
        System.out.println("Viewing video - " + title + " In HDQuality!");
    }
}

class UltraQuality implements VideoQuality{
    @Override
    public void load(String title) {
        System.out.println("Viewing video - " + title + " In UltraQuality!");
    }
}

abstract class VideoPlayer{
    protected VideoQuality videoQuality;

    public VideoPlayer(VideoQuality videoQuality){
        this.videoQuality = videoQuality;
    }

    public abstract void play(String title);
}

class WebPlayer extends VideoPlayer{

    WebPlayer(VideoQuality videoQuality) {
        super(videoQuality);
    }

    @Override
    public void play(String title) {
        System.out.println("Web platform!");
        videoQuality.load(title);
    }
}

class MobilePlayer extends VideoPlayer{

    MobilePlayer(VideoQuality videoQuality) {
        super(videoQuality);
    }

    @Override
    public void play(String title) {
        System.out.println("Mobile platform!");
        videoQuality.load(title);
    }
}

class TVPlayer extends VideoPlayer{

    TVPlayer(VideoQuality videoQuality) {
        super(videoQuality);
    }

    @Override
    public void play(String title) {
        System.out.println("TV platform!");
        videoQuality.load(title);
    }
}

public class Main {
    public static void main(String[] args) {
        VideoPlayer player1 = new TVPlayer(new UltraQuality());
        player1.play("Shinchan");
    }
}