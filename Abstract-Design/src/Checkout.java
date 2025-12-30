// paymentGateway, indian factory, different types of payments and main abstract interface

interface RegionFactory {
    PaymentGateway createPayment(String type);
    Invoice createInvoice();
}

interface PaymentGateway{
    void pay(int amount);
}

class RazorPay implements PaymentGateway{

    @Override
    public void pay(int amount) {
        System.out.println("Successfully paid " + amount + " using RazorPay");
    }
}

class UPI implements PaymentGateway{

    @Override
    public void pay(int amount) {
        System.out.println("Successfully paid " + amount + " using UPI");
    }
}

class Stripe implements PaymentGateway{

    @Override
    public void pay(int amount) {
        System.out.println("Successfully paid " + amount + " using Stripe");
    }
}

class Paypal implements PaymentGateway{

    @Override
    public void pay(int amount) {
        System.out.println("Successfully paid " + amount + " using Paypal");
    }
}

interface Invoice{
    void generateInvoice();
}

class GstInvoice implements Invoice{
    @Override
    public void generateInvoice() {
        System.out.println("Sent Invoice using Indian GST to xyz person successfully!");
    }
}

class USAInvoice implements Invoice{
    @Override
    public void generateInvoice() {
        System.out.println("Sent Invoice using USA Invoice to xyz person successfully!");
    }
}

class IndianFactory implements RegionFactory {

    @Override
    public PaymentGateway createPayment(String type) {

        switch (type) {
            case "razorPay":
                return new RazorPay();
            case "upi":
                return new UPI();
            default:
                throw new RuntimeException("Invalid Payment method");
        }
    }

    @Override
    public Invoice createInvoice() {
        return new GstInvoice();
    }
}

class USAFactory implements RegionFactory {

    @Override
    public PaymentGateway createPayment(String type) {
        switch (type) {
            case "stripe":
                return new Stripe();
            case "paypal":
                return new Paypal();
            default:
                throw new RuntimeException("Invalid Payment method");
        }
    }

    @Override
    public Invoice createInvoice() {
        return new USAInvoice();
    }
}

class CheckoutService {
    private final PaymentGateway paymentGateway;
    private final Invoice invoice;

    CheckoutService(RegionFactory regionFactory, String type){
        this.paymentGateway = regionFactory.createPayment(type);
        this.invoice = regionFactory.createInvoice();
    }

    public void completeOrder(int amount){
        paymentGateway.pay(amount);
        invoice.generateInvoice();
    }

}

public class Checkout {
    public static void main(String[] args) {
        CheckoutService checkoutService = new CheckoutService(new USAFactory(), "stripe");
        checkoutService.completeOrder(100);
    }
}