package classes;

public class Main {

    public static void main(String[] args) {
        ContaBanco c1 = new ContaBanco();
        ContaBanco c2 = new ContaBanco();

        c1.abrirConta("CP", "Jubileu");
        c1.depositar(300);

        System.out.println("Conta criada! - Dono: " + c1.getDono() + " - Tipo: " + c1.getTipo());
        System.out.printf("Jubileu possui: R$ %.2f\n", c1.getSaldo());

        c2.abrirConta("CC", "Creusa");
        c2.depositar(500);

        System.out.println("Conta criada! - Dono: " + c2.getDono() + " - Tipo: " + c2.getTipo());
        System.out.printf("Creusa possui: R$ %.2f\n", c2.getSaldo());

        c2.sacar(100);
        System.out.println("Creusa precisou sacar 100 reais!");
        System.out.printf("Creusa ficou com: R$ %.2f\n", c2.getSaldo());

    }

}
