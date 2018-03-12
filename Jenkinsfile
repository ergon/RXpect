#!usr/bin/env/groovy

pipeline {
    agent {
        node {
            label 'docker'
        }
    }

    stages {
		stage('Build') {
			steps {
				sh 'buildstack update && buildstack --version'
				sh 'buildstack run "./gradlew build" "openjdk8"'
			}
		}
    }
}
