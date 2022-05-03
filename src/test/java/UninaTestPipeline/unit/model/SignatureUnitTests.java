package UninaTestPipeline.unit.model;


import de.rwth.swc.coffee4j.engine.configuration.model.InputParameterModel;
import static de.rwth.swc.coffee4j.engine.configuration.model.Parameter.parameter;

import de.rwth.swc.coffee4j.junit.engine.annotation.CombinatorialTest;
import de.rwth.swc.coffee4j.junit.engine.annotation.configuration.sequential.generation.EnableGeneration;
import de.rwth.swc.coffee4j.junit.engine.annotation.parameter.parameter.InputParameter;
import model.Signature;



import java.util.ArrayList;
import java.util.List;

import static de.rwth.swc.coffee4j.engine.configuration.model.constraints.ConstraintBuilder.constrain;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class SignatureUnitTests {
    private static final List<Signature> signatures = new ArrayList<>();

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
    public void AddSignatureTest (@InputParameter("FirstName") String FirstName,
                                   @InputParameter("LastName") String LastName){
        signatures.add(new Signature(FirstName,LastName));
        assertThat(signatures.get(signatures.size() - 1).getFirstName(),equalTo(FirstName));
        assertThat(signatures.get(signatures.size() - 1).getLastName(),equalTo(LastName));

    }

}
