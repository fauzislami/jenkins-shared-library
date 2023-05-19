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

def readAndPrintListOfJobs(String fileName) {
    def fileContent = new File(fileName).text
    def script = evaluate(fileContent)
    def jobsToTrigger = script.jobsToTrigger

    jobsToTrigger.each { job ->
        println("Job: ${job.job}")
        job.params.each { param ->
            println("Parameter - Name: ${param.name}, Value: ${param.value}")
        }
        println()
    }

    return jobsToTrigger
}

def call(String fileName) {
    def jobsToTrigger = readAndPrintListOfJobs(fileName)
    def jenkins = Jenkins.instance
    def existingJobs = jenkins.getItems()

    for (jobToTrigger in jobsToTrigger) {
        def jobExists = existingJobs.find { job -> job.name == jobToTrigger.job }
        if (!jobExists) {
            createNewJob([existingJobName: 'test-1', newJobName: jobToTrigger.job ]) // Change 'test-1' with the template we created before
        }
    }
}



