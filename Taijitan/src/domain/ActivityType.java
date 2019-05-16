package domain;

public enum ActivityType {

    Excursie(0),
    Stage(1);

    private int id;

    ActivityType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static ActivityType getById(int id) {
        for (ActivityType c : values()) {
            if (c.id == id) return c;
        }
        return null;
    }

}
