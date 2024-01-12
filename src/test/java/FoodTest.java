import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.generation.entities.Food;
import com.generation.factories.FoodRepositoryFactory;
import com.generation.interfaces.Repository;

public class FoodTest 
{
    @Test
    void testReadAll()
    {
        Repository<Food> repo = FoodRepositoryFactory.make();
        List<Food> allFoods = repo.readAll();
        assertTrue(allFoods.size()!=0);
    }

    @Test
    void testReadById()
    {
        Repository<Food> repo = FoodRepositoryFactory.make();
        Food food = repo.readById(1);
        assertTrue(food!=null);
    }

    @Test
    void testInsert()
    {
        Repository<Food> repo = FoodRepositoryFactory.make();
        List<Food> allFoodsOld = repo.readAll();
        Food newFood = new Food();
        repo.insert(newFood);
        List<Food> allFoodsNew = repo.readAll();

        assertEquals(allFoodsOld.size()+1, allFoodsNew.size());

        repo.delete(allFoodsNew.get(allFoodsNew.size()-1).getId());
    }

    @Test
    void deleteTest()
    {
        Repository<Food> repo = FoodRepositoryFactory.make();
        Food newFood = new Food();
        repo.insert(newFood);
        List<Food> allFoodsOld = repo.readAll();
        repo.delete(allFoodsOld.get(allFoodsOld.size()-1).getId());
        List<Food> allFoodsNew = repo.readAll();

        assertEquals(allFoodsOld.size()-1, allFoodsNew.size());
    }

    @Test
    void testUpdate()
    {
        Repository<Food> repo = FoodRepositoryFactory.make();
        Food newFood = new Food();
        repo.insert(newFood);
        List<Food> allFoodsNew = repo.readAll();
        newFood = repo.readById(allFoodsNew.get(allFoodsNew.size()-1).getId());
        newFood.setName("TEST");
        repo.update(newFood);
        newFood = repo.readById(newFood.getId());
        assertEquals("TEST", newFood.getName());

        repo.delete(newFood);
    }
}
