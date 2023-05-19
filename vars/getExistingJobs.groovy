import jenkins.model.Jenkins

def call(Map parameters) {
  def jobsToTrigger = parameters.jobsToTrigger
  def jenkins = Jenkins.instance
  def existingJobs = jenkins.getItems()

  for (jobToTrigger in jobsToTrigger) {
      def jobExists = existingJobs.find { job -> job.name == jobToTrigger.job }
      if (!jobExists) {
          createNewJob([existingJobName: 'test-1', newJobName: jobToTrigger.job ]) //change 'test-1' with the template we created before
      }  
  }
}



// import jenkins.model.Jenkins

// def listJobs() {
//     def filePath = new File('/var/jenkins_home/workspace/Master Pipeline/listOfJobs.groovy').text
//     def fileContent = filePath
// }

// def readListJobs(Map parameters) {
//     def jobs = listJobs()
//     return jobs
// }

// def call() {
//     def jobs = readListJobs()
//     def jenkins = Jenkins.instance
//     def existingJobs = jenkins.getItems()

//     for (jobToTrigger in jobs) {
//         def jobExists = existingJobs.find { job -> job.name == jobToTrigger.job }
//         if (!jobExists) {
//             createNewJob([existingJobName: 'test-1', newJobName: jobToTrigger.job ]) // Change 'test-1' with the template we created before
//         }
//     }
// }



