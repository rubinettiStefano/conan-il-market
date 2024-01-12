import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.generation.entities.Shoe;
import com.generation.factories.ShoeRepositoryFactory;
import com.generation.interfaces.Repository;

public class ShoeTest 
{
    @Test
    void testReadAll()
    {
        Repository<Shoe> repo = ShoeRepositoryFactory.make();
        List<Shoe> allShoes = repo.readAll();
        assertTrue(allShoes.size()!=0);
    }

    @Test
    void testReadById()
    {
        Repository<Shoe> repo = ShoeRepositoryFactory.make();
        Shoe Shoe = repo.readById(1);
        assertTrue(Shoe!=null);
    }

    @Test
    void testInsert()
    {
        Repository<Shoe> repo = ShoeRepositoryFactory.make();
        List<Shoe> allShoesOld = repo.readAll();
        Shoe newShoe = new Shoe();
        repo.insert(newShoe);
        List<Shoe> allShoesNew = repo.readAll();

        assertEquals(allShoesOld.size()+1, allShoesNew.size());

        repo.delete(allShoesNew.get(allShoesNew.size()-1).getId());
    }

    @Test
    void deleteTest()
    {
        Repository<Shoe> repo = ShoeRepositoryFactory.make();
        Shoe newShoe = new Shoe();
        repo.insert(newShoe);
        List<Shoe> allShoesOld = repo.readAll();
        repo.delete(allShoesOld.get(allShoesOld.size()-1).getId());
        List<Shoe> allShoesNew = repo.readAll();

        assertEquals(allShoesOld.size()-1, allShoesNew.size());
    }

    @Test
    void testUpdate()
    {
        Repository<Shoe> repo = ShoeRepositoryFactory.make();
        Shoe newShoe = new Shoe();
        repo.insert(newShoe);
        List<Shoe> allShoesNew = repo.readAll();
        newShoe = repo.readById(allShoesNew.get(allShoesNew.size()-1).getId());
        newShoe.setName("TEST");
        repo.update(newShoe);
        newShoe = repo.readById(newShoe.getId());
        assertEquals("TEST", newShoe.getName());

        repo.delete(newShoe);
    }
}
