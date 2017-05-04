package de.espend.idea.php.behat.tests.intention;

import de.espend.idea.php.behat.tests.BehatLightCodeInsightFixtureTestCase;
import org.jetbrains.plugins.cucumber.psi.GherkinFileType;

/**
 * @author Daniel Espendiller <daniel@espendiller.net>
 * @see de.espend.idea.php.behat.intention.TestRunIntentionAction
 */
public class TestRunIntentionActionTest extends BehatLightCodeInsightFixtureTestCase {
    public void testThatIntentionIsAvailableForFeature() {
        assertIntentionIsAvailable(
            GherkinFileType.INSTANCE,
            "Feature: foob<caret>ar\n",
            "Behat: Run Test"
        );
    }

    public void testThatIntentionIsAvailableForScenario() {
        assertIntentionIsAvailable(
            GherkinFileType.INSTANCE,"" +
                "Feature: foobar\n" +
                "  Scenario: Foob<caret>ar\n",
            "Behat: Run Test"
        );
    }

    public void testThatIntentionIsAvailableForScenarioStep() {
        assertIntentionIsAvailable(
            GherkinFileType.INSTANCE,"" +
                "Feature: foobar\n" +
                "  Scenario: Foobar\n" +
                "     Given Foo<caret>bar\n",
            "Behat: Run Test"
        );
    }
}
