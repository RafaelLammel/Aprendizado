using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Support.UI;
using System;
using System.Linq;
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
        private Endereco novoEndereco;

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
            novoEndereco = await correiosService.ConsultaCEP("01001000");
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
        public void AutomacaoCompraCriarContaTest()
        {

            /* Preparação */

            // Abrindo o site
            _driver.Navigate().GoToUrl("http://automationpractice.com/");
            _driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromSeconds(30);
            _driver.Manage().Timeouts().PageLoad = TimeSpan.FromSeconds(30);

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

            // Fechando modal
            _driver.FindElement(By.XPath("//*[@id='layer_cart']/div[1]/div[2]/div[4]/span")).Click();

            /*********** INICIO CASO DE TESTE 2 **********/

            // Adicionando item com tamanho personalizado M ao carrinho na página de detalhes do item
            _driver.FindElement(By.XPath("//*[@id='homefeatured']/li[2]/div/div[2]/div[2]/a[2]")).Click();
            _wait.Until(c => c.Url.Contains("controller=product") && c.Url.Contains("id_product"));
            _driver.FindElement(By.Id("group_1")).SendKeys("M");
            _driver.FindElement(By.XPath("//*[@id='add_to_cart']/button")).Click();

            /************ FIM CASO DE TESTE 2 ************/

            // Navegando para a página de pedidos (Orders)
            _driver.FindElement(By.XPath("//*[@id='layer_cart']/div[1]/div[2]/div[4]/a")).Click();
            _wait.Until(c => c.Url == "http://automationpractice.com/index.php?controller=order");

            /*********** INICIO CASO DE TESTE 3 **********/

            // Aumentando a quantidade do item adicionado no carrinho
            _driver.FindElement(By.Id("cart_quantity_up_1_1_0_0")).Click();

            // Verificação do caso de teste 3 é feita junto com caso de teste 4

            /************ FIM CASO DE TESTE 3 ************/

            /*********** INICIO CASO DE TESTE 4 **********/

            // Removendo segundo item da lista de pedido
            _driver.FindElements(By.ClassName("cart_quantity_delete")).Last().Click();
            Thread.Sleep(3000);

            // Pegando os preços unitário e total
            var unitPrice = Decimal.Parse(_driver.FindElement(By.XPath("//*[@id='product_price_1_1_0']/span")).GetAttribute("innerHTML").Trim().Remove(0, 1));
            var totalPrice = Decimal.Parse(_driver.FindElement(By.Id("total_product_price_1_1_0")).Text.Trim().Remove(0, 1));

            // Pegando a quantiade
            var quantity = Int32.Parse(_driver.FindElement(By.Name("quantity_1_1_0_0")).GetAttribute("value"));

            Thread.Sleep(3000);

            // Verificando se o preço e a quantidade estão coerentes
            Assert.Equal(unitPrice * 2, totalPrice);
            Assert.Equal(2, quantity);

            /************ FIM CASO DE TESTE 4 ************/
            
            // Navegar para a página de login
            _driver.FindElement(By.XPath("//*[@id='center_column']/p[2]/a[1]")).Click();
            _wait.Until(c => c.Url == "http://automationpractice.com/index.php?controller=authentication&multi-shipping=0&display_guest_checkout=0&back=http%3A%2F%2Fautomationpractice.com%2Findex.php%3Fcontroller%3Dorder%26step%3D1%26multi-shipping%3D0");

            /*********** INICIO CASO DE TESTE 5 **********/

            // Adicionando e testando campo de e-mail para cadastro no passo a passo da compra
            var emailInput = _driver.FindElement(By.Id("email_create"));
            var submitEmail = _driver.FindElement(By.Id("SubmitCreate"));
            var errorMsg = _driver.FindElement(By.Id("create_account_error"));

            submitEmail.Click();
            Assert.True(_wait.Until(c => errorMsg.Displayed));

            string email = $"{RandomString(5)}@{RandomString(5)}.test";
            string password = "Senha@123";

            emailInput.SendKeys(email);
            submitEmail.Click();
            Thread.Sleep(1500);

            // Preenchendo o formulário de cadastro
            _driver.FindElement(By.Id("id_gender1")).Click();
            _driver.FindElement(By.Id("customer_firstname")).SendKeys("Selenium");
            _driver.FindElement(By.Id("customer_lastname")).SendKeys("Teste");
            _driver.FindElement(By.Id("passwd")).SendKeys(password);
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

            Assert.Equal("Selenium Teste",
                _driver.FindElement(By.XPath("//*[@id='header']/div[2]/div/div/nav/div[1]/a/span")).GetAttribute("innerHTML").Trim());

            /************ FIM CASO DE TESTE 5 ************/

            // Casos de teste 6 e 7 são cobertos no teste de compra com login

            /************ INICIO CASO DE TESTE 8 ********/

            _driver.FindElement(By.XPath("//*[@id='center_column']/form/div/p/a")).Click();

            // Preenchendo o formulário
            _driver.FindElement(By.Id("address1")).SendKeys(novoEndereco.Logradouro);
            _driver.FindElement(By.Id("city")).SendKeys(novoEndereco.Localidade);
            _driver.FindElement(By.Id("id_state")).SendKeys("A");
            _driver.FindElement(By.Id("postcode")).SendKeys("12345");
            _driver.FindElement(By.Id("phone")).SendKeys("41123456789");
            _driver.FindElement(By.Id("phone_mobile")).SendKeys("41123456789");
            _driver.FindElement(By.Id("alias")).Clear();
            _driver.FindElement(By.Id("alias")).SendKeys("Custom New Address");
            _driver.FindElement(By.Id("submitAddress")).Click();

            Assert.Contains("Custom New Address",
                _driver.FindElement(By.Id("id_address_delivery")).GetAttribute("innerHTML"));

            /************ FIM CASO DE TESTE 8 ************/

            /************ INICIO CASO DE TESTE 9 ********/

            // Atualizar Zip Code do endereço
            _driver.FindElement(By.ClassName("address_update")).FindElement(By.TagName("a")).Click();
            _driver.FindElement(By.Id("postcode")).Clear();
            _driver.FindElement(By.Id("postcode")).SendKeys("54321");
            _driver.FindElement(By.Id("submitAddress")).Click();

            // Verificando se ao voltarmos para o processo de compra o valor alterado está correto
            Assert.Contains("54321",
                _driver.FindElement(By.ClassName("address_postcode")).Text);

            /************ FIM CASO DE TESTE 9 ************/

            /************ INICIO CASO DE TESTE 10 ********/

            // Navegar para próximo passo
            _driver.FindElement(By.XPath("//*[@id='center_column']/form/p/button")).Click();

            // Tentar avançar sem aceitar os termos, esperar por um erro
            var proccedToPayment = _driver.FindElement(By.XPath("//*[@id='form']/p/button"));
            proccedToPayment.Click();
            Assert.True(_wait.Until(c => c.FindElement(By.ClassName("fancybox-overlay")).Displayed));
            _driver.FindElement(By.XPath("//*[@id='order']/div[2]/div/div/a")).Click();

            // Aceitar os termos e avançar
            _driver.FindElement(By.Id("cgv")).Click();
            proccedToPayment.Click();

            // Escolher forma de pagamento e finalizar compra
            _driver.FindElement(By.ClassName("bankwire")).Click();
            _driver.FindElement(By.XPath("//*[@id='cart_navigation']/button")).Click();

            // Validar que carrinho ficou vazio
            Assert.True(_wait.Until(c => c.FindElement(By.ClassName("ajax_cart_no_product")).Displayed));

            /************ FIM CASO DE TESTE 10 ***********/
        }

        [Fact]
        public void AutomacaoCompraLoginTest()
        {

            /* Preparação */

            // Abrindo o site
            _driver.Navigate().GoToUrl("http://automationpractice.com/");
            _driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromSeconds(30);
            _driver.Manage().Timeouts().PageLoad = TimeSpan.FromSeconds(30);

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

            // Fechando modal
            _driver.FindElement(By.XPath("//*[@id='layer_cart']/div[1]/div[2]/div[4]/span")).Click();

            /*********** INICIO CASO DE TESTE 2 **********/

            // Adicionando item com tamanho personalizado M ao carrinho na página de detalhes do item
            _driver.FindElement(By.XPath("//*[@id='homefeatured']/li[2]/div/div[2]/div[2]/a[2]")).Click();
            _wait.Until(c => c.Url.Contains("controller=product") && c.Url.Contains("id_product"));
            _driver.FindElement(By.Id("group_1")).SendKeys("M");
            _driver.FindElement(By.XPath("//*[@id='add_to_cart']/button")).Click();

            /************ FIM CASO DE TESTE 2 ************/

            // Navegando para a página de pedidos (Orders)
            _driver.FindElement(By.XPath("//*[@id='layer_cart']/div[1]/div[2]/div[4]/a")).Click();
            _wait.Until(c => c.Url == "http://automationpractice.com/index.php?controller=order");

            /*********** INICIO CASO DE TESTE 3 **********/

            // Aumentando a quantidade do item adicionado no carrinho
            _driver.FindElement(By.Id("cart_quantity_up_1_1_0_0")).Click();

            // Verificação do caso de teste 3 é feita junto com caso de teste 4

            /************ FIM CASO DE TESTE 3 ************/

            /*********** INICIO CASO DE TESTE 4 **********/

            // Removendo segundo item da lista de pedido
            _driver.FindElements(By.ClassName("cart_quantity_delete")).Last().Click();
            Thread.Sleep(3000);

            // Pegando os preços unitário e total
            var unitPrice = Decimal.Parse(_driver.FindElement(By.XPath("//*[@id='product_price_1_1_0']/span")).GetAttribute("innerHTML").Trim().Remove(0, 1));
            var totalPrice = Decimal.Parse(_driver.FindElement(By.Id("total_product_price_1_1_0")).Text.Trim().Remove(0, 1));

            // Pegando a quantiade
            var quantity = Int32.Parse(_driver.FindElement(By.Name("quantity_1_1_0_0")).GetAttribute("value"));


            // Verificando se o preço e a quantidade estão coerentes
            Assert.Equal(unitPrice * 2, totalPrice);
            Assert.Equal(2, quantity);

            /************ FIM CASO DE TESTE 4 ************/

            // Navegar para a página de login
            _driver.FindElement(By.XPath("//*[@id='center_column']/p[2]/a[1]")).Click();
            _wait.Until(c => c.Url == "http://automationpractice.com/index.php?controller=authentication&multi-shipping=0&display_guest_checkout=0&back=http%3A%2F%2Fautomationpractice.com%2Findex.php%3Fcontroller%3Dorder%26step%3D1%26multi-shipping%3D0");

            // Caso de teste 5 é contemplado na automação de compra com criação de conta

            /*********** INICIO CASO DE TESTE 6 **********/

            _driver.FindElement(By.XPath("//*[@id='login_form']/div/p[1]/a")).Click();

            _driver.FindElement(By.Id("email")).SendKeys("automacaotestes@teste.com");
            _driver.FindElement(By.XPath("//*[@id='form_forgotpassword']/fieldset/p/button")).Click();
            Assert.True(_wait.Until(c => c.FindElement(By.ClassName("alert-success")).Displayed));

            _driver.Navigate().Back();
            _driver.Navigate().Back();

            /************ FIM CASO DE TESTE 6 ************/

            /*********** INICIO CASO DE TESTE 7 **********/

            _driver.FindElement(By.Id("email")).SendKeys("automacaotestes@teste.com");
            _driver.FindElement(By.Id("passwd")).SendKeys("Senha@123");
            _driver.FindElement(By.Id("SubmitLogin")).Click();

            Assert.Equal("Selenium Testes", 
                _driver.FindElement(By.XPath("//*[@id='header']/div[2]/div/div/nav/div[1]/a/span")).GetAttribute("innerHTML").Trim());

            /************ FIM CASO DE TESTE 7 ************/

            /************ INICIO CASO DE TESTE 8 ********/

            _driver.FindElement(By.XPath("//*[@id='center_column']/form/div/p/a")).Click();

            string addressName = RandomString(7);

            // Preenchendo o formulário
            _driver.FindElement(By.Id("address1")).SendKeys(novoEndereco.Logradouro);
            _driver.FindElement(By.Id("city")).SendKeys(novoEndereco.Localidade);
            _driver.FindElement(By.Id("id_state")).SendKeys("A");
            _driver.FindElement(By.Id("postcode")).SendKeys("12345");
            _driver.FindElement(By.Id("phone")).SendKeys("41123456789");
            _driver.FindElement(By.Id("phone_mobile")).SendKeys("41123456789");
            _driver.FindElement(By.Id("alias")).Clear();
            _driver.FindElement(By.Id("alias")).SendKeys(addressName);
            _driver.FindElement(By.Id("submitAddress")).Click();

            Assert.Contains(addressName,
                _driver.FindElement(By.Id("id_address_delivery")).GetAttribute("innerHTML"));

            /************ FIM CASO DE TESTE 8 ************/

            /************ INICIO CASO DE TESTE 9 ********/

            // Atualizar Zip Code do endereço
            _driver.FindElement(By.ClassName("address_update")).FindElement(By.TagName("a")).Click();
            _driver.FindElement(By.Id("postcode")).Clear();
            _driver.FindElement(By.Id("postcode")).SendKeys("54321");
            _driver.FindElement(By.Id("submitAddress")).Click();

            // Verificando se ao voltarmos para o processo de compra o valor alterado está correto
            Assert.Contains("54321",
                _driver.FindElement(By.ClassName("address_postcode")).Text);

            /************ FIM CASO DE TESTE 9 ************/

            /************ INICIO CASO DE TESTE 10 ********/

            // Navegar para próximo passo
            _driver.FindElement(By.XPath("//*[@id='center_column']/form/p/button")).Click();

            // Tentar avançar sem aceitar os termos, esperar por um erro
            var proccedToPayment = _driver.FindElement(By.XPath("//*[@id='form']/p/button"));
            proccedToPayment.Click();
            Assert.True(_wait.Until(c => c.FindElement(By.ClassName("fancybox-overlay")).Displayed));
            _driver.FindElement(By.XPath("//*[@id='order']/div[2]/div/div/a")).Click();

            // Aceitar os termos e avançar
            _driver.FindElement(By.Id("cgv")).Click();
            proccedToPayment.Click();

            // Escolher forma de pagamento e finalizar compra
            _driver.FindElement(By.ClassName("bankwire")).Click();
            _driver.FindElement(By.XPath("//*[@id='cart_navigation']/button")).Click();

            // Validar que carrinho ficou vazio
            Assert.True(_wait.Until(c => c.FindElement(By.ClassName("ajax_cart_no_product")).Displayed));

            /************ FIM CASO DE TESTE 10 ***********/
        }

    }
}
