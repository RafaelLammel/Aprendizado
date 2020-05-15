using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;
using LivrariaDotnet.Data;
using LivrariaDotnet.Models;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using System;

namespace LivrariaDotnet.Controllers
{
    [ApiController]
    [Route("livro")]
    public class BookController : ControllerBase
    {
        [HttpGet]
        [Route("")]
        public async Task<ActionResult<List<Book>>> Get([FromServices] DataContext context)
        {
            var b = await context.Books.Include(x => x.Categoria).ToListAsync();
            return Ok(b);
        }

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