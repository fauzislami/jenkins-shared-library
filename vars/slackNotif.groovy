def call(List<String> groovyFiles) {
    def jobResultsByType = [:]
    def combinedMessage = ""
}

// Define the retrieveLatestBuild function here
def retrieveLatestBuild(jobName) {
    def build = jenkins.model.Jenkins.instance.getItemByFullName(jobName).getLastBuild()
    return build
}
