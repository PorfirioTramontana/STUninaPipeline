package controller;

import model.Signature;

public class SignatureBuilder {

    private String FirstName;
    private String LastName;
    private Signature builtSignature;
    private final SignatureChecker checker = new SignatureChecker();

    public SignatureBuilder(){}

    public boolean build(){
        String firstName = this.FirstName;
        String lastName = this.LastName;
        FirstName = "";
        LastName = "";
        Signature theSignature = new Signature(firstName,lastName);
        if (checker.check(theSignature)){
            builtSignature = theSignature;
            return true;
        }
        return false;
    }

    public Signature getBuiltSignature(){
        return builtSignature;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    private static class SignatureChecker {

        public boolean check(Signature theSignature) {
            if(isTheFirstOrLastNameAVoidName(theSignature.getFirstName(),theSignature.getLastName())) return false;
            return !isTheFirstOrLastNameANonValidName(theSignature.getFirstName(), theSignature.getLastName());
        }

        private boolean isTheFirstOrLastNameANonValidName(String firstName, String lastName) {
            return isTheNameANonValidName(firstName) || isTheNameANonValidName(lastName);
        }

        private boolean isTheNameANonValidName(String Name) {
            return Name.matches("[a-zA-Z]+");
        }

        private boolean isTheFirstOrLastNameAVoidName(String firstName,String lastName){
            return isTheNameAVoidName(firstName) || isTheNameAVoidName(lastName);
        }

        private boolean isTheNameAVoidName(String Name) {
            return Name.equals("");
        }

    }
}
