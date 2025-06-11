# LiterAlura

Projeto desenvolvido com **Java 24** e **Spring Boot**, utilizando a [API do Gutendex](https://gutendex.com/) para buscar livros, salvá-los no banco de dados e permitir consultas simples via terminal.

---

## 📌 Objetivo

Demonstrar a integração entre uma aplicação Java e uma API pública de livros (Gutendex), armazenando os dados localmente e oferecendo ao usuário uma interface de linha de comando para realizar buscas e gerenciar informações sobre livros e autores.

---

## 🛠️ Tecnologias Utilizadas

- **Java 24** – Com uso de recursos modernos como `record`
- **Spring Boot** – Para injeção de dependência e inicialização rápida
- **JPA / Hibernate** – Mapeamento objeto-relacional com PostgreSQL
- **PostgreSQL** – Banco de dados relacional
- **HttpClient** – Para consumo da API Gutendex
- **Gson** – Para análise de JSON

---

## 📁 Estrutura do Projeto

```
src/main/java
└── br.com.alura.challenger.SpringBoot
    ├── DesafioLiterAluraApplication.java
    ├── menu
    │   └── MainMenuRunner.java
    ├── DTO
    │   ├── LivroDto.java
    │   └── AutorDto.java
    ├── modelo
    │   ├── Livro.java
    │   └── Autor.java
    ├── repositório
    │   ├── LivroRepository.java
    │   └── AutorRepository.java
    ├── serviço
    │   ├── ConsultaApi.java
    │   └── DadosService.java
    └── utilitários
        └── AutorUtils.java
```

---

## ✅ Funcionalidades

- 🔎 Buscar livros na API Gutendex por título ou autor  
- 💾 Salvar livros no banco de dados  
- 📚 Listar todos os livros registrados  
- 🧑‍💼 Listar todos os autores registrados  
- 🕰️ Listar autores vivos em um determinado ano  
- 🌐 Listar livros por idioma  

> **Nota:** Para simplificação, apenas o **primeiro autor** de cada livro é mostrado no menu. Internamente, o sistema suporta múltiplos autores por obra.

---

## ▶️ Como Executar

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/seu-usuario/desafio-LiterAlura.git
   ```
2. **Configure o banco de dados no `application.properties`:**
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/seu_banco
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   spring.jpa.hibernate.ddl-auto=update
   ```
3. **Inicie a aplicação:**
   ```bash
   ./mvnw spring-boot:run
   ```

---

## 💻 Exemplo de Uso

```text
==== MENU ====
1 - Buscar e salvar livros
2 - Listar livros registrados
3 - Listar autores registrados
4 - Listar autores vivos em um ano
5 - Listar livros por idioma
0 - Sair

Digite a opção: 1
Digite o título ou autor: machado de assis

Livros encontrados:
Título: The Complete Project Gutenberg Works of...
Autores: Machado de Assis (1839-1908)
Idioma: en | Transferências: 1357

Deseja salvar estes livros no banco? (S/N): S
✅ Livros salvos com sucesso!
```

---

## 🌟 Melhorias Futuras (Ideias)

- Mostrar **todos os autores** no menu  
- Adicionar **paginação** nas listas  
- Exportar resultados para **CSV/JSON**  
- Criar uma **interface web** (Thymeleaf ou React)  
- Permitir busca por **trechos do nome** do autor  

---

## 👨‍💻 Créditos

Desenvolvido por **Paulo R.** como parte do desafio técnico proposto pela [Alura](https://www.alura.com.br/).

**API utilizada:** [Gutendex](https://gutendex.com/) – Base de dados de livros públicos baseada no Projeto Gutenberg.

---

## 📝 Licença

Este projeto está licenciado sob a **MIT License** – veja o arquivo [LICENSE](LICENSE) para mais detalhes.
