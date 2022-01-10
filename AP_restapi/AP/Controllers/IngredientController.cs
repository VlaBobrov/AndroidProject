using AP.Data;
using AP.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace AP.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class IngredientController : Controller
    {
        private readonly IApDbRepo _repo;
        public IngredientController(IApDbRepo apDbRepo)
        {
            _repo = apDbRepo;
        }
        // GET: CakeController
        [HttpGet]
        public ActionResult<IEnumerable<Ingredient>> GetAllIngredients()
        {
            return Ok(_repo.GetIngredients());
        }
        [HttpGet("{id}")]
        public ActionResult<Ingredient> GetIngredient(int id)
        {
            return Ok(_repo.GetIngredientById(id));
        }

        // Post: Add Cake
        [HttpPost]
        public ActionResult Create(Ingredient ingredient)
        {
            _repo.AddIngredient(ingredient);
            return Ok(ingredient);
        }
        [HttpPut]
        // GET: CakeController/Edit/5
        public ActionResult Edit(Ingredient ingredient)
        {
            _repo.UpdateIngredient(ingredient);
            return Ok(ingredient);
        }
        [HttpDelete("{id}")]
        public ActionResult Delete(int id)
        {
            _repo.DeleteIngredient(id);
            return Ok(_repo.GetIngredients());
        }
    }
}
