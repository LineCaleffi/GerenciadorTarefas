## Gerenciador de tarefas
Realizando um projeto de backend para a empresa CRJA Consultoria. 

## Desenvolvedor
Aline Caleffi

## Tecnologias Utilizadas
- Linguagem: Java
- Framework: SpringBoot
- Banco de dados: Mysql
- Persistência de Dados: JPA
- Gerenciador de Dependências: Maven

## Funcionalidades desejadas:

Precisamos criar uma API de gerenciamento de tarefas para disponibilizar para nossa equipe de front.

Requisitos:

- A API deve ser REST
- Cada pessoa terá um id, nome, departamento e lista de tarefas
- Cada tarefa terá id, título, descrição, prazo, departamento, duração, pessoa alocada e se já foi finalizado.

Funcionalidades:

- Adicionar um pessoa (post/pessoas)
- Alterar um pessoa (put/pessoas/{id})
- Remover pessoa (delete/pessoas/{id})
- Listar pessoas trazendo nome, departamento, total horas gastas nas tarefas.(get/pessoas)
- Buscar pessoas por nome e período, retorna média de horas gastas por tarefa. (get/pessoas/gastos)
- Listar departamento e quantidade de pessoas e tarefas (get/departamentos)
- istar 3 tarefas que estejam sem pessoa alocada com os prazos mais antigos. (get/tarefas/pendentes)
- Adicionar um tarefa (post/tarefas)
- Finalizar a tarefa (put/tarefas/finalizar/{id})
- Alocar uma pessoa na tarefa que tenha o mesmo departamento (put/tarefas/alocar/{id})
