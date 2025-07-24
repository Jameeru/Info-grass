package Examples;

enum PizzaSize {
    SMALL, MEDIUM, LARGE, EXTRALARGE
}

class PizzaOrder {
    PizzaSize pizzaSize;

    public PizzaOrder(PizzaSize pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

    public void orderPizza() {
        switch (pizzaSize) {
            case SMALL:
                System.out.println("I ordered a Small size pizza.");
                break;
            case MEDIUM:
                System.out.println("I ordered a Medium size pizza.");
                break;
            case LARGE:
                System.out.println("I ordered a Large size pizza.");
                break;
            case EXTRALARGE:
                System.out.println("I ordered an Extralarge size pizza.");
                break;
            default:
                System.out.println("I don't know which one to order.");
        }
    }
}

public class EnumDemo_1 {
    public static void main(String[] args) {
        PizzaOrder order = new PizzaOrder(PizzaSize.MEDIUM);
        order.orderPizza();
    }
}
