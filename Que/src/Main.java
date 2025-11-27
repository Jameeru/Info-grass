import java.util.*;

class Gener<T, U, K, V, E> {
    T typeThing;
    U unknownThing;
    Map<K, V> keyValuePairs;
    List<E> elements;

    public Gener(T t, U u) {
        this.typeThing = t;
        this.unknownThing = u;
        this.keyValuePairs = new HashMap<>();
        this.elements = new ArrayList<>();
    }

    public void addKeyValue(K key, V value) {
        keyValuePairs.put(key, value);
    }

    public void addElement(E element) {
        elements.add(element);
    }

    public void printBox() {
        System.out.println(" T (typeThing): " + typeThing);
        System.out.println(" U (unknownThing): " + unknownThing);
        System.out.println(" K/V (Map): " + keyValuePairs);
        System.out.println(" E (List): " + elements);
    }
}
public class Main {
    public static void main(String[] args) {
        // Use: T = String, U = Integer, K = String, V = Double, E = Character
        Gener<String, Integer, String, Double, Character> myBox =
                new Gener<>("Lego Set", 8);//Second thing different type

        myBox.addKeyValue("Price", 1499.99);//Key, Value
        myBox.addKeyValue("Weight", 2.5);

        myBox.addElement('A');
        myBox.addElement('B');
        myBox.addElement('C');//List

        myBox.printBox();
    }
}
