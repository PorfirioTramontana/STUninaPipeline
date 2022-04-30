package UninaTestPipeline.integration.controller;

import controller.SignatureBuilder;
import de.rwth.swc.coffee4j.algorithmic.sequential.generator.ipogneg.algorithm.IpogNeg;
import de.rwth.swc.coffee4j.engine.configuration.model.InputParameterModel;
import de.rwth.swc.coffee4j.junit.engine.annotation.CombinatorialTest;
import de.rwth.swc.coffee4j.junit.engine.annotation.configuration.sequential.generation.EnableGeneration;
import de.rwth.swc.coffee4j.junit.engine.annotation.parameter.parameter.InputParameter;
import model.Signature;
import org.junit.experimental.categories.Category;

import static de.rwth.swc.coffee4j.engine.configuration.model.Parameter.parameter;
import static de.rwth.swc.coffee4j.engine.configuration.model.constraints.ConstraintBuilder.constrain;
import static org.junit.Assert.*;

@Category(UninaTestPipeline.categories.IntegrationTest.class)
public class SignatureBuilderTest {

    private static SignatureBuilder Builder = new SignatureBuilder();

    private static InputParameterModel model(){
        return InputParameterModel.inputParameterModel("Signature Model")
                .positiveTestingStrength(2)
                .parameters(
                        parameter("FirstName").values("", "Marco", "Stefano", "Walter", "Antonio", "Ernesto",
                                "Giuseppe","Sonia","Anna","Chiara","!=(", "234412"),
                        parameter("LastName").values("","Esposito","Conte","De Pasuqale", "Tartaglia", "Sivo", "!=(","321412")
                ).errorConstraints(
                        constrain("FirstName","LastName").withName("Non void String")
                                .by((String FirstName, String LastName) -> (!FirstName.equals("") && !LastName.equals(""))),
                        constrain("FirstName","LastName").withName("Only Regular Names")
                                .by((String FirstName, String LastName) -> (FirstName.matches("[a-zA-Z]+") && LastName.matches("[a-zA-Z]+")))
                ).build();
    }

    @CombinatorialTest()
    @EnableGeneration()
    public void checkBuildingSignatures (@InputParameter("FirstName") String FirstName,
                                   @InputParameter("LastName") String LastName){
        Builder.setFirstName(FirstName);
        Builder.setLastName(LastName);
        assertTrue(Builder.build());
        Signature theSignature = Builder.getBuiltSignature();
        assertTrue(theSignature.getFirstName().equals(FirstName) && theSignature.getLastName().equals(LastName));

    }

    @CombinatorialTest()
    @EnableGeneration(algorithms = {IpogNeg.class})
    public void checkNotBuildingNonValidSignatures (@InputParameter("FirstName") String FirstName,
                                                @InputParameter("LastName") String LastName){
        Builder.setFirstName(FirstName);
        Builder.setLastName(LastName);
        boolean built = Builder.build();
        Signature theSignature = Builder.getBuiltSignature();
        if(!built){
            assertNull(theSignature);
            assertTrue(FirstName.equals("") || !FirstName.matches("[a-zA-Z]+")  || LastName.equals("") || !LastName.matches("[a-zA-Z]+"));
        }else{
            assertNotNull(theSignature);
        }
    }

}