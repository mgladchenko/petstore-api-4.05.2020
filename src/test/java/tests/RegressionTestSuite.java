package tests;
/**
 * Regression Test Suite. Classes are executed in exact order from @SuiteClasses
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import static org.junit.runners.Suite.*;

@RunWith(Suite.class)

@SuiteClasses({
       GetPetTests.class,
       UpdatePetTests.class
})
public class RegressionTestSuite {
}
