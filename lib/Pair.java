package lib;

public class Pair {
    Object first;
    Object second;

    public Object getFirst() {
        return this.first;
    }

    public Object getSecond() {
        return this.second;
    }

    public Pair(Object a, Object b) {
        this.first = a;
        this.second = b;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        return "( " + this.first.toString() + " , " + this.second.toString() + " ) ";
    }
}
