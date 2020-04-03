using System.Collections.Generic;
using TodoAspNET.Models;

namespace TodoAspNET.ViewModels
{
    public class IndexViewModel
    {
        public List<Tarefa> Tarefas { get; set; }
        public Tarefa TarefaToSend { get; set; }

    }
}