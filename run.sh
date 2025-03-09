#!/bin/bash

RED="\e[31m"
GREEN="\e[32m"
YELLOW="\e[33m"
BLUE="\e[34m"
CYAN="\e[36m"
BOLD="\e[1m"
RESET="\e[0m"

print_title() {
    echo -e "\n${GREEN}=================================================${RESET}"
    echo -e "${BOLD}$1${RESET}"
    echo -e "${GREEN}=================================================${RESET}\n"
    sleep 1 
}

command -v docker >/dev/null 2>&1 || { echo -e "${RED}❌ Docker no está instalado. Por favor, instálalo y vuelve a intentarlo.${RESET}"; exit 1; }
command -v docker-compose >/dev/null 2>&1 || { echo -e "${RED}❌ Docker Compose no está instalado. Por favor, instálalo y vuelve a intentarlo.${RESET}"; exit 1; }

if ! docker info >/dev/null 2>&1; then
    echo -e "${RED}❌ No tienes permisos para ejecutar Docker. Asegúrate de estar en el grupo 'docker' o ejecuta con sudo.${RESET}"
    exit 1
fi

print_title "             🚀 ZwippeTech - Zelify 🚀"
echo "Desafío de código Semi-Senior"

print_title "🚀 Pulsa cualquier tecla para empezar"
read -n 1 -s -r -p "Presiona cualquier tecla para continuar..."

print_title "🐳 Construyendo imágenes Docker..."
if ! docker-compose build; then
    echo -e "${RED}❌ Error al construir las imágenes Docker.${RESET}"
    exit 1
fi

print_title "🚢 Levantando contenedores..."
if ! docker-compose up -d; then
    echo -e "${RED}❌ Error al levantar los contenedores.${RESET}"
    exit 1
fi

print_title "✅ Microservicios desplegados correctamente"
docker ps

print_title "🚀 Accede a los servicios desplegados"

echo -e "\n${YELLOW}⚠️ Para visualizar los microservicios debes esperar:${RESET} ${BOLD}Los servicios estan iniciando${RESET}\n\n"

echo -e "${GREEN}🚀 Accede al Swagger del microservicio transaction-service:"
echo -e "${BLUE}http://localhost:8080/swagger-ui/index.html${RESET}"

echo -e "\n${GREEN}🚀 Accede a Kafdrop para monitorear el clúster de Kafka:"
echo -e "${BLUE}http://localhost:9000/${RESET}" 

echo -e "\n${GREEN}🚀 Mensajes del Topic transaction-events:"
echo -e "${BLUE}http://localhost:9000/topic/transaction-events/allmessages${RESET}" 

echo -e "\n${GREEN}🚀 Mensajes del Topic login-events:"
echo -e "${BLUE}http://localhost:9000/topic/login-events/allmessages/${RESET}" 

echo -e "\n${YELLOW}⚠️ Para detener los contenedores, ejecuta:${RESET} ${BOLD}docker-compose down${RESET}"
