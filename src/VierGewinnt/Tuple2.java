package VierGewinnt;

public class Tuple2<T1, T2>{
    private T1 val1;
    private T2 val2;

    Tuple2(T1 val1, T2 val2)
    {
        this.val1 = val1;
        this.val2 = val2;
    }

    public T1 getVal1() {
        return val1;
    }

    public T2 getVal2() {
        return val2;
    }
}