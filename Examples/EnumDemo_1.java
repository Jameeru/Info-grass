package Examples;

enum ShirtSize {
    SMALL, MEDIUM, LARGE, EXTRALARGE
}

class PizzaOrder {
    ShirtSize pizzaSize;  // Note: Using ShirtSize as a placeholder; adjust as needed
    public PizzaOrder(ShirtSize pizzaSize) {
        this.pizzaSize = pizzaSize;
    }
    public void orderPizza() {
        switch(pizzaSize) {
            case SMALL:
                System.out.println("I ordered a small size pizza.");
                break;
            case MEDIUM:
                System.out.println("I ordered a medium size pizza.");
                break;
            default:
                System.out.println("I don't know which one to order.");
                break;
        }
    }
}

public class EnumDemo_1 {

      public static void main(String[] args) {
        PizzaOrder order = new PizzaOrder(ShirtSize.MEDIUM);
        order.orderPizza();
      }
}
