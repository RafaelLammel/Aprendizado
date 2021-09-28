package one.digitalinnovation.queue;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class ExercicioFila {

    public static void main(String[] args) {
        //Criando Fila
        Queue<String> nomes = new LinkedList<>();

        //Adicionando 5 nomes
        nomes.add("Juliana");
        nomes.add("Pedro");
        nomes.add("Carlos");
        nomes.add("Larissa");
        nomes.add("João");

        //Nevagando pela fila
        for(String nome : nomes) {
            System.out.println(nome);
        }

        //Primeiro item da fila (Sem remover)
        System.out.println(nomes.peek());

        //Primeiro item da fila (Removendo)
        System.out.println(nomes.poll());

        //Adicionando um novo nome e retornando a fila
        nomes.add("Daniel");
        System.out.println(nomes);

        //Tamanho da fila
        System.out.println(nomes.size());

        //Verificar se a fila está vazia
        System.out.println(nomes.isEmpty());

        //Verificar se o nome Carlos está na fila
        System.out.println(nomes.contains("Carlos"));

    }

}
