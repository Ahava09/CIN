using Microsoft.AspNetCore.Mvc;
using FoncierApi.Models;
using System;
using System.Collections.Generic;
using System.Text; 

namespace FoncierApi.Controllers
{
    [ApiController]
    [Route("api/Points")]
    public class PointsController : ControllerBase
    {
        [HttpPost("receive-message")]
        public async Task ReceiveMessage()
        {
            try
            {
                using (StreamReader reader = new StreamReader(Request.Body, Encoding.UTF8))
                {
                    // message = await reader.ReadToEndAsync();
                    // Console.WriteLine("Message reçu :"+message+"''");
                    Console.WriteLine("huh");
                    // return Ok($"Message reçu : {message}");
                }

            }
            catch (Exception ex)
            {
                Console.WriteLine("Erreur lors de la réception du message : " + ex.Message);
            }
        }

        [HttpGet("borne")]
        public List<Borne> GetBorne(int id_foncier)
        {
            Console.WriteLine(id_foncier);
            List<Borne> all = Borne.GetAllBorne(id_foncier);
            return all;
        } 
    }
}
