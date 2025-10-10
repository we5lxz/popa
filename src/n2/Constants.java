package n2;

public class Constants {
    public static final double PI = 3.14159;

    public static void main(String[] args) {
        double radius = 5.0;

        double area = Constants.PI * radius * radius;

        System.out.println("радиус " + radius);
        System.out.println("площадь " + area);
    }
}
