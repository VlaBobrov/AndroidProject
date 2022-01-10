using Microsoft.EntityFrameworkCore.Migrations;

namespace AP.Migrations
{
    public partial class InProgressMigration : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "Taste",
                table: "ingredients");

            migrationBuilder.AddColumn<int>(
                name: "CakeId",
                table: "histories",
                type: "int",
                nullable: false,
                defaultValue: 0);

            migrationBuilder.AddColumn<bool>(
                name: "InProgress",
                table: "histories",
                type: "bit",
                nullable: false,
                defaultValue: false);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "CakeId",
                table: "histories");

            migrationBuilder.DropColumn(
                name: "InProgress",
                table: "histories");

            migrationBuilder.AddColumn<string>(
                name: "Taste",
                table: "ingredients",
                type: "nvarchar(max)",
                nullable: false,
                defaultValue: "");
        }
    }
}
