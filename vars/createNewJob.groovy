// pipelineJob.groovy

def call(String jobName, String dslScript) {
    def jobDslScript = "pipelineJob('$jobName') {\n" +
                       "  definition {\n" +
                       "    cps {\n" +
                       "      script('$dslScript')\n" +
                       "    }\n" +
                       "  }\n" +
                       "}"

    jobDsl(scriptText: jobDslScript)
}
