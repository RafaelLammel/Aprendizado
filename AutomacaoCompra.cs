using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Internal;
using OpenQA.Selenium.Support.UI;
using System;
using System.Threading;
using Xunit;

namespace Automacao.Tests
{
    public class AutomacaoCompra : IDisposable
    {

        private readonly IWebDriver _driver;
        private readonly WebDriverWait _wait;

        public AutomacaoCompra()
        {
            _driver = new ChromeDriver();
            _wait = new WebDriverWait(_driver, TimeSpan.FromSeconds(10));
        }

        public void Dispose()
        {
            _driver.Quit();
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
            var unitPrice = Decimal.Parse(_driver.FindElement(By.XPath("//*[@id='product_price_1_1_0']/span")).GetAttribute("innerHTML").Trim().Remove(0,1));
            Thread.Sleep(1500);
            var totalPrice = Decimal.Parse(_driver.FindElement(By.Id("total_product_price_1_1_0")).Text.Trim().Remove(0,1));

            // Pegando a quantiade
            var quantity = Int32.Parse(_driver.FindElement(By.Name("quantity_1_1_0_0")).GetAttribute("value"));

            Assert.Equal(unitPrice*2, totalPrice);
            Assert.Equal(2, quantity);

            /************ FIM CASO DE TESTE 2 ************/

            _driver.FindElement(By.XPath("//*[@id='center_column']/p[2]/a[1]")).Click();
            _wait.Until(c => c.Url == "http://automationpractice.com/index.php?controller=authentication&multi-shipping=0&display_guest_checkout=0&back=http%3A%2F%2Fautomationpractice.com%2Findex.php%3Fcontroller%3Dorder%26step%3D1%26multi-shipping%3D0");

        }

    }
}
