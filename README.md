# Tech Challenge - Sistema de Usuários

## Visão Geral
Este projeto é uma API REST desenvolvida utilizando tecnologias modernas do ecossistema Java/Spring para gerenciamento de usuários. O sistema foi construído seguindo as melhores práticas de desenvolvimento e arquitetura de software.

## Tecnologias Utilizadas
- Java 21
- Spring Boot
- Spring Data JPA
- Spring MVC
- Lombok
- Docker
- Kubernetes

## Estrutura do Projeto
O projeto segue uma arquitetura moderna e organizada, contendo:
- `/src` - Código fonte da aplicação
- `/kubernetes` - Arquivos de configuração para deploy em Kubernetes
- `/assets` - Recursos estáticos do projeto
- `Dockerfile` e `docker-compose.yml` - Configurações para containerização
- `.env` - Arquivo para variáveis de ambiente

## Funcionalidades Principais
1. Gerenciamento de Usuários
2. Gerenciamento de papéis de usuários
3. APIs RESTful para integração

## Infraestrutura
- O projeto está preparado para containerização com Docker
- Suporte a orquestração com Kubernetes
- Configurações de CI/CD através do diretório `.github`

## Requisitos para Execução
- JDK 21
- Maven
- Docker (opcional)
- Kubernetes (opcional)

## Como Executar
1. Clone o repositório
2. Configure as variáveis de ambiente no arquivo `.env`
3. Execute utilizando Maven:
   ```bash
   ./mvnw spring-boot:run
   ```

   Ou via Docker:
   ```bash
   docker-compose up
   ```
4. Acesse `http://localhost:8080` e terá acesso ao Swagger da aplicação

## Cobertura Sonar
<img width="2041" height="956" alt="image" src="https://github.com/user-attachments/assets/f5ed48f6-5332-411c-b7ce-416214742965" />


## BDD - Behavior-Driven Development
Funcionalidade: Identificação do Cliente

Como um cliente do autoatendimento de um fast-food
<br>
Quero me identificar apenas com meu CPF
<br>
Para que eu seja autenticado e possa realizar um pedido vinculado ao meu cadastro

Cenário: Identificar cliente por CPF no início da jornada
<br>
Dado que o cliente informe um CPF válido
<br>
Quando a jornada de autoatendimento for iniciada
<br>
Então o sistema deve buscar o cliente pelo CPF
<br>
E criar um novo cadastro se ele não existir
<br>
E disponibilizar a continuidade do pedido vinculado ao cliente identificado

## Arquitetura
Clean Archtecture.

## Estruturação das pastas
![image](https://github.com/user-attachments/assets/c8996715-f174-4611-ab40-7c1d5ba35877)
Considerando o uso da clean archtecture foi pensada da seguinte maneira:
- As camadas presentation/infrasctructre equivalem a Framework & Drivers, sendo a presentation responsável por capturar a entrada do usuário e a infrastrucutre pela comunicação com camadas externas.
- A camada Application contempla as camadas Application Business Roles e Interface Adapters.
- A camda de Entities representa a camada Domain.

## Arquitetura Infraestrutura

### Diagrama de Fluxo
![image](https://github.com/user-attachments/assets/0211d2e7-9e7f-486a-8005-b8a32682a7fc)
- Dentro do Resource Group techchallenge-rg, há uma zona de DNS configurada com o domínio techchallenge.app.br, tornando-o acessível externamente.
- Quando acessado, o tráfego é direcionado para o IP público do POD ingress-nginx-controller.
- O Ingress Controller então roteia as requisições para o serviço interno Java App, utilizando a comunicação via Cluster IP.
- O Java App, quando necessário, se comunica com o POD PostgreSQL, que contém a base de dados, também via Cluster IP.
### Diagrama de Componente
![Arquitetura_Kubernetes](https://github.com/user-attachments/assets/8c5c551b-f5d1-4f37-833c-bb082a6d6594)
O cluster k8s-fiap é configurado com dois namespaces principais, cada um com funções específicas:
- default: Namespace onde as aplicações principais são implantadas e gerenciadas, contendo os PODs:
  - java-app-*: microsserviço presente no cluster.
    - Ingress: Configurado para gerenciar o tráfego de entrada direcionado à aplicação Java.
    - Cluster IP: Endereço IP interno para comunicação dentro do cluster.
    - Deployment: Gerencia a implantação e a escalabilidade da aplicação Java.
    - Secret: Armazena dados sensíveis, como chaves de API ou credenciais usadas pela aplicação.
    - Horizontal Pod Autoscaler (HPA): Configurado para escalar automaticamente o número de réplicas do pod com base na utilização de CPU.
    - Configuração do HPA:
      - Mínimo de 1 e máximo de 3 réplicas.
      - Escala a partir da métrica de uso de CPU atingir 70%.
    - Role HPA: Define as permissões necessárias para que o HPA acesse métricas do cluster (como CPU e memória) para tomar decisões de escalabilidade.
- ingress-basic: é responsável por gerenciar o tráfego externo e rotear as requisições para os serviços no namespace default.
  - ingress-nginx-controller: Executa o controlador NGINX Ingress, que atua como ponto de entrada para requisições externas e roteia o tráfego para os serviços apropriados no namespace default.
    - Ingress: Define as regras de roteamento para requisições externas (por exemplo, rotear requisições para o serviço do java-app).
    - Service: Expõe o controlador NGINX internamente no cluster.
    - Endpoint: Mapeia os endpoints para os serviços internos.
    - Deployment: Gerencia a implantação do controlador NGINX.
    - ConfigMap: Armazena configurações do NGINX, como limites de requisições, timeouts e outras opções de personalização.
    - Secret: Armazena informações sensíveis, como certificados TLS para habilitar HTTPS.    
*Os arquivos de configuração do Kubernetes (em formato .yml) estão organizados no diretório kubernetes/, que contém os recursos descritos no diagrama.
