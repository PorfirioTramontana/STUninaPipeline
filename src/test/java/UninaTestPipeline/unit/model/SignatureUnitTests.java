package UninaTestPipeline.unit.model;

import UninaTestPipeline.categories.IntegrationTest;
import UninaTestPipeline.categories.UnitTest;
import controller.SignatureController;
import model.Signature;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class SignatureUnitTests {
    private List<Signature> signatures;

    @Before
    public void setUp() {
        SignatureController sc = new SignatureController();
        sc.wipeSignatureFile();

        this.signatures = new ArrayList<>();
        this.signatures.add(new Signature("Pino", "Occhio"));
        this.signatures.add(new Signature("Topo", "Lino"));
    }

    @Test
    public void checkFirstName() throws Exception {
        assertThat(this.signatures.get(0).getFirstName(),equalTo("Pino"));
        assertThat(this.signatures.get(1).getFirstName(),equalTo("Topo"));
        System.out.println("checkFirstName Test Successful");
    }

    @Test
    public void checkLastName() throws Exception {
        assertThat(this.signatures.get(0).getLastName(),equalTo("Occhio"));
        assertThat(this.signatures.get(1).getLastName(),equalTo("Lino"));
        System.out.println("checkLastName Test Successful");
    }
}
