def call(List<String> groovyFiles) {
    def jobResultsByType = [:]
    def combinedMessage = ""
    def BaseJobs = []
    def PlatformsJobs = []

    for (groovyFile in groovyFiles) {
        def jobType = groovyFile.tokenize('.')[0]
        def varsFile = load 'groovyFile'
        def allJobs = BaseJobs + PlatformsJobs
        println varsFile

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


def retrieveLatestBuild(jobName) {
    def build = jenkins.model.Jenkins.instance.getItemByFullName(jobName).getLastBuild()
    return build
}
