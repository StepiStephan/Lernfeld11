package VierGewinnt;

public class Tuple3<T1, T2, T3> extends Tuple2<T1,T2> {
    private T3 val3;

    Tuple3(T1 val1, T2 val2, T3 val3) {
        super(val1, val2);
        this.val3 = val3;
    }
    public T3 getVal3() {
        return val3;
    }
}
