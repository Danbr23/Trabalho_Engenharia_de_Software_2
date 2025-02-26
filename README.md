# Editor de Código em JavaFX

## Curso
Engenharia da Computação - IFSULDEMINAS Campus Poços de Caldas - Engenharia de Software II

## 📌 Sobre o Projeto
Este é um editor de código desenvolvido em **JavaFX** utilizando a biblioteca **RichTextFX**. O objetivo do projeto é criar uma ferramenta semelhante ao **Notepad++**, com suporte para **realce de sintaxe, numeração de linhas, pesquisa de texto, desfazer/refazer** e outras funcionalidades essenciais para edição de código.

## 🚀 Funcionalidades
- **Realce de Sintaxe** para a linguagem **C** (implementado com o padrão Decorator)
- **Numeração de Linhas**
- **Pesquisa de Texto** com realce automático (implementado com o padrão Observer)
- **Suporte a Desfazer/Refazer** (implementado com o padrão Command)
- **Criação dinâmica de abas** (implementado com o padrão Factory)
- **Suporte para colar texto mantendo histórico**
- **Sistema de dobra de código**

## 🛠️ Tecnologias Utilizadas
- **Java 11+**
- **JavaFX**
- **RichTextFX**

## 📌 Padrões de Design Aplicados
Este projeto implementa diversos padrões de design para manter a organização e flexibilidade do código:

### 🔔 Observer (Padrão Observador)
Utilizado para a **barra de pesquisa**, permitindo que todas as abas do editor sejam notificadas automaticamente quando uma pesquisa for feita.

### 🎮 Command (Padrão Comando)
Usado para **desfazer e refazer ações** como colar texto, garantindo que cada ação seja armazenada e possa ser revertida.

### 🏭 Factory (Padrão Fábrica)
Facilita a criação de **novas abas do editor** com a configuração adequada.

### 🎨 Decorator (Padrão Decorador)
Permite adicionar **realce de sintaxe** de forma modular, facilitando a expansão para outras linguagens no futuro.

## 📦 Como Executar
1. Clone este repositório:
   ```sh
   git clone https://github.com/seu-usuario/editor-javafx.git
   ```
2. Importe o projeto em sua IDE preferida (Eclipse, IntelliJ, NetBeans).
3. Certifique-se de ter o **JavaFX** configurado corretamente.
4. Compile e execute a aplicação.

