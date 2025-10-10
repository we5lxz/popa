package n2;

class Parent {
    final void displayMessage() {
        System.out.println("This is a final method");
    }
}

class Child extends Parent {
    //@Override
    //void displayMessage() {
       // System.out.println("Trying to override final method");
        //java: displayMessage() in n2.Child cannot override displayMessage() in n2.Parent
        //  overridden method is final
        //тоже самое что и в прошлом
    //}
}

class Main {
    public static void main(String[] args) {
        Child child = new Child();
        child.displayMessage();
    }
}