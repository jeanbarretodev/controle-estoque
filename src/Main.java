import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

     public static void main(String[] args) {
        System.out.println("Digite o valor inicial do caixa: ");
        double caixa = scanner.nextDouble();

        StockService service = new StockService(caixa);

        service.cadastrar("RTX 5060", 2, 2700);
        service.cadastrar("SSD 1TB", 2, 900);
        service.cadastrar("MOUSE", 2, 100);

        int option;
        do {
            System.out.println("========CONTROLE DE ESTOQUE========");
            System.out.printf("Valor de caixa: R$%.2f\n", service.getCaixa());
            System.out.printf("Patrimônio líquido: R$%.2f\n", service.getPatrimonio());
            System.out.println("1. Comprar produto");
            System.out.println("2. Vender produto");
            System.out.println("3. Consultar produto");
            System.out.println("4. Listar todos os produtos");
            System.out.println("5. Remover produto");
            System.out.println("6. Listar produtos com baixa quantidade");
            System.out.println("7. Calcular valor total do estoque");
            System.out.println("0. Sair do programa");

            option = scanner.nextInt();

            switch (option){
                case 1 -> {
                    System.out.println("Digite o nome do produto: ");
                    scanner.nextLine();
                    String name = scanner.nextLine();
                    if (name.isBlank()){
                        System.out.println("Digite um nome válido.");
                        break;
                    }
                    service.comprar(name);
                }

                case 2 -> {
                    System.out.println("Digite o nome do produto: ");
                    scanner.nextLine();
                    String name = scanner.nextLine();
                    if (name.isBlank()){
                        System.out.println("Digite um nome válido.");
                        break;
                    }
                    service.vender(name);
                }

                case 3 -> {
                    System.out.println("Digite o nome do produto que deseja buscar: ");
                    scanner.nextLine();
                    String busca = scanner.nextLine();
                    if (busca.isBlank()){
                        System.out.println("Digite um nome válido.");
                        break;
                    }
                    service.consultar(busca);
                }

                case 4 -> service.listar();

                case 5 -> {
                    System.out.println("Digite o nome do produto que deseja excluir: ");
                    scanner.nextLine();
                    String busca = scanner.nextLine();
                    if (busca.isBlank()){
                        System.out.println("Digite um nome válido.");
                        break;
                    }
                    service.remover(busca);
                }

                case 6 -> {
                    int quantity;
                    do {
                        System.out.println("Digite a quantidade mínima do produto: ");
                        quantity = scanner.nextInt();
                        if (quantity <= 0){
                            System.out.println("Digite uma quantidade válida.");
                        }
                    } while (quantity <= 0);

                    service.alertaQuantidade(quantity);
                }

                case 7 -> {
                    double valor = service.calcularValor();
                    System.out.printf("O valor total do estoque é: R$%.2f \n", valor);
                }

                case 0 -> System.exit(0);
            }
        }while (true);
    }
}