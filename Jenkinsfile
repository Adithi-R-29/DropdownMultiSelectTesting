pipeline {
    agent any

    tools {
        // Configure these tool names under Jenkins > Manage Jenkins > Tools,
        // or remove this tools block if Java and Maven are already on PATH.
        jdk 'JDK17'
        maven 'Maven3'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Run Selenium Tests') {
            steps {
                bat 'mvn -B clean test -Dselenium.headless=true'
            }
        }
    }

    post {
        always {
            junit testResults: 'target/surefire-reports/TEST-*.xml', allowEmptyResults: true
            archiveArtifacts artifacts: 'target/surefire-reports/**', allowEmptyArchive: true
        }
    }
}
