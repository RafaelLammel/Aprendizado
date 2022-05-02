namespace RpgPoo.src.Entities
{
    public abstract class Wizard : Hero
    {
        public Wizard(string name, int level, string heroType, int HP, int MP)
        : base(name, level, heroType, HP, MP)
        {}

        public override string Attack()
        {            
            return $"{this.Name} Lançou uma magia";
        }

        public string Attack(int bonus)
        {
            if(MP - 20 <= 0)
                return $"{this.Name} está sem pontos de magia para realizar um ataque multiplo!";
            
            MP -= 20;

            if(bonus > 6)
            {
                return $"{this.Name} Lançou uma magia super efetiva com bonus de ataque de {bonus}";
            }

            return $"{this.Name} Lançou uma magia com força fraca com bonus de ataque de {bonus}";
        }
    }
}