namespace TodoApp.Models
{
    public class TodoTask
    {
        //------------------------------------------------------------------------------  
        // Variáveis com GETs e SETs:

        public int Id { get; set; }
        public string Texto { get; set; }
        public bool Concluido { get; set; }

    }
}