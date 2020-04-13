package one.digitalinnovation.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Estudante> estudantes = new ArrayList<>();
        estudantes.add(new Estudante("Rafael", 21));
        estudantes.add(new Estudante("Leonardo", 12));
        estudantes.add(new Estudante("Lia", 20));
        estudantes.add(new Estudante("Gabriel", 12));

        System.out.println("Lista no inicio: ");
        System.out.println(estudantes);

        System.out.println("Ordenação com Comparator: ");
        Collections.sort(estudantes, new EstudanteIdadeComparator());
        System.out.println(estudantes);

        Collections.shuffle(estudantes);

        System.out.println("Ordenação com Comparable: ");
        Collections.sort(estudantes);
        System.out.println(estudantes);

        Collections.shuffle(estudantes);

        System.out.println("Ordenação com expressão Lambda: ");
        estudantes.sort((first, second) -> first.getIdade() - second.getIdade());
        System.out.println(estudantes);

        Collections.shuffle(estudantes);

        System.out.println("Ordenação com referencia de método 1: ");
        estudantes.sort(Comparator.comparingInt(Estudante::getIdade));
        System.out.println(estudantes);

        Collections.shuffle(estudantes);

        System.out.println("Ordenação com referencia de método 2 (do maior para o menor com Reversed): ");
        estudantes.sort(Comparator.comparingInt(Estudante::getIdade).reversed());
        System.out.println(estudantes);
    }

}
