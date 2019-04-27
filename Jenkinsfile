pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        openshiftBuild 'buildconfig.build.openshift.io/buildconfig-ssotest'
      }
    }
  }
}