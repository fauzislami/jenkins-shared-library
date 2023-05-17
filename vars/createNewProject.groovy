// vars/createNewProject.groovy

def call(String projectName, Map<String, String> parameters) {
    pipeline {
        agent any
        
        parameters {
            stringParam(name: 'city', defaultValue: parameters.city, description: 'City')
            stringParam(name: 'province', defaultValue: parameters.province, description: 'Province')
            stringParam(name: 'nation', defaultValue: parameters.nation, description: 'Nation')
        }
        
        stages {
            stage('Checkout') {
                steps {
                    script {
                        def p4 = perforce credential: 'creds-p4'
                        p4.sync workspace: "${projectName}-workspace", stream: '//test1/game'
                    }
                }
            }
            
            stage('Build') {
                steps {
                    script {
                        // Perform build steps here
                    }
                }
            }
        }
        
        post {
            always {
                script {
                    // Perform cleanup or post-build actions here
                }
            }
        }
    }
}
