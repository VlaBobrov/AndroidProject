using AP.Models;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace AP.Data
{
    public class ApDbRepo : IApDbRepo
    {
        private readonly ApDbContext _context;

        public ApDbRepo(ApDbContext dbContext)
        {
            _context = dbContext;
        }
        public void AddCake(Cake cake)
        {
            if(cake == null)
            {
                throw new ArgumentNullException("cake");
            }
            _context.cakes.Add(cake);
            _context.SaveChanges();
     
        }

        public IEnumerable<Cake> GetAllCakesBy(IEnumerable<Ingredient> ingredients)
        {
            var cakes = GetCakes();
            var ingr = GetIngredients();
            var cakeIds = new List<int>();
            foreach (var item in ingredients)
            {
                var srcby = ingr.Where(ing => ing.Type == item.Type && ing.Name == item.Name);
                foreach (var ite in srcby)
                {
                    cakeIds.Add(ite.CakeId);
                }
            }

            var cakesBy = cakes.Where(c => cakeIds.Contains(c.Id)).ToList();

            return cakesBy;
        } 
        public IQueryable<Cake> GetCakes()
        {
            return _context.cakes;
        }
        public Cake GetCakeById(int id)
        {
            return _context.cakes.FirstOrDefault(t => t.Id == id);
        }

        public void UpdateCake(Cake cake)
        {
            if (cake == null)
            {
                throw new ArgumentNullException("cake");
            }
            _context.cakes.Update(cake);
            _context.SaveChanges();
        }


        public void DeleteCake(int id)
        {
            var cake = this.GetCakeById(id);
            if (cake == null)
            {
                throw new ArgumentNullException("cake");
            }
            _context.cakes.Remove(cake);
            _context.SaveChanges();
        }
/// <summary>
/// /////////////////////////////////////////////////////////////////////////////////////////////////////////
/// </summary>
/// <param name="history"></param>
        public void AddHistory(History history)
        {
            if (history == null)
            {
                throw new ArgumentNullException("history");
            }
            _context.histories.Add(history);
            _context.SaveChanges();
        }


        public IQueryable<History> GetHistories()
        {
            return _context.histories;
        }

        public History GetHistoryById(int id)
        {
            return _context.histories.FirstOrDefault(t => t.Id == id);
        }

        public void UpdateHistory(History history)
        {
            if (history == null)
            {
                throw new ArgumentNullException("history");
            }
            _context.histories.Update(history);
            _context.SaveChanges();
        }

        public void DeleteHistory(int id)
        {
            var history = this.GetHistoryById(id);
            if (history == null)
            {
                throw new ArgumentNullException("history");
            }
            _context.histories.Remove(history);
            _context.SaveChanges();
        }
        /// <summary>
        /// /////////////////////////////////////////////////////////////////////////////////////////////////////////
        /// </summary>
        /// <param name="ingredient"></param>
        public void AddIngredient(Ingredient ingredient)
        {
            if (ingredient == null)
            {
                throw new ArgumentNullException("ingredient");
            }
            _context.ingredients.Add(ingredient);
            _context.SaveChanges();
        }
 
        public void DeleteIngredient(int id)
        {
            var ingr  = this.GetIngredientById(id);
            if (ingr == null)
            {
                throw new ArgumentNullException("ingr");
            }
            _context.ingredients.Remove(ingr);
            _context.SaveChanges();
        }

        public Ingredient GetIngredientById(int id)
        {
            return _context.ingredients.FirstOrDefault(t => t.Id == id);
        }

        public IQueryable<Ingredient> GetIngredients()
        {
            return _context.ingredients;
        }


        public void UpdateIngredient(Ingredient ingredient)
        {
            if (ingredient == null)
            {
                throw new ArgumentNullException("ingredient");
            }
            _context.ingredients.Update(ingredient);
            _context.SaveChanges();
        }

    }
}
