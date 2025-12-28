interface PaymentModeInterface{
    void pay();
}

class RazorPay implements PaymentModeInterface{
    @Override
    public void pay(){
        System.out.println("Successfully Paid using Razorpay!");
    }
}

class UpiPay implements PaymentModeInterface{
    @Override
    public void pay(){
        System.out.println("Successfully Paid using UpiPay!");
    }
}

class PaymentFactory{
    public static PaymentModeInterface getPaymentMode(PaymentMode mode) throws Exception {
        switch (mode) {
            case PaymentMode.RAZORPAY:
                return new RazorPay();
            case PaymentMode.UPI:
                return new UpiPay();
            default:
                throw new Exception("No payment mode available with - " + mode); // custom excpection call extends runtime and super lo msg pampithe best, general flow adhi
        }

    }
}

class PaymentService {
    public void makePayment(PaymentMode mode) throws Exception {
        PaymentModeInterface paymentFactory = PaymentFactory.getPaymentMode(mode);
        paymentFactory.pay();
    }
}


public class PaymentGateway {
    public static void main(String[] args) throws Exception {
        PaymentService paymentService = new PaymentService();
        paymentService.makePayment(PaymentMode.UPI);
    }
}

// Enums
enum PaymentMode {
    RAZORPAY,
    UPI
}
