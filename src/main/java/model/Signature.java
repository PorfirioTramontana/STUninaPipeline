package model;

import java.util.HashSet;
import java.util.Set;
import java.util.List;

public class Signature {
    private Integer id;
    private String firstName;
    private String lastName;

    public Signature(){}

    public Signature(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static Set<String> getFirstNameSet(List<Signature> SignatureList){
        Set<String> FirstNameSet = new HashSet<>();
        for (Signature signature: SignatureList) {
            FirstNameSet.add(signature.getFirstName());
        }
        return FirstNameSet;
    }

    public static Set<String> getLastNameSet(List<Signature> SignatureList){
        Set<String> LastNameSet = new HashSet<>();
        for (Signature signature: SignatureList) {
            LastNameSet.add(signature.getLastName());
        }
        return LastNameSet;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
