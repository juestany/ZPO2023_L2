import javax.sound.midi.Soundbank;

public class Main {
    public static void main(String[] args) {
        Vector2D v1 = new Vector2D(4, 3);
        Vector2D v2 = new Vector2D(-3, -7);
        ComplexNumbers c1 = new ComplexNumbers(5, -4);
        ComplexNumbers c2 = new ComplexNumbers(2, 3);

        System.out.println("Vector v1: " + v1.toString());
        System.out.println("Vector v2: " + v2.toString());
        System.out.println("Length of v1: " + v1.calculateLength());
        System.out.println("Sum of v1 and v2: " + Vector2D.add(v1, v2).toString());
        System.out.println("v1 multiplied by 3: " + Vector2D.multiply(v1, 3));
        System.out.println("Are v1 and v2 equal? " + v1.equals(v2));
        System.out.printf("-----\n\n");

        System.out.println("Complex number c1: " + c1.toString() + "\n");
        System.out.println("Complex number c2: " + c2.toString() + "\n");
        System.out.println("Length (module) of c1: " + v1.calculateLength());
        System.out.println("c1 and c2 multiplied: " + ComplexNumbers.multiply(c1, c2).toString());
        System.out.println("c1 multiplied by 4: " + ComplexNumbers.multiply(c1, 4).toString());
        System.out.println("c1 divided by c2: " + ComplexNumbers.divide(c1, c2).toString());
        System.out.println("c1 to the power of 2: " + ComplexNumbers.nThPower(c1, 2).toString());
        System.out.println("New vector by given module of 4 and phi arg of 1.5: " + ComplexNumbers
                .getXYCoordinates(4, 1.5).toString());
        System.out.println("c2's module: " + c2.getModule());
        System.out.println("c2's phi argument: " + c2.getPhi_arg());
    }
}