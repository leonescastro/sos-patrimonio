# sos-patrimonio

O sos-patrimonio é um microsserviço para gerenciar os patrimônios da empresa. 

Este projeto é codificado em Java e utiliza o framework [Quarkus](<https://quarkus.io/>).

Para rodar o microsserviço vamos precisar ter o `docker` instalado na sua maquina e seguir os passos abaixo.

1. Verifique se possui acesso de execução nos scripts `utils/run.sh` e `mvnw`, caso não possua conceda com os comandos abaixo:

```bash
$ chmod +x ./utils/run.sh 
```

```bash
$ chmod +x mvnw 
```

2. Agora podemos executar o script que inicia o microsserviço: 

```bash
./utils/run.sh             
```

3. Após o microsserviço iniciar, podemos realizar os primeiros testes no endereço http://localhost:8080.

Para construir a tela foi utilizado o template https://www.tutorialrepublic.com/codelab.php?topic=bootstrap-3&file=crud-data-table-for-database-with-modal-form.

4. Para fazer os testes dos endpoints, pode ser usado o Swagger no endereço http://localhost:8080/api-docs

### Arquitetura

- Back-End: `QUARKUS` <br>
- Front-End: `HTML5 + BootStrap3 + AngularJS.`