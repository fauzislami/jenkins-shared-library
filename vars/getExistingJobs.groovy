import jenkins.model.Jenkins

def call(Map parameters) {
  def jobsToTrigger = parameters.jobsToTrigger
  def jenkins = Jenkins.instance
  def existingJobs = jenkins.getItems()

  for (jobToTrigger in jobsToTrigger) {
      def jobExists = existingJobs.find { job -> job.name == jobToTrigger.job }
      if (!jobExists) {
          //println "Creating '${jobToTrigger.job}'."
          createNewJob([existingJobName: 'test-1', newJobName: jobToTrigger.job ])
      }  
  }
}
