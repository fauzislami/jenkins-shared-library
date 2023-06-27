// import jenkins.model.Jenkins

// def call(Map parameters) {
//   def jobsToTrigger = parameters.jobsToTrigger
//   def jobTemplate = parameters.jobTemplate
//   def jenkins = Jenkins.instance
//   def existingJobs = jenkins.getItems()

//   for (jobToTrigger in jobsToTrigger) {
//       def jobExists = existingJobs.find { job -> job.name == jobToTrigger.job }
//       if (!jobExists) {
//           createNewJob([existingJobName: jobTemplate, newJobName: jobToTrigger.job ])
//       }  
//   }
// }



import jenkins.model.Jenkins

def call(Map parameters) {
    def jenkins = Jenkins.instance
    def existingJobs = jenkins.getItems()

    parameters.each { key, value ->
        value.each { jobToTrigger ->
            def jobExists = existingJobs.find { job -> job.name == jobToTrigger.job }
            if (!jobExists) {
                createNewJob([existingJobName: parameters.jobTemplate, newJobName: jobToTrigger.job])
            }
        }
    }
}

return this
