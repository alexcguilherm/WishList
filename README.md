# Guia de Configuração da Aplicação Wishlist

Este guia fornecerá instruções detalhadas sobre como configurar e executar a aplicação Wishlist em seu ambiente local.

## Requisitos

Antes de começar, certifique-se de ter instalado o seguinte em seu sistema:

- Java 17
- Docker
- Docker Compose

## Configuração

Siga estas etapas para configurar a aplicação:

1. Clone o repositório da aplicação:

   ```bash
   git clone https://github.com/seu-usuario/wishlist.git
   ```

2. Navegue até o diretório do projeto:

   ```bash
   cd wishlist
   ```

3. Crie um arquivo `.env` na raiz do projeto e defina as variáveis de ambiente necessárias. Você pode usar o arquivo `.env.example` como referência.

4. Execute o seguinte comando para iniciar a aplicação:

   ```bash
   docker-compose up -d
   ```

5. Aguarde até que todos os serviços sejam iniciados. Você pode verificar o status dos contêineres usando o seguinte comando:

   ```bash
   docker-compose ps
   ```
6. Abra o projeto em sua IDE de preferência e execute a aplicação.

7. Uma vez que todos os serviços estejam em execução, você poderá acessar a aplicação em seu navegador da web:

    - Aplicação: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
    - MongoDB Express: [http://localhost:8081](http://localhost:8081)

## Uso

Agora que a aplicação está em execução, você pode começar a usá-la normalmente. Certifique-se de consultar a documentação da API para obter informações sobre os endpoints disponíveis.

## Parando a Aplicação

Para parar a aplicação e desligar os contêineres, execute o seguinte comando na raiz do projeto:

```bash
docker-compose down
```

Isso encerrará todos os contêineres e liberará os recursos ocupados.

## Suporte

Se você tiver algum problema ou precisar de ajuda, não hesite em [abrir uma issue](https://github.com/seu-usuario/wishlist/issues) no repositório do projeto.

# Recursos

## Adicionar Item à Wishlist

Adiciona um item à lista de desejos.

- **URL:** `/wishlist/add`
- **Método:** POST
- **Descrição:** Adiciona um item à lista de desejos.
- **Parâmetros do corpo da solicitação:**
    - `customerId` (integer, required): ID do cliente.
    - `product` (object, required): Objeto representando o produto a ser adicionado. Consulte a definição do esquema abaixo para os campos aceitos.
- **Respostas:**
    - `201 Created`: Retorna o novo item adicionado à lista de desejos.
- **Exemplo do corpo da solicitação:**
- **RequestBody**:

```json
{
  "customerId": 123,
  "product": {
    "productId": 456,
    "name": "Nome do Produto",
    "description": "Descrição do Produto",
    "price": 99.99,
    "brand": "Marca",
    "model": "Modelo",
    "available": true,
    "rating": 4.5
  }



## Encontrar Produto por ID na Wishlist

Encontra um produto pelo ID na lista de desejos.

- **URL:** `/wishlist`
- **Método:** GET
- **Descrição:** Encontra um produto pelo ID na lista de desejos.
- **Parâmetros da consulta:**
    - `customerId` (integer, required): ID do cliente.
    - `productId` (integer, required): ID do produto.
- **Respostas:**
    - `200 OK`: Retorna o produto encontrado na lista de desejos.
- **Exemplo da consulta:**
- **Path**: /wishlist?customerId=123&productId=456




## Encontrar Todos os Itens

Busca todos os itens na lista de desejos de um cliente.

- **URL:** `/wishlist/products/{customerId}`
- **Método:** GET
- **Descrição:** Busca todos os itens na lista de desejos de um cliente.
- **Parâmetros da consulta:**
    - `customerId` (integer, required): ID do cliente.
- **Respostas:**
    - `200 OK`: Retorna todos os itens na lista de desejos do cliente.
- **Exemplo da consulta:**
- **Path**: /wishlist/products/123



## Excluir Item da Wishlist

Exclui um item da lista de desejos.

- **URL:** `/wishlist`
- **Método:** DELETE
- **Descrição:** Exclui um item da lista de desejos.
- **Parâmetros da consulta:**
    - `customerId` (integer, required): ID do cliente.
    - `productId` (integer, required): ID do produto.
- **Respostas:**
    - `204 No Content`: Retorna um status de sucesso sem conteúdo.
- **Exemplo da consulta:**

- **Path**: /wishlist?customerId=123&productId=456






# Produtos para Cadastro
```json
{
  "customerId": 123,
  "product": {
    "productId": 456,
    "name": "Nome do Produto",
    "description": "Descrição do Produto",
    "price": 99.99,
    "brand": "Marca",
    "model": "Modelo",
    "available": true,
    "rating": 4.5
  }
}






# Produtos para Cadastro

```json
{
  "productId": 1,
  "name": "Sony WH-1000XM5 Wireless Noise-Canceling Headphones",
  "description": "Premium wireless headphones with industry-leading noise cancellation, high-resolution audio, and long battery life.",
  "price": 349.99,
  "brand": "Sony",
  "model": "WH-1000XM5",
  "available": true,
  "rating": 4.9
}

2. **Samsung Galaxy S21**
   {
   "productId": 2,
   "name": "Samsung Galaxy S21",
   "description": "A flagship smartphone from Samsung with a stunning display.",
   "price": 899.99,
   "brand": "Samsung",
   "model": "S21",
   "available": true,
   "rating": 4.7
   }

3. **Sony PlayStation 5**
   {
   "productId": 3,
   "name": "Sony PlayStation 5",
   "description": "The latest gaming console from Sony with 4K gaming capabilities.",
   "price": 499.99,
   "brand": "Sony",
   "model": "PlayStation 5",
   "available": true,
   "rating": 4.9
   }

4. **Dell XPS 13 Laptop**
   {
   "productId": 4,
   "name": "Dell XPS 13 Laptop",
   "description": "A high-performance laptop with a compact design and long battery life.",
   "price": 1299.99,
   "brand": "Dell",
   "model": "XPS 13",
   "available": true,
   "rating": 4.6
   }

5. **LG OLED C1 4K TV**
   {
   "productId": 5,
   "name": "LG OLED C1 4K TV",
   "description": "A premium OLED TV with stunning picture quality and smart features.",
   "price": 1999.99,
   "brand": "LG",
   "model": "OLED C1",
   "available": true,
   "rating": 4.9
   }

6. **Nike Air Zoom Pegasus 38 Running Shoes**
   {
   "productId": 6,
   "name": "Nike Air Zoom Pegasus 38 Running Shoes",
   "description": "A popular running shoe with responsive cushioning and a comfortable fit.",
   "price": 119.99,
   "brand": "Nike",
   "model": "Air Zoom Pegasus 38",
   "available": true,
   "rating": 4.8
   }

7. **Amazon Echo Dot (4th Gen) Smart Speaker**
   {
   "productId": 7,
   "name": "Amazon Echo Dot (4th Gen) Smart Speaker",
   "description": "A compact smart speaker with Alexa voice control and crisp sound.",
   "price": 49.99,
   "brand": "Amazon",
   "model": "Echo Dot (4th Gen)",
   "available": true,
   "rating": 4.7
   }

8. **GoPro HERO9 Black Action Camera**
   {
   "productId": 8,
   "name": "GoPro HERO9 Black Action Camera",
   "description": "A versatile action camera with 5K video recording and a rugged design.",
   "price": 449.99,
   "brand": "GoPro",
   "model": "HERO9 Black",
   "available": true,
   "rating": 4.8
   }

9. **Apple MacBook Pro (M1, 2020)**
   {
   "productId": 9,
   "name": "Apple MacBook Pro (M1, 2020)",
   "description": "A powerful laptop with Apple's M1 chip for fast performance and long battery life.",
   "price": 1299.99,
   "brand": "Apple",
   "model": "MacBook Pro (M1, 2020)",
   "available": true,
   "rating": 4.9
   }

10. **Sony WH-1000XM4 Wireless Noise-Canceling Headphones**
    {
    "productId": 10,
    "name": "Sony WH-1000XM4 Wireless Noise-Canceling Headphones",
    "description": "Premium wireless headphones with industry-leading noise cancellation and long battery life.",
    "price": 349.99,
    "brand": "Sony",
    "model": "WH-1000XM4",
    "available": true,
    "rating": 4.9
    }

11. **Canon EOS R5 Mirrorless Camera**
    {
    "productId": 11,
    "name": "Canon EOS R5 Mirrorless Camera",
    "description": "A high-end mirrorless camera with 8K video recording and advanced autofocus capabilities.",
    "price": 3899.99,
    "brand": "Canon",
    "model": "EOS R5",
    "available": true,
    "rating": 4.8
    }

12. **Microsoft Xbox Series X Gaming Console**
    {
    "productId": 12,
    "name": "Microsoft Xbox Series X Gaming Console",
    "description": "The latest gaming console from Microsoft with powerful performance and next-gen gaming experiences.",
    "price": 499.99,
    "brand": "Microsoft",
    "model": "Xbox Series X",
    "available": true,
    "rating": 4.7
    }

13. **Samsung QN90A Neo QLED 4K TV**
    {
    "productId": 13,
    "name": "Samsung QN90A Neo QLED 4K TV",
    "description": "A premium QLED TV with stunning picture quality, high brightness, and smart features.",
    "price": 2499.99,
    "brand": "Samsung",
    "model": "QN90A",
    "available": true,
    "rating": 4.9
    }

14. **Google Pixel 6 Pro Smartphone**
    {
    "productId": 14,
    "name": "Google Pixel 6 Pro Smartphone",
    "description": "The latest flagship smartphone from Google with advanced camera features and fast performance.",
    "price": 899.99,
    "brand": "Google",
    "model": "Pixel 6 Pro",
    "available": true,
    "rating": 4.7
    }

15. **Bose QuietComfort 45 Wireless Headphones**
    {
    "productId": 15,
    "name": "Bose QuietComfort 45 Wireless Headphones",
    "description": "Premium wireless headphones with active noise cancellation and comfortable design for long listening sessions.",
    "price": 329.99,
    "brand": "Bose",
    "model": "QuietComfort 45",
    "available": true,
    "rating": 4.8
    }

16. **Microsoft Surface Laptop 4**
    {
    "productId": 16,
    "name": "Microsoft Surface Laptop 4",
    "description": "A sleek and powerful laptop with a touchscreen display and excellent battery life.",
    "price": 999.99,
    "brand": "Microsoft",
    "model": "Surface Laptop 4",
    "available": true,
    "rating": 4.6
    }

17. **LG CX OLED 4K TV**
    {
    "productId": 17,
    "name": "LG CX OLED 4K TV",
    "description": "A top-of-the-line OLED TV with stunning picture quality, deep blacks, and Dolby Vision support.",
    "price": 2799.99,
    "brand": "LG",
    "model": "CX",
    "available": true,
    "rating": 4.9
    }

18. **Lenovo ThinkPad X1 Carbon Gen 9**
    {
    "productId": 18,
    "name": "Lenovo ThinkPad X1 Carbon Gen 9",
    "description": "An ultraportable business laptop with a durable design, powerful performance, and long battery life.",
    "price": 1399.99,
    "brand": "Lenovo",
    "model": "ThinkPad X1 Carbon Gen 9",
    "available": true,
    "rating": 4.7
    }

19. **Canon EOS Rebel T8i DSLR Camera**
    {
    "productId": 19,
    "name": "Canon EOS Rebel T8i DSLR Camera",
    "description": "An entry-level DSLR camera with versatile features and excellent image quality.",
    "price": 749.99,
    "brand": "Canon",
    "model": "EOS Rebel T8i",
    "available": true,
    "rating": 4.8
    }

20. **Fitbit Charge 5 Fitness Tracker**
    {
    "productId": 20,
    "name": "Fitbit Charge 5 Fitness Tracker",
    "description": "A feature-packed fitness tracker with built-in GPS, heart rate monitoring, and sleep tracking.",
    "price": 179.95,
    "brand": "Fitbit",
    "model": "Charge 5",
    "available": true,
    "rating": 4.7
    }

