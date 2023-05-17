// createNewProject.groovy

import javaposse.jobdsl.dsl.DslFactory

def createNewProject(String projectName) {
    def dslFactory = new DslFactory()
    def jobDslScript = '''
        job('${projectName}') {
            // Configure your project here
            // For example, SCM, build steps, etc.
            // See Job DSL documentation for available options
        }
    '''
    dslFactory.runScript(jobDslScript)
}
