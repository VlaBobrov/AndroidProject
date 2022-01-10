using Microsoft.EntityFrameworkCore.Migrations;

namespace AP.Migrations
{
    public partial class DoneHistory : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_cakes_histories_HistoryId",
                table: "cakes");

            migrationBuilder.DropIndex(
                name: "IX_cakes_HistoryId",
                table: "cakes");

            migrationBuilder.DropColumn(
                name: "HistoryId",
                table: "cakes");

            migrationBuilder.RenameColumn(
                name: "InProgress",
                table: "histories",
                newName: "Done");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.RenameColumn(
                name: "Done",
                table: "histories",
                newName: "InProgress");

            migrationBuilder.AddColumn<int>(
                name: "HistoryId",
                table: "cakes",
                type: "int",
                nullable: true);

            migrationBuilder.CreateIndex(
                name: "IX_cakes_HistoryId",
                table: "cakes",
                column: "HistoryId");

            migrationBuilder.AddForeignKey(
                name: "FK_cakes_histories_HistoryId",
                table: "cakes",
                column: "HistoryId",
                principalTable: "histories",
                principalColumn: "Id",
                onDelete: ReferentialAction.Restrict);
        }
    }
}
