def call(List<String> groovyFiles) {
    def jobResultsByType = [:]
    def combinedMessage = ""

    for (groovyFile in groovyFiles) {
        def jobType = groovyFile.tokenize('.')[0]
        def varsFile = load groovyFile
    }
}

// Define the retrieveLatestBuild function here
def retrieveLatestBuild(jobName) {
    def build = jenkins.model.Jenkins.instance.getItemByFullName(jobName).getLastBuild()
    return build
}
