package one.digitalinnovation.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ExercicioHashMap {

    public static void main(String[] args) {
        //Criando Map com os estados brasileiros
        Map<String, String> estados = new HashMap<>();

        estados.put("AC", "Acre");
        estados.put("AL", "Alagoas");
        estados.put("AP", "Amapá");
        estados.put("AM", "Amazonas");
        estados.put("BA", "Bahia");
        estados.put("CE", "Ceará");
        estados.put("ES", "Espírito Santo");
        estados.put("GO", "Goiás");
        estados.put("MA", "Maranhão");
        estados.put("MT", "Mato Grosso");
        estados.put("MS", "Mato Grosso do Sul");
        estados.put("MG", "Minas Gerais");
        estados.put("PA", "Pará");
        estados.put("PB", "Paraíba");
        estados.put("PR", "Paraná");
        estados.put("PE", "Pernambuco");
        estados.put("PI", "Piauí");
        estados.put("RF", "Rio de Janeiro");
        estados.put("RN", "Rio Grande do Norte");
        estados.put("RS", "Rio Grande do Sul");
        estados.put("RO", "Rondônia");
        estados.put("RR", "Roraima");
        estados.put("SC", "Santa Catarina");
        estados.put("SP", "São Paulo");
        estados.put("SE", "Sergipe");
        estados.put("TO", "Tocantins");

        //Removendo Minas Gerais
        estados.remove("MG");

        //Adicionando DF
        estados.put("DF", "Distrito Federal");

        //Verificando tamanho do mapa
        System.out.println(estados.size());

        //Removendo MS
        estados.values().remove("Mato Grosso do Sul");

        //Iterando sobre o Map no formato NOME (SIGLA)
        for(String key : estados.keySet()) {
            System.out.println(estados.get(key) + " (" + key + ")");
        }

        //Verificando se Santa Catarina existe no map por sua Key
        System.out.println(estados.containsKey("SC"));

        //Verificando se Maranhão existe no map por seu value
        System.out.println(estados.containsValue("Maranhão"));

    }

}
