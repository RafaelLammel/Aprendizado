package com.algaworks.cadastrocliente;

import com.algaworks.cadastrocliente.model.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Exemplo {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("Clientes-PU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        /*//Buscando Cliente por ID
        Cliente cliente = entityManager.find(Cliente.class, 1);
        System.out.println(cliente.getNome());

        //Inserção no BD
        Cliente clienteInsert = new Cliente();
        clienteInsert.setNome("Autopeças Estrada");

        entityManager.getTransaction().begin();
        entityManager.persist(clienteInsert);
        entityManager.getTransaction().commit();

        //Remover dados do BD
        Cliente clienteRemove = entityManager.find(Cliente.class, 3);
        entityManager.getTransaction().begin();
        entityManager.remove(clienteRemove);
        entityManager.getTransaction().commit();

        //Alteração de dados do BD
        Cliente clienteAltera = entityManager.find(Cliente.class, 1);

        entityManager.getTransaction().begin();
        clienteAltera.setNome("Nome alterado");
        entityManager.getTransaction().commit();

        //Alteração de dados do BD (Usando uma classe ainda não gerenciada pelo Hibernate)
        Cliente clienteAlteraMerge = new Cliente();
        clienteAlteraMerge.setId(1);
        clienteAlteraMerge.setNome("Novo nome Merge");

        entityManager.getTransaction().begin();
        entityManager.merge(clienteAlteraMerge);
        entityManager.getTransaction().commit();*/

        //Inserção de dados no BD (Usando Merge)
        Cliente clienteAlteraMerge = new Cliente();
        clienteAlteraMerge.setNome("Novo registro Merge");

        entityManager.getTransaction().begin();
        entityManager.merge(clienteAlteraMerge);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }

}
