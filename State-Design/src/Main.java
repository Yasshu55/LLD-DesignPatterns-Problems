interface OrderState{
    void nextState(OrderContext context);
    void cancel(OrderContext context);
    String getState();
}

class OrderConfirmed implements OrderState{
    @Override
    public void nextState(OrderContext context) {
        context.setState(new OrderPreparing());
        System.out.println("Order is now being prepared!");
    }

    @Override
    public void cancel(OrderContext context) {
        context.setState(new Cancelled());
        System.out.println("Order Cancelled!");
    }

    @Override
    public String getState() {
        return "ORDER_CONFIRMED";
    }
}

class OrderPreparing implements OrderState{
    @Override
    public void nextState(OrderContext context) {
        context.setState(new OutForDelivery());
        System.out.println("Order is out for delivery");
    }

    @Override
    public void cancel(OrderContext context) {
        context.setState(new Cancelled());
        System.out.println("Order Cancelled!");
    }

    @Override
    public String getState() {
        return "ORDER_PREPARING";
    }
}

class OutForDelivery implements OrderState{
    @Override
    public void nextState(OrderContext context) {
        context.setState(new Delivered());
        System.out.println("Order is delivered!");
    }

    @Override
    public void cancel(OrderContext context) {
        System.out.println("Cannot be cancelled!");
    }

    @Override
    public String getState() {
        return "OUT_FOR_DELIVERY";
    }
}

class Delivered implements OrderState{
    @Override
    public void nextState(OrderContext context) {
        System.out.println("Order is already deliverd");
    }

    @Override
    public void cancel(OrderContext context) {
        System.out.println("Cannot be cancelled already delivered!");
    }

    @Override
    public String getState() {
        return "DELIVERED";
    }
}

class Cancelled implements OrderState{
    @Override
    public void nextState(OrderContext context) {
        System.out.println("Cannot move to next state!");
    }

    @Override
    public void cancel(OrderContext context) {
        System.out.println("ORDER ALREADY Cancelled");
    }

    @Override
    public String getState() {
        return "CANCELLED";
    }
}

class OrderContext{
    OrderState state;

    OrderContext(){
        this.state = new OrderConfirmed();
    }

    void setState(OrderState state){
        this.state = state;
    }

    void next(){
        state.nextState(this);
    }

    void cancel(){
        state.cancel(this);
    }

    public String getState(){
        return state.getState();
    }


}

public class Main {
    public static void main(String[] args) {
        OrderContext order = new OrderContext();

        order.next(); // moves to preparing
        System.out.println(order.getState()); // ORDER_PREPARING
        order.next(); // out for delivery
        order.cancel(); // cannot cancel
        order.next(); // delivered
        order.next(); // order already delivered

        System.out.println();
        OrderContext order2 = new OrderContext();

        order2.next(); // moves to preparing
        System.out.println(order2.getState()); // ORDER_PREPARING
        order2.cancel(); // cancelled
        order2.cancel(); // already cancel
        order2.next(); // delivered
        order2.next(); // order already delivered
    }
}