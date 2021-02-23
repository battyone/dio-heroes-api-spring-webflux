package one.digitalinnovation.heroesapi.document;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import static one.digitalinnovation.heroesapi.constant.HeroesConstant.HEROES_TABLE_NAME;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = HEROES_TABLE_NAME)
public class Heroes {

  @Id
  @DynamoDBHashKey(attributeName = "id")
  private String id;

  private String name;

  private String universe;

  private int films;

}
