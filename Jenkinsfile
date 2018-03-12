#!usr/bin/env/groovy

pipeline {
    agent {
        node {
            label 'docker'
        }
    }

    stages {
		stage('Build') {
			sh 'buildstack update && buildstack --version'
			sh 'buildstack run "./gradlew build" "openjdk8"'
		}
    }
}
