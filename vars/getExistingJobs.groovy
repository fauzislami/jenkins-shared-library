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
  def jobsToTrigger = parameters.jobsToTrigger
  def jobTemplate = parameters.jobTemplate
  def jenkins = Jenkins.instance
  def existingJobs = jenkins.getItems()

  for (jobToTrigger in jobsToTrigger) {
    def jobExists = existingJobs.find { job -> job.name == jobToTrigger.job }
    if (!jobExists) {
      createNewJob([existingJobName: jobTemplate, newJobName: jobToTrigger.job])
    }
  }
}

def createNewJob(Map parameters) {
  def existingJobName = parameters.existingJobName
  def newJobName = parameters.newJobName
  def jenkins = Jenkins.instance
  def existingJob = jenkins.getItemByFullName(existingJobName)

  if (existingJob) {
    def targetFolder = jenkins.getItemByFullName(newJobName.substring(0, newJobName.lastIndexOf("/")))

    if (targetFolder && targetFolder instanceof com.cloudbees.hudson.plugins.folder.Folder) {
      def newJob = targetFolder.getItem(newJobName.substring(newJobName.lastIndexOf("/") + 1))
      if (!newJob) {
        newJob = targetFolder.copy(existingJob, newJobName.substring(newJobName.lastIndexOf("/") + 1))
        newJob.save()
        println "New Job '${newJobName}' created successfully in '${targetFolder.fullName}'!"
      }
    } else {
      println "Target Folder '${newJobName.substring(0, newJobName.lastIndexOf("/"))}' not found or is not a valid folder."
    }
  } else {
    println "Existing Job '${existingJobName}' not found."
  }
}
