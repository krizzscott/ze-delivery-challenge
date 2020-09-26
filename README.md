# ze-delivery-challenge

## Challenges

- [x] **1.1 Criação de um Parceiro do Zé:**  Salvar no banco de dados  **todas**  as seguintes informações representadas por este JSON junto com as regras subsequentes:
```json
{
  "id": 1, 
  "tradingName": "Adega da Cerveja - Pinheiros",
  "ownerName": "Zé da Silva",
  "document": "1432132123891/0001",
  "coverageArea": { 
    "type": "MultiPolygon", 
    "coordinates": [
      [[[30, 20], [45, 40], [10, 40], [30, 20]]], 
      [[[15, 5], [40, 10], [10, 20], [5, 10], [15, 5]]]
    ]
  },
  "address": { 
    "type": "Point",
    "coordinates": [-46.57421, -21.785741]
  }
}
```

- [x] **1.2 Carregar parceiro pelo  `id`:** Retornar um parceiro específico baseado no seu campo  `id`  com todos os campos apresentados acima.
- [x] **1.3 Busca de Parceiro:** Dada uma localização pelo usuário da API (coordenadas  `long`  e  `lat`), procure o parceiro que esteja  **mais próximo**  e  **que cuja área de cobertura inclua**  a localização.

# Dependências para Validação local do Desafio

 - [Java JDK 8](https://www.oracle.com/br/java/technologies/javase/javase-jdk8-downloads.html)
 - [Apache Maven](https://maven.apache.org/download.cgi)
 - [Docker](https://www.docker.com/products/docker-desktop) 
## Passos para Build do projeto:
### Considerando que você esteja na pasta raiz do projeto execute os seguintes comandos:
- **Montagem da imagem do docker com os itens de infraestrutura**
		- `docker-compose up -d`
- **Empacotamento e execução de testes do nosso .jar**
		-  `mvn clean install`
- **Start do projeto**
		- `java -jar target/ze-delivery-challenge-1.3.0.jar`

- **Acessando a documentacao da API para maiores detalhes e realizacao de testes**
		- http://localhost:8080/swagger-ui.html
	
# Referencias
- Documento de apoio usado para estudo de como efetuar a validação do GeoJSON MultiPolygon type [aqui](https://tools.ietf.org/html/rfc7946#section-3.1.6)