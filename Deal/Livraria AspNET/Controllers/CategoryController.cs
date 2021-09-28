using System.Collections.Generic;
using System.Threading.Tasks;
using LivrariaDotnet.Data;
using LivrariaDotnet.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System.Linq;

namespace LivrariaDotnet.Controllers
{
    [ApiController]
    [Route("categoria")]
    public class CategoryController : ControllerBase
    {   
        /// <summary>
        /// Retorna uma lista com todos as categiruas cadastradas 
        /// </summary>
        [HttpGet]
        [Route("")]
        public async Task<ActionResult<List<Category>>> Get([FromServices] DataContext context, [FromQuery] string ordenarPor)
        {
            List<Category> c = await context.Categories.ToListAsync();
            switch(ordenarPor)
            {
                case "nome":
                    return Ok(c.OrderBy(x => x.Nome));
                default:
                    return Ok(c.OrderBy(x => x.Id));
            }
        }

        /// <summary>
        /// Retorna uma categoria com base no ID fornecido 
        /// </summary>
        [HttpGet]
        [Route("{id:int}")]
        public async Task<ActionResult<Category>> GetById([FromServices] DataContext context, int id)
        {
            var c = await context.Categories.FirstOrDefaultAsync(x => x.Id == id);
            if(c != null)
            {
                return Ok(c);
            }
            return BadRequest("Nenhuma categoria com este ID foi encontrado");
        }

        /// <summary>
        /// Cadastra uma nova categoria
        /// </summary>
        [HttpPost]
        [Route("")]
        public async Task<ActionResult<Category>> Post([FromServices] DataContext context, [FromBody] Category category)
        {
            if(ModelState.IsValid)
            {
                context.Categories.Add(category);
                await context.SaveChangesAsync();
                return Created($"/categoria/{category.Id}",category);
            }
            return BadRequest(ModelState);
        }

        /// <summary>
        /// Altera a categoria com o ID fornecido
        /// </summary>
        [HttpPut]
        [Route("{id:int}")]
        public async Task<ActionResult<Category>> Put([FromServices] DataContext context, [FromBody] Category category, int id)
        {
            if(ModelState.IsValid)
            {
                var c = await context.Categories.FirstOrDefaultAsync(x => x.Id == id);
                if(c != null)
                {
                    context.Entry(c).CurrentValues.SetValues(category);
                    await context.SaveChangesAsync();
                    return Ok(c);
                }
                return BadRequest("Nenhums cateogria com este ID foi encontrado");
            }
            return BadRequest(ModelState);
        }

        /// <summary>
        /// Exclui a categoria com o ID fornecido 
        /// </summary>
        [HttpDelete]
        [Route("{id:int}")]
        public async Task<ActionResult> Delete([FromServices] DataContext context, int id)
        {
            var c = await context.Categories.FirstOrDefaultAsync(x => x.Id == id);
            if(c != null)
            {
                context.Categories.Remove(c);
                await context.SaveChangesAsync();
                return Ok();
            }
            return BadRequest("Nenhuma categoria com este ID foi encontrado");
        }
    }
}