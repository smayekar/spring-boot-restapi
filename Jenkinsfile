pipeline {
    agent any
    tools {
        maven 'maven'
    }

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Docker') {
            steps {
                sh "mvn -DpushImage=true -Dbuild.tag=${env.BUILD_TAG} docker:build"
            }
        }
        stage('Deploy') {
            steps {
            	script {
            		try {
			     sh "helm upgrade dcc-yellowdemo-${env.BRANCH_NAME} --namespace=dcc-yellowdemo-${env.BRANCH_NAME} --set tag=${env.BUILD_TAG} src/main/helm"
            		} catch (e) {
            		     sh "helm install --name=dcc-yellowdemo-${env.BRANCH_NAME} --namespace=dcc-yellowdemo-${env.BRANCH_NAME} --set tag=${env.BUILD_TAG} src/main/helm"
            		}
            	}
            }
        }
    }
}