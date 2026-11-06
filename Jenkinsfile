
pipeline {
    agent any

    options {
        timestamps()
        disableConcurrentBuilds()
    }

    environment {
        MYSQL_DATABASE = 'employee_management'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build and Deploy EMS') {
            steps {
                withCredentials([
                    string(credentialsId: 'ems-mysql-root-password', variable: 'MYSQL_ROOT_PASSWORD'),
                    string(credentialsId: 'ems-mysql-password', variable: 'MYSQL_PASSWORD'),
                    string(credentialsId: 'ems-mysql-user', variable: 'MYSQL_USER')
                ]) {
                    bat 'docker compose -p employee-management-system config -q'
                    bat 'docker compose -p employee-management-system up -d --build'
                    bat 'docker compose -p employee-management-system ps'
                }
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
