package UninaTestPipeline.integration.controller;

import controller.SignatureBuilder;
import controller.SignatureController;
import model.Signature;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SignatureControllerTest {

    List<Signature> signatureList;
    SignatureController theController;

    @Before
    public void setUp(){
        signatureList = new ArrayList<>();
        SignatureBuilder builder = new SignatureBuilder();
        theController = new SignatureController();

        builder.setLastName("Topo");
        builder.setFirstName("Lino");
        builder.build();

        signatureList.add(builder.getBuiltSignature());

        builder.setLastName("Pape");
        builder.setFirstName("Rino");
        builder.build();

        signatureList.add(builder.getBuiltSignature());
    }

    @Test
    public void AddSignatureTest(){
        Signature theSignature = signatureList.get(0);
        String out = theController.addSignature(theSignature);
        assertEquals("Hi " + theSignature.getFirstName() + " " + theSignature.getLastName() + ", we've just added you to the welcome list!",out);
    }

    @Test
    public void AddVoidSignatureTest(){
        String out = theController.addSignature(new Signature("",""));
        assertEquals("",out);
    }

    @Test
    public void AddSignatureWithVoidFirstNameTest(){
        String out = theController.addSignature(new Signature("","Lino"));
        assertEquals("",out);
    }

    @Test
    public void AddSignatureWithVoidLastNameTest(){
        String out = theController.addSignature(new Signature("Topo",""));
        assertEquals("",out);
    }

    @Test
    public void CheckFileWipingTest(){
        theController.addSignature(signatureList.get(0));
        theController.addSignature(signatureList.get(1));
        File signatureFile = new File("signatures.txt");
        theController.wipeSignatureFile();
        assertEquals(0,signatureFile.length());
    }

    @Test
    public void CheckFileWritingTest(){
        theController.addSignature(signatureList.get(0));
        theController.addSignature(signatureList.get(1));
        File signatureFile = new File("signatures.txt");
        assertTrue(signatureFile.length() > 0);
    }

 //   @Test
 //   public void failureTest(){fail();}
}