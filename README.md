# 🐍 Jogo da Cobrinha em Java

Projeto desenvolvido em Java utilizando a biblioteca Swing, com o objetivo de recriar o clássico jogo da cobrinha (Snake). O jogo possui sistema de pontuação, aumento progressivo de velocidade e modo hardcore.

---

## 🎯 Objetivo do Projeto

Este projeto foi desenvolvido com fins educacionais, visando:

- Praticar programação orientada a objetos em Java
- Trabalhar com interfaces gráficas usando Swing
- Implementar lógica de jogos
- Manipular eventos de teclado
- Utilizar timers e animações

---

## 🚀 Funcionalidades

- Tela inicial interativa
- Controle da cobra via teclado (W, A, S, D)
- Sistema de pontuação
- Aumento automático da velocidade
- Modo Hardcore
- Detecção de colisão
- Tela de fim de jogo
- Reinício da partida

---

## 🛠️ Tecnologias Utilizadas

- Java
- Java Swing (JPanel, Timer, Graphics)
- AWT (KeyListener, Eventos)
- Programação Orientada a Objetos

---

## 🎮 Controles

| Tecla | Ação               |
|-------|--------------------|
| W     | Mover para cima    |
| S     | Mover para baixo   |
| A     | Mover para esquerda|
| D     | Mover para direita |
| Enter | Iniciar / Reiniciar|
| H     | Ativar Hardcore    |

---

## ▶️ Como Executar o Projeto

### 1️⃣ Pré-requisitos

- Java JDK 8 ou superior instalado
- IDE Java (Eclipse, IntelliJ, NetBeans) ou terminal

---

### 2️⃣ Compilar o Projeto

Se estiver usando terminal:
```bash
javac jogoDaCobrinha/IniciarJogo.java
```

---

### 3️⃣ Executar o Jogo
```bash
java jogoDaCobrinha.IniciarJogo
```

---

## 📐 Estrutura do Projeto

```bash
src/
├── TelaDoJogo.java
└── IniciarJogo.java
```

---

## 📊 Mecânica do Jogo

- A cobra inicia com tamanho fixo
- A cada bloco coletado:
  - A cobra cresce
  - A pontuação aumenta
  - A velocidade é incrementada em 5%
- O jogo termina quando:
  - A cobra colide com o próprio corpo
  - A cobra atinge as bordas da tela

---

## 📚 Aprendizados

Com este projeto foi possível:

- Compreender melhor o funcionamento do Timer no Swing
- Trabalhar com renderização gráfica
- Gerenciar eventos de teclado
- Desenvolver lógica de colisão
- Criar um jogo completo em Java
