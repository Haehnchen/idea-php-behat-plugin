package de.espend.idea.php.behat.tests.linemarker;

import com.intellij.psi.PsiFile;
import de.espend.idea.php.behat.tests.BehatLightCodeInsightFixtureTestCase;
import org.jetbrains.plugins.cucumber.psi.GherkinFeature;
import org.jetbrains.plugins.cucumber.psi.GherkinScenario;

/**
 * @author Daniel Espendiller <daniel@espendiller.net>
 * @see de.espend.idea.php.behat.linemarker.TestRunLineMarkerProvider
 */
public class TestRunLineMarkerProviderTest extends BehatLightCodeInsightFixtureTestCase {

    public void testLineMarkerForFeatureAndScenario() {
        PsiFile psiFile = myFixture.configureByText("foobar.feature", "" +
            "Feature: Foobar:\n" +
            "   Scenario: Foobar\n" +
            "       Given Foobar\n"
        );

        assertLineMarker(psiFile, markerInfo ->
            markerInfo.getElement() instanceof GherkinFeature && "Run Test".equals(markerInfo.getLineMarkerTooltip())
        );

        assertLineMarker(psiFile, markerInfo ->
            markerInfo.getElement() instanceof GherkinScenario && "Run Test".equals(markerInfo.getLineMarkerTooltip())
        );
    }

}
