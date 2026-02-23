package home_appliances;

public enum Modalita {
    ECO, ADAPTIVE, PERFORMANCE;

    public double moltiplicatore() {
        return switch (this) {
            case ECO -> 0.8;
            case ADAPTIVE -> 1.0;
            case PERFORMANCE -> 1.3;
        };
    }
}