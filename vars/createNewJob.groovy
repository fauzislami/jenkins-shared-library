def call(String jobName) {
    multibranchPipelineJob(jobName) {
        branchSources {
            git {
                remote('https://github.com/your-repo.git')
                credentialsId('your-credentials-id')
                includes('*/master') // Update branch pattern as needed
            }
        }
        configure { project ->
            project / 'sources' / 'data' / 'jenkins.branch.BranchSource' / 'source' / 'git' / 'GitSCM' / 'excludedUsers' << 'your-excluded-user'
        }
    }
}
