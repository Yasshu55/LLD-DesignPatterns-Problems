class PaymentService{
    void doPayment(int amount){
        System.out.println("Payment of " + amount + " has been done!");
    }
}

class TicketBookingService{
    void bookTicket(int seatId, String movie){
        System.out.println("Ticket has been booked at " + seatId + " for this : " + movie);
    }
}

class NotificationService{
    void notifyUsers(){
        System.out.println("Notified");
    }
}

class MovieBookingFacade {
    private PaymentService paymentService;
    private TicketBookingService ticketBookingService;
    private NotificationService notificationService;

    public MovieBookingFacade(){
        this.paymentService = new PaymentService();
        this.ticketBookingService= new TicketBookingService();
        this.notificationService = new NotificationService();
    }

    public void bookMyticket(int amount, int seatId, String movie){
        paymentService.doPayment(amount);
        ticketBookingService.bookTicket(seatId, movie);
        notificationService.notifyUsers();

        System.out.println("Successfully done!");
    }

}

public class Main {
    public static void main(String[] args) {

        MovieBookingFacade movieBookingFacade = new MovieBookingFacade();
        movieBookingFacade.bookMyticket(200, 15, "Varansi");
    }
}