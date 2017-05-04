package de.espend.idea.php.behat.utils;

import com.intellij.execution.ProgramRunnerUtil;
import com.intellij.execution.actions.ConfigurationContext;
import com.intellij.execution.actions.ConfigurationFromContext;
import com.intellij.execution.actions.RunConfigurationProducer;
import com.intellij.execution.executors.DefaultDebugExecutor;
import com.intellij.psi.PsiElement;
import com.jetbrains.php.behat.run.BehatRuntimeConfigurationProducer;
import org.jetbrains.annotations.NotNull;

/**
 * @author Daniel Espendiller <daniel@espendiller.net>
 * @see com.intellij.testIntegration.TestRunLineMarkerProvider
 */
public class BehatPluginUtil {
    /**
     * Run tests for given element
     *
     * @param psiElement Elements are PhpClass or Method possible context
     */
    public static void executeDebugRunner(@NotNull PsiElement psiElement) {
        ConfigurationFromContext context = RunConfigurationProducer.getInstance(BehatRuntimeConfigurationProducer.class)
            .createConfigurationFromContext(new ConfigurationContext(psiElement));

        if(context != null) {
            ProgramRunnerUtil.executeConfiguration(
                psiElement.getProject(),
                context.getConfigurationSettings(),
                DefaultDebugExecutor.getDebugExecutorInstance()
            );
        }
    }
}
