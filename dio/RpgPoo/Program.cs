using RpgPoo.src.Entities;

namespace RpgPoo
{
    class Program
    {
        static void Main(string[] args)
        {
            Knight arus = new Knight("Arus", 23, "Knight", 50, 30);
            Ninja wedge = new Ninja("Wedge", 42, "Ninja", 50, 30);
            WhiteWizard jenica = new WhiteWizard("Jennica", 23, "White Wizard", 50, 30);
            BlackWizard topapa = new BlackWizard("Topapa", 42, "Black Wizard", 50, 30);

            Console.WriteLine(arus);
            Console.WriteLine(wedge);
            Console.WriteLine(jenica);
            Console.WriteLine(topapa);

            Console.WriteLine();

            Console.WriteLine(arus.Attack(2));
            Console.WriteLine(jenica.TakeDamage(40));
            
            Console.WriteLine();

            Console.WriteLine(wedge.SneakAttack());
            Console.WriteLine(arus.TakeDamage(30));

            Console.WriteLine();

            Console.WriteLine(topapa.ForbiddenMagicAttack());
            Console.WriteLine(wedge.TakeDamage(50));

            Console.WriteLine();

            Console.WriteLine(jenica.Attack(8));
            Console.WriteLine(topapa.TakeDamage(30));

            Console.WriteLine();

            Console.WriteLine(arus.Attack(5));
            
            Console.WriteLine();

            Console.WriteLine(jenica.Attack());
            Console.WriteLine(arus.TakeDamage(20));

            Console.WriteLine();
            Console.WriteLine("Jenica venceu!");
        }
    }
}