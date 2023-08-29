def call(List<String> groovyFiles) {
    def jobResultsByType = [:]
    def combinedMessage = ""

    for (groovyFile in groovyFiles) {
        def jobType = groovyFile.tokenize(".")[0]
        varsFile = load groovyFile
        println varsFile
        
        
        for (job in varsFile) {
            def jobName = job.job
            def build = retrieveLatestBuild(jobName)
            def buildResult = "${build.result}"
            def buildUrl = build.getAbsoluteUrl()

            if (buildResult != "SUCCESS") {
                def emoji = buildResult == "FAILURE" ? ":x:" : ":no_entry_sign:"
                if (!jobResultsByType.containsKey(jobType)) {
                    jobResultsByType[jobType] = []
                }
                jobResultsByType[jobType].add("[${jobName}] - <${buildUrl}|See here> - ${buildResult} $emoji")
            }
        }
    }

    jobResultsByType.each { version, results ->
        if (!results.isEmpty()) {
            combinedMessage += "*THE FOLLOWING JOBS FAILED FOR ${version}:*\n" + results.join('\n') + "\n"
        }
    }

    if (!combinedMessage.isEmpty()) {
        slackSend(channel: '#jenkins-notif-test', message: combinedMessage, color: 'danger')
    } 
    else {
       slackSend(channel: '#jenkins-notif-test', message: "All jobs succeed :white_check_mark:", color: 'good')
    }
}

// Define the retrieveLatestBuild function here
def retrieveLatestBuild(jobName) {
    def build = jenkins.model.Jenkins.instance.getItemByFullName(jobName).getLastBuild()
    return build
}
