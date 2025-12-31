import java.util.ArrayList;
import java.util.List;

interface CartItem{
    String getName();
    int getPrice();
}

class Product implements CartItem{
    private String name;
    private int price;

    public Product(String name, int price) {
        this.price = price;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return price;
    }
}

class ProductBundle implements CartItem{
    private String bundleName;
    List<CartItem> cartItemList;

    public ProductBundle(String bundleName){
        this.bundleName = bundleName;
        this.cartItemList = new ArrayList<>();
    }

    public void addProduct(CartItem product){
        cartItemList.add(product);
    }

    @Override
    public String getName() {
        StringBuilder products = new StringBuilder(bundleName + " [ ");
        for (CartItem item : cartItemList) {
            products.append(item.getName()).append(", ");
        }
        products.append("]");
        return products.toString();
    }


    @Override
    public int getPrice() {
        int price = 0;

        for(CartItem cartItem : cartItemList){
            price += cartItem.getPrice();
        }

        return price;
    }
}

public class Main {
    public static void main(String[] args) {
        CartItem phone = new Product("IPhone", 100000);
        CartItem earphones = new Product("earBuds", 200);

        ProductBundle productBundle = new ProductBundle("Male Shopping");
        productBundle.addProduct(new Product("Cap",200));
        productBundle.addProduct(new Product("Shirt",200));
        productBundle.addProduct(new Product("Crocs",1000));
        productBundle.addProduct(new Product("Shoes",1200));
        productBundle.addProduct(new Product("Jeans",2000));

        List<CartItem> cart = new ArrayList<>();
        cart.add(phone);
        cart.add(productBundle);
        cart.add(earphones);

        int total = 0;
        for(CartItem cartItem : cart ){
            total += cartItem.getPrice();
        }

        System.out.println(total);

    }
}