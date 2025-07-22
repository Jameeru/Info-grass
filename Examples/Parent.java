package Examples;

class Parent {
    final void display() {
        System.out.println("Final method");
    }
}

class Child extends Parent {
    // void display() {} ❌ Error: Cannot override
}

