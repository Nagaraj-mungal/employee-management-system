pipeline {
    agent any

    options {
        timestamps()
        disableConcurrentBuilds()
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Validate Docker Compose') {
            steps {
                bat 'docker compose -p employee-management-system config -q'
            }
        }

        stage('Build and Deploy EMS') {
            steps {
                bat 'docker compose -p employee-management-system up -d --build'
            }
        }

        stage('Verify Deployment') {
            steps {
                bat 'docker compose -p employee-management-system ps'
            }
        }
    }

    post {
        success {
            echo 'EMS CI/CD pipeline completed successfully!'
            echo 'Application URL: http://localhost:8082'
        }

        failure {
            echo 'EMS CI/CD pipeline failed. Check the Jenkins console output.'
        }
    }
}