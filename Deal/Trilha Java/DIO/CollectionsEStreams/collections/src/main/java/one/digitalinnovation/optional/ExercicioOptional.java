package one.digitalinnovation.optional;

import java.util.Optional;

public class ExercicioOptional {

    public static void main(String[] args) {
        //Criando um optional presente, vazio e null
        Optional<String> opt = Optional.of("Presente");
        //Optional<String> opt = Optional.empty();
        //Optional<String> opt = Optional.ofNullable(null);

        //Exibindo na tela
        if(opt.isPresent()) {
            String presente = opt.get();
            System.out.println(presente);
            System.out.println("Valor filtrado para verificar se possui mais de 5 caracteres: " + opt.filter(value -> value.length() > 5));
        } else {
            System.out.println("Optional Vazio");
            throw new IllegalStateException();
        }

    }

}
