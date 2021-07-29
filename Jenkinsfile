pipeline {
  options {
    buildDiscarder(logRotator(artifactNumToKeepStr: '10'))
  }
  agent {
    docker {
      args '-v gradle-cache:/home/gradle/.gradle'
      image 'gradle:7.1.1-jdk16'
    }

  }
  stages {
    stage('Build') {
      steps {
        sh 'gradle build --no-daemon'
      }
    }
    stage('Archive') {
      steps {
        archiveArtifacts 'build/libs/*.jar'
      }
    }
  }
}
