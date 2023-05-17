// createPipelineJob.groovy
def call(String jobName, String repoUrl) {
  job(jobName) {
    logRotator {
      numToKeep(50)
    }
    scm {
      git {
        branch('${revision}')
        remote {
          url(repoUrl)
          credentials('github')
        }
      }
    }
    steps {
      // Assuming 'myPipeline' is a custom step defined in the shared library
      myPipeline(projectName: 'test-name')
    }
  }
}
