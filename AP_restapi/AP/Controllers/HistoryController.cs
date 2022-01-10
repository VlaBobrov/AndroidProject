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
    public class HistoryController : ControllerBase
    {
        private readonly IApDbRepo _repo;
        public HistoryController(IApDbRepo apDbRepo)
        {
            _repo = apDbRepo;
        }
        // GET: CakeController
        [HttpGet]
        public ActionResult<IEnumerable<History>> GetAllHistories()
        {
   
            return Ok(_repo.GetHistories().OrderBy(d => d.Done));
        }
        [HttpGet("{id}")]
        public ActionResult<History> GetHistory(int id)
        {
            return Ok(_repo.GetHistoryById(id));
        }

        // Post: Add Cake
        [HttpPost]
        public ActionResult Create(History history)
        {
            _repo.AddHistory(history);
            return Ok(history);
        }
        [HttpPut]
        // GET: CakeController/Edit/5
        public ActionResult Edit(History history)
        {
            _repo.UpdateHistory(history);
            return Ok(history);
        }
        [HttpDelete("{id}")]
        public ActionResult Delete(int id)
        {
            _repo.DeleteHistory(id);
            return Ok(_repo.GetHistories());
        }
    }
}
