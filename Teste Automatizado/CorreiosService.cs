using System;
using System.Net.Http;
using System.Text.Json;
using System.Threading.Tasks;

namespace Automacao.Tests
{
    class CorreiosService
    {
        public async Task<Endereco> ConsultaCEP(string CEP)
        {
            var request = new HttpRequestMessage(HttpMethod.Get,
            $"https://viacep.com.br/ws/{CEP}/json/");
            request.Headers.Add("Accept", "application/json");

            using (var httpClient = new HttpClient())
            {
                HttpResponseMessage response = await httpClient.SendAsync(request);
                if (response.IsSuccessStatusCode)
                {
                    using var responseStream = await response.Content.ReadAsStreamAsync();
                    return await JsonSerializer.DeserializeAsync<Endereco>(responseStream, 
                        new JsonSerializerOptions()
                        {
                            PropertyNameCaseInsensitive = true
                        });
                }
                else
                {
                    return null;
                }
            }
        }
    }
}
