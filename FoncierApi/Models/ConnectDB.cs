using System.Data.SqlClient;

namespace FoncierApi.Models
{

    public class ConnectDB
    {
        private static string _connectionString = @"Data Source=(localdb)\MSSQLLocalDB;Database=Land;Trusted_Connection=true;trustServerCertificate=true";

        public static SqlConnection GetConnection()
        {
            SqlConnection connection = new SqlConnection(_connectionString);
            return connection;
        }

    }

}