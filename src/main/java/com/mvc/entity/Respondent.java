package com.mvc.entity;

public class Respondent {
    private int age;
    private String location;

    public Respondent() {
        this.location = "";
    }
    public Respondent(int age, String location) {
        this.age = age;
        this.location = location;
    }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String toString() {
        return "[" + location + "] : " + age;
    }

    public boolean equals(Object obj) {
        if( obj == null ) {
            return false;
        }

        if( obj == this ) {
            return true;
        } else {
            if( obj instanceof Respondent ) {
                Respondent temp = (Respondent)obj;

                return ((this.age == temp.age) && (this.location.equals(temp.location)));
            } else {
                return false;
            }
        }
    }

    public int hashCode() {
        int result = 17;

        result += this.age;
        result += this.location.hashCode();
        
        return result;
    }

}