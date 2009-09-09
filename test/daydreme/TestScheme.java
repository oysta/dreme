package daydreme;

import static daydreme.SchemeObjects.ENVIRONMENT;
import static daydreme.List.toList;
import dreme.TokenStream;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;

public class TestScheme {
    static final java.util.List<String> SUITES = Arrays.asList(
        "lambda-tests", "define-tests", "if-tests", "define-syntax-tests");

    public static Test suite() throws Exception {
        TestSuite result = new TestSuite();
        for (String suiteName : SUITES) {
            List tests = new Parser().parse(new TokenStream(getReader(suiteName + ".txt")));
            TestSuite suite = new TestSuite(suiteName);
            for (SchemeObject testObj : tests) {
                List test = toList(testObj);
                String name = ((Identifier) test.get(0)).getName();
                SchemeObject actual = toList(test.get(1));
                SchemeObject expected = test.get(2);
                suite.addTest(constructTest(name, expected, actual));
            }
            result.addTest(suite);
        }
        return result;
    }

    private static TestCase constructTest(final String name, final SchemeObject expected,
        final SchemeObject actual)
    {
        return new TestCase() {
            public String getName() {
                return name;
            }

            protected void runTest() throws Throwable {
                assertEquals(expected, actual.evaluate(ENVIRONMENT));
            }
        };
    }

    private static Reader getReader(String fileName) {
        return new InputStreamReader(TestScheme.class.getResourceAsStream(fileName));
    }
}