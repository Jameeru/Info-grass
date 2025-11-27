import java.util.Optional;


//Optional is like an Gift Box
// There is may or may not the gift inside the box
//Before seeing this check whether the gift is inside or not



public class OptionalExample {

    public static void main(String[] args) {
        // Creating an Optional with a gift inside (toy)
        Optional<String> giftWithToy = Optional.of("Teddy Bear");

        // Creating an Optional that's empty (no gift)
        Optional<String> emptyGift = Optional.empty();

        // 1. isPresent() - Check if the gift box has a toy
        System.out.println("Gift with toy present? " + giftWithToy.isPresent()); // true
        System.out.println("Empty gift present? " + emptyGift.isPresent()); // false

        // 2. ifPresent() - Only do something if the gift is present
        giftWithToy.ifPresent(gift -> System.out.println("I am playing with: " + gift));

        // 3. orElse() - Get the gift, or give a default if empty
        String myGift = emptyGift.orElse("Chocolate");
        System.out.println("My gift: " + myGift); // "Chocolate" because the gift box is empty

        // 4. orElseGet() - Lazily provide a default gift using a Supplier (just like orElse but uses a function)
        String lazyGift = emptyGift.orElseGet(() -> "Lego Set");
        System.out.println("Lazy gift: " + lazyGift); // "Lego Set" (only created when needed)

        // 5. orElseThrow() - Throw an exception if the gift box is empty
        try {
            giftWithToy.orElseThrow(() -> new IllegalArgumentException("No gift found!"));
            emptyGift.orElseThrow(() -> new IllegalArgumentException("No gift found!")); // This will throw!
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage()); // "No gift found!" when trying to unwrap empty box
        }

        // 6. map() - Transform the value inside the box (e.g., change toy to uppercase)
        Optional<String> transformedGift = giftWithToy.map(gift -> gift.toUpperCase());
        transformedGift.ifPresent(gift -> System.out.println("Transformed gift: " + gift)); // "TEDDY BEAR"

        // 7. filter() - Only keep the gift if it matches a condition (e.g., must be a "Teddy Bear")
        Optional<String> filteredGift = giftWithToy.filter(gift -> gift.equals("Teddy Bear"));
        filteredGift.ifPresent(gift -> System.out.println("Filtered gift: " + gift)); // "Teddy Bear"

        // 8. get() - Get the value (but it's dangerous because it will throw exception if empty)
        // Use only if you are sure the box is not empty
        try {
            String dangerousGift = giftWithToy.get();
            System.out.println("Dangerous gift: " + dangerousGift); // "Teddy Bear"

            // This will throw NoSuchElementException because the box is empty
            String emptyDangerousGift = emptyGift.get();
        } catch (Exception e) {
            System.out.println("Error when accessing empty gift: " + e);
        }
    }
}
