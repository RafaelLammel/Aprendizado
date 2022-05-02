namespace DIO.Series.Interfaces
{
    public interface IRepository<T>
    {
        List<T> FindAll();
        T FindById(int id);
        void Insert(T entity);
        void RemoveById(int id);
        void Update(int id, T entity);
        int NextId();
    }
}