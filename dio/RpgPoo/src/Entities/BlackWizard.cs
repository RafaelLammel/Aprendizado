namespace RpgPoo.src.Entities
{
    public class BlackWizard : Wizard
    {
        public BlackWizard(string name, int level, string heroType, int HP, int MP)
        : base(name, level, heroType, HP, MP)
        {
        }

        public string ForbiddenMagicAttack()
        {
            if(MP - 20 <= 0)
                return $"{this.Name} está sem pontos de magia para realizar um ataque multiplo!";
            
            if(HP - 20 <= 0)
                return $"{this.Name} não pode usar magia proibida, ou vai morrer!";

            MP -= 20;
            HP -= 20;
            return $"{this.Name} usou uma magia proibida! Causou mais dano, mas também causou dano a si mesmo no processo!";
        }
    }
}