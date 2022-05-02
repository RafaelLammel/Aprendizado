namespace RpgPoo.src.Entities
{
    public class Ninja : Hero
    {
        public Ninja(string name, int level, string heroType, int HP, int MP)
        : base(name, level, heroType, HP, MP)
        {
        }

        public override string Attack()
        {
            return $"{this.Name} Atacou com sua adaga";
        }

        public string SneakAttack()
        {
            if(this.MP - 20 <= 0)
                return $"{this.Name} está sem pontos de magia para realizar um ataque multiplo!";
            
            this.MP -= 20;
            return $"{this.Name} Atacou furtivamente, causando mais dano!";
        }
    }
}