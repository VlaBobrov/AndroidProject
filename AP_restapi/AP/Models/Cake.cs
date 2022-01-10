using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
namespace AP.Models
{
    public class Cake
    {
        [Key]
        [Required]
        public int Id { get; set; }
        [Required]
        public string Name { get; set; }
        [Required]
        public int Weight { get; set; }
        [Required]
        public int Price { get; set; }
        [Required]
        public string Description { get; set; }
        [Required]
        public string PathToImage { get; set; }
        [Required]
        public IEnumerable<Ingredient> Ingredients { get; set; }
   
    }
}
