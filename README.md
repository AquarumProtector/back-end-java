
# Aquarum Protector API

Aquarum Protector é um aplicativo móvel que permite aos usuários verificar e marcar locais de fontes naturais (rios, lagos, nascentes, represas etc.) como potáveis ou contaminados. O objetivo é oferecer uma solução simples e eficaz para identificar a qualidade da água, prevenindo doenças e incentivando práticas seguras de consumo. 


# Tecnologias Utilizadas

- Spring Boot 3.4.5
- Java 17+
- Banco de Dados Oracle
- Docker
- Spring Data JPA
- Spring Security
- Hibernate
- Spring Validation
- Spring Web
- Spring DevTools
- Lombok
- Maven

## Requisitos

Antes de começar, certifique-se de que você tem os seguintes requisitos:

- [Java 17](https://adoptopenjdk.net/)
- [Docker](https://docs.docker.com/get-docker/) (caso você queira rodar com o Docker)
- [Maven](https://maven.apache.org/) (caso queira compilar a aplicação localmente)
## Instruções para Rodar a Aplicação com Docker

Este projeto já contém um `Dockerfile` configurado para facilitar a construção e execução da aplicação dentro de um contêiner Docker.

### Passo 1: Clonar o Repositório

Clone o repositório para a sua máquina local:

```bash
git clone https://github.com/AquarumProtector/back-end-java
cd back-end-java
````
### Passo 2: Construir a imagem

```bash
docker build -t nome-da-imagem .
````

### Passo 3: Rodar a imagem

```bash
docker run -p 8080:8080 nome-da-imagem
````
### Passo 4: Acessar  a API
A API estará disponível em:

```bash
  http://localhost:8080
```
## Rodando localmente

Clone o projeto

```bash
  git clone https://github.com/AquarumProtector/back-end-java
```

Entre no diretório do projeto

```bash
  cd back-end-java
```

Compile e execute a aplicação

```bash
  mvn spring-boot:run
```

A API estará disponível em:

```bash
  http://localhost:8080
```
## Variáveis de Ambiente

Para rodar esse projeto, você vai precisar adicionar as seguintes variáveis de ambiente no seu application.properties

`ORACLE_DB_CONNECTION_STRING` | string de conexão com o banco de dados

`ORACLE_DB_USER` | usuário do banco de dados
 
`ORACLE_DB_PASSWORD` senha do usuário do banco de dados


## Funcionalidades

O Aquarum Protector oferece as seguintes funcionalidades para garantir um monitoramento eficiente da qualidade da água em fontes naturais:

- Cadastro de Usuários:
Permite que os usuários criem uma conta para acessar o aplicativo e participar da verificação da qualidade da água em diferentes locais. O cadastro é simples e rápido, garantindo que os dados sejam registrados de forma segura.

- Cadastro de Localizações de Fontes de Água:
Usuários cadastrados podem adicionar novas fontes de água ao sistema. Ao registrar uma nova localização, o usuário fornece informações como o tipo de fonte (rio, lago, nascente, represa, etc.), nome do local e coordenadas geográficas. Isso cria um banco de dados atualizado de fontes naturais de água.

- Lista de Fontes de Água:
Os usuário do sistema poderão de forma fácil, ver um lista completa das fontes de água disponíveis, onde ele pode ver onde tem a fonte de água potável mais próxima da sua localização e se digirir até o local.

- Atualizações de Qualidade da Água:
A cada verificação ou inspeção feita por um usuário, é possível atualizar a qualidade da água da fonte registrada. O usuário pode marcar a água como potável ou contaminada, permitindo que outras pessoas visualizem a condição atual da água e tomem decisões informadas sobre seu consumo.

Essas funcionalidades são projetadas para promover o compartilhamento de informações sobre a qualidade da água de forma colaborativa e em tempo real, ajudando a prevenir doenças e promovendo práticas seguras de consumo.

## Documentação da API

### Auth

#### Registra usuário

```bash
  POST /auth/register
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `login` | `string` | Nome do seu usuário |
| `password` | `string` | Sua senha |


#### Login de  um usuário

```bash
  POST /auth/login
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `login` | `string` | Nome do seu usuário |
| `password` | `string` | Sua senha |

Retorna o token bearer do usuário

### WaterSource

#### Retorna todas as fontes de água registradas

```bash
  GET /watersource
```

#### Retorna a fonte de água com o id fornecido

```bash
  GET /watersource/{id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id` | `Long` | Id da fonte de água |

#### Retorna as fontes de água filtrado por tipo de fonte

```bash
  GET /watersource/tipo/{type}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `type` | `WaterSourceType` | Tipo da fonte de água |

#### Retorna as fontes de água filtrado por fonte ativa ou não ativa

```bash
  GET /watersource/ativo/{isActive}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `isActive` | `boolean` |  Estado da fonte de água |

#### Adiciona fonte de água

```bash
  POST /watersource
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `waterSource` | `WaterSource` | Fonte de água a ser adicionada |

#### Atualiza fonte de água

```bash
  PUT /watersource/{id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `waterSource` | `WaterSource` | Fonte de água a ser atualizada |
| `id` | `Long` | Id da fonte de água a ser atualizada |

#### Deleta fonte de água

```bash
  DELETE /watersource/{id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id` | `Long` | Id da fonte de água a ser deletada |

### WaterSourceUpdate

#### Retorna todas as atualizações de fontes de água registradas

```bash
  GET /watersourceupdate
```

#### Retorna a atualização da fonte de água com o id fornecido

```bash
  GET /watersourceupdate/{id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id` | `Long` | Id da atualização da fonte de água |

#### Retorna as fontes de água filtrado por status

```bash
  GET /watersourceupdate/status/{status}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `status` | `WaterSourceStatus` | Status da fonte de água |

#### Adiciona atualização de fonte de água

```bash
  POST /watersourceupdate
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `waterSourceUpdate` | `WaterSourceUpdate` | Atualização de fonte de água a ser adicionada |


#### Deleta atualização de fonte de água

```bash
  DELETE /watersourceupdate/{id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id` | `Long` | Id da atualização de fonte de água a ser deletada |

## Autores

- [@gabrielmarcello](https://github.com/gabrielmarcello) Nome: Gabriel Marcello RM:556783
- [@meiranicolas](https://github.com/meiranicolas) Nome: Nicolas Meira RM:554464
- [@gustavoaraujo06](https://github.com/gustavoaraujo06) Nome: Gustavo Araujo RM:555277
