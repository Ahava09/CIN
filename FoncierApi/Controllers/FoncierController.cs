using Microsoft.AspNetCore.Mvc;
using System.Globalization;
using FoncierApi.Models;

namespace FoncierApi.Controllers
{
    [Route("api/Foncier")]
    [ApiController]
    public class FoncierController : ControllerBase
    {
        [HttpPost("ajout")]
        public ActionResult Ajout([FromBody] Foncier foncier)
        {
            try
            {
                foncier.Insert(); // Supposons que cette méthode insère dans la base de données
                foncier = Foncier.getLastFoncier(foncier.id_cin);
                // Renvoyez un message de réussite en tant que réponse
                return Ok(foncier);
            }
            catch (Exception ex)
            {
                // En cas d'erreur, vous pouvez renvoyer un message d'erreur avec un code d'erreur approprié
                return BadRequest("Échec de l'ajout de l'objet foncier : " + ex.Message);
            }
        }

    }
}
