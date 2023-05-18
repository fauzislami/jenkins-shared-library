def call(String jobName, String credentialId, String stream, String city, String province, String nation) {
    jobDsl{
        pipelineJob(jobName) {
            definition {
                cps {
                    script('''
                        pipeline {
                            agent any
                            
                            parameters {
                                string(name: 'city', defaultValue: '', description: 'City')
                                string(name: 'province', defaultValue: '', description: 'Province')
                                string(name: 'nation', defaultValue: '', description: 'Nation')
                            }
                            
                            stages {
                                stage('Build') {
                                    steps {
                                        // Add your build steps here
                                    }
                                }
                                
                                stage('Test') {
                                    steps {
                                        // Add your test steps here
                                    }
                                }
                                
                                stage('Deploy') {
                                    steps {
                                        // Add your deployment steps here
                                    }
                                }
                            }
                        }
                    ''')
                }
            }
            scm {
                p4(credentialId, stream)
            }
        }
    }

}
