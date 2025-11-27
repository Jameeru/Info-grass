public class Gene {

    // Simple generic method
    public <T> void genericsMethod(T data) {
        System.out.println("Generics Method: " + data);
    }

    // Generic method with multiple type parameters
    public <T, U> void multipleTypeMethod(T t, U u) {
        System.out.println("Multiple Type Method: " + t + ", " + u);
    }

    // Generic method with a return type
    public <T> T genericReturnMethod(T data) {
        System.out.println("Generic Return Method: " + data);
        return data;
    }

    // Static generic method
    public static <T> boolean isEqual(T a, T b) {
        return a.equals(b);
    }

    // Generic method with bounded type
    public <T extends Number> void boundedMethod(T number) {
        System.out.println("Bounded Method (double value): " + number.doubleValue());
    }

    // Generic method for arrays
    public static <T> void printArray(T[] array) {
        System.out.println("Print Array Method:");
        for (T item : array) {
            System.out.println(item);
        }
    }

    public static void main(String[] args) {
        Gene demo = new Gene();

        // 1. Simple generic method
        demo.genericsMethod("Java");
        demo.genericsMethod(10);

        // 2. Generic method with multiple type parameters
        demo.multipleTypeMethod("Age", 25);

        // 3. Generic method with return type
        String s = demo.genericReturnMethod("Returned Value");
        Integer i = demo.genericReturnMethod(100);

        // 4. Static generic method
        System.out.println("Is Equal: " + Gene.isEqual(10, 10));
        System.out.println("Is Equal: " + Gene.isEqual("Java", "Python"));

        // 5. Generic method with bounded types
        demo.boundedMethod(3.1415);
        demo.boundedMethod(42);

        // 6. Generic method with array
        String[] strArr = {"A", "B", "C"};
        Integer[] intArr = {1, 2, 3};
        Gene.printArray(strArr);
        Gene.printArray(intArr);
    }
}
