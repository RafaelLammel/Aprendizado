package one.digitalinnovation.list;

import java.util.*;

public class ExercicioLista {

    public static void main(String[] args) {

        //Criando a lista
        List<String> nomes = new ArrayList<>();

        //Adicionando 5 nomes
        nomes.add("Juliana");
        nomes.add("Pedro");
        nomes.add("Carlos");
        nomes.add("Larissa");
        nomes.add("João");

        //Navegando pela lista e exibindo cada nome
        for(String nome : nomes) {
            System.out.println(nome);
        }

        //Substituindo Carlos por Roberto
        nomes.set(2, "Roberto");

        //Nome na posição 1
        System.out.println(nomes.get(1));

        //Nome na posição 4
        System.out.println(nomes.get(4));

        //Removendo João
        nomes.remove("João");

        //Quantidade de itens na lista
        System.out.println(nomes.size());

        //Verificando se Juliano existe na lista
        System.out.println(nomes.contains("Juliano"));

        //Criando nova lista
        List<String> novosNomes = new Vector<>();

        //Adicionando nomes a nova lista e depois adicionando a nova lista na lista antiga
        novosNomes.add("Ismael");
        novosNomes.add("Rodrigo");

        nomes.addAll(novosNomes);

        //Ordenando lista
        Collections.sort(nomes);
        System.out.println(nomes);

        //Verificando se a lista está vazia
        System.out.println(nomes.isEmpty());
        
    }

}
