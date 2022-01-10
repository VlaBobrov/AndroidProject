using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace AP.Models
{
    public class History
    {
        [Key]
        [Required]
        public int Id { get; set; }
        [Required]
        public int CakeId { get; set; }
        [Required]
        public string Date { get; set; }
        [Required]
        public bool Done { get; set; }
    }
}
