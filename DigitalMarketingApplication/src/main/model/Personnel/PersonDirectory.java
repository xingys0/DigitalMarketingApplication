package main.model.Personnel;

import java.util.ArrayList;

public class PersonDirectory {

    ArrayList<Person> personlist;
  
    public PersonDirectory() {
      personlist = new ArrayList<Person>();
    }
  
    public Person newPerson(String id) {
      Person p = new Person(id);
      personlist.add(p);
      return p;
    }
  
    public Person findPerson(String id) {
      for (Person p : personlist) {
        if (p.isMatch(id)) {
          return p;
        }
      }
      return null; //not found after going through the whole list
    }
  }
  
