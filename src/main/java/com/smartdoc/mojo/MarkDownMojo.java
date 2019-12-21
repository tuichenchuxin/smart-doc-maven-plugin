package com.smartdoc.mojo;

import com.power.doc.builder.ApiDocBuilder;
import com.power.doc.model.ApiConfig;
import com.smartdoc.constant.GlobalConstants;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Execute;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;

import static com.smartdoc.util.MojoUtils.buildConfig;

/**
 * @author xingzi 2019/12/06 17:38
 */
@Execute(phase = LifecyclePhase.COMPILE)
@Mojo(name = "markdown")
public class MarkDownMojo extends AbstractMojo {

    @Parameter(property = "configFile", defaultValue = GlobalConstants.DEFAULT_CONFIG)
    private File configFile;

    @Parameter(property = "projectName")
    private String projectName;

    @Parameter(defaultValue = "${project}", readonly = true)
    private MavenProject project;

    @Override
    public void execute() {
        ApiConfig apiConfig = buildConfig(configFile, projectName, project, getLog());
        if (apiConfig == null) {
            getLog().info(GlobalConstants.ERROR_MSG);
            return;
        }
        ApiDocBuilder.builderControllersApi(apiConfig);
    }
}
