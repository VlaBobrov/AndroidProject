using AP.Data;
using AP.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using static System.Net.Mime.MediaTypeNames;

namespace AP.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class CakeController : ControllerBase
    {
        private readonly IApDbRepo _repo;
        public CakeController(IApDbRepo apDbRepo)
        {
            _repo = apDbRepo;
        }
        // GET: CakeController
        [HttpGet]
        public ActionResult<IEnumerable<Cake>> GetAllCakes()
        {
            var cakes = _repo.GetCakes();

            foreach (var item in cakes)
            {
                byte[] imageArray = System.IO.File.ReadAllBytes(item.PathToImage);
                item.PathToImage = Convert.ToBase64String(imageArray);

            }
            return Ok(_repo.GetCakes());
        }
        [HttpPost("filter")]
        public ActionResult<IEnumerable<Cake>> GetAllCakesBy(IEnumerable<Ingredient> ingredients)
        {
            var cakes = _repo.GetAllCakesBy(ingredients);
            foreach (var item in cakes)
            {
                byte[] imageArray = System.IO.File.ReadAllBytes(item.PathToImage);
                item.PathToImage = Convert.ToBase64String(imageArray);

            }

            return Ok(cakes);
        }
        [HttpGet("{id}")]
        public ActionResult<Cake> GetCake(int id)
        {
            var item = _repo.GetCakeById(id);
            byte[] imageArray = System.IO.File.ReadAllBytes(item.PathToImage);
            item.PathToImage = Convert.ToBase64String(imageArray);
            return Ok(item);
        }

        // Post: Add Cake
        [HttpPost]
        public ActionResult Create(Cake cake)
        {
            _repo.AddCake(cake);
            return Ok(cake);
        }
        [HttpPut]
        // GET: CakeController/Edit/5
        public ActionResult Edit(Cake cake)
        {
            _repo.UpdateCake(cake);
            return Ok(cake);
        }
        [HttpDelete("{id}")]
        public ActionResult Delete(int id)
        {
            _repo.DeleteCake(id);
            return Ok(_repo.GetCakes());
        }
    }
}
