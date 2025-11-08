# Cadastro de Clientes em Java (CRUD com JOptionPane e DAO)

Este projeto é um sistema simples de **cadastro de clientes (CRUD)** desenvolvido em **Java**, utilizando:

- `JOptionPane` para interface gráfica simples
- Padrão DAO (Data Access Object)
- Armazenamento em memória usando `HashMap`
- Organização em camadas (`domain`, `dao`, `app`)

O objetivo é demonstrar os conceitos de **Programação Orientada a Objetos (POO)**, **manipulação de dados**, **separação de responsabilidades** e **boas práticas de desenvolvimento**.

---

## ✅ **Funcionalidades**

O sistema permite:

### **1. Cadastrar Cliente**
Informando:
- Nome  
- CPF  
- Telefone  
- Endereço  
- Número  
- Cidade  
- Estado  

Todos separados por vírgula.

### **2. Consultar Cliente**
Busca pelo CPF.

### **3. Excluir Cliente**
Remove o cliente do sistema usando CPF.

### **4. Alterar Cliente**
Atualiza:
- Nome  
- Telefone  
- Endereço  
- Número  
- Cidade  
- Estado  

### **5. Sair do Sistema**

---

## ✅ **Estrutura do Projeto**

src/
└── br/com/lreiiss/
├── App.java
├── domain/
│ └── Cliente.java
└── dao/
├── IClienteDAO.java
└── ClienteMapDAO.java


### **App.java**
- Contém o menu principal via `JOptionPane`
- Faz validações
- Chama o DAO
- Controla o fluxo do programa

### **Cliente.java**
- Representa o cliente (objeto do domínio)
- Faz limpeza de CPF, telefone e número
- Override de `equals`, `hashCode` e `toString`

### **IClienteDAO.java**
- Interface que define os métodos CRUD

### **ClienteMapDAO.java**
- Implementa o DAO usando `HashMap<Long, Cliente>`
- CPF é usado como chave (Map Key)

---

## ✅ **Tecnologias Utilizadas**

- **Java 17**
- **JDK Microsoft Build**
- **JOptionPane (Swing)**
- **Coleções Java (HashMap)**
- **Padrão DAO**
- **POO**
