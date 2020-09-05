package utils;

public enum Direction {
    LEFT(-1),
    RIGHT(1);

    private Integer direction;

    Direction(Integer direction) {
        this.direction = direction;
    }

    public Integer getDirection() {
        return direction;
    }

    public static Direction from(String direction) {
        try {
            if (Integer.parseInt(direction) == 1) {
                return RIGHT;
            }
            return LEFT;
        } catch (NumberFormatException nfe) {
            return LEFT;
        }
    }
}
