using System.ComponentModel.DataAnnotations.Schema;

namespace TodoAspNET.Models
{
    public class Lista
    {

        public int Id { get; set; }
        public string Nome { get; set; }
        
        [ForeignKey("AspNetUsers")]
        public int IdUsuario { get; set; }
        
    }
}