package main.model.Personnel;

public class EmployeeProfile extends Profile {
    public EmployeeProfile(Person p) {

        super(p); 

    }
    @Override
    public String getRole(){
        return  "Admin";
    }
    
}
