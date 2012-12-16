package testAhoCorasick;

import junit.framework.Test;
import junit.framework.TestSuite;





public class KaikkiTestit {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for testAhoCorasick");
		// $JUnit-BEGIN$
		suite.addTestSuite(DynaaminenTaulukkoTestit.class);
		suite.addTestSuite(JonoTest.class);
		suite.addTestSuite(TriePuuTest.class);
		suite.addTestSuite(ALTulosteFunktioTest.class);
		suite.addTestSuite(AhoCorasickSearchTest.class);

		// $JUnit-END$
		return suite;
	}

}
