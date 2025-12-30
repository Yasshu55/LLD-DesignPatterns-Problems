import java.time.LocalDateTime;

interface NotificationWrapper{
    void sendNotification(String orderId, String name);
}

class EmailNotification implements  NotificationWrapper{

    @Override
    public void sendNotification(String orderId, String name) {
        System.out.println("Mail - Hi " + name + " Your order : " + orderId + " has been successfully confirmed");
    }
}

class SMSNotification implements  NotificationWrapper{

    @Override
    public void sendNotification(String orderId, String name) {
        System.out.println("Sms - Hi " + name + " Your order : " + orderId + " has been successfully confirmed");
    }
}

class SlackNotificationAdapter implements  NotificationWrapper{
    private final SlackNotificationAPI slackNotificationAPI;

    SlackNotificationAdapter(SlackNotificationAPI slackNotificationAPI){
        this.slackNotificationAPI = slackNotificationAPI;
    }

    @Override
    public void sendNotification(String orderId, String name) {
        slackNotificationAPI.notify(orderId, name, LocalDateTime.now());
    }
}

class SlackNotificationAPI{
    void notify(String orderId, String name, LocalDateTime localDateTime){
        System.out.println("Slack - Hi " + name + " Your order : " + orderId + " has been successfully confirmed at " + localDateTime);
    }
}

class NotificationService {
    NotificationWrapper notificationWrapper;

    NotificationService(NotificationWrapper notificationWrapper){
        this.notificationWrapper = notificationWrapper;
    }

    void send(String orderId, String name){
        notificationWrapper.sendNotification(orderId, name);
    }
}

public class NotificationMain {
    public static void main(String[] args) {
        NotificationService notificationService = new NotificationService(new SlackNotificationAdapter(new SlackNotificationAPI()));
        notificationService.send("1834359", "Yash");
    }
}