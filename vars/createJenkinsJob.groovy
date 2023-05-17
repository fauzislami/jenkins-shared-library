def call(String jobName, String credentialName, String stream, String jenkinsfilePath, Map<String, String> parameters) {
    String jobScript = """
pipeline {
    agent any
    parameters {
        ${parameters.collect { paramName, paramValue -> "string(name: '${paramName}', defaultValue: '${paramValue}')\n" }.join()}
    }
    stages {
        stage('Checkout') {
            steps {
                perforce credential: '${credentialName}', populate: autoClean(delete: true, modtime: false, parallel: [enable: false, minbytes: '1024', type: ''], pin: '', quiet: true, replace: true, tidy: false, viewMask: '', workspace: [charset: 'none', name: "jenkins-${NODE_NAME}-${JOB_NAME}-${EXECUTOR_NUMBER}", pinHost: false, spec: [[allwrite: false, clobber: false, compress: false, line: '', locked: false, modtime: false, rmdir: false, serverID: '', streamName: '${stream}', useTemplate: false, view: "\${WORKSPACE}/..."]]])
            }
        }
        stage('Build') {
            steps {
                // Perform build steps here
            }
        }
        // Add more stages as needed
    }
    post {
        always {
            cleanWs()
        }
    }
}
"""

    writeFile file: 'dynamicJob/Jenkinsfile', text: jobScript

    load 'dynamicJob'
}
