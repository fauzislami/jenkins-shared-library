import jenkins.model.Jenkins

def call(String existingJobName, String newJobName) {
    // Get the Jenkins instance
    def jenkins = Jenkins.instance

    // Get the existing job
    def existingJob = jenkins.getItem(existingJobName)

    // Print existing job details
    if (existingJob) {
        def newJob = jenkins.copy(existingJob, newJobName)
        newJob.save()

        println "New Job '${newJobName}' created successfully!"
    } else {
        println "Existing Job '${existingJobName}' not found."
    }
}
