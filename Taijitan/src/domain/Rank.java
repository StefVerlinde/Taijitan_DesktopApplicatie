package domain;

public enum Rank {
    Kyu6(1),

    Kyu5(2),

    Kyu4(3),

    Kyu3(4),

    Kyu2(5),

    Kyu1(6),

    Dan1(7),

    Dan2(8),

    Dan3 (9),

    Dan4(10),

    Dan5(11),

    Dan6(12),

    Dan7(13),

    Dan8(14),

    Dan9(15),

    Dan10(16),

    Dan11(17),

    Dan12(18);

    private int id;

    Rank(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Rank getById(int id) {
        for (Rank r : values()) {
            if (r.id == id) return r;
        }
        return null;
    }
}
