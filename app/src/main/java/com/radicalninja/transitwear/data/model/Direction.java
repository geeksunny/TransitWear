package com.radicalninja.transitwear.data.model;

public enum Direction {

    NORTH('N'),
    SOUTH('S'),
    EAST('E'),
    WEST('W');

    public static final Direction[] A = { NORTH, EAST };
    public static final Direction[] B = { SOUTH, WEST };

    private final char code;

    Direction(final char directionCode) {
        code = directionCode;
    }

    public static Direction fromCode(final char directionCode) {
        switch (directionCode) {
            case 'N':
            case 'n':
                return NORTH;
            case 'S':
            case 's':
                return SOUTH;
            case 'E':
            case 'e':
                return EAST;
            case 'W':
            case 'w':
                return WEST;
        }
        return null;
    }

}
