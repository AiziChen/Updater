import org.coqur.updater.tools.$;
import org.coqur.updater.exception.InvalidVersionCodeException;
import org.junit.jupiter.api.Test;

public class ToolsTest {
    @Test
    public void invalidVersionTest() {
        String version = "12.B2.A1";
        assert $.isValidVersion(version);

        version = "a*d";
        assert !$.isValidVersion(version);
        version = "_a-d";
        assert !$.isValidVersion(version);
    }

    @Test
    public void isBiggerVersionCodeTest() throws InvalidVersionCodeException {
        String v1 = "23.1a";
        String v2 = "18.ab";
        assert $.isBiggerVersion(v1, v2);
        assert !$.isBiggerVersion(v1, v1);
        assert !$.isBiggerVersion(v2, v1);

        v1 = "12.a3.83";
        v2 = "12.a3.83";
        assert !$.isBiggerVersion(v1, v2);

        v1 = "12.a3.83";
        v2 = "12.a3";
        assert $.isBiggerVersion(v1, v2);
    }
}
