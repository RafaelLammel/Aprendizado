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
            
           //------------------------------------------------------------------------------  
           // Laço de repetição:
            while(menu != "0")
            {
                Console.Write("Escolha a Opção: ");
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
                        Console.WriteLine();
                        Console.WriteLine("Resposta: Tarefa adicionada com sucesso!");
                        Console.WriteLine();
                        break;
                    case "2":
                        Consultar();
                        break;
                    case "3":
                        Console.Write("Insira o ID da tarefa: ");
                        var id = Console.ReadLine();
                        Concluir(Int32.Parse(id));
                        Console.WriteLine();
                        Console.WriteLine("Resposta: Tarefa Concluída com sucesso!");
                        Console.WriteLine();
                        break;
                    default:
                        Console.WriteLine();
                        Console.WriteLine("Você saiu do programa!");
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
                Console.WriteLine();
                foreach(var task in lista)
                {
                    string status = task.Concluido ? "Concluído" : "Não concluído";
                    Console.WriteLine(" {0} - {1} | Status: {2}", task.Id, task.Texto.PadRight(25), status);
                    Console.WriteLine();
                }   
            }
            else 
            {
                Console.WriteLine("Não há tarefas Agendadas!");
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