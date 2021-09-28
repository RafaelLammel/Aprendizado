using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace LivrariaDotnet.Models
{
    [Table("categoria")]
    public class Category
    {
        
        [Column("id")]
        [Key]
        public int Id { get; set; }
        
        [Column("nome")]
        [MaxLength(20)]
        [Required(ErrorMessage="Este campo é obrigatório")]
        public string Nome { get; set; }

    }
}