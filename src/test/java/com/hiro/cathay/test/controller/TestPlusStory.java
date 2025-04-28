package com.hiro.cathay.test.controller;

import com.hiro.cathay.test.TestStory;
import com.hiro.cathay.test.TestStoryConfig;
import lombok.Data;
import net.thucydides.core.annotations.Step;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import org.junit.Assert;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@TestStoryConfig(
        storyPaths = "**/test-plus.story",
        stepsClass = TestPlusStory.TestPlusSteps.class
)
public class TestPlusStory extends TestStory {


    public static class TestPlusSteps {

        private List<FruitPrice> fruitPrices;

        private double result;

        @Given("價碼表 list:$priceTable")
        @Step
        public void givenNum1(ExamplesTable priceTable) {
            List<Map<String, String>> rows = priceTable.getRows();
            this.fruitPrices = rows.stream().map(row -> {
                FruitPrice fruitPrice = new FruitPrice();
                fruitPrice.setName(row.get("name"));
                fruitPrice.setPrice(Double.parseDouble(row.get("price")));
                fruitPrice.setNumber(Integer.parseInt(row.get("number")));
                return fruitPrice;
            }).collect(Collectors.toList());
            System.out.println(fruitPrices);
        }

        @When("加總")
        @Step
        public void givenNum2() {
            if (fruitPrices == null)  {
                result = -1;
                return;
            }
            result = fruitPrices.stream()
                    .mapToDouble(option -> option.getNumber() * option.getPrice()).
                    sum();
        }

        @Then("取得結果: $expected")
        @Step
        public void expectSum(double expected) {
            Assert.assertEquals(expected, result, 0.000001);
        }

        @Data
        public static class FruitPrice {
            private String name;
            private double price;
            private int number;
        }
    }

}
