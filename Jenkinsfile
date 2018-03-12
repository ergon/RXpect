#!usr/bin/env/groovy

node('docker') {
	stage('Build') {
		sh 'buildstack update && buildstack --version'
		sh 'buildstack run "./gradlew build" "gradle-4"'
	}
}
