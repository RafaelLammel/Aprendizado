using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;
using LivrariaDotnet.Data;
using LivrariaDotnet.Models;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using System.Linq;

namespace LivrariaDotnet.Controllers
{
    [ApiController]
    [Route("livro")]
    public class BookController : ControllerBase
    {
        /// <summary>
        /// Retorna uma lista com todos os livros cadastrados 
        /// </summary>
        [HttpGet]
        [Route("")]
        public async Task<ActionResult<List<Book>>> Get([FromServices] DataContext context, [FromQuery] string ordenarPor)
        {
            List<Book> b = await context.Books.Include(x => x.Categoria).ToListAsync();
            switch(ordenarPor)
            {
                case "preco":
                    return Ok(b.OrderBy(x => x.Preco));
                case "paginas":
                    return Ok(b.OrderBy(x => x.Paginas));
                default:
                    return Ok(b.OrderBy(x => x.Id));
            }
        }

        /// <summary>
        /// Retorna um livro com base no ID fornecido 
        /// </summary>
        [HttpGet]
        [Route("{id:int}")]
        public async Task<ActionResult<Book>> GetById([FromServices] DataContext context, int id)
        {
            var b = await context.Books.Include(x => x.Categoria)
                .FirstOrDefaultAsync(x => x.Id == id);

            if(b != null)
            {
                return Ok(b);
            }
            return BadRequest("Nenhum livro com este ID foi encontrado");
        }

        /// <summary>
        /// Cadastra um novo livro
        /// </summary>
        [HttpPost]
        [Route("")]
        public async Task<ActionResult<Book>> Post([FromServices] DataContext context, [FromBody] Book book)
        {
            if(ModelState.IsValid)
            {
                context.Books.Add(book);
                await context.SaveChangesAsync();
                return Created($"/livro/{book.Id}", book);
            }
            return BadRequest(ModelState);
        }

        /// <summary>
        /// Altera o livro com o ID fornecido
        /// </summary>
        [HttpPut]
        [Route("{id:int}")]
        public async Task<ActionResult<Book>> Put([FromServices] DataContext context, [FromBody] Book book, int id)
        {
            if(ModelState.IsValid)
            {
                var b = await context.Books.FirstOrDefaultAsync(x => x.Id == id);
                if(b != null)
                {
                    context.Entry(b).CurrentValues.SetValues(book);
                    await context.SaveChangesAsync();
                    return Ok(b);
                }
                return BadRequest("Nenhum livro com este ID foi encontrado");
            }
            return BadRequest(ModelState);
        }

        /// <summary>
        /// Exclui o livro com o ID fornecido 
        /// </summary>
        [HttpDelete]
        [Route("{id:int}")]
        public async Task<ActionResult> Delete([FromServices] DataContext context, int id)
        {
            var b = await context.Books.FirstOrDefaultAsync(x => x.Id == id);
            if(b != null)
            {
                context.Books.Remove(b);
                await context.SaveChangesAsync();
                return Ok();
            }
            return BadRequest("Nenhum livro com este ID foi encontrado");
        }
    }
}