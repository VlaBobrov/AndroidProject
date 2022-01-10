using System.ComponentModel.DataAnnotations;


namespace AP.Models
{
    public class Ingredient
    {
        [Key]
        [Required]
        public int Id { get; set; }
        [Required]
        public string Type { get; set; }
        [Required]
        public string Name { get; set; }
        public int CakeId { get; set; }
    }
}
