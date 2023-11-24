using Microsoft.AspNetCore.Mvc;
using FoncierApi;
using FoncierApi.Models;
using System.Text; // Ajoutez cette ligne pour résoudre l'erreur CS0103
using System.Collections.Generic;

namespace FoncierApi.Controllers
{
    [ApiController]
    [Route("api/Test")]
    public class TestController : ControllerBase
    {

        [HttpGet("send-acte")]
        public  List<Foncier> GetString(string cin)
        {
            List<Foncier> all = Foncier.GetAllFonciers(cin);
            Console.WriteLine(cin);
            return all;
        } 
    }
}
