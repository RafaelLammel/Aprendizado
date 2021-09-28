package one.digitalinnovation.set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ExercicioHashSet {

    public static void main(String[] args) {
        //Criando Set
        Set<Integer> numeros = new HashSet<>();

        //Adicionando 5 números
        numeros.add(3);
        numeros.add(88);
        numeros.add(20);
        numeros.add(44);
        //Esse número não vai entrar pois Set não permite entradas duplicadas
        numeros.add(3);

        //Navegando pelo Set
        for(int i : numeros) {
            System.out.println(i);
        }

        //Removendo primeiro item do Set

        Iterator iterator = numeros.iterator();

        numeros.remove(iterator.next());

        //Adicionando um novo número no set
        numeros.add(23);

        //Verificando o tamanho do set
        System.out.println(numeros.size());

        //Verificar se o set está vazio
        System.out.println(numeros.isEmpty());

    }

}
