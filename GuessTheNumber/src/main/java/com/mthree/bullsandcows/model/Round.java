package com.mthree.bullsandcows.model;

import java.util.Objects;

public class Round {

    private final String guess;
    private final String result;
    private final int gameID;
    private String time;

    public Round(String guess, String result, int gameID) {
        this.guess = guess;
        this.result = result;
        this.gameID = gameID;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.guess);
        hash = 67 * hash + Objects.hashCode(this.result);
        hash = 67 * hash + this.gameID;
        hash = 67 * hash + Objects.hashCode(this.time);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Round other = (Round) obj;
        if (this.gameID != other.gameID) {
            return false;
        }
        if (!Objects.equals(this.guess, other.guess)) {
            return false;
        }
        if (!Objects.equals(this.result, other.result)) {
            return false;
        }
        if (!Objects.equals(this.time, other.time)) {
            return false;
        }
        return true;
    }

    public String getGuess() {
        return guess;
    }

    public String getResult() {
        return result;
    }

    public int getGameID() {
        return gameID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
