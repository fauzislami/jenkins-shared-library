package com.example

import javaposse.jobdsl.dsl.*
import jenkins.model.*

def call(String jobName, String perforceCredentials, String perforceStream, String jenkinsfilePath, Map<String, String> parameters) {
    def jobManager = Jenkins.instance.getExtensionList('javaposse.jobdsl.dsl.JobManagement').get(0)
    
    def dslScript = """
        pipelineJob('$jobName') {
            definition {
                cpsScm {
                    scm {
                        perforce {
                            credentialsId('$perforceCredentials')
                            populate {
                                forceClean(true)
                                deleteNonSyncedFiles(true)
                            }
                            stream('$perforceStream')
                        }
                        scriptPath('$jenkinsfilePath')
                    }
                }
            }
            parameters {
                ${generateParameters(parameters)}
            }
        }
    """
    
    jobManager.createJob(dslScript, true)
}

def generateParameters(Map<String, String> parameters) {
    def parameterDefinitions = ""
    parameters.each { key, value ->
        parameterDefinitions += "stringParam('${key}', '${value}')\n"
    }
    return parameterDefinitions
}
