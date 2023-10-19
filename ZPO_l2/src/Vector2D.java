public class Vector2D {
    protected double x;
    protected double y;

    public Vector2D(double x, double y) {
        /**
         * Constructor function
         */
        this.x = x;
        this.y = y;
    }

    public double calculateLength() {
        /**
         * A method that calculates length of a vector.
         * @return vector length
         */
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public static Vector2D add(Vector2D v1, Vector2D v2){
        /**
         * A static method that adds two vectors.
         * @param v1 (Vector2D)
         * @param v2 (Vector2D)
         * @return new Vector2D
         */
        return new Vector2D(v1.x + v2.x, v1.y + v2.y);
    }

    public static Vector2D subtract(Vector2D v1, Vector2D v2) {
        /**
         * A method that subtracts given vectors.
         * @param v1 (Vector2D)
         * @param v2 (Vector2D)
         * @return new Vector2D
         */
        return new Vector2D(v1.x - v2.x, v1.y - v2.y);
    }

    public static Vector2D multiply(Vector2D v, double scalar) {
        /**
         * A method that multiplies vector by a given scalar.
         * @param v (Vector2D)
         * @param scalar (double)
         * @return new Vector2D
         */
        return new Vector2D(v.x * scalar, v.y * scalar);
    }

    public static Vector2D divide(Vector2D v, double scalar) {
        /**
         * Method that divides vector by a given scalar.
         * @param v (Vector2D)
         * @param scalar (double)
         * @return new Vector2D
         */
        return new Vector2D(v.x / scalar, v.y / scalar);
    }

    @Override
    public String toString() {
        /**
         * Method returning a String representation of a vector.
         * @return vector
         */
        return "Vector: [" + this.x + ", " + this.y + "]";
    }

    public boolean equals(Vector2D v) {
        /**
         * A method that checks equality between two vectors
         * @param v (Vector2D)
         * @return true if (this.x == c.x && this.y == c.y)
         */
        return this.x == v.x && this.y == v.y;
    }
}
