# Sistema de Controle de Estoque

Sistema de controle de estoque via terminal desenvolvido em Java,
simulando operações reais de gestão de estoque.

A ideia principal é evoluir esse projeto junto com meus estudos, posteriormente conectando em banco de dados e criando uma API REST.

## Funcionalidades

- Compra e venda de produtos com validação de saldo em caixa
- Acompanhamento em tempo real do caixa e patrimônio líquido
- Consulta, listagem e remoção de produtos
- Alerta de produtos com baixo estoque
- Ordenação alfabética automática dos produtos
- Validação de inputs e programação defensiva

## Como o sistema funciona

### Produtos
- Produtos são cadastrados automaticamente na primeira compra
- A lista é mantida em ordem alfabética
- Não é possível cadastrar produtos com nome em branco

### Compra
- Ao comprar um produto já existente, é possível atualizar o preço de custo
- Ao comprar um produto novo, ele é cadastrado automaticamente no estoque
- O valor da compra é debitado do caixa

### Venda
- O preço de venda é informado no momento da venda e pode ser diferente do preço de custo
- Não é possível vender mais unidades do que o estoque disponível
- O valor da venda é creditado no caixa

### Caixa e Patrimônio
- O caixa representa o dinheiro disponível
- O patrimônio líquido é a soma do caixa com o valor total do estoque
- Ambos são atualizados em tempo real a cada transação

## Tecnologias

- Java 25
- Orientação a Objetos
- IntelliJ IDEA

## Como executar

1. Clone o repositório
2. Abra o projeto no IntelliJ IDEA
3. Execute o `Main.java`
4. Informe o valor inicial do caixa e comece a gerenciar seu estoque

## Estrutura do projeto

- `Main.java` — menu e leitura de inputs do usuário
- `StockService.java` — regras de negócio
- `Product.java` — modelo de produto

## Sobre

Este projeto foi desenvolvido como parte da minha jornada de aprendizado
em Java back-end, inspirado na minha experiência profissional com
logística e controle de estoque.