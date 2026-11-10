pipeline {
    agent any

    // Definición de variables de entorno globales para todo el pipeline
    environment {
        PROJECT_NAME = 'pmng'
        ENV_FILE     = '.env'
        COMPOSE_FILE = 'docker-compose.yml'
    }

    stages {
        stage('Parar servicios') {
            steps {
                echo "Deteniendo contenedores para el proyecto ${env.PROJECT_NAME}..."
                sh '''
                    docker compose -p ${PROJECT_NAME} -f ${COMPOSE_FILE} --env-file ${ENV_FILE} down
                '''
            }
        }

        stage('Eliminar imágenes') {
            steps {
                echo 'Eliminando imágenes anteriores...'
                sh '''
                    IMAGES=$(docker compose -p ${PROJECT_NAME} -f ${COMPOSE_FILE} --env-file ${ENV_FILE} config --images | sort-u)

                    for IMAGE in $IMAGES; do
                        echo "Eliminando immagen: $IMAGE"
                        docker rmi "$IMAGE" || true
                    done
                '''
            }
        }

        stage('Construir imágenes') {
            steps {
                echo "Construyendo imágenes para ${env.PROJECT_NAME}..."
                sh '''
                    docker compose -p ${PROJECT_NAME} -f ${COMPOSE_FILE} --env-file ${ENV_FILE} build --no-cache
                '''
            }
        }

         stage('Levantar servicios') {
            steps {
                echo "Levantando contenedores..."
                sh '''
                    docker compose -p ${PROJECT_NAME} -f ${COMPOSE_FILE} --env-file ${ENV_FILE} build up -d
                '''
            }
        }

        
    }

   post {
        always {
            echo 'Limpiando el entorno de trabajo...'
            cleanWs()
        }
        success {
            echo '¡El despliegue con Docker Compose se realizó exitosamente!'
            // Opcional: enviar notificación a Slack, correo, etc.
        }
        failure {
            echo 'Ocurrió un error al levantar los servicios con Docker Compose.'
            // Opcional: notificar el fallo
        }
    }
}