package UninaTestPipeline.integration.controller;

import controller.SignatureBuilder;
import de.rwth.swc.coffee4j.engine.configuration.model.InputParameterModel;
import de.rwth.swc.coffee4j.junit.engine.annotation.CombinatorialTest;
import de.rwth.swc.coffee4j.junit.engine.annotation.configuration.sequential.generation.EnableGeneration;
import de.rwth.swc.coffee4j.junit.engine.annotation.parameter.parameter.InputParameter;

import static de.rwth.swc.coffee4j.engine.configuration.model.Parameter.parameter;
import static org.junit.Assert.assertTrue;

public class SignatureBuilderTest {

    private static SignatureBuilder Builder = new SignatureBuilder();

    private static InputParameterModel model(){
        return InputParameterModel.inputParameterModel("Signature Model")
                .positiveTestingStrength(2)
                .parameters(
                        parameter("FirstName").values("", "Marco", "Stefano", "Walter", "Antonio", "Ernesto",
                                "Giuseppe","Sonia","Anna","Chiara","!=(", "234412"),
                        parameter("LastName").values("","Esposito","Conte","De Pasuqale", "Tartaglia", "Sivo", "!=(","321412")
                ).build();
    }

    @CombinatorialTest()
    @EnableGeneration()
    public void checkBuildingNonVoidSignatures (@InputParameter("FirstName") String FirstName,
                                   @InputParameter("LastName") String LastName){
        Builder.setFirstName(FirstName);
        Builder.setLastName(LastName);
        assertTrue(Builder.build() && (!FirstName.equals("") && !LastName.equals("")));
    }

}