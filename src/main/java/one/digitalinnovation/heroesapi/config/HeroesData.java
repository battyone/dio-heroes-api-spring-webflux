package one.digitalinnovation.heroesapi.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;

import static one.digitalinnovation.heroesapi.constant.HeroesConstant.*;

public class HeroesData {

  public static void main(String[] args) {
    AmazonDynamoDB client = AmazonDynamoDBClientBuilder
      .standard()
      .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(DYNAMO_ENDPOINT, DYNAMO_REGION))
      .build();

    DynamoDB dynamoDB = new DynamoDB(client);
    Table table = dynamoDB.getTable(HEROES_TABLE_NAME);

    Item hero1 = new Item()
      .withPrimaryKey("id", "1")
      .withString("name", "Spider-Man")
      .withString("universe", "Marvel")
      .withNumber("films", 5);

    Item hero2 = new Item()
      .withPrimaryKey("id", "2")
      .withString("name", "Wolverine")
      .withString("universe", "Marvel")
      .withNumber("films", 3);

    Item hero3 = new Item()
      .withPrimaryKey("id", "3")
      .withString("name", "Superman")
      .withString("universe", "DC Comics")
      .withNumber("films", 9);

    Item hero4 = new Item()
      .withPrimaryKey("id", "4")
      .withString("name", "Batman")
      .withString("universe", "DC Comics")
      .withNumber("films", 7);

    PutItemOutcome outcome1 = table.putItem(hero1);
    PutItemOutcome outcome2 = table.putItem(hero2);
    PutItemOutcome outcome3 = table.putItem(hero3);
    PutItemOutcome outcome4 = table.putItem(hero4);
  }

}
