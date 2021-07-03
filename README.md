# API Carros
API capaz de listar, cadastrar, detalhar, atualizar valor (preço), deletar, filtrar e ordenar carros.

  Gerando com tais atribuições, um CRUD:
  - Create (cadastrar carros)
  - Read (listar carros)
  - Update (atualizar valor dos carros)
  - Delete (excluindo carros)
  
  No contexto de API REST, utilizando os métodos HTTP:
  - POST (@PostMapping)
  - GET (@GetMapping)
  - PUT (PutMapping)
  - DELETE (@DeleteMapping)

---
Projeto desenvolvido durante a *Sprint 3 do Programa de Bolsa da [Compasso UOL](https://compassouol.com/)*


### Tecnologias
- [Spring Boot API REST](https://start.spring.io/)
- [H2 Database](http://www.h2database.com/html/quickstart.html)
- [Swagger](https://editor.swagger.io/)
- [Postman](https://www.postman.com/downloads/)

### Pré-requisitos

Antes de começar, você vai precisar ter em sua máquina os arquivos disponibilizados nesse repositório [(master)](https://github.com/domafras/sprint3-backup/tree/master). 

Além do Java e uma IDE como [Eclipse](https://www.eclipse.org/eclipseide/) em que você pode seguir esses passos:
```bash
1. 'Import existing Maven Project'
2. Run 'Sprint3Application.java'
3. Acessar 'localhost:8080/api/cars'
4. Para 'GET' -> Passos possíveis no navegador
5. Para 'POST','PUT','DELETE' -> Postman e Swagger Editor
```
## Postman 
No Postman é possível utilizar a API de maneira à criar lista de carros, retornar lista de carros, detalhar carros, atualizar valor (preço) de carros, deletar carros, filtrar e ordenar carros.

Vantagem:
- Possui filtros, como de maior e menor preço
- Diferentes ordenações
```bash
Endereço base: 'localhost:8080/api'
```
```bash
(GET) /cars/caro
# Apresenta o carro de valor mais alto cadastrado
Acesse: localhost:8080/api/cars/caro
```
```bash
(GET) /cars/barato
# Apresenta o carro de valor mais barato cadastrado
Acesse: localhost:8080/api/cars/barato
```
```bash
Gerenciar ordenação com 'asc' ou 'desc'

    # Por ano de fabricação mais antigo:
    Acesse: localhost:8080/api/cars?sort=ano,asc

    # Por ano de fabricação mais atual:
    Acesse: localhost:8080/api/cars?sort=ano,desc
```
```bash
Combinar filtros e ordenações

    # Somente marca Ford do ano 2015:
    Acesse: localhost:8080/api/cars?marca=ford&ano=2015
    
    # Somente marca Fiat, modelo Uno e ordenando pelo menor valor:
    Acesse: localhost:8080/api/cars?marca=fiat&nome=Uno&sort=valor,asc
```
## Swagger
Existem várias funcionalidades para o Swagger, a que iremos utilizar é capaz de nos auxiliar no desenvolvimento mas também no entendimento de uma API. Estamos falando do [Swagger Editor](https://editor.swagger.io/)

```bash
Run 'Sprint3Application.java'
```
Com a aplicação Java sendo executada:
```bash
Alerta de possível problema ao tentar executar requisições:
    
    "Failed to fetch."
    "Possible Reasons:"
        - CORS
        - Network Failure
        - URL scheme must be "http" or "https" for CORS request
```
Possível solução (@Chrome)
```bash
Para Windows:
    1. Abra o menu iniciar
    2. Digite Windows + R (abra o 'Executar')
    3. Execute o seguinte comando
```
```bash
chrome.exe --user-data-dir="C://Chrome dev session" --disable-web-security
```
Fonte: [Stack Overflow](https://stackoverflow.com/questions/3102819/disable-same-origin-policy-in-chrome)
*No meu caso, o problema persistia em vários navegadores e foi solucionado assim.
** Utilizar esta instância de navegador somente para essa função

Ao acessar o editor.swagger.io (com --disable-web-security):
```bash
Na parte superior:
    1. File
    2. Import file
    3. "swagger.yaml" (contido neste repositório)
```
Na interface, com métodos de requisição:
```bash
(GET) /cars 
- Lista carros
# Expanda a aba e clique em "Try it out"
    
    Todos: basta executar sem filtros e ordenações.
    *Filtros: digite de acordo com os campos (case sensitive) e execute.
    *Ordenação: selecione de acordo com os campos e execute.
    
*> Ambos podem agir de forma independente ou em conjunto.
```
```bash
(POST) /cars
- Cadastra carros
# Expanda a aba e clique em "Try it out"

    Altere os valores dos atributos e execute.
    #Exemplo:
        Chassi: "9BBNSZPPA288003333" (exatamente 18 caracteres e diferente dos já cadastrados)
        Nome: "Golf"
        Marca: "volkswagen"
        Cor: "prata"
        Valor: 88000 (até 999999)
        Ano: 2019
```
```bash
(GET) /cars/{id}
- Detalha carro
# Expanda a aba e clique em "Try it out"

    Digite o ID do carro que deseja ter detalhado e execute.
```
```bash
(PUT) /cars/{id}
- Atualiza valor do carro
# Expanda a aba e clique em "Try it out"

    Digite o ID do carro que deseja alterar o valor (preço)
    Altere o "0" pelo valor atualizado e execute.
```
```bash
(DELETE) /cars/{id}
- Deleta carro
# Expanda a aba e clique em "Try it out"

    Digite o ID do carro que deseja excluir da lista e execute.
```

---
### Licença
Sem restrições ao uso, modificações e distribuição do código fonte.

### Contato
Feito com ❤️ por [Leonardo Mafra](https://www.linkedin.com/in/leomafra/)
