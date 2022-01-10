using AP.Models;
using System.Collections.Generic;
using System.Linq;

namespace AP.Data
{
    public interface IApDbRepo
    {
        public IQueryable<Cake> GetCakes();
        public Cake GetCakeById(int id);
        public void AddCake(Cake cake);
        public void UpdateCake(Cake cake);
        public void DeleteCake(int id);
        public IEnumerable<Cake> GetAllCakesBy(IEnumerable<Ingredient> ingredients);


        public IQueryable<History> GetHistories();
        public History GetHistoryById(int id);
        public void AddHistory(History history);
        public void UpdateHistory(History history);
        public void DeleteHistory(int id);

        public IQueryable<Ingredient> GetIngredients();
        public Ingredient GetIngredientById(int id);
        public void AddIngredient(Ingredient ingredient);
        public void UpdateIngredient(Ingredient ingredient);
        public void DeleteIngredient(int id);
    }
}