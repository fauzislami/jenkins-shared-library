// createNewProject.groovy

def createNewProject(String projectName) {
    jobDsl {
        // Use the Job DSL syntax to define and configure the project
        // For example, SCM, build steps, etc.
        // See Job DSL documentation for available options
        additionalClasspath('path/to/extra/jar1.jar')  // Optional: Add additional JARs if needed
        additionalClasspath('path/to/extra/jar2.jar')
        targets {
            job(projectName) {
                // Configure your project here
                // For example, SCM, build steps, etc.
                // See Job DSL documentation for available options
            }
        }
    }
}
