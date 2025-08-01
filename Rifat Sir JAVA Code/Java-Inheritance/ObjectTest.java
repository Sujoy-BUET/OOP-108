import java.util.HashMap;
import java.util.Objects;

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Point)) {
            return false;
        }
        Point p = (Point) o;
        if (p.x == this.x && p.y == this.y)
            return true;
        return false;
    }

    //So essentially, this ensures that two objects with the same values for x and y will produce the same hash code. 
    //It’s often paired with an overridden equals() method, so the two behave consistently.

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}

public class ObjectTest {
    public static void main(String[] args) {
        Point p1 = new Point(10, 20);
        // without override toString() method the
        // following will print something like this
        // Point@3cd1a2f1
        System.out.println(p1);
        Point p2 = new Point(10, 20);
        System.out.println(p2);

        System.out.println(p1 == p2);
        System.out.println(p1.equals(p2));
        System.out.println(p1.hashCode()+" " + p2.hashCode());
        HashMap<Point, String> m = new HashMap<>();
        m.put(p1, "Hello");
        System.out.println(m.get(p2));
    }
}
