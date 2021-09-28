using System.ComponentModel.DataAnnotations.Schema;

namespace TodoAspNET.Models
{
    public class Tarefa
    {

        public int Id { get; set; }
        public string Descricao { get; set; }
        public bool Status { get; set; }
        
        [ForeignKey("Lista")]
        public int IdLista { get; set; }
        
    }
}