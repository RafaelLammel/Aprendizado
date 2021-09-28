using System;
using System.Collections.Generic;
using System.Linq;
using System.IO;
using System.Text.Json;
using TodoApp.Models;

namespace TodoApp.Classes
{
    public class Todo
    {

        // Variáveis:
        private List<TodoTask> lista;
        private int idIncrement;
        private string dataPath = "data/data.json";

        // Método com a entrada de dados e a lista: 
        public Todo()
        {
            string json = File.ReadAllText(dataPath);
            
            lista = JsonSerializer.Deserialize<List<TodoTask>>(json);

            idIncrement = lista.Count > 0 ? lista.Max(x => x.Id)+1 : 1;

            string menu = "-1";

            Console.WriteLine("#######################################");
            Console.WriteLine("#####       ESCOLHA A OPÇÂO       #####");
            Console.WriteLine("#######################################");
            Console.WriteLine("#####                             #####");
            Console.WriteLine("#####        [1] - [Adicionar]    #####");
            Console.WriteLine("#####        [2] - [Consultar]    #####");
            Console.WriteLine("#####        [3] - [Concluir]     #####");
            Console.WriteLine("#####        [4] - [Deletar]      #####");
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
                        break;
                    case "4":
                        Console.Write("Insira o ID da tarefa: ");
                        var idDelete = Console.ReadLine();
                        DeletarTarefa(Int32.Parse(idDelete));
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

            SalvarDados();
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
            try
            {
                var task = lista.Where(x => x.Id == id).First();
                if(!task.Concluido)
                {
                    task.Concluido = true;
                    SalvarDados();
                    Console.WriteLine("\nResposta: Tarefa Concluída com sucesso!");
                }
                else
                    Console.WriteLine("\nEssa tarefa já foi concluida!");
            }
            catch
            {
                Console.WriteLine("\nEsse ID não pertence a nenhuma tarefa!");
            }
        }

        public void DeletarTarefa(int id)
        {
            try
            {
                var task = lista.Where(x => x.Id == id).First();
                lista.Remove(task);
                SalvarDados();
                Console.WriteLine("\nTarefa excluída com sucesso!");
            }
            catch
            {
                Console.WriteLine("\nEsse ID não pertence a nenhuma tarefa!");
            }
        }

        public void SalvarDados()
        {
            string json = JsonSerializer.Serialize(lista);
            File.WriteAllText(dataPath, json);
        }
    }

}