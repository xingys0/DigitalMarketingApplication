package main.model.UserAccountManagement;

import java.util.ArrayList;

import main.model.Personnel.Profile;

public class UserAccountDirectory {
    ArrayList<UserAccount> useraccountlist;

  public UserAccountDirectory() {
    useraccountlist = new ArrayList<UserAccount>();
  }

  public UserAccount newUserAccount(Profile p, String un, String pw) {
    UserAccount ua = new UserAccount(p, un, pw);
    useraccountlist.add(ua);
    return ua;
  }

  public UserAccount findUserAccount(String id) {
    for (UserAccount ua : useraccountlist) {
      if (ua.isMatch(id)) {
        return ua;
      }
    }
    return null; //not found after going through the whole list
  }

  public UserAccount AuthenticateUser(String un, String pw) {
    for (UserAccount ua : useraccountlist) {
      if (ua.IsValidUser(un, pw)) {
        return ua;
      }
    }
    return null; //not found after going through the whole list
  }
}

