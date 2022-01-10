using Microsoft.EntityFrameworkCore.Migrations;

namespace AP.Migrations
{
    public partial class CakeIdIngr : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_ingredients_cakes_CakeId",
                table: "ingredients");

            migrationBuilder.AlterColumn<int>(
                name: "CakeId",
                table: "ingredients",
                type: "int",
                nullable: false,
                defaultValue: 0,
                oldClrType: typeof(int),
                oldType: "int",
                oldNullable: true);

            migrationBuilder.AddForeignKey(
                name: "FK_ingredients_cakes_CakeId",
                table: "ingredients",
                column: "CakeId",
                principalTable: "cakes",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_ingredients_cakes_CakeId",
                table: "ingredients");

            migrationBuilder.AlterColumn<int>(
                name: "CakeId",
                table: "ingredients",
                type: "int",
                nullable: true,
                oldClrType: typeof(int),
                oldType: "int");

            migrationBuilder.AddForeignKey(
                name: "FK_ingredients_cakes_CakeId",
                table: "ingredients",
                column: "CakeId",
                principalTable: "cakes",
                principalColumn: "Id",
                onDelete: ReferentialAction.Restrict);
        }
    }
}
