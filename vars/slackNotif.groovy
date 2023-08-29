def call() {
    def jobResultsByType = [:]
    def combinedMessage = ""
    def groovyFiles = ["UE4_27.groovy", "UE5_0.groovy", "UE5_1.groovy", "UE5_2.groovy"] 
    

    for (groovyFile in groovyFiles) {
        def jobType = groovyFile.tokenize('.')[0]
        def varsFile = load groovyFile
        def BaseJobs = ""
        def PlatformsJobs = ""
        def allJobs = BaseJobs + PlatformsJobs
        println allJobs

        for (job in allJobs) {
            def jobName = job.job
            def build = retrieveLatestBuild(jobName)
            def buildResult = "${build.result}"
            def buildUrl = build.getAbsoluteUrl()

            if (buildResult != "SUCCESS") {
                def emoji = buildResult == "FAILURE" ? ":x:" : ":no_entry_sign:"
                if (!jobResultsByType.containsKey(type)) {
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
    } else {
        slackSend(channel: '#jenkins-notif-test', message: "All jobs succeed :white_check_mark:", color: 'good')
    }
}

return this


def retrieveLatestBuild(jobName) {
    def build = jenkins.model.Jenkins.instance.getItemByFullName(jobName).getLastBuild()
    return build
}
