// final undali main method which we will call

abstract class NotificationSender{
    public final void send(String sender, String to, String title, String content){
        rateLimit(sender);
        String formatted = preprocess(title, content);

        notify(sender, to, formatted);

        analytics(sender, to, title, content);
    }

    // common steps
    private void rateLimit(String sender){
        System.out.println("\nChecking Rate Limiting for " + sender);
    }

    private String preprocess(String title, String content){
        System.out.println("Preprocessing the content and title!");
        return "Yahooo : " + content; // some logic like html, emoji processing etc
    }

    // logic not common
    abstract protected void notify(String sender, String to, String formatted);

    // common but can be changed by subclass
    protected void analytics(String sender, String to, String title, String content){
        System.out.println("Analytics of the sender " + sender + " title : " + title + ", content : " + content);
    }
}

class EmailNotificationSender extends NotificationSender{

    @Override
    protected void notify(String sender, String to, String formatted) {
        System.out.println("Sending Email to - " + to +"\n" + formatted + "\nFrom - "+sender);
    }
}

class SMSNotificationSender extends NotificationSender{

    @Override
    protected void notify(String sender, String to, String formatted) {
        System.out.println("Sending Email to - " + to +"\n  " + formatted + "\n From - "+sender);
    }

    @Override
    protected void analytics(String sender, String to, String title, String content) {
        System.out.println("Analytics of the sender " + sender + " title : ,"  + title + ", content : " + content + ", TO : " + to);
    }
}

public class Main {
    public static void main(String[] args) {
        NotificationSender email = new EmailNotificationSender();
        email.send("Yash", "Karun", "Flipkart Sale", "New deals available! checkout!");

        NotificationSender sms = new EmailNotificationSender();
        sms.send("Yash", "Karun", "Flipkart sale", "New deals available! download the app");
    }
}