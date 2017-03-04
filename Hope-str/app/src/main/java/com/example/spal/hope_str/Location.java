package com.example.spal.hope_str;

public class Location {
    private String mCity;
    private String mState;

    /** Setters */
    public Location(String city, String state) {
        setCity(city);
        setState(state);
    }
    public void setState(String state) {
        mState = state;
    }
    public void setCity(String city) {
        mCity = city;
    }

    /** Getters */
    public String getState() {
        return mState;
    }
    public String getCity() {
        return mCity;
    }

    /* Returns the string representation of this Class.*/
    @Override
    public String toString() {
        return mCity + ", " + mState;
    }
}
