using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace LivrariaDotnet.Models
{
    [Table("livro")]
    public class Book
    {
        [Column("id")]
        [Key]
        public int Id { get; set; }

        [Column("codigo")]
        [Required(ErrorMessage="Este campo é obrigatório")]
        public int Codigo { get; set; }

        [Column("paginas")]
        [Required(ErrorMessage="Este campo é obrigatório")]
        public int Paginas { get; set; }
        
        [Column("nome")]
        [MaxLength(50)]
        [Required(ErrorMessage="Este campo é obrigatório")]
        public string Nome { get; set; }
        
        [Column("preco")]
        [Required(ErrorMessage="Este campo é obrigatório")]
        public decimal Preco { get; set; }

        [Column("id_categoria")]
        public int CategoriaId { get; set; }
        public Category Categoria { get; set; }
    }
}