import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class StockService {

   private final Scanner scanner = new Scanner(System.in);

   private final List<Product> produtos = new ArrayList<>();
   private double caixa;

   public StockService(double caixa) {
      this.caixa = caixa;
   }

   public double getPatrimonio() {
      return (caixa + calcularValor());
   }

   public double getCaixa() {
      return caixa;
   }

   public void cadastrar(String name, int quantity, double price){
      produtos.add(new Product(name, quantity, price));
      produtos.sort(Comparator.comparing(Product::getName));
   }

   public void consultar(String nomeBuscado){
      boolean achou = false;

      for (Product produto : produtos){
         if (produto.getName().equalsIgnoreCase(nomeBuscado)){
            System.out.println("Nome do produto: " + produto.getName());
            System.out.println("Quantidade: " + produto.getQuantity());
            System.out.println("Preço unitário: " + produto.getPrice());
            achou = true;
         }
      }

      if (!achou){
         System.out.println("Produto não encontrado");
      }
   }

   public void remover(String nomeBuscado){
      Product paraRemover = null;

      for (Product produto : produtos){
         if (produto.getName().equalsIgnoreCase(nomeBuscado)){
            paraRemover = produto;
         }
      }

      if (paraRemover != null){
         produtos.remove(paraRemover);
         System.out.println("Produto removido com sucesso");
      } else {
         System.out.println("Produto nao encontrado");
      }
   }

   public void listar(){
      if (produtos.isEmpty()){
         System.out.println("Nenhum produto encontrado");
         return;
      }

      for (Product produto : produtos){
         System.out.println("Nome do produto: " + produto.getName());
         System.out.println("Quantidade: " + produto.getQuantity());
         System.out.println("Preço unitário: " + produto.getPrice());
         System.out.println("--------------------------");
      }
   }

   public void atualizar(String nomeBuscado){
      boolean achou = false;
      for (Product produto : produtos){
         if (produto.getName().equalsIgnoreCase(nomeBuscado)){
            System.out.println("O que você deseja alterar? ");
            System.out.println("1. quantidade");
            System.out.println("2. preço");
            System.out.println("3. ambos");
            int option = scanner.nextInt();

            switch (option){
               case 1 -> {
                  System.out.println("Digite a nova quantidade: ");
                  int novo = scanner.nextInt();
                  produto.setQuantity(novo);
               }

               case 2 -> {
                  System.out.println("Digite o novo preço: ");
                  double novo = scanner.nextDouble();
                  produto.setPrice(novo);
               }

               case 3 -> {
                  System.out.println("Digite a nova quantidade: ");
                  int novo = scanner.nextInt();
                  produto.setQuantity(novo);
                  System.out.println("Digite o novo preço: ");
                  double novo1 = scanner.nextDouble();
                  produto.setPrice(novo1);
               }
            }
            achou = true;
            System.out.println("Produto atualizado com sucesso!");
            break;
         }
      }

      if (!achou){
         System.out.println("Produto não encontrado.");
      }
   }

   public double calcularValor (){
      double valor = 0;
      for (Product produto : produtos){
         valor += (produto.getPrice() * produto.getQuantity());
      }
      return valor;
   }

   public void alertaQuantidade(int quantity){
      boolean baixaQuantidade = false;
      for (Product produto : produtos){
         if (produto.getQuantity() < quantity){
            System.out.println("Nome: " + produto.getName());
            System.out.println("Quantidade: " + produto.getQuantity());
            System.out.println("---------------------------");

            baixaQuantidade = true;
         }
      }

      if (!baixaQuantidade){
         System.out.printf("Nenhum produto com quantidade abaixo de %d.\n", quantity);
      }
   }

   public void comprar(String nomeBuscado){
      boolean achou = false;
      for (Product produto : produtos) {
         if (produto.getName().equalsIgnoreCase(nomeBuscado)) {
            achou = true;

            System.out.printf("Produto encontrado: %s (%d unidades)\n", produto.getName(), produto.getQuantity());
            System.out.println("Preço de custo atual: R$" + produto.getPrice());
            int option;
            do {
               System.out.println("Deseja atualizar o preço de custo? 1- Sim / 2- Não");
               option = scanner.nextInt();
               if (option != 1 && option != 2) {
                  System.out.println("Opção inválida. Digite 1 para atualizar ou 2 para ir adiante.");
               }
            } while (option != 1 && option != 2);
            if (option == 1){
               System.out.println("Digite o novo preço de custo: ");
               produto.setPrice(scanner.nextDouble());
            }

            int quantity;
            do {
               System.out.println("Digite a quantidade que deseja comprar: ");
               quantity = scanner.nextInt();
               if (quantity <= 0){
                  System.out.println("Digite uma quantia válida.");
               }
            } while (quantity <= 0);


            double total = quantity * produto.getPrice();
            int confirmacao;
            do {
               System.out.printf("Total da compra: R$%.2f. Confirmar? (1-Sim / 2-Não)%n", total);
               confirmacao = scanner.nextInt();

               if (confirmacao != 1 && confirmacao != 2) {
                  System.out.println("Opção inválida. Digite 1 para confirmar ou 2 para cancelar.");
               }
            } while (confirmacao != 1 && confirmacao != 2);

            if (confirmacao == 1) {
               if (total <= caixa) {
                  produto.setQuantity(produto.getQuantity() + quantity);
                  caixa -= (total);
                  System.out.println("Compra realizada com sucesso!");
                  System.out.printf("Saldo atual: R$%.2f%n", caixa);
                  break;
               } else {
                  System.out.println("Saldo insuficiente.");
                  return;
               }
            } else {
               System.out.println("Compra cancelada.");
               return;
            }
         }
      }

      if (!achou){
         System.out.println("--CADASTRAR NOVO PRODUTO--");
         int quantity;
         do {
            System.out.println("Digite a quantidade que deseja comprar: ");
            quantity = scanner.nextInt();
            if (quantity <= 0){
               System.out.println("Digite uma quantia válida.");
            }
         } while (quantity <= 0);

         double price;
         do {
            System.out.println("Digite o preço unitário do produto: ");
            price = scanner.nextDouble();
            if (price <= 0){
               System.out.println("Digite um preço válido.");
            }
         } while (price <= 0);

         double total = quantity * price;
         int confirmacao;
         do {
            System.out.printf("Total da compra: R$%.2f. Confirmar? (1-Sim / 2-Não)%n", total);
            confirmacao = scanner.nextInt();

            if (confirmacao != 1 && confirmacao != 2) {
               System.out.println("Opção inválida. Digite 1 para confirmar ou 2 para cancelar.");
            }
         } while (confirmacao != 1 && confirmacao != 2);

         if (confirmacao == 1) {
            if (total <= caixa) {
              cadastrar(nomeBuscado, quantity, price);
              caixa -= (total);
              System.out.println("Compra realizada com sucesso!");
              System.out.printf("Saldo atual: R$%.2f.\n", caixa);
            } else {
               System.out.println("Saldo insuficiente.");
            }
         } else {
            System.out.println("Compra cancelada.");
         }
      }
   }

   public void vender(String nomeBuscado){
      boolean achou = false;
      for (Product produto : produtos){
         if (produto.getName().equalsIgnoreCase(nomeBuscado)){
            achou = true;
            System.out.printf("Produto encontrado: %s (%d pçs)", produto.getName(), produto.getQuantity());
            int quantity;
            do {
               System.out.println("Digite a quantidade que deseja vender: ");
               quantity = scanner.nextInt();
               if (quantity <= 0){
                  System.out.println("Digite uma quantia válida.");
               } else if (quantity > produto.getQuantity()) {
                  System.out.println("Sem estoque suficiente.");
               }
            } while (quantity <= 0 || quantity > produto.getQuantity());
            double price;
            do {
               System.out.println("Digite o preço de venda: ");
               price = scanner.nextDouble();
               if (price <= 0){
                  System.out.println("Digite uma quantia válida.");
               }
            } while (price <= 0);

            double total = price * quantity;
            int confirmacao;
            do {
               System.out.printf("Vender %d unidades por R$%.2f. Confirmar? (1 - Sim / 2- Não)\n", quantity, total);
               confirmacao = scanner.nextInt();
               if (confirmacao != 1 && confirmacao != 2){
                  System.out.println("Opção inválida. Digite 1 para confirmar ou 2 para cancelar.");
               }
            } while (confirmacao != 1 && confirmacao != 2);

            if (confirmacao == 1){
               produto.setQuantity(produto.getQuantity() - quantity);
               caixa += total;
               System.out.println("Venda concluída com sucesso!");
               System.out.printf("Saldo atual: R$%.2f.\n", caixa);
               break;
            } else {
               System.out.println("Venda cancelada.");
               return;
            }
         }
      }

      if (!achou) {
         System.out.println("Produto não existe, verifique se o nome está correto.");
      }
   }
}