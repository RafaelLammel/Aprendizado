using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Support.UI;
using System;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using Xunit;

namespace Automacao.Tests
{
    public class AutomacaoCompra : IDisposable
    {

        private readonly IWebDriver _driver;
        private readonly WebDriverWait _wait;
        private Endereco endereco;

        public AutomacaoCompra()
        {
            _driver = new ChromeDriver();
            _wait = new WebDriverWait(_driver, TimeSpan.FromSeconds(10));
            GetEndereco().Wait();
        }

        public void Dispose()
        {
            _driver.Quit();
        }

        private async Task GetEndereco()
        {
            var correiosService = new CorreiosService();
            endereco = await correiosService.ConsultaCEP("81750440");
        }

        private string RandomString(int length)
        {
            const string pool = "abcdefghijklmnopqrstuvwxyz";
            Random random = new Random();
            var builder = new StringBuilder();

            for (var i = 0; i < length; i++)
            {
                var c = pool[random.Next(0, pool.Length)];
                builder.Append(c);
            }

            return builder.ToString();
        }

        [Fact]
        public void AutomacaoCompraTest()
        {

            /* Preparação */

            // Abrindo o site
            _driver.Navigate().GoToUrl("http://automationpractice.com/");
            _driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromSeconds(10);
            _driver.Manage().Timeouts().PageLoad = TimeSpan.FromSeconds(10);

            /* Execução dos testes */

            /*********** INICIO CASO DE TESTE 1 **********/

            // Adicionando primeiro item disponivel ao carrinho
            _driver.FindElement(By.XPath("//*[@id='homefeatured']/li[1]/div/div[2]/div[2]/a[1]")).Click();

            // Verificando se a modal e o contador do carrinho foram mostrados, e se o preço está coerente
            Assert.True(_wait.Until(c => c.FindElement(By.Id("layer_cart")).Displayed),
                "Modal item adicionado não apareceu");
            Assert.True(_wait.Until(c => c.FindElement(By.ClassName("ajax_cart_quantity")).Displayed),
                "Quantidade de itens no carrinho não foi atualizada");
            Assert.Equal(_driver.FindElement(By.XPath("//*[@id='homefeatured']/li[1]/div/div[1]/div/div[2]/span")).GetAttribute("innerHTML").Trim(),
                _wait.Until(c => c.FindElement(By.Id("layer_cart_product_price"))).Text.Trim());

            /************ FIM CASO DE TESTE 1 ************/

            // Navegando para a página de pedidos (Orders)
            _driver.FindElement(By.XPath("//*[@id='layer_cart']/div[1]/div[2]/div[4]/a")).Click();
            _wait.Until(c => c.Url == "http://automationpractice.com/index.php?controller=order");

            /*********** INICIO CASO DE TESTE 2 **********/

            // Aumentando a quantidade do item adicionado no carrinho
            _driver.FindElement(By.Id("cart_quantity_up_1_1_0_0")).Click();

            // Pegando os preços unitário e total
            var unitPrice = Decimal.Parse(_driver.FindElement(By.XPath("//*[@id='product_price_1_1_0']/span")).GetAttribute("innerHTML").Trim().Remove(0, 1));
            Thread.Sleep(1500);
            var totalPrice = Decimal.Parse(_driver.FindElement(By.Id("total_product_price_1_1_0")).Text.Trim().Remove(0, 1));

            // Pegando a quantiade
            var quantity = Int32.Parse(_driver.FindElement(By.Name("quantity_1_1_0_0")).GetAttribute("value"));

            // Verificando se o preço e a quantidade estão coerentes
            Assert.Equal(unitPrice * 2, totalPrice);
            Assert.Equal(2, quantity);

            /************ FIM CASO DE TESTE 2 ************/

            // Navegar para a página de login
            _driver.FindElement(By.XPath("//*[@id='center_column']/p[2]/a[1]")).Click();
            _wait.Until(c => c.Url == "http://automationpractice.com/index.php?controller=authentication&multi-shipping=0&display_guest_checkout=0&back=http%3A%2F%2Fautomationpractice.com%2Findex.php%3Fcontroller%3Dorder%26step%3D1%26multi-shipping%3D0");

            /*********** INICIO CASO DE TESTE 3 **********/

            // Adicionando e testando campo de e-mail para cadastro no passo a passo da compra
            var emailInput = _driver.FindElement(By.Id("email_create"));
            var submitEmail = _driver.FindElement(By.Id("SubmitCreate"));
            var errorMsg = _driver.FindElement(By.Id("create_account_error"));

            submitEmail.Click();
            Assert.True(_wait.Until(c => errorMsg.Displayed));

            emailInput.SendKeys($"{RandomString(5)}@{RandomString(5)}.test");
            submitEmail.Click();
            Thread.Sleep(1500);

            // Preenchendo o formulário
            _driver.FindElement(By.Id("id_gender1")).Click();
            _driver.FindElement(By.Id("customer_firstname")).SendKeys("Rafael");
            _driver.FindElement(By.Id("customer_lastname")).SendKeys("Lammel");
            _driver.FindElement(By.Id("passwd")).SendKeys("Senha@123");
            _driver.FindElement(By.Id("days")).SendKeys("24");
            _driver.FindElement(By.Id("months")).SendKeys("J");
            _driver.FindElement(By.Id("years")).SendKeys("1");
            _driver.FindElement(By.Id("address1")).SendKeys(endereco.Logradouro);
            _driver.FindElement(By.Id("city")).SendKeys(endereco.Localidade);
            _driver.FindElement(By.Id("id_state")).SendKeys("A");
            _driver.FindElement(By.Id("postcode")).SendKeys("12345");
            _driver.FindElement(By.Id("phone_mobile")).SendKeys("41123456789");

            _driver.FindElement(By.Id("submitAccount")).Click();

            _wait.Until(c => c.FindElement(By.XPath("//*[@id='header']/div[2]/div/div/nav/div[1]/a/span")));

            Assert.Equal("Rafael Lammel",
                _driver.FindElement(By.XPath("//*[@id='header']/div[2]/div/div/nav/div[1]/a/span")).GetAttribute("innerHTML").Trim());

            /************ FIM CASO DE TESTE 3 ************/
        }

    }
}
