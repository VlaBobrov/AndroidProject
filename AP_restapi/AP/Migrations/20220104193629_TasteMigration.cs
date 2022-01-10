using Microsoft.EntityFrameworkCore.Migrations;

namespace AP.Migrations
{
    public partial class TasteMigration : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<string>(
                name: "Taste",
                table: "ingredients",
                type: "nvarchar(max)",
                nullable: false,
                defaultValue: "");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "Taste",
                table: "ingredients");
        }
    }
}
