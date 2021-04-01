package com.example.friendmanager;

public class Friend {
    private int id;
    private String firstName;
    private String lastName;
    private String email;

    public Friend(int newId, String newFirst, String newLast, String newEmail)
    {
        setId(newId);
        setFirstName(newFirst);
        setLastName(newLast);
        setEmail(newEmail);
    }

    public void setId(int newId)
    {
        id = newId;
    }

    public void setFirstName(String newFirst)
    {
        firstName = newFirst;
    }

    public void setLastName(String newLast)
    {
        lastName = newLast;
    }

    public void setEmail(String newEmail)
    {
        email = newEmail;
    }

    public int getId()
    {
        return id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public String toString()
    {
        return id + " " + firstName + lastName + " " + email;
    }
}
