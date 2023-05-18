import hudson.model.*
import jenkins.model.Jenkins

def call(String jobName, Map<String, String> parameters) {
    def triggerJob(String jobName, Map<String, String> parameters) {
        def jenkins = Jenkins.instance
        def job = jenkins.getItemByFullName(jobName)
        def params = parameters.collect { paramName, paramValue ->
            new StringParameterValue(paramName, paramValue)
        }
        def paramsAction = new ParametersAction(params as Action[])

        def queue = jenkins.getQueue()
        def future = job.scheduleBuild2(0, paramsAction)
        queue.schedule(future)

        println "Job '${jobName}' triggered successfully with parameters: ${parameters}"
    }

    triggerJob(jobName, parameters)
}
