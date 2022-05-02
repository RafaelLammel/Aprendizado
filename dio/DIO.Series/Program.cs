using DIO.Series.Classes.Entities;
using DIO.Series.Classes.Repositories;
using DIO.Series.Enums;

namespace DIO.Series
{
    class Program
    {
        static ShowRepository repository = new ShowRepository();

        static void Main(string[] args)
        {
            string userOption = GetUserOption();

            while(userOption.ToUpper() != "X")
            {
                switch(userOption)
                {
                    case "1":
                        GetShows();
                        break;
                    case "2":
                        AddShow();
                        break;
                    case "3":
                        UpdateShow();
                        break;
                    case "4":
                        DeleteShow();
                        break;
                    case "5":
                        GetShow();
                        break;
                    case "C":
                        Console.Clear();
                        break;
                    default:
                        throw new ArgumentOutOfRangeException();
                }

                userOption = GetUserOption();
            }
        }

        private static void GetShows()
        {
            Console.WriteLine("Listar séries");

            var shows = repository.FindAll();

            if(shows.Count == 0)
            {
                Console.WriteLine("Nenhuma série cadastrada.");
                return;
            }

            foreach (var show in shows)
            {
                Console.WriteLine($"#ID {show.getId()} - {show.getTitle()}" + (show.getDeleted() ? " **Excluido**" : ""));
            }
        }

        private static void GetShow()
        {
            Console.WriteLine("Digite o id da série: ");
            int id = int.Parse(Console.ReadLine());

            var show = repository.FindById(id);

            Console.WriteLine(show);
        }

        private static void AddShow()
        {
            Console.WriteLine("Inserir nova série");

            var show = ShowForm();

            repository.Insert(show);
        }

        private static void UpdateShow()
        {
            Console.WriteLine("Digite o id da Série: ");
            int id = int.Parse(Console.ReadLine());

            var show = ShowForm(id);

            repository.Update(id, show);
        }

        private static void DeleteShow()
        {
            Console.WriteLine("Digite o id da Série: ");
            int id = int.Parse(Console.ReadLine());

            repository.RemoveById(id);
        }

        private static Show ShowForm(int id = -1)
        {
            foreach (int i in Enum.GetValues(typeof(Genre)))
            {
                Console.WriteLine($"{i} - {Enum.GetName(typeof(Genre), i)}");
            }
            Console.Write("Digite o genêro entre as opções acima: ");
            int genre = int.Parse(Console.ReadLine());

            Console.Write("Digite o Titulo da Série: ");
            string title = Console.ReadLine();

            Console.Write("Digite o Ano de Início da Série: ");
            int year = int.Parse(Console.ReadLine());

            Console.Write("Digite a Descrição da Série: ");
            string description = Console.ReadLine();

            return new Show(id == -1 ? repository.NextId() : id, (Genre)genre, title, description, year);
        }

        private static string GetUserOption()
        {
            Console.WriteLine();
            Console.WriteLine("DIO Séries a seu dispor!!!");
            Console.WriteLine("Informe a opção desejada:");

            Console.WriteLine("1 - Listar Séries");
            Console.WriteLine("2 - Inserir nova série");
            Console.WriteLine("3 - Atualizar série");
            Console.WriteLine("4 - Excluir série");
            Console.WriteLine("5 - Visualizar série");
            Console.WriteLine("C - Limpar Tela");
            Console.WriteLine("X - Sair");
            Console.WriteLine();

            string userOption = Console.ReadLine().ToUpper();
            Console.WriteLine();
            return userOption;
        }
    }
}