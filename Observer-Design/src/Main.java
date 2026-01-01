import java.util.ArrayList;
import java.util.List;

interface Channel{
    void subscribe(Subscriber subscriber);
    void unsubscribe(Subscriber subscriber);
    void notifySubscribers(String videoTitle);
}

class YoutubeChannel implements Channel{
    private List<Subscriber> subscriberList = new ArrayList<>();
    private String channelName;

    public YoutubeChannel(String channelName){
        this.channelName = channelName;
    }

    void uploadVideo(String videoTitle){
            System.out.println(channelName + " has uploaded a new video : " + videoTitle);
            notifySubscribers(videoTitle);
    }


    @Override
    public void subscribe(Subscriber subscriber) {
        subscriberList.add(subscriber);
    }

    @Override
    public void unsubscribe(Subscriber subscriber) {
        subscriberList.remove(subscriber);
    }

    @Override
    public void notifySubscribers(String videoTitle) {
        for(Subscriber subscriber : subscriberList){
            subscriber.update(videoTitle);
        }
    }
}

interface Subscriber{
    void update(String videoTitle);
}

class EmailSubscriber implements Subscriber{

    @Override
    public void update(String videoTitle) {
        System.out.println("Mail - XYZ person has uploaded a new video " + videoTitle);
    }
}

class MobileSubscriber implements Subscriber{

    @Override
    public void update(String videoTitle) {
        System.out.println("Mobile notification - XYZ person has uploaded a new video" + videoTitle);
    }
}


public class Main {
    public static void main(String[] args) {
        YoutubeChannel ytChannel = new YoutubeChannel("Mrbeast");
        ytChannel.subscribe(new EmailSubscriber());
        ytChannel.subscribe(new MobileSubscriber());

        ytChannel.uploadVideo("LLD Video - Observer");
    }
}