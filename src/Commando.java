public class Commando extends Human {
    private static final String TYPE = "Commando";

    private static final int HEALTH = 850;
    private static final int STRENGTH = 12;
    private static final int SPEED = 4;

    public Commando() {
        super(HEALTH, STRENGTH, SPEED, TYPE);
    }

    int getInitialHealth() {
        return HEALTH;
    }
}