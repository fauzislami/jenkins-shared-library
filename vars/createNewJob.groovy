def call(String jobName, String scriptContent) {
    pipelineJob(jobName) {
        definition {
            cps {
                script(scriptContent)
            }
        }
    }
}
