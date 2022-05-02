namespace RpgPoo.src.Entities
{
    public abstract class Hero
    {
        public Hero(string name, int level, string heroType, int HP, int MP)
        {
            this.Name = name;
            this.Level = level;
            this.HeroType = heroType;
            this.HP = HP;
            this.MP = MP;
        }

        protected string Name;
        protected int Level;
        protected string HeroType;
        protected int HP;
        protected int MP;

        public override string ToString()
        {
            return $"{this.Name} {this.Level} {this.HeroType}";
        }

        public virtual string Attack()
        {
            return $"{this.Name} Atacou com sua espada";
        }

        public string TakeDamage(int damage)
        {
            string damageMessage = $"{this.Name} Tomou {damage} de dano!";
            
            this.HP = this.HP - damage;

            if(this.HP <= 0)
                damageMessage += $" - {this.Name} foi nocauteado!";
            
            return damageMessage;
        }
    }
}