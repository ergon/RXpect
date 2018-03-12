#!usr/bin/env/groovy

node('docker') {
	stage('Build') {
		sh 'buildstack update && buildstack --version'
		sh 'ls -la'
		sh 'buildstack run "ls -la" "openjdk8"'
		sh 'buildstack run "pwd" "openjdk8"'
		sh 'buildstack run "./gradlew build" "openjdk8"'
	}
}
