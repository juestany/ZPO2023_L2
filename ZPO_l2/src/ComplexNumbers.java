import java.util.*;
import java.io.*;
public class ComplexNumbers extends Vector2D {
    public ComplexNumbers(double x, double y) {
        /**
         * Constructor function
         * @param x (double) real part of a complex number
         * @param y (double) imaginary part of a complex number
         */
        super(x, y);
    }

    public static ArrayList<double[]> readComplexData(String filename) throws FileNotFoundException {
        /**
         * A method that reads data from file in the form of: 't x+yi' and saves it in an arraylist in the form
         * of: 't mod arg'.
         * @param filename (String) path of the file
         * @returns resultArrayList (Arraylist<double[]>) an arraylist of arrays of doubles
         * @throws FileNotFoundException
         */
        File file = new File(filename);
        Scanner sc = new Scanner(file);

        ArrayList<double[]> doubleArrayList = new ArrayList<>();
        ArrayList<double[]> resultArrayList = new ArrayList<>();

        sc.nextLine(); // skip first line
        while (sc.hasNextLine()) {
            String[] line = sc.nextLine().replace("i", "").split("\\s+"); // remove all
                                                                // 'i' letters and split on space into [t, x+y]
            double first, second; // first = x, second = y

            String secondArg = line[1]; // x+y
            int substringLength = secondArg.startsWith("-") ? 8 : 7; // including the length of substring depending on
                                                                // whether there's a minus in front

            first = Double.parseDouble(secondArg.substring(0, substringLength)); // parsing both to doubles
            second = Double.parseDouble(secondArg.substring(substringLength));

            double[] newLine = {Double.parseDouble(line[0]), first, second}; // reformed line
            doubleArrayList.add(newLine);
        }

        for (double[] line : doubleArrayList) { // this loop calculates mod and arg of complex number and puts it in
                                                // the resultArrayList
            double[] newLine = new double[3];
            newLine[0] = line[0];
            ComplexNumbers complexNumber = new ComplexNumbers(line[1], line[2]);
            newLine[1] = complexNumber.getModule();
            newLine[2] = complexNumber.getPhi_arg();
            resultArrayList.add(newLine);
        }
        return resultArrayList;
    }

    public static void saveComplexData(ArrayList<double[]> data) throws IOException {
        /**
         * A method that saves complex data in the form of 't mod arg'. If file already exists, the data gets written
         * to it. If it doesn't, a new file is created.
         * @param data (ArrayList<double[]>) data to save
         * @throws IOException
         */
        File newFile = new File("out_data.out");
        boolean fileExists = newFile.exists();

        try (FileWriter writer = new FileWriter(newFile, fileExists)) {
            if (fileExists) {
                System.out.println("File exists. Appending data to: " + newFile.getName());
            } else {
                System.out.println("File created: " + newFile.getName());
            }

            writer.write("t mod arg\n");
            for (double[] line : data) { // write data to the file
                for (double value : line) {
                    writer.write(value + " ");
                }
                writer.write("\n");
            }
            System.out.println("Data written to the file.");
        } catch (IOException ex) {
            System.out.println("An error occurred while writing data to the file.");
        }
    }


    @Override
    public String toString() {
        /**
         * A method that returns polar and algebraic form of a
         * complex number as a String object.
         */
        return "\nPolar form: " + this.getModule() + "exp(" + this.getPhi_arg() + "i)\n" +
                "Algebraic form: z = " + this.x + " + " + this.y + "i";
    }

    public void readComplexNumber() {
        /**
         * A method that reads user's complex number in a form of x+iy and sets it.
         * Also removes any whitespaces, the '*' and 'i' symbol.
         */
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a complex number in the form of x+iy:");
        String input = sc.nextLine();
        input = input.replace("*", "") // remove '*' symbol
                .replaceAll("\\s+","") // remove whitespaces
                .replace("i", ""); // remove complex symbol 'i'

        try {
            int plusIndex = input.indexOf('+');
            if (plusIndex == 0 || plusIndex == input.length() - 1 || plusIndex == -1) {
                throw new InputMismatchException("Invalid input format. Expected x+iy.");
            }
            String real = input.substring(0, plusIndex);
            String imag = input.substring(plusIndex);
            this.x = Double.parseDouble(real);
            this.y = Double.parseDouble(imag);
        } catch (InputMismatchException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static ComplexNumbers multiply(ComplexNumbers c1, ComplexNumbers c2) {
        /**
         * A method that multiplies two complex numbers.
         * @param c1 (ComplexNumbers) first complex number
         * @param c2 (ComplexNumbers) second complex number
         * @return new complex number
         */
        return new ComplexNumbers(c1.x * c2.x - c1.y * c2.y, c1.y * c2.x + c1.x * c2.y);
    }

    public static ComplexNumbers divide(ComplexNumbers c1, ComplexNumbers c2) {
        /**
         * A method that divides two complex numbers.
         * @param c1 (ComplexNumbers) first complex number
         * @param c2 (ComplexNumbers) second complex number
         * @throw ArithmeticException if(c2.x + c2.y == 0 || denominator == 0)
         * @return new complex number
         */
        double denominator = Math.pow(c2.getModule(), 2);
        if(c2.x + c2.y == 0 || denominator == 0) {
            throw new ArithmeticException("Divided by zero.");
        } else {
            ComplexNumbers c3 = multiply(c1, c2.conjugate());
            return new ComplexNumbers(c3.x / denominator, c3.y / denominator);
        }
    }

    public ComplexNumbers conjugate() {
        return new ComplexNumbers(this.x, -this.y);
    }

    public static ComplexNumbers nThPower(ComplexNumbers c, int n) {
        /**
         * A method that calculates the n-th power of a complex number.
         * @param c (ComplexNumbers)
         * @param n (int)
         * @throw ArithmeticException if n < 0
         * @return c to the power of n
         */
        if(n < 0) {
            throw new ArithmeticException("Negative exponent not supported.");
        } else {
            double module = Math.pow(c.getModule(), n);
            double phi_arg = c.getPhi_arg() * n;
            return getXYCoordinates(module, phi_arg);
        }
    }

    public static ComplexNumbers getXYCoordinates(double module, double phi_arg) {
        /**
         * A method that returns a complex number objects using given
         * module and phi argument.
         * @param module (double)
         * @param phi_arg (double)
         * @return a complex number based on the module and phi argument
         */
        return new ComplexNumbers(module * Math.cos(phi_arg), module * Math.sin(phi_arg));
    }

    public double getModule() {
        return calculateLength();
    }

    public double getPhi_arg() {
        return Math.atan(this.y / this.x); // returned in radians
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }
}
