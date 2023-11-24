using Microsoft.AspNetCore.Mvc;
// using Newtonsoft.Json;
using FoncierApi.Models;

namespace FoncierApi.Controllers
{
    [Route("api/Foncier")]
    [ApiController]
    public class BorneController : ControllerBase
    {
        [HttpPost("ajout-borne")]
        public ActionResult Ajout([FromBody] Borne borne)
        {
            try
            {
                // Exemple : Enregistrez l'objet foncier dans la base de données
                borne.Insert(); 
                return Ok("Borne inserer");
            }
            catch (Exception ex)
            {
                // En cas d'erreur, vous pouvez renvoyer un message d'erreur avec un code d'erreur approprié
                return BadRequest("Échec de l'ajout de l'objet foncier : " + ex.Message);
            }
        }

        [HttpGet("terrain-affichage")]
        public List<Borne> GetBorne(int id_foncier)
        {
            Console.WriteLine(id_foncier);
            List<Borne> all = Borne.GetAllBorne(id_foncier);
            return all;
        } 

    }
}
