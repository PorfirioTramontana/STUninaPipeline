package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import model.Signature;


public class SignatureController {

    private File signaturesFile = new File("signatures.txt");

    public void checkSignaturesFile(){
        if(!signaturesFile.exists()){
            createSignaturesFile();
        }
    }

    public void createSignaturesFile(){
        signaturesFile = new File("signatures.txt");
    }

    public String showSignatures() {
        checkSignaturesFile();
        String out = new String();
        try {
            File signaturesObj = new File("signatures.txt");
            Scanner signaturesReader = new Scanner(signaturesObj);
            while (signaturesReader.hasNextLine()) {
                String data = signaturesReader.nextLine();
                out = out.concat(data + "\n");
            }
            signaturesReader.close();
        } catch (FileNotFoundException e) {
            errorHandler(e);
        }
        return "Welcome to: \n" + out;
    }

    public String addSignature(Signature newSignature) {
        if(isNotValidSignature(newSignature)){
            return "";
        }
        checkSignaturesFile();
        writeSignatureToFile(newSignature);
        return "Hi " + newSignature.getFirstName() + " " + newSignature.getLastName() + ", we've just added you to the welcome list!";
    }

    private boolean isNotValidSignature(Signature newSignature) {
        return newSignature.getLastName().equals("") || newSignature.getFirstName().equals("");
    }

    private void writeSignatureToFile(Signature newSignature) {
        try {
            FileWriter signaturesWriter = new FileWriter("signatures.txt", true);
            signaturesWriter.append(newSignature.getFirstName().concat(" ".concat(newSignature.getLastName())));
            signaturesWriter.append("\n");
            signaturesWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            errorHandler(e);
        }
    }

    public void wipeSignatureFile() {
        try {
            FileWriter signaturesWriter = new FileWriter("signatures.txt");
            signaturesWriter.write("");
            signaturesWriter.close();
            System.out.println("Successfully wiped.");
        } catch (IOException e) {
            errorHandler(e);
        }
    }

    private void errorHandler(Exception e){
        System.out.println("An error occurred.");
        e.printStackTrace();
    }
}
