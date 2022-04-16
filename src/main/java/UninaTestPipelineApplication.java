import controller.SignatureController;
import model.Signature;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * sea method
 * */
@SuppressWarnings("checkstyle:Indentation")
public class UninaTestPipelineApplication {
    public static void main(String[] args) {
        manageMenuChoices();
    }

    public static void manageMenuChoices(){
        String cmd = new String();
        String firstName = new String();
        String lastName = new String();
        SignatureController sc = new SignatureController();

        while(!cmd.equals("3")){
            System.out.println("Select an operation:\n0) Wipe signature file\n1) Insert a new signature\n2) Show all signatures\n3) Exit");
            try{
                BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                cmd = bufferRead.readLine();
            }
            catch(IOException e) {
                e.printStackTrace();
            }
            if(cmd.equals("0")){
                clearScreen();
                sc.wipeSignatureFile();
            } else if(cmd.equals("1")){
                clearScreen();
                System.out.println("Insert your first name: ");
                firstName = getString();
                System.out.println("Insert your last name: ");
                lastName = getString();
                insertNewSignature(firstName,lastName);
            } else if (cmd.equals("2")) {
                showAllSignature();
            } else if (!cmd.equals("3")){
                System.out.println("Invalid choice");
                clearScreen();
            }
        }
    }

    public static void insertNewSignature(String first, String last){
        SignatureController sc = new SignatureController();
        Signature newSig = new Signature(first,last);
        System.out.println(sc.addSignature(newSig));
    }

    public static void showAllSignature(){
        SignatureController sc = new SignatureController();
        System.out.println(sc.showSignatures());
    }

    public static String getString(){
        String str = new String();
        try{
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            str = bufferRead.readLine();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static void clearScreen(){
        try {
            clearTerminal();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void clearTerminal() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
}
