package model;

public class Signature {
    private Integer id;
    private String firstName;
    private String lastName;

    public Signature(){}

    public Signature(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }
}
