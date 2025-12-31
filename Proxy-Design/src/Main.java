import java.util.HashMap;
import java.util.Map;

interface VideoDownloader{
    String downloadVideo(String url);
}

class RealVideoDownloader implements  VideoDownloader{

    @Override
    public String downloadVideo(String url) {
        System.out.println("Downloading video from url - " + url);
        return "Video content";
    }
}


class CachedVideoDownloader implements VideoDownloader{
    Map<String, String> cache;
    VideoDownloader videoDownloader;

    public CachedVideoDownloader(VideoDownloader videoDownloader){
        this.videoDownloader = videoDownloader;
        this.cache = new HashMap<>();
    }

    @Override
    public String downloadVideo(String url){
        if(cache.containsKey(url)){
            System.out.println("Returning Cached Video for - " + url + " Content - " + cache.get(url)+ "\n");
            return cache.get(url);
        }

        System.out.println("Cache miss, downloading... ");
        String content = videoDownloader.downloadVideo(url);
        cache.put(url, content);
        System.out.println("Downloaded content - "+ content +"\n");
        return content;
    }
}

public class Main {
    public static void main(String[] args) {
        VideoDownloader real = new RealVideoDownloader();
        VideoDownloader proxy = new CachedVideoDownloader(real);

        proxy.downloadVideo("http://proxy-pattern");
        proxy.downloadVideo("http://proxy-pattern");
    }
}