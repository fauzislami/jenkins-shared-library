def call(List<String> groovyFiles) {
  for (groovyFile in groovyFiles) {
      def ueVersion = groovyFile.tokenize('.')[0]
      def varsFile = load new File(groovyFile).text
      def allJobs = BaseJobs + PlatformsJobs
      println allJobs
  }
}
