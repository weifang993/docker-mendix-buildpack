pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'docker build --build-arg BUILD_PATH="SSOTest" -t ssotest:v1 .'
      }
    }
  }
}