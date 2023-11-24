using System;
using System.Data.SqlClient;
using System.Globalization;

namespace FoncierApi.Models
{
    public class Foncier
    {
        public int id {get; set;}
        public string partielle {get; set;}
        public string heritage {get; set;}
        public string id_cin {get; set;}
        public string localisation {get; set;}
        public double prix {get; set;}
        public double superficie {get; set;}

        public Foncier() { }

        public Foncier(int id, string id_cin, string partielle, string localisation, double prix, int heritage, double superficie)
        {
            this.SetId(id);
            this.SetIdCin(id_cin);
            this.SetHeritage(heritage);
            this.SetLocalisation(localisation);
            this.SetPartielle(partielle);
            this.SetPrix(prix);
            this.SetSuperficie(superficie);
        }
        

        public Foncier( string id_cin, string partielle, string localisation, double prix, int heritage, double superficie)
        {
            this.SetIdCin(id_cin);
            this.SetHeritage(heritage);
            this.SetHeritage(heritage);
            this.SetLocalisation(localisation);
            this.SetPartielle(partielle);
            this.SetPrix(prix);
            this.SetSuperficie(superficie);
        }

        
        public Foncier( string id_cin, string partielle, string localisation, double prix, string heritage, double superficie)
        {
            this.SetIdCin(id_cin);
            this.SetHeritage(heritage);
            this.SetHeritage(heritage);
            this.SetLocalisation(localisation);
            this.SetPartielle(partielle);
            this.SetPrix(prix);
            this.SetSuperficie(superficie);
        }


        // Méthodes Setter
        public void SetId(int id)
        {
            this.id = id;
        }

        public void SetPartielle(string partielle)
        {
            this.partielle = partielle;
        }

        public void SetHeritage(int heritage)
        {
            this.heritage = (heritage == 1) ? "OUI" : "NON";
        }
        
        public void SetHeritage(string heritage)
        {
            this.heritage = heritage ;
        }

        public void SetIdCin(string id_cin)
        {
            this.id_cin = id_cin;
        }

        public void SetLocalisation(string localisation)
        {
            this.localisation = localisation;
        }

        public void SetPrix(double prix)
        {
            this.prix = prix;
        }

        public void SetSuperficie(double superficie)
        {
            this.superficie = superficie;
        }

        // Méthodes Getter
        public int GetId()
        {
            return id;
        }

        public string GetPartielle()
        {
            return partielle;
        }

        public string GetHeritage()
        {
            return heritage;
        }

        public string GetIdCin()
        {
            return id_cin;
        }

        public string GetLocalisation()
        {
            return localisation;
        }

        public double GetPrix()
        {
            return prix;
        }

        public double GetSuperficie()
        {
            return superficie;
        }


        public static List<Foncier> GetAllFonciers(string id_cin)
        {
            List<Foncier> fonciers = new List<Foncier>();

            SqlConnection connection = ConnectDB.GetConnection();
            connection.Open();
            Console.WriteLine("Connection opened successfully.");
            string query = "SELECT * FROM dbo.Foncier where id_cin ='"+id_cin+"'";
            Console.WriteLine(query);

                using (SqlCommand command = new SqlCommand(query, connection))
                {
                    using (SqlDataReader reader = command.ExecuteReader())
                    {
                        while (reader.Read())
                        {
                            Foncier foncier = new Foncier(
                                reader.GetInt32(0), // id
                                reader.GetString(1), // id_cin
                                reader.GetString(2), // partielle
                                reader.GetString(3), // local
                                reader.GetDouble(4), // prix
                                reader.GetInt32(5), // heritage
                                reader.GetDouble(6) // superficie
                            );
                            Console.WriteLine("ID: " + foncier.GetId());
                            Console.WriteLine("Partielle: " + foncier.GetPartielle());
                            Console.WriteLine("Heritage: " + foncier.GetHeritage());
                            Console.WriteLine("ID Cin: " + foncier.GetIdCin());
                            Console.WriteLine("Localisation: " + foncier.GetLocalisation());
                            Console.WriteLine("Prix: " + foncier.GetPrix());
                            Console.WriteLine("Superficie: " + foncier.GetSuperficie());

                            fonciers.Add(foncier);
                        }
                    }
                }
            
                connection.Close();
            return fonciers;
        }

        public static string ConvertStringToDoubleString(string input)
        {
            CultureInfo culture = CultureInfo.InvariantCulture;
            
            if (double.TryParse(input.Replace(",", "."), NumberStyles.Float, culture, out double result))
            {
                return result.ToString(culture); // Convertit le double en string avec la culture Invariant
            }
            else
            {
                // En cas d'erreur, vous pouvez gérer l'exception ou renvoyer une valeur par défaut
                return "0.0"; // Ou toute autre valeur par défaut sous forme de string
            }
        }



        public void Insert()
        {
            double prix = this.prix;
            double superficie = this.superficie;
            SqlConnection connection = ConnectDB.GetConnection();
            connection.Open();
            Console.WriteLine("Connection opened successfully.");
            int heritage = this.heritage.Equals("OUI", StringComparison.OrdinalIgnoreCase) ? 1 : 0;
            string query = $"INSERT INTO dbo.Foncier (id_cin, partielle, localisation, prix, heritage, superficie) " +
                        $"VALUES ('{this.id_cin}', '{this.partielle}', '{this.localisation}', {ConvertStringToDoubleString(prix.ToString())}, " +
                        $"{heritage}, {ConvertStringToDoubleString(superficie.ToString())})";

            // Affichez la requête SQL avec les valeurs dans la console
            Console.WriteLine("Requête SQL avec valeurs : " + query);

            using (SqlCommand command = new SqlCommand(query, connection))
            {
                command.ExecuteNonQuery();
            }

            connection.Close();
        }

        public static Foncier getLastFoncier(String id_cin) {
            Foncier foncier = new Foncier();

            SqlConnection connection = ConnectDB.GetConnection();
            connection.Open();
            Console.WriteLine("Connection opened successfully.");
            string query = "SELECT * FROM dbo.Foncier where id_cin ='"+id_cin+"' order by id desc";

                using (SqlCommand command = new SqlCommand(query, connection))
                {
                    using (SqlDataReader reader = command.ExecuteReader())
                    {
                        while (reader.Read())
                        {
                            foncier.id=reader.GetInt32(0);
                            foncier.id_cin=reader.GetString(1);
                            foncier.partielle=reader.GetString(2);
                            foncier.localisation=reader.GetString(3);
                            foncier.prix=reader.GetDouble(4);
                            foncier.SetHeritage(reader.GetInt32(5));
                            foncier.superficie=reader.GetDouble(6);
                            connection.Close();
                            return foncier;
                        }
                    }
                }
            return foncier;
        }

    }
}