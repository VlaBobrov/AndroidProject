using AP.Models;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace AP.Data
{
    public class ApDbContext: DbContext
    {
        public ApDbContext(DbContextOptions options): base(options)
        {
        }

        public DbSet<Cake> cakes { get; set; }
        public DbSet<Ingredient> ingredients { get; set; }
        public DbSet<History> histories { get; set; }

    }
}
