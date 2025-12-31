interface Pizza{
    String getDescription();
    double getCost();
}

class PlainPizza implements  Pizza{

    @Override
    public String getDescription() {
        return "Plain Dough Pizza!";
    }

    @Override
    public double getCost() {
        return 100.0;
    }
}

class MetroPolitanPizza implements  Pizza{

    @Override
    public String getDescription() {
        return "MetroPolitanPizza Pizza!";
    }

    @Override
    public double getCost() {
        return 200.0;
    }
}

abstract class PizzaDecorator implements Pizza {
    protected Pizza pizza;

    public PizzaDecorator(Pizza pizza){
        this.pizza = pizza;
    }
}

class ExtraCheese extends PizzaDecorator{

    public ExtraCheese(Pizza pizza){
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + " + Extra Cheese";
    }

    @Override
    public double getCost(){
        return pizza.getCost() + 40.0;
    }
}

class Mushrooms extends PizzaDecorator{

    public Mushrooms(Pizza pizza){
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + " + Mushrooms";
    }

    @Override
    public double getCost(){
        return pizza.getCost() + 50.0;
    }
}


public class PizzaShop {
    public static void main(String[] args) {
        Pizza extraCheesePlainPizza = new ExtraCheese(new PlainPizza());
        Pizza metroPolitanWithMushroom = new Mushrooms(new MetroPolitanPizza());
        Pizza metroPolitanWithMushroomAndExtraCheese = new ExtraCheese(new Mushrooms(new MetroPolitanPizza()));

        System.out.println(metroPolitanWithMushroomAndExtraCheese.getCost());
    }
}