// import jenkins.model.Jenkins

// def call(Map parameters, String template) {
//   def jobsToTrigger = parameters.jobsToTrigger
//   def jenkins = Jenkins.instance
//   def existingJobs = jenkins.getItems()

//   for (jobToTrigger in jobsToTrigger) {
//       def jobExists = existingJobs.find { job -> job.name == jobToTrigger.job }
//       if (!jobExists) {
//           createNewJob([existingJobName: template, newJobName: jobToTrigger.job ])
//       }  
//   }
// }


import jenkins.model.Jenkins

def call(Map parameters) {
  def jobsToTrigger = parameters.jobsToTrigger
  def jobTemplate = parameters.jobTemplate
  def jenkins = Jenkins.instance
  def existingJobs = jenkins.getItems()

  for (jobToTrigger in jobsToTrigger) {
      def jobExists = existingJobs.find { job -> job.name == jobToTrigger.job }
      if (!jobExists) {
          createNewJob([existingJobName: jobTemplate, newJobName: jobToTrigger.job ])
      }  
  }
}
