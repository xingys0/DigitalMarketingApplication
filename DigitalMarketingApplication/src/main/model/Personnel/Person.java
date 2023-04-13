package main.model.Personnel;

public class Person {
    String id;

    public Person(String id) {

        this.id = id;
    }

    public String getPersonId() {
        return id;
    }

    public boolean isMatch(String id) {
        if (getPersonId().equals(id)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return getPersonId();
    }
}

    
