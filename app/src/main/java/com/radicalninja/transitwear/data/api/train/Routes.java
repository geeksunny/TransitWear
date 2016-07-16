package com.radicalninja.transitwear.data.api.train;

public enum Routes {

    RED("red"),
    BLUE("blue"),
    BROWN("brn"),
    GREEN("g"),
    ORANGE("org"),
    PURPLE("p"),
    PURPLE_EXPRESS("pexp"),
    PINK("pink"),
    YELLOW("y");

    final String name;

    Routes(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
