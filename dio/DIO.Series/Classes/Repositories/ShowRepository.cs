using DIO.Series.Classes.Entities;
using DIO.Series.Interfaces;

namespace DIO.Series.Classes.Repositories
{
    public class ShowRepository : IRepository<Show>
    {
        private List<Show> shows = new List<Show>();

        public List<Show> FindAll()
        {
            return shows;
        }

        public Show FindById(int id)
        {
            return shows[id];
        }

        public void Insert(Show entity)
        {
            shows.Add(entity);
        }

        public int NextId()
        {
            return shows.Count;
        }

        public void RemoveById(int id)
        {
            shows[id].Delete();
        }

        public void Update(int id, Show entity)
        {
            shows[id] = entity;
        }
    }
}