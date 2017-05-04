package de.espend.idea.php.behat.linemarker;

import com.intellij.codeInsight.daemon.GutterIconNavigationHandler;
import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.codeInsight.daemon.LineMarkerProvider;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.psi.PsiElement;
import com.intellij.util.ConstantFunction;
import de.espend.idea.php.behat.utils.BehatPluginUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.plugins.cucumber.psi.GherkinFeature;
import org.jetbrains.plugins.cucumber.psi.GherkinScenario;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.List;

/**
 * @author Daniel Espendiller <daniel@espendiller.net>
 * @see com.intellij.testIntegration.TestRunLineMarkerProvider
 */
public class TestRunLineMarkerProvider implements LineMarkerProvider {
    @Nullable
    @Override
    public LineMarkerInfo getLineMarkerInfo(@NotNull PsiElement psiElement) {
        return null;
    }

    @Override
    public void collectSlowLineMarkers(@NotNull List<PsiElement> psiElements, @NotNull Collection<LineMarkerInfo> collection) {
        for (PsiElement psiElement : psiElements) {
            if(psiElement instanceof GherkinScenario) {
                collection.add(createLineMakerInfo(psiElement, AllIcons.RunConfigurations.TestState.Run));
            } else if(psiElement instanceof GherkinFeature) {
                collection.add(createLineMakerInfo(psiElement, AllIcons.RunConfigurations.TestState.Run_run));
            }
        }
    }

    @NotNull
    private LineMarkerInfo<PsiElement> createLineMakerInfo(@NotNull PsiElement psiElement, @NotNull Icon icon) {
        return new LineMarkerInfo<>(
            psiElement,
            psiElement.getTextRange(),
            icon,
            6,
            new ConstantFunction<>("Run Test"),
            new MyProgramRunnerGutterIconNavigationHandler(),
            GutterIconRenderer.Alignment.LEFT
        );
    }

    private static class MyProgramRunnerGutterIconNavigationHandler implements GutterIconNavigationHandler<PsiElement> {
        @Override
        public void navigate(MouseEvent mouseEvent, PsiElement psiElement) {
            BehatPluginUtil.executeDebugRunner(psiElement);
        }
    }
}
