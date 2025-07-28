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

## Execução em ambiente local
### Pré-requisitos
- Ter o Docker instalado e em execução. Para mais informações:
  - Windows: https://docs.docker.com/engine/install/ubuntu/
  - Linux: https://docs.docker.com/desktop/setup/install/windows-install/

### Execução
1. Clone o projeto para sua máquina:
```
git clone https://github.com/AleCigolini/fiap-tech-challenge-02.git
```
2. Acesse o diretório do projeto clonado e execute:
```
docker-compose up -d
```

Será inicializado 3 contêineres:
- Aplicação Java
- Flyway (Inicializar estrutura de banco de dados)
- PostgreSQL

Segue URL do banco de dados abaixo. Credenciais para acesso estão no arquivo .env do projeto.
```
jdbc:postgresql://localhost:5432/techchallenge
```

### Visualização do Swagger
Com a aplicação rodando, via navegador de sua preferência, acesse a URL:
```
http://localhost:8080
```

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
![image](https://github.com/user-attachments/assets/26923c74-d144-4001-88e0-a3d90be58a36)
O cluster techchallenge-k8s é configurado com dois namespaces principais, cada um com funções específicas:
- default: Namespace onde as aplicações principais são implantadas e gerenciadas, contendo os PODs:
  - java-app: aplicação principal que contém as APIs do sistema.
    - Ingress: Configurado para gerenciar o tráfego de entrada direcionado à aplicação Java.
    - Cluster IP: Endereço IP interno para comunicação dentro do cluster.
    - Deployment: Gerencia a implantação e a escalabilidade da aplicação Java.
    - Secret: Armazena dados sensíveis, como chaves de API ou credenciais usadas pela aplicação.
    - Horizontal Pod Autoscaler (HPA): Configurado para escalar automaticamente o número de réplicas do pod com base na utilização de CPU.
    - Configuração do HPA:
      - Mínimo de 1 e máximo de 3 réplicas.
      - Escala a partir da métrica de uso de CPU atingir 70%.
    - Role HPA: Define as permissões necessárias para que o HPA acesse métricas do cluster (como CPU e memória) para tomar decisões de escalabilidade.
  - postgress: hospeda o banco de dados PostgreeSQL usado para o armazenamento de dados.
    - Stateful.
    - Volume: utilizado para o armazenamento dos dados.
    - Cluster IP:  Endereço IP interno para comunicação com outros pods no cluster.
    - Secret: contém as credenciais do banco de dados.
- ingress-basic: é responsável por gerenciar o tráfego externo e rotear as requisições para os serviços no namespace default.
  - ingress-nginx-controller: Executa o controlador NGINX Ingress, que atua como ponto de entrada para requisições externas e roteia o tráfego para os serviços apropriados no namespace default.
    - Ingress: Define as regras de roteamento para requisições externas (por exemplo, rotear requisições para o serviço do java-app).
    - Service: Expõe o controlador NGINX internamente no cluster.
    - Endpoint: Mapeia os endpoints para os serviços internos.
    - Deployment: Gerencia a implantação do controlador NGINX.
    - ConfigMap: Armazena configurações do NGINX, como limites de requisições, timeouts e outras opções de personalização.
    - Secret: Armazena informações sensíveis, como certificados TLS para habilitar HTTPS.    
*Os arquivos de configuração do Kubernetes (em formato .yml) estão organizados no diretório kubernetes/, que contém os recursos descritos no diagrama.
