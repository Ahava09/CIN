using System;
using System.Data.SqlClient;

namespace FoncierApi.Models
{
    public class Borne
    {
        public int Id { get; set; }
        public int Id_foncier { get; set; }
        public double Latitude { get; set; }
        public double Longitude { get; set; }

        public Borne(){}
        
        public Borne(int id, int id_foncier, double latitude ,double longitude)
        {
            Id = id;
            Id_foncier = id_foncier;
            Latitude = latitude;
            Longitude = longitude;
        }

        public static List<Borne> GetAllBorne(int id_foncier)
        {
            List<Borne> bornes = new List<Borne>();

            SqlConnection connection = ConnectDB.GetConnection();
            connection.Open();
            string query = "SELECT * FROM dbo.Borne where id_foncier ="+id_foncier;
            Console.WriteLine(query);


                using (SqlCommand command = new SqlCommand(query, connection))
                {
                    using (SqlDataReader reader = command.ExecuteReader())
                    {
                        while (reader.Read())
                        {
                            Borne borne = new Borne(
                                reader.GetInt32(0), // id
                                reader.GetInt32(1), // id_foncier
                                reader.GetDouble(2), // lat
                                reader.GetDouble(3) // long

                            );
                            bornes.Add(borne);
                        }
                    }
                }
            
                connection.Close();
            return bornes;
        }


        public void Insert()
        {
            double latitude = this.Latitude;
            double longitude = this.Longitude;
            SqlConnection connection = ConnectDB.GetConnection();
            connection.Open();
            Console.WriteLine("Connection opened successfully.");
            string query = $"INSERT INTO dbo.Borne (id_foncier, latitude, longitude) " +
                        $"VALUES ({this.Id_foncier}, {Foncier.ConvertStringToDoubleString(latitude.ToString())}, {Foncier.ConvertStringToDoubleString(longitude.ToString())}) ";

            // Affichez la requête SQL avec les valeurs dans la console
            Console.WriteLine("Requête SQL avec valeurs : " + query);

            using (SqlCommand command = new SqlCommand(query, connection))
            {
                command.ExecuteNonQuery();
            }

            connection.Close();
        }

        
    }

}