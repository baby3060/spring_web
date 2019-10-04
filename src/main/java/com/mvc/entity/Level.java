package com.mvc.entity;

public enum Level {
    PLATINUM(3, null), GOLD(2, PLATINUM), SILVER(1, GOLD), BRONZE(0, SILVER);

    private int value;
    private Level nextLevel;

    Level(int value, Level nextLevel) {
        this.value = value;
        this.nextLevel = nextLevel;
    }

    public int getValue() {
        return value;
    }

    public Level getNextLevel() {
        return nextLevel;
    }

    public static Level valueOf(int value) {
        switch(value) {
            case 0 : return BRONZE;
            case 1 : return SILVER;
            case 2 : return GOLD;
            case 3 : return PLATINUM;
            default : throw new AssertionError("Unknown value : " + value);
        }
    }

}