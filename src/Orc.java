public class Orc extends Mystic {
    private static final String TYPE = "Orc";

    private static final int HEALTH = 1200;
    private static final int STRENGTH = 15;
    private static final int SPEED = 1;

    Orc() {
        super(HEALTH, STRENGTH, SPEED, TYPE);
    }

    int getInitialHealth() {
        return HEALTH;
    }
}