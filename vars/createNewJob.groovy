// createPipelineJob.groovy
def call(String jobName, String repoUrl) {
  pipelineJob(jobName) {
    logRotator {
      numToKeep(50)
    }
    definition {
      cps {
        script('''
          pipeline {
            agent any
            libraries {
              lib("pipeline-lib@master")
            }
            parameters {
              gitParameter name: 'revision',
                type: 'PT_BRANCH_TAG',
                defaultValue: 'origin/master',
                selectedValue: 'DEFAULT',
                description: '',
                branch: '',
                branchFilter: '',
                tagFilter: '',
                useRepository: '',
                quickFilterEnabled: true
              }
            stages {
              stage('Build') {
                steps {
                  git branch: "${revision}",
                    url: "${repoUrl}",
                    credentialsId: 'github'
                  myPipeline projectName: 'test-name'
                }
              }
            }
          }
        ''')
      }
    }
  }
}
