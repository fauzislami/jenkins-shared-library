// import jenkins.model.Jenkins

// def call(Map parameters) {
//   def jobsToTrigger = parameters.jobsToTrigger
//   def jenkins = Jenkins.instance
//   def existingJobs = jenkins.getItems()

//   for (jobToTrigger in jobsToTrigger) {
//       def jobExists = existingJobs.find { job -> job.name == jobToTrigger.job }
//       if (!jobExists) {
//           createNewJob([existingJobName: 'test-1', newJobName: jobToTrigger.job ]) //change 'test-1' with the template we created before
//       }  
//   }
// }


import jenkins.model.Jenkins

def call(Map parameters) {
    def jenkins = Jenkins.instance
    def existingJobs = jenkins.getItems()
  
    // Get the file path parameter
    def filePath = parameters.filePath
  
    // Invoke the listOfJobs.groovy file
    def listOfJobs = load filePath
    def jobsToTrigger = listOfJobs.jobsToTrigger
  
    for (jobToTrigger in jobsToTrigger) {
        def jobExists = existingJobs.find { job -> job.name == jobToTrigger.job }
        if (!jobExists) {
            createNewJob([existingJobName: 'test-1', newJobName: jobToTrigger.job ]) //change 'test-1' with the template we created before
        }  
    }
}
