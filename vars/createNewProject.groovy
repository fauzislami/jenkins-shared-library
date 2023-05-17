package com.example

import hudson.model.*
import jenkins.model.*
import org.apache.commons.io.IOUtils

class ProjectReplicator {
    def replicateProject(String existingProjectName, String newProjectName) {
        def existingProject = Jenkins.instance.getItemByFullName(existingProjectName)
        if (!existingProject) {
            println("Existing project '${existingProjectName}' not found.")
            return
        }

        def configXml = existingProject.getConfigFile().file.text

        def newProject = Jenkins.instance.createProject(FreeStyleProject, newProjectName)
        newProject.updateByXml(new StreamSource(new StringReader(configXml)))
        newProject.save()

        println("Project '${newProjectName}' replicated successfully.")
    }
}
