def call(String jobName, String paramName) {
    jobDsl {
        lightweight true
        scriptText '''
            pipelineJob("${jobName}") {
                parameters {
                    stringParam("${paramName}", '', 'Name of the person')
                }
                definition {
                    cps {
                        script('''
                            pipeline {
                                agent any
                                stages {
                                    stage('Greet') {
                                        steps {
                                            echo "Hello!! \${params.${paramName}}"
                                        }
                                    }
                                }
                            }
                        '''.stripIndent())
                        sandbox()
                    }
                }
            }
        '''
    }
}
