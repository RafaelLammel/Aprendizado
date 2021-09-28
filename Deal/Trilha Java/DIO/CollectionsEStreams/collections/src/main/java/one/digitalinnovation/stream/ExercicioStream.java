package one.digitalinnovation.stream;

import one.digitalinnovation.util.Estudante;
import one.digitalinnovation.util.EstudanteIdadeComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExercicioStream {

    public static void main(String[] args) {

        //Criando uma lista de estudantes
        List<Estudante> estudantes = new ArrayList<>();
        estudantes.add(new Estudante("Rafael", 21));
        estudantes.add(new Estudante("Leonardo", 12));
        estudantes.add(new Estudante("Lia", 20));
        estudantes.add(new Estudante("Gabriel", 12));
        estudantes.add(new Estudante("Bia", 11));

        System.out.println("Cada estudante como String: " + estudantes.stream().peek(estudante -> estudante.toString()).collect(Collectors.toList()));

        System.out.println("Quantidade de elementos: " + estudantes.stream().count());

        System.out.println("Estudantes com idade maior ou igual a 18: " + estudantes.stream().filter(estudante -> estudante.getIdade() > 18).collect(Collectors.toList()));

        System.out.println("Exibindo cada elemento: ");
        estudantes.forEach(estudante -> System.out.println(estudante));

        System.out.println("Estudantes que possuem a letra B: " + estudantes.stream().filter(estudante -> estudante.getNome().toLowerCase().contains("b")).collect(Collectors.toList()));

        System.out.println("Algum estudante contém a letra D? " + estudantes.stream().anyMatch(estudante -> estudante.getNome().toLowerCase().contains("d")));

        System.out.println("Estudante mais velho e mais jovem: " + estudantes.stream().max(new EstudanteIdadeComparator()).get() + " " + estudantes.stream().min(new EstudanteIdadeComparator()).get());

    }

}
