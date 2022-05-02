using DIO.Series.Enums;

namespace DIO.Series.Classes.Entities
{
    public class Show : BaseEntity
    {
        private Genre Genre { get; set; }
        private string Title { get; set; }
        private string Description { get; set; }
        private int Year { get; set; }
        private bool Deleted { get; set; }

        public Show(int id, Genre genre, string title, string description, int year)
        {
            this.Id = id;
            this.Genre = genre;
            this.Title = title;
            this.Description = description;
            this.Year = year;
            this.Deleted = false;
        }

        public override string ToString()
        {
            return $"Genero: {this.Genre}\nTitulo: {this.Title}\nDescricao: {this.Description}\nAno: {this.Year}\nExcluido: {this.Deleted}";
        }

        public string getTitle()
        {
            return this.Title;
        }

        public int getId()
        {
            return this.Id;
        }

        public void Delete()
        {
            this.Deleted = true;
        }

        public bool getDeleted()
        {
            return this.Deleted;
        }
    }
}