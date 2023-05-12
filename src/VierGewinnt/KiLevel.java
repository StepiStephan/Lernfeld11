package VierGewinnt;

public enum KiLevel {
    EASY(0),
    MEDIUM(2),
    HARD(4),
    VERYHARD(8);

    public final int value;
    KiLevel(int i) {
        value = i;
    }
}
