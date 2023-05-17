// vars/setupNewProject.groovy

def call(Map<String, String> projectConfig) {
    pipeline {
        agent any
        
        parameters {
            string(name: 'city', defaultValue: projectConfig.city, description: 'City')
            string(name: 'province', defaultValue: projectConfig.province, description: 'Province')
            string(name: 'nation', defaultValue: projectConfig.nation, description: 'Nation')
        }
        
        stages {
            stage('Checkout') {
                steps {
                    script {
                        def p4 = perforce credential: projectConfig.credentials
                        p4.sync workspace: "${projectConfig.name}-workspace", stream: projectConfig.stream
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
