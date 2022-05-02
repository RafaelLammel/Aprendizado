namespace RpgPoo.src.Entities
{
    public class Knight : Hero
    {
        public Knight(string name, int level, string heroType, int HP, int MP)
        : base(name, level, heroType, HP, MP)
        {}

        public string Attack(int numberOfAttacks)
        {
            if(this.MP - 20 <= 0)
                return $"{this.Name} está sem pontos de magia para realizar um ataque multiplo!";
            
            this.MP -= 20;
            return $"{this.Name} Atacou {numberOfAttacks} vezes com sua espada!";
        }
    }
}