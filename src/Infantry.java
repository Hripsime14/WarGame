public class Infantry extends Human {
    private static final String TYPE = "Infantry";

    private static final int HEALTH = 800;
    private static final int STRENGTH = 7;
    private static final int SPEED = 3;

    Infantry() {
        super(HEALTH, STRENGTH, SPEED, TYPE);
    }

    int getInitialHealth() {
        return HEALTH;
    }

    public static int getSTRENGTH() {
        return STRENGTH;
    }

    public static int getSPEED() {
        return SPEED;
    }
}
