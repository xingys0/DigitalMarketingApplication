package main.model.Personnel;

public abstract class Profile {
    
    Person person;

    public Profile(Person p) {
      person = p;
    }
  
    public abstract String getRole();
  
    public Person getPerson() {
      return person;
    }
  
    public boolean isMatch(String id) {
      if (person.getPersonId().equals(id)) {
        return true;
      }
      return false;
    }
  }
  
