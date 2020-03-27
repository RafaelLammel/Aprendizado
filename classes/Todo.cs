using System;
using System.Collections.Generic;
using System.Linq;
using TodoApp.Models;

namespace TodoApp.Classes
{
    public class Todo
    {

        // Variáveis:
        private List<TodoTask> lista;
        private int idIncrement;

        // Método com a entrada de dados e a lista: 
        public Todo()
        {

            lista = new List<TodoTask>();
            idIncrement = 1;

            string menu = "-1";

            Console.WriteLine("#######################################");
            Console.WriteLine("#####       ESCOLHA A OPÇÂO       #####");
            Console.WriteLine("#######################################");
            Console.WriteLine("#####                             #####");
            Console.WriteLine("#####        [1] - [Adicionar]    #####");
            Console.WriteLine("#####        [2] - [Consultar]    #####");
            Console.WriteLine("#####        [3] - [Concluir]     #####");
            Console.WriteLine("#####        [0] - [Sair]         #####");
            Console.WriteLine("#####                             #####");
            Console.WriteLine("#######################################");
            Console.WriteLine("#######################################");
            Console.WriteLine("Rscolha a Opção: ");
           //------------------------------------------------------------------------------  
           // Laço de repetição:
            while(menu != "0")
            {
                menu = Console.ReadLine();
            
                //------------------------------------------------------------------------------  
                // Condição dos casos::
        
                switch (menu)
                {
                    case "0":
                        break;
                    case "1":
                        Console.Write("Insira o texto da tarefa: ");
                        var tarefa = Console.ReadLine();
                        Adicionar(tarefa);
                        Console.WriteLine("Tarefa adicionada com sucesso!");
                        break;
                    case "2":
                        Consultar();
                        break;

                    case "3":
                        Console.Write("Insira o ID da tarefa: ");
                        var id = Console.ReadLine();
                        Concluir(Int32.Parse(id));
                        Console.WriteLine("Tarefa Concluída com sucesso!");
                        //Concluir();
                        break;
                    default:
                        Console.WriteLine("Você saiu do programa! Tente novamente!");
                        break;
                }
            }
        }

        //------------------------------------------------------------------------------    
        // Método adicionar:

        public void Adicionar(string texto)
        {
            lista.Add(new TodoTask {
                Id =  idIncrement,
                Texto = texto,
                Concluido = false
            });

            idIncrement++;
        }

        //------------------------------------------------------------------------------
        // Método consultar: 

        public void Consultar()
        {
            if(lista.Count > 0)
            {
                foreach(var task in lista)
                {
                    string status = task.Concluido ? "Concluído" : "Não concluído";
                    Console.WriteLine($"{task.Id} - {task.Texto} --> {status}");
                }   
            }
            else 
            {
                Console.WriteLine("Não há tarefas registradas!");
            }
        }
        
        //------------------------------------------------------------------------------
        // Método concluir:

        public void Concluir(int id)
        {
            lista.Where(x => x.Id == id).ToList().ForEach(x => x.Concluido = true);
        }

    }

}