def createPerforceJobWithParameters(String jobName, String credentialName, String stream, String jenkinsfilePath, Map<String, String> parameters) {
    job(jobName) {
        displayName(jobName)
        
        // Parameters
        parameters {
            parameters.each { paramName, paramValue ->
                stringParam(paramName, paramValue)
            }
        }

        // Perforce SCM configuration
        scm {
            perforce {
                credential(credentialName)
                depotPath(stream)
                populate([
                    stream: 'true',
                    format: "jenkins-${NODE_NAME}-${JOB_NAME}-${EXECUTOR_NUMBER}",
                    mapping: [
                        view: "${stream}/..."
                    ]
                ])
                workspaceView("${stream}/...")
            }
        }

        // Jenkinsfile configuration
        definition {
            cpsScm {
                scm {
                    perforce {
                        credential(credentialName)
                        populate([
                            stream: 'true',
                            format: "jenkins-${NODE_NAME}-${JOB_NAME}-${EXECUTOR_NUMBER}",
                            mapping: [
                                view: "${stream}/..."
                            ]
                        ])
                        workspaceView("${stream}/...")
                    }
                }
                scriptPath(jenkinsfilePath)
            }
        }
    }
}

