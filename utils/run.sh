#!/bin/bash

set -e

# ----------------------------------------------
# Step #3: Trap CTRL+C, CTRL+Z and quit singles
# ----------------------------------------------
trap 'Finalizando...' 3

ENV_LOCAL=$PWD/.env
function setenv() {
	if test -f "$ENV_LOCAL"; then
		# shellcheck disable=SC1090
		source "$ENV_LOCAL"
	fi
}

function build() {
  export REPO_LOCAL="/temp/.m2/repository"
	printf "============== Construido o Projeto com Maven Local ==============\n"
  printf "\n"

	mkdir -p ~/.m2/repository

	docker run --user ${UID}:${GID} -w /app -v $PWD:/app \
	    -v ~/.m2/repository:/temp/.m2/repository -e REPO_LOCAL  \
	    --rm -it maven:3.6.3-jdk-11 \
	    mvn clean install
	printf "\n"
  printf "============ Projeto Construido com Sucesso pelo Maven ===========\n"
  printf "\n"
}

function start() {
  export WORKSPACE="${PWD}/src/main/resources"
  export myUID=${UID}
  export myGroup=${GID}
  # shellcheck disable=SC2059
  printf "${WORKSPACE}"
  printf "\n"
  printf "\n"

	printf "== Executando o Docker Compose Com Build para subir a aplicação ==\n"
  printf "\n"
	docker-compose -f "$PWD"/utils/docker-compose.yaml up --build -d --remove-orphans
}

function log() {
  local conteiner=$1;
	if [[ -z "${conteiner}" ]]; then
		select opt in $(docker-compose -f utils/docker-compose.yaml ps --services); do
			conteiner=$opt
			break
		done
	fi
	docker-compose -f utils/docker-compose.yaml logs -tf --tail="200" $conteiner
}

function down() {
	docker-compose -f utils/docker-compose.yaml down --remove-orphans
}

function stop() {
	docker-compose -f utils/docker-compose.yaml stop
}

setenv

printf "\n"
printf '==================================================================================\n'
printf '========== INICIADO SCRIPT DE RUN CONFIGURADO PARA HOT DEPLOY QUARKUS ============\n'
printf '==================================================================================\n'
printf "\n"

case $1 in
-l | --log)
	log "$2"
	exit
	;;
-d | --down)
	down
	exit
	;;
-st | --stop)
	stop
	exit
	;;
-b)
	build
	exit
	;;
*)
	start
	log 'sos-patrimonio'
	exit
	;;
esac
