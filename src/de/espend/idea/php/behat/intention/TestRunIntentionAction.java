package de.espend.idea.php.behat.intention;

import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import de.espend.idea.php.behat.utils.BehatPluginUtil;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.plugins.cucumber.psi.GherkinFeature;
import org.jetbrains.plugins.cucumber.psi.GherkinScenario;
import org.jetbrains.plugins.cucumber.psi.GherkinStep;

/**
 * @author Daniel Espendiller <daniel@espendiller.net>
 */
public class TestRunIntentionAction extends PsiElementBaseIntentionAction {
    @Override
    public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement psiElement) throws IncorrectOperationException {
        PsiElement context = getTestContextElement(psiElement);
        if(context != null) {
            BehatPluginUtil.executeDebugRunner(psiElement);
        }
    }

    @Override
    public boolean isAvailable(@NotNull Project project, Editor editor, @NotNull PsiElement psiElement) {
        return getTestContextElement(psiElement) != null;
    }

    @Nullable
    private PsiElement getTestContextElement(@NotNull PsiElement psiElement) {
        if(psiElement instanceof GherkinScenario || psiElement instanceof GherkinFeature) {
            return psiElement;
        }

        // Scenario: Foo<caret>bar
        PsiElement parent = psiElement.getParent();
        if(parent instanceof GherkinScenario || parent instanceof GherkinFeature) {
            return parent;
        }

        // Scenario: Foobar
        //   Given <caret>
        if(parent instanceof GherkinStep) {
            PsiElement parent1 = parent.getParent();
            if(parent1 instanceof GherkinScenario || parent1 instanceof GherkinFeature) {
                return parent;
            }
        }

        return null;
    }

    @NotNull
    @Override
    public String getText() {
        return "Behat: Run Test";
    }

    @Nls
    @NotNull
    @Override
    public String getFamilyName() {
        return "Behat";
    }

    @Override
    public boolean startInWriteAction() {
        return false;
    }
}
