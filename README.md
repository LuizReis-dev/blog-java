# Blog Spring 3.1 com Java 17
Este é um projeto de blog desenvolvido em Spring 3.1, utilizando Java 17 como linguagem de programação. O aplicativo é alimentado por um banco de dados MySQL e faz uso de JPA/Hibernate para persistência de dados. O objetivo principal deste projeto é criar uma plataforma de blogging interativa e moderna.

# Funcionalidades Principais
* Cadastro e Login de Usuários: Os usuários podem se cadastrar (signup) e fazer login no sistema.

* Seguir Outros Usuários: Os usuários têm a capacidade de seguir outros usuários registrados no blog.

* Visualizar Postagens em Alta: A plataforma destaca postagens populares e as exibe em uma seção de "Postagens em Alta".

* Ver Postagens de Quem o Usuário Segue: Os usuários podem ver postagens apenas daqueles que eles seguem.

# Tecnologias Utilizadas
* Spring 3.1: Framework de desenvolvimento de aplicativos Java.

* Java 17: Versão mais recente da linguagem Java.

* MySQL: Banco de dados relacional para armazenar dados do aplicativo.

* JPA/Hibernate: Framework de mapeamento objeto-relacional para gerenciar a persistência de dados.

# Instruções de Uso
**Pré-requisitos: Certifique-se de ter o Java 17 e o MySQL instalados em sua máquina.**

1. Clonando o Repositório:
```
git clone https://github.com/seu-usuario/blog-spring-3.1.git
```

2. Configurando o Banco de Dados: Crie um banco de dados MySQL e configure as credenciais no arquivo application.properties.

3. Compilando e Executando:

```
cd blog-spring-3.1
./mvnw clean install
./mvnw spring-boot:run
```

